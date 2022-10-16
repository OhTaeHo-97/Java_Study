package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1915 {
	static int n, m, result;
	static int[][] matrix, dp;
	static void input() {
		Reader scanner = new Reader();
		n = scanner.nextInt();
		m = scanner.nextInt();
		matrix = new int[n][m];
		dp = new int[n][m];
		result = 0;
		for(int row = 0; row < n; row++) {
			String info = scanner.nextLine();
			for(int col = 0; col < m; col++) {
				matrix[row][col] = info.charAt(col) - '0';
				dp[row][col] = matrix[row][col];
				if(dp[row][col] == 1) result = 1;
			}
		}
	}
	
	static void solution() {
		for(int row = 1; row < n; row++) {
			for(int col = 1; col < m; col++) {
				if(matrix[row][col] == 1) {
					dp[row][col] = Math.min(dp[row][col - 1], Math.min(dp[row - 1][col], dp[row - 1][col - 1])) + 1;
					result = dp[row][col] > result ? dp[row][col] : result;
				}
			}
		}
		System.out.println(result * result);
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
		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
