package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1238 {
	static int N, M, X;
	static HashMap<Integer, ArrayList<Edge>> map;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		X = scanner.nextInt();
		map = new HashMap<Integer, ArrayList<Edge>>();
		for(int vertex = 1; vertex <= N; vertex++) map.put(vertex, new ArrayList<Edge>());
		for(int index = 0; index < M; index++) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			int weight = scanner.nextInt();
			map.get(start).add(new Edge(end, weight));
		}
	}
	
	static void solution() {
		int[][] distances = new int[N + 1][N + 1];
		for(int vertex = 1; vertex <= N; vertex++) {
			dijkstra(distances[vertex]);
		}
		int max = Integer.MIN_VALUE;
		for(int vertex = 1; vertex <= N; vertex++) {
			int distance = distances[vertex][X] + distances[X][vertex];
			max = Math.max(max, distance);
		}
		System.out.println(max);
	}
	
//	static int bfs(int start) {
//		Queue<Edge> queue = new LinkedList<Edge>();
//		queue.offer(new Edge(start, 0));
//		boolean[] visited = new boolean[N + 1];
//		visited[start] = true;
//		int distance = Integer.MAX_VALUE;
//		while(!queue.isEmpty()) {
//			Edge curEdge = queue.poll();
//			if(curEdge.vertex == X) {
//				distance = Math.min(distance, curEdge.weight);
//			}
//			for(Edge e : map.get(curEdge.vertex)) {
//				if(!visited[e.vertex]) {
//					visited[e.vertex] = true;
//					queue.offer(new Edge(e.vertex, curEdge.weight + e.weight));
//				}
//			}
//		}
//		return distance;
//	}
	
	static void dijkstra(int[] distance) {
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
		queue.offer(new Edge(X, 0));
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[X] = 0;
		while(!queue.isEmpty()) {
			Edge curEdge = queue.poll();
			if(distance[curEdge.vertex] < curEdge.weight) continue;
			for(Edge e : map.get(curEdge.vertex)) {
				if(distance[e.vertex] > e.weight + distance[curEdge.vertex]) {
					distance[e.vertex] = e.weight + distance[curEdge.vertex];
					queue.offer(new Edge(e.vertex, distance[e.vertex]));
				}
			}
		}
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
	
	static class Edge implements Comparator<Edge> {
		int vertex, weight;
		public Edge(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		public int compare(Edge e1, Edge e2) {
			if(e1.weight != e2.weight) {
				return e1.weight - e2.weight;
			} else {
				return e1.vertex - e2.vertex;
			}
		}
	}
}
