package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun2458 {
	static int N, M;
	static ArrayList<Edge>[] map;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		map = new ArrayList[N + 1];
		for(int stu = 1; stu <= N; stu++) map[stu] = new ArrayList<>();
		for(int edge = 0; edge < M; edge++) {
			int small = scanner.nextInt(), big = scanner.nextInt();
			map[small].add(new Edge(big, 1));
		}
	}
	
	static void solution() {
		int[][] distance = new int[N + 1][N + 1];
		for(int stu = 1; stu <= N; stu++) dijkstra(stu, distance[stu]);
		int stuNum = 0;
		for(int stu = 1; stu <= N; stu++) {
			boolean flag = false;
			for(int other = 1; other <= N; other++) {
				if(distance[stu][other] == Integer.MAX_VALUE && distance[other][stu] == Integer.MAX_VALUE) {
					flag = true;
					break;
				}
			}
			if(!flag) stuNum++;
		}
		System.out.println(stuNum);
	}
	
	static void dijkstra(int start, int[] dist) {
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		Queue<Edge> queue = new LinkedList<>();
		queue.offer(new Edge(start, 0));
		while(!queue.isEmpty()) {
			Edge cur = queue.poll();
			if(dist[cur.node] < cur.weight) continue;
			for(Edge e : map[cur.node]) {
				if(dist[e.node] > dist[cur.node] + e.weight) {
					dist[e.node] = dist[cur.node] + e.weight;
					queue.offer(new Edge(e.node, dist[e.node]));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		input();
		solution();
	}
	
	static class Edge {
		int node, weight;
		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
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
