package programmers.MonthlyCodeChallangeSeason1;

import java.util.*;

public class Level3_StarSeries {
	static HashMap<Integer, ArrayList<Integer>> map;
	static boolean[] visited;
	public static int solution(int[] a) {
		map = new HashMap<>();
		for(int idx = 0; idx < a.length; idx++) {
			if(!map.containsKey(a[idx])) map.put(a[idx], new ArrayList<>());
			map.get(a[idx]).add(idx);
		}
		List<Map.Entry<Integer, ArrayList<Integer>>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, ArrayList<Integer>>>() {
			public int compare(Map.Entry<Integer, ArrayList<Integer>> l1, Map.Entry<Integer, ArrayList<Integer>> l2) {
				return l2.getValue().size() - l1.getValue().size();
			}
		});
		int answer = 0;
		for(Map.Entry<Integer, ArrayList<Integer>> entry : list) {
			if(answer >= entry.getValue().size() * 2) break;
			int size = 0;
			visited = new boolean[a.length];
			for(int idx : entry.getValue()) {
				if(visited[idx]) continue;
				if(idx - 1 >= 0) {
					if(!visited[idx - 1] && a[idx - 1] != a[idx]) {
						size += 2;
						visited[idx - 1] = visited[idx] = true;
						continue;
					}
				}
				if(idx + 1 < a.length) {
					if(!visited[idx + 1] && a[idx + 1] != a[idx]) {
						size += 2;
						visited[idx + 1] = visited[idx] = true;
						continue;
					}
				}
			}
			if(size != 0) answer = Math.max(answer, size);
		}
		return answer;
	}
	
	public static void main(String[] args) {
//		int[] a = {0};
//		int[] a = {5,2,3,3,5,3};
		int[] a = {0,3,3,0,7,2,0,2,2,0};
		System.out.println(solution(a));
	}
}
