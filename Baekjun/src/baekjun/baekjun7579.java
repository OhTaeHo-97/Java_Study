package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun7579 {
	static int N, M;
	static int[] memories, costs;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		memories = new int[N];
		costs = new int[N];
		for(int idx = 0; idx < N; idx++) memories[idx] = scanner.nextInt();
		for(int idx = 0; idx < N; idx++) costs[idx] = scanner.nextInt();
	}
	
	static void solution() {
		int answer = Integer.MAX_VALUE;
		int[][] dp = new int[N][10001];
		for(int idx = 0; idx < N; idx++) {
			int cost = costs[idx], memory = memories[idx];
			for(int c = 0; c <= 10000; c++) {
				if(idx == 0) {
					if(c >= cost) dp[idx][c] = memory;
				} else {
					if(c >= cost) dp[idx][c] = Math.max(dp[idx - 1][c], dp[idx - 1][c - cost] + memory);
					else dp[idx][c] = dp[idx - 1][c];
				}
				if(dp[idx][c] >= M) answer = Math.min(answer, c);
			}
		}
		System.out.println(answer);
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
