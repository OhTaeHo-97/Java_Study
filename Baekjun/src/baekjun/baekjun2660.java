package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun2660 {
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int[][] distance;
	static final int INF = 1000000000;
	
	static void input() {
		Reader scanner = new Reader();
		n = scanner.nextInt();
		distance = new int[n + 1][n + 1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i != j) {
					distance[i][j] = INF;
				}
			}
		}
		int n1, n2;
		n1 = scanner.nextInt();
		n2 = scanner.nextInt();
		while(n1 != -1 && n2 != -1) {
			distance[n1][n2] = 1;
			distance[n2][n1] = 1;
			n1 = scanner.nextInt();
			n2 = scanner.nextInt();
		}
	}
	
	static void FloydWarshall() { // 플로이드 와샬 알고리즘
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				for(int k = 1; k <= n; k++) {
					if(distance[j][k] > distance[j][i] + distance[i][k]) {
						distance[j][k] = distance[j][i] + distance[i][k];
					}
				}
			}
		}
	}
	
	static void solution() {
		FloydWarshall();
		int[] each_distance = new int[n + 1];
		int min = INF;
		for(int i = 1; i <= n; i++) {
			int dis = 0;
			for(int j = 1; j <= n; j++) {
				if(distance[i][j] != INF) {
					dis = Math.max(dis, distance[i][j]);
				}
			}
			each_distance[i] = dis;
			min = Math.min(min, dis);
		}
		ArrayList<Integer> candidate = new ArrayList<Integer>();
		for(int i = 1; i <= n; i++) {
			if(min == each_distance[i]) {
				candidate.add(i);
			}
		}
		sb.append(min + " " + candidate.size());
		sb.append("\n");
		for(int c : candidate) {
			sb.append(c + " ");
		}
		sb.append("\n");
	}
	
	public static void main(String[] args) {
		input();
		solution();
		System.out.println(sb.toString());
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
}
