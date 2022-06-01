package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun2293 {
	public int getNumOfCase(int objective, int[] coins) {
		int[] dp = new int[objective + 1];
		dp[0] = 1;
		for(int i = 0; i < coins.length; i++) {
			for(int j = 1; j <= objective; j++) {
				if(j >= coins[i]) {
					dp[j] = dp[j] + dp[j - coins[i]];
				}
			}
		}
		return dp[objective];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int coin_num = Integer.parseInt(input[0]);
		int objective = Integer.parseInt(input[1]);
		int[] coins = new int[coin_num];
		for(int i = 0; i < coin_num; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun2293 b = new baekjun2293();
		bw.write(b.getNumOfCase(objective, coins) + "\n");
		bw.flush();
		bw.close();
	}
}
