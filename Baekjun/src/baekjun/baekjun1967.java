package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1967 {
	static int n, max = Integer.MIN_VALUE;
	static HashMap<Integer, ArrayList<Edge>> map;
	static ArrayList<Integer> leafs;
	static void input() {
		Reader scanner = new Reader();
		n = scanner.nextInt();
		map = new HashMap<>();
		for(int vertex = 1; vertex <= n; vertex++) map.put(vertex, new ArrayList<Edge>());
		leafs = new ArrayList<Integer>();
		for(int edge = 0; edge < n - 1; edge++) {
			int parent = scanner.nextInt();
			int child = scanner.nextInt();
			int weight = scanner.nextInt();
			map.get(parent).add(new Edge(child, weight));
			map.get(child).add(new Edge(parent, weight));
		}
		for(int vertex = 1; vertex <= n; vertex++) {
			if(map.get(vertex).size() == 1 && vertex != 1) leafs.add(vertex);
		}
	}
	
	static void solution() {
		for(int index = 0; index < leafs.size(); index++) {
			int weight = dijkstra(leafs.get(index));
			max = Math.max(max, weight);
		}
		System.out.println(max);
	}
	
	static int dijkstra(int vertex) {
		boolean[] visited = new boolean[n + 1];
		int[] distance = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		Queue<Edge> queue = new LinkedList<Edge>();
		distance[vertex] = 0;
		visited[vertex] = true;
		queue.add(new Edge(vertex, 0));
		while(!queue.isEmpty()) {
			Edge cur_edge = queue.poll();
			if(distance[cur_edge.vertex] < cur_edge.weight) continue;
			for(Edge e : map.get(cur_edge.vertex)) {
				int next_vertex = e.vertex;
				int weight = e.weight;
				if(distance[next_vertex] > distance[cur_edge.vertex] + weight) {
					distance[next_vertex] = distance[cur_edge.vertex] + weight;
					queue.offer(new Edge(next_vertex, distance[next_vertex]));
				}
			}
		}
		int max_weight = 0;
		for(int v = 1; v <= n; v++) {
			if(v == vertex) continue;
			max_weight = Math.max(max_weight, distance[v]);
		}
		return max_weight;
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
