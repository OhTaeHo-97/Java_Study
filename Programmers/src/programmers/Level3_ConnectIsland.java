package programmers;

import java.util.*;

public class Level3_ConnectIsland {
	static int[] parents;
	public static int solution(int n, int[][] costs) {
		parents = new int[n];
		for(int node = 0; node < n; node++) parents[node] = node;
		Arrays.sort(costs, new Comparator<int[]>() {
			public int compare(int[] c1, int[] c2) {
				return c1[2] - c2[2];
			}
		});
		int idx = 0, minLen = 0;
		for(int i = 0; i < n - 1; i++) {
			int[] edge = costs[idx];
			if(isSameParents(edge[0], edge[1])) {
				i--;
				idx++;
				continue;
			} else {
				union(edge[0], edge[1]);
				minLen += edge[2];
			}
		}
		return minLen;
	}
	
	static int findParents(int node) {
		if(parents[node] == node) return node;
		return parents[node] = findParents(parents[node]);
	}
	
	static void union(int node1, int node2) {
		int parent1 = findParents(node1), parent2 = findParents(node2);
		if(parent1 != parent2) {
			if(parent1 < parent2) parents[parent2] = parent1;
			else parents[parent1] = parent2;
		}
	}
	
	static boolean isSameParents(int node1, int node2) {
		int parent1 = findParents(node1), parent2 = findParents(node2);
		if(parent1 == parent2) return true;
		return false;
	}
	
	public static void main(String[] args) {
		int n = 4;
		int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
		System.out.println(solution(n, costs));
	}
}
