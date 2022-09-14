package programmers;

import java.util.*;

public class Level2_SearchRanking {
	static HashMap<String, ArrayList<Integer>> infoMap;
	
	public int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
		infoMap = new HashMap<String, ArrayList<Integer>>();
		for(String i : info)
			dfs("", 0, i.split(" "));
		
		for(int index = 0; index < query.length; index++) {
			query[index] = query[index].replace(" and ", "");
			String[] querySplit = query[index].split(" ");
			answer[index] = binarySearch(querySplit[0], Integer.parseInt(querySplit[1]));
		}
		return answer;
	}
	
	public void dfs(String infos, int depth, String[] info) {
		if(depth == 4) {
			if(!infoMap.containsKey(infos))
				infoMap.put(infos, new ArrayList<Integer>());
			infoMap.get(infos).add(Integer.parseInt(info[4]));
			return;
		}
		dfs(infos + info[depth], depth + 1, info);
		dfs(infos + "-", depth + 1, info);
	}
	
	public int binarySearch(String query, int score) {
		if(!infoMap.containsKey(query)) return 0;
		ArrayList<Integer> candidate = infoMap.get(query);
		Collections.sort(candidate);
		int L = 0, R = candidate.size() - 1;
		while(L <= R) {
			int mid = (L + R) / 2;
			if(candidate.get(mid) < score) {
				L = mid + 1;
			} else {
				R = mid - 1;
			}
		}
		return candidate.size() - L;
	}
	
	public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		Level2_SearchRanking l = new Level2_SearchRanking();
		int[] answer = l.solution(info, query);
		for(int i : answer) System.out.println(i);
	}
}
