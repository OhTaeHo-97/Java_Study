package programmers.MonthlyCodeChallangeSeason2;

import java.util.*;

public class Level3_MakeAllZero {
	static HashMap<Integer, ArrayList<Integer>> tree;
	static long[] weights;
	static int[] indegree;
	static int size, root;
	static long answer;
	public static long solution(int[] a, int[][] edges) {
		long sum = 0L;
		boolean isZero = true;
		for(int weight : a) {
			if(weight != 0) isZero = false;
			sum += weight;
		}
		if(sum != 0) return -1;
		if(isZero) return 0;
		if(a.length == 2) return Math.abs(a[0]);
		
		size = a.length;
		weights = new long[size];
		for(int idx = 0; idx < a.length; idx++) weights[idx] = a[idx];
		answer = 0L;
		makeTree(edges);
		bfs();
		return answer;
	}
	
	static void makeTree(int[][] edges) {
		tree = new HashMap<>();
		indegree = new int[size];
		for(int node = 0; node < size; node++) tree.put(node, new ArrayList<>());
		for(int[] edge : edges) {
			tree.get(edge[0]).add(edge[1]);
			tree.get(edge[1]).add(edge[0]);
			indegree[edge[0]]++;
			indegree[edge[1]]++;
		}
	}
	
	static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		for(int node = 1; node < size; node++) {
			if(tree.get(node).size() == 1) queue.offer(node);
		}
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			answer += Math.abs(weights[cur]);
			indegree[cur]--;
			for(int idx = 0; idx < tree.get(cur).size(); idx++) {
				int next = tree.get(cur).get(idx);
				if(indegree[next] == 0) continue;
				indegree[next]--;
				weights[next] += weights[cur];
				if(indegree[next] == 1) {
					if(next == root) continue;
					queue.offer(next);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] a = {-5,0,2,1,2};
		int[][] edges = {{0,1},{3,4},{2,3},{0,3}};
		System.out.println(solution(a, edges));
	}
}
