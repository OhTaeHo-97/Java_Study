package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun11779 {
	static StringBuilder sb = new StringBuilder();
	static int n, m, startCity, endCity;
	static PriorityQueue<Edge> candidate;
	static HashMap<Integer, ArrayList<Edge>> map;
	static void input() {
		Reader scanner = new Reader();
		n = scanner.nextInt();
		m = scanner.nextInt();
		map = new HashMap<>();
		for(int city = 1; city <= n; city++) map.put(city, new ArrayList<Edge>());
		for(int bus = 0; bus < m; bus++) {
			int start = scanner.nextInt(), end = scanner.nextInt(), weight = scanner.nextInt();
			map.get(start).add(new Edge(end, weight));
		}
		startCity = scanner.nextInt();
		endCity = scanner.nextInt();
	}
	
	static void solution() {
		candidate = new PriorityQueue<>();
		dijkstra(startCity, endCity);
		Edge answer = candidate.poll();
		String[] path = answer.path.split(" ");
		sb.append(answer.weight).append('\n');
		sb.append(path.length).append('\n');
		for(String city : path) sb.append(city).append(' ');
		System.out.println(sb);
	}
	
	static void dijkstra(int start, int end) {
		Queue<Edge> queue = new LinkedList<>();
		queue.offer(new Edge(start, 0, start + " "));
		int[] weights = new int[n + 1];
		Arrays.fill(weights, Integer.MAX_VALUE);
		weights[start] = 0;
		while(!queue.isEmpty()) {
			Edge cur = queue.poll();
			if(cur.city == end) candidate.offer(cur);
			if(weights[cur.city] < cur.weight) continue;
			for(Edge e : map.get(cur.city)) {
				int nextCity = e.city, weight = e.weight;
				if(weights[nextCity] > weights[cur.city] + weight) {
					weights[nextCity] = weights[cur.city] + weight;
					queue.offer(new Edge(nextCity, weights[nextCity], cur.path + nextCity + " "));
				}
			}
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int city, weight;
		String path;
		public Edge(int city, int weight) {
			this.city = city;
			this.weight = weight;
			this.path = null;
		}
		public Edge(int city, int weight, String path) {
			this.city = city;
			this.weight = weight;
			this.path = path;
		}
		public int compareTo(Edge e) {
			return this.weight - e.weight;
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
}
