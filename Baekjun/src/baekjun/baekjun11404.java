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

public class baekjun11404 {
	static StringBuilder sb = new StringBuilder();
	static int n, m;
	static HashMap<Integer, ArrayList<Edge>> map;
	static void input() {
		Reader scanner = new Reader();
		n = scanner.nextInt();
		m = scanner.nextInt();
		map = new HashMap<>();
		for(int i = 1; i <= n; i++) {
			map.put(i, new ArrayList<>());
		}
		for(int i = 0; i < m; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int c = scanner.nextInt();
			map.get(a).add(new Edge(b, c));
		}
	}
	
	static void solution() {
		for(int i = 1; i <= n; i++) {
			dijkstra(i);
		}
		System.out.println(sb.toString());
	}
	
	static void dijkstra(int start) {
		int[] distance = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		Queue<Edge> queue = new LinkedList<>();
		queue.offer(new Edge(start, 0));
		while(!queue.isEmpty()) {
			Edge cur_edge = queue.poll();
			int cur_vertex = cur_edge.vertex;
			int cur_distance = cur_edge.distance;
			if(distance[cur_vertex] < cur_distance) continue;
			for(Edge e : map.get(cur_vertex)) {
				if(distance[e.vertex] > distance[cur_vertex] + e.distance) {
					distance[e.vertex] = distance[cur_vertex] + e.distance;
					queue.offer(new Edge(e.vertex, distance[e.vertex]));
				}
			}
		}
		for(int i = 1; i <= n; i++) {
			if(distance[i] == Integer.MAX_VALUE) sb.append(0).append(' ');
			else sb.append(distance[i]).append(' ');
		}
		sb.append('\n');
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
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
		int vertex, distance;
		public Edge(int vertex, int distance) {
			this.vertex = vertex;
			this.distance = distance;
		}
	}
}
