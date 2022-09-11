package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1967 {
	static int n, max = Integer.MIN_VALUE;
	static ArrayList<Edge>[] map;
	static boolean[] visited;
	static void input() {
		Reader scanner = new Reader();
		n = scanner.nextInt();
		map = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++) map[i] = new ArrayList<Edge>();
		visited = new boolean[n + 1];
		for(int edge = 0; edge < n - 1; edge++) {
			int parent = scanner.nextInt();
			int child = scanner.nextInt();
			int weight = scanner.nextInt();
			map[parent].add(new Edge(child, weight));
			map[child].add(new Edge(parent, weight));
		}
	}
	
	static void solution() {
		for(int i = 1; i <= n; i++) {
			visited = new boolean[n + 1];
			visited[i] = true;
			dfs(i, 0);
		}
		System.out.println(max);
	}
	
	static void dfs(int num, int weight) {
		for(Edge e : map[num]) {
			if(!visited[e.vertex]) {
				visited[e.vertex] = true;
				dfs(e.vertex, weight + e.weight);
			}
		}
		max = Math.max(max, weight);
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
	
	static class Edge {
		int vertex, weight;
		public Edge(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}
}
