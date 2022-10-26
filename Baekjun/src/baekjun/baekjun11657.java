package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun11657 {
	static StringBuilder sb = new StringBuilder();
	static final long INF = 30000000001L;
	static int N, M;
	static Edge[] edges;
	static long[] weight;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		edges = new Edge[M];
		for(int edge = 0; edge < M; edge++) {
			int A = scanner.nextInt(), B = scanner.nextInt(), C = scanner.nextInt();
			edges[edge] = new Edge(A, B, C);
		}
	}
	
	static void solution() {
		if(Bellman_Ford(1)) {
			System.out.println(-1);
			return;
		}
		for(int v = 2; v <= N; v++)
			sb.append(weight[v] == INF ? -1 : weight[v]).append('\n');
		System.out.println(sb);
	}
	
	static boolean Bellman_Ford(int start) {
		// 초기화
		weight = new long[N + 1];
		Arrays.fill(weight, INF);
		weight[start] = 0L;
		boolean isChanged = false;
		
		outerloop:
		for(int v = 1; v <= N; v++) {
			isChanged = false;
			for(Edge e : edges) {
				if(weight[e.start] == INF) continue;
				if(weight[e.end] > weight[e.start] + e.weight) {
					weight[e.end] = weight[e.start] + e.weight;
					isChanged = true;
					// 음수 사이클 존재 여부 확인
					if(v == N) {
						isChanged = true;
						break outerloop;
					}
				}
			}
			if(!isChanged) break;
		}
		return isChanged;
	}
    
    public static void main(String[] args) {
		input();
		solution();
	}
	
	static class Edge {
		int start, end, weight;
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
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
