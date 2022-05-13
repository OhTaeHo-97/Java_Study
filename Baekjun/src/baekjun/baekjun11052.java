package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun11052 {
	static int[] prices;
	public int getMaxPrice(int num) {
		if(num == 1) {
			return prices[0];
		}
		int[] dp = new int[prices.length];
		dp[1] = prices[1];
		for(int i = 2; i <= num; i++) {
			dp[i] = prices[i];
			for(int j = 0; j <= i / 2; j++) {
				dp[i] = Math.max(dp[i], dp[i - j] + dp[j]);
			}
		}
		return dp[num];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		prices = new int[num + 1];
		String[] input = br.readLine().split(" ");
		br.close();
		for(int i = 1; i <= num; i++) {
			prices[i] = Integer.parseInt(input[i - 1]);
		}
		baekjun11052 b = new baekjun11052();
		bw.write(b.getMaxPrice(num) + "\n");
		bw.flush();
		bw.close();
	}
}