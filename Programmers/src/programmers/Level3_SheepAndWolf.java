package programmers;

import java.util.*;

public class Level3_SheepAndWolf {
	static HashMap<Integer, ArrayList<Integer>> map;
	static int maxCnt;
	public void dfs(int idx, int s, int w, ArrayList<Integer> list, int[] info) {
		if(info[idx] == 0)
			s++;
		else
			w++;
		if(s <= w)
			return;
		
		maxCnt = Math.max(maxCnt, s);
		ArrayList<Integer> next = new ArrayList<>();
		next.addAll(list);
		if(map.containsKey(idx))
			next.addAll(map.get(idx));
		next.remove(Integer.valueOf(idx));
		
		for(int n : next) {
			dfs(n, s, w, next, info);
		}
		return;
	}
	
	public int solution(int[] info, int[][] edges) {
		maxCnt = 0;
		map = new HashMap<>();
		for(int[] e : edges) {
			if(!map.containsKey(e[0])) {
				map.put(e[0], new ArrayList<Integer>());
			}
			map.get(e[0]).add(e[1]);
		}
		ArrayList<Integer> list = new ArrayList<>();
		list.add(0);
		dfs(0, 0, 0, list, info);
		return maxCnt;
	}
}
