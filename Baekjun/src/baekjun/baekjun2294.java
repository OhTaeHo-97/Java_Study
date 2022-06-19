package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun2294 {
	public int getMinCoinNum(int n, int k, int[] coins) {
		int[] dp = new int[k + 1];
		for(int i = 1; i <= k; i++) {
			dp[i] = Integer.MAX_VALUE - 1;
		}
		for(int i = 1; i <= n; i++) {
			for(int j = coins[i]; j <= k; j++) {
				dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
			}
		}
		if(dp[k] == Integer.MAX_VALUE - 1) {
			return -1;
		}
		return dp[k];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]);
		int[] coins = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun2294 b = new baekjun2294();
		bw.write(b.getMinCoinNum(n, k, coins) + "\n");
		bw.flush();
		bw.close();
	}
}
