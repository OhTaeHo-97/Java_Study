package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1956 {
	static int V, E;
	static int[][] distance;
	static void input() {
		Reader scanner = new Reader();
		V = scanner.nextInt();
		E = scanner.nextInt();
		distance = new int[V + 1][V + 1];
		for(int row = 0; row <= V; row++) {
			for(int col = 0; col <= V; col++) {
				if(row != col) distance[row][col] = Integer.MAX_VALUE;
			}
		}
		for(int edge = 0; edge < E; edge++) {
			int start = scanner.nextInt(), end = scanner.nextInt(), weight = scanner.nextInt();
			distance[start][end] = weight;
		}
	}
	
	static void solution() {
		floydWarshall();
		int min = Integer.MAX_VALUE;
		for(int row = 1; row <= V; row++) {
			for(int col = 1; col <= V; col++) {
				if(row == col) continue;
				if(distance[row][col] != Integer.MAX_VALUE && distance[col][row] != Integer.MAX_VALUE)
					min = Math.min(min, distance[row][col] + distance[col][row]);
			}
		}
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	static void floydWarshall() {
		for(int node = 1; node <= V; node++) {
			for(int node1 = 1; node1 <= V; node1++) {
				for(int node2 = 1; node2 <= V; node2++) {
					if(node1 == node2) continue;
					if(distance[node1][node] == Integer.MAX_VALUE || distance[node][node2] == Integer.MAX_VALUE) continue;
					if(distance[node1][node2] > distance[node1][node] + distance[node][node2])
						distance[node1][node2] = distance[node1][node] + distance[node][node2];
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
}
