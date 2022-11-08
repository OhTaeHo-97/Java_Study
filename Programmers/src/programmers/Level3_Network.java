package programmers;

import java.util.*;

public class Level3_Network {
	static int network;
	static HashMap<Integer, ArrayList<Integer>> map;
	static boolean[] visited;
	public static int solution(int n, int[][] computers) {
		makeMap(n, computers);
		network = 0;
		visited = new boolean[n];
		for(int computer = 0; computer < n; computer++) {
			if(!visited[computer]) {
				network++;
				dfs(computer);
			}
		}
		return network;
	}
	
	static void makeMap(int n, int[][] computers) {
		map = new HashMap<>();
		for(int computer = 0; computer < n; computer++) map.put(computer, new ArrayList<>());
		for(int c1 = 0; c1 < n; c1++) {
			for(int c2 = 0; c2 < n; c2++) {
				if(c2 == c1) continue;
				if(computers[c1][c2] == 1) map.get(c1).add(c2);
			}
		}
	}
	
	static void dfs(int computer) {
		visited[computer] = true;
		for(int c : map.get(computer)) {
			if(!visited[c]) dfs(c);
		}
	}
	
	public static void main(String[] args) {
//		int n = 3;
//		int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
		int n = 3;
		int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
		System.out.println(solution(n, computers));
	}
}
