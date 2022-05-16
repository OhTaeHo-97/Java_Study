package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun2225 {
	final int divisor = 1000000000;
	int[][] dp;
	
	// bottom-up 1
//	public int getCount(int num, int k) {
//		if(k == 1) {
//			return 1;
//		}
//		long[][] dp = new long[k + 1][num + 1];
//		Arrays.fill(dp[1], 1L);
//		for(int i = 2; i <= k; i++) {
//			for(int j = 0; j <= num; j++) {
//				for(int l = 0; l <= j; l++) {
//					dp[i][j] += dp[i - 1][l];
//				}
//				dp[i][j] %= divisor;
//			}
//		}
//		return (int)dp[k][num];
//	}
	
	// bottom-up 2
//	public int getCount(int num, int k) {
//		if(k == 1) {
//			return 1;
//		}
//		int[][] dp = new int[num + 1][k + 1];
//		Arrays.fill(dp[0], 1);
//		for(int i = 0; i < dp.length; i++) {
//			dp[i][1] = 1;
//		}
//		for(int i = 1; i <= num; i++) {
//			for(int j = 2; j <= k; j++) {
//				dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % divisor;
//			}
//		}
//		return dp[num][k];
//	}
	
	public int findCount(int num, int k) {
		if(num == 1) {
			dp[num][k] = k;
		} else if(dp[num][k] == 0) {
			dp[num][k] = findCount(num - 1, k) + findCount(num, k - 1);
		}
		return dp[num][k] % divisor;
	}
	
	// bottom-up 2의 top-down
	public int getCount(int num, int k) {
		if(k == 1) {
			return 1;
		}
		dp = new int[num + 1][k + 1];
		Arrays.fill(dp[0], 1);
		for(int i = 0; i < dp.length; i++) {
			dp[i][1] = 1;
		}
		return findCount(num, k);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		br.close();
		int num = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]);
		baekjun2225 b = new baekjun2225();
		bw.write(b.getCount(num, k) + "\n");
		bw.flush();
		bw.close();
	}
}
