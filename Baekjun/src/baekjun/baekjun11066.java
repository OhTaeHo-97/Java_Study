package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun11066 {
	static StringBuilder sb = new StringBuilder();
	static int K;
	static int[] sizes, sum;
	static void input() {
		Reader scanner = new Reader();
		int test_num = scanner.nextInt();
		for(int test = 1; test <= test_num; test++) {
			K = scanner.nextInt();
			sizes = new int[K + 1];
			sum = new int[K + 1];
			for(int chapter = 1; chapter <= K; chapter++) {
				sizes[chapter] = scanner.nextInt();
				sum[chapter] = sum[chapter - 1] + sizes[chapter];
			}
			solution();
		}
	}
	
	static void solution() {
		int[][] dp = new int[K + 1][K + 1];
		for(int chapter = 1; chapter <= K; chapter++) {
			for(int index = 1; index + chapter <= K; index++) {
				int to = index + chapter;
				dp[index][to] = Integer.MAX_VALUE;
				for(int divide = index; divide < to; divide++) {
					dp[index][to] = Math.min(dp[index][to], dp[index][divide] + dp[divide + 1][to] + sum[to] - sum[index - 1]);
				}
			}
		}
		sb.append(dp[1][K]).append('\n');
	}
	
	public static void main(String[] args) {
		input();
		System.out.println(sb);
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
