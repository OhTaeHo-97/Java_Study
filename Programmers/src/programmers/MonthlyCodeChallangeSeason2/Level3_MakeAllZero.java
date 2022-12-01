package programmers.MonthlyCodeChallangeSeason2;

import java.util.*;

public class Level3_MakeAllZero {
	static HashMap<Integer, ArrayList<Integer>> tree;
	static long[] weights;
	static int size;
	static long answer;
	static HashSet<Integer> visited;
	static ArrayList<Integer> leafs;
	public static long solution(int[] a, int[][] edges) {
		long sum = 0L;
		boolean isZero = true;
		for(int weight : a) {
			if(weight != 0) isZero = false;
			sum += weight;
		}
		if(sum != 0) return -1;
		if(isZero) return 0;
		size = a.length;
		weights = new long[a.length];
		for(int idx = 0; idx < a.length; idx++) weights[idx] = a[idx];
		answer = 0L;
		visited = new HashSet<>();
		makeTree(edges);
		findLeaf();
		calc(leafs);
		return answer;
	}
	
	static void makeTree(int[][] edges) {
		tree = new HashMap<>();
		for(int node = 0; node < size; node++) tree.put(node, new ArrayList<>());
		for(int[] edge : edges) {
			tree.get(edge[0]).add(edge[1]);
			tree.get(edge[1]).add(edge[0]);
		}
	}
	
	static void findLeaf() {
		leafs = new ArrayList<>();
		for(int node = 0; node < size; node++) {
			if(tree.get(node).size() == 1) leafs.add(node);
		}
	}
	
	static void calc(ArrayList<Integer> list) {
		if(list.size() == 0 || (list.size() == 1 && visited.contains(list.get(0)))) return;
		ArrayList<Integer> temp = new ArrayList<>();
		for(int node : list) {
			if(!visited.add(node)) continue;
			for(int neighbor : tree.get(node)) {
				if(!visited.contains(neighbor)) {
					weights[neighbor] += weights[node];
					answer += Math.abs(weights[node]);
					temp.add(neighbor);
				}
			}
		}
		calc(temp);
	}
	
	public static void main(String[] args) {
		int[] a = {-5,0,2,1,2};
		int[][] edges = {{0,1},{3,4},{2,3},{0,3}};
		System.out.println(solution(a, edges));
	}
}
