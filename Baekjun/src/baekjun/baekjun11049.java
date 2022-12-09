package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun11049 {
	static int N;
	static int[] data;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		data = new int[N + 1];
		for(int idx = 0; idx < N; idx++) {
			int row = scanner.nextInt(), col = scanner.nextInt();
			data[idx] = row;
			data[idx + 1] = col;
		}
	}
	
	static void solution() {
		int dp[][] = new int[N][N];
		for(int idx = 2; idx < N + 1; idx++) {
			for(int idx2 = 0; idx2 < N - idx + 1; idx2++) {
				dp[idx2][idx2 + idx - 1] = Integer.MAX_VALUE;
				for(int k = idx2; k < idx2 + idx - 1; k++) {
					int value = dp[idx2][k] + dp[k + 1][idx2 + idx - 1] + (data[idx2] * data[k + 1] * data[idx2 + idx]);
					dp[idx2][idx2 + idx - 1] = Math.min(dp[idx2][idx2 + idx - 1], value);
				}
			}
		}
		System.out.println(dp[0][N - 1]);
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
