package programmers;

import java.util.*;

public class Level2_SearchRanking {
	static HashMap<String, ArrayList<Integer>> infoMap;
	
//	public int[] solution(String[] info, String[] query) {
//		int[] answer = new int[query.length];
//		infoMap = new HashMap<String, ArrayList<Integer>>();
//		for(String i : info)
//			dfs("", 0, i.split(" "));
//		
//		for(int index = 0; index < query.length; index++) {
//			query[index] = query[index].replace(" and ", "");
//			String[] querySplit = query[index].split(" ");
//			answer[index] = binarySearch(querySplit[0], Integer.parseInt(querySplit[1]));
//		}
//		return answer;
//	}
//	
//	public void dfs(String infos, int depth, String[] info) {
//		if(depth == 4) {
//			if(!infoMap.containsKey(infos))
//				infoMap.put(infos, new ArrayList<Integer>());
//			infoMap.get(infos).add(Integer.parseInt(info[4]));
//			return;
//		}
//		dfs(infos + info[depth], depth + 1, info);
//		dfs(infos + "-", depth + 1, info);
//	}
//	
//	public int binarySearch(String query, int score) {
//		if(!infoMap.containsKey(query)) return 0;
//		ArrayList<Integer> candidate = infoMap.get(query);
//		Collections.sort(candidate);
//		int L = 0, R = candidate.size() - 1;
//		while(L <= R) {
//			int mid = (L + R) / 2;
//			if(candidate.get(mid) < score) {
//				L = mid + 1;
//			} else {
//				R = mid - 1;
//			}
//		}
//		return candidate.size() - L;
//	}
	
	public int[] solution(String[] info, String[] query) {
        // 1. info를 기반으로 hashMap 만들기
        infoMap = new HashMap<>();
        
        for (String i : info) {
            String[] data = i.split(" ");
            String[] languages = { data[0], "-" };
            String[] jobs = { data[1], "-" };
            String[] exps = { data[2], "-" };
            String[] foods = { data[3], "-" };
            Integer value = Integer.parseInt(data[4]);
            for (String lang : languages)
                for (String job : jobs)
                    for (String exp : exps)
                        for (String food : foods) {
                            String[] keyData = { lang, job, exp, food };
                            String key = String.join(" ", keyData);
                            ArrayList<Integer> arr = infoMap.getOrDefault(key, new ArrayList<Integer>());

                            arr.add(value);
                            infoMap.put(key, arr);
                        }
        }

        // 2. 각 hashMap의 value를 오름차순 정렬하기
        for (ArrayList<Integer> scoreList : infoMap.values())
            scoreList.sort(null);

        // 3. query 조건에 맞는 지원자를 가져오기
        int[] answer = new int[query.length];
        int i = 0;
        for (String q : query) {
            String[] data = q.split(" and ");
            int target = Integer.parseInt(data[3].split(" ")[1]);
            data[3] = data[3].split(" ")[0];
            String key = String.join(" ", data);

            if (infoMap.containsKey(key)) {
                ArrayList<Integer> list = infoMap.get(key);
                // 4. lower-bound/하한선 찾기
                int left = 0;
                int right = list.size();
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (list.get(mid) >= target)
                        right = mid;
                    else
                        left = mid + 1;
                }

                answer[i] = list.size() - left;
            }
            i++;
        }
        return answer;
    }
	
	public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		Level2_SearchRanking l = new Level2_SearchRanking();
		int[] answer = l.solution(info, query);
		for(int a : answer) {
			System.out.println(a);
		}
	}
}
