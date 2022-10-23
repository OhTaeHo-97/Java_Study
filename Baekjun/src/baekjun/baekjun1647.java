package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class baekjun1647 {
	static int N, M;
	static int[][] edges;
	static int[] parents;
	static HashMap<Integer, ArrayList<Integer>> map;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		edges = new int[M][3];
		parents = new int[N + 1];
		for(int house = 1; house <= N; house++) parents[house] = house;
		for(int edge = 0; edge < M; edge++) {
			int n1 = scanner.nextInt(), n2 = scanner.nextInt(), weight = scanner.nextInt();
			int min = Math.min(n1, n2), max = Math.max(n1, n2);
			edges[edge][0] = min;
			edges[edge][1] = max;
			edges[edge][2] = weight;
		}
	}
	
	static void solution() {
		Arrays.sort(edges, new Comparator<int[]>() {
			public int compare(int[] e1, int[] e2) {
				if(e1[2] != e2[2]) return e1[2] - e2[2];
				else {
					if(e1[0] != e2[0]) return e1[0] - e2[0];
					else return e1[1] - e2[1];
				}
			}
		});
		int result = kruskal();
		System.out.println(result);
	}
	
	static int kruskal() {
		ArrayList<int[]> mst = new ArrayList<>();
		int totalWeight = 0;
		for(int[] edge : edges) {
			if(mst.size() == N - 1) break;
			if(mst.size() >= N - 2) {
				for(int house = 1; house <= N; house++) findParents(house);
				int count = 0;
				HashSet<Integer> set = new HashSet<>();
				for(int house = 1; house <= N; house++) {
					if(set.add(parents[house])) count++;
				}
				if(count == 2) break;
			}
			if(!isSameParents(edge[0], edge[1])) {
				union(edge[0], edge[1]);
				mst.add(edge);
			}
		}
		for(int index = 0; index < mst.size(); index++) totalWeight += mst.get(index)[2];
		return totalWeight;
	}
	
	static int findParents(int house) {
		if(house == parents[house]) return house;
		return parents[house] = findParents(parents[house]);
	}
	
	static void union(int house1, int house2) {
		int parent1 = findParents(house1), parent2 = findParents(house2);
		if(parent1 != parent2) {
			if(parent1 < parent2) parents[parent2] = parent1;
			else parents[parent1] = parent2;
		}
	}
	
	static boolean isSameParents(int house1, int house2) {
		int parent1 = findParents(house1), parent2 = findParents(house2);
		if(parent1 == parent2) return true;
		return false;
	}
	
	public static void main(String[] args) {
		input();
		solution();
	}
	
	static class Reader {
		BufferedReader br;
		StringTokenizer st;
		public Reader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		String next() {
			while(st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
