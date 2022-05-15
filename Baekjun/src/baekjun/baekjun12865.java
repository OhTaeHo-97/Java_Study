package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun12865 {
//	Integer[][] dp;
	static int[] p_weight;
	static int[] p_value;
	// Top-Down
//	public int findMaxValue(int p, int weight) {
//		if(p < 0) {
//			return 0;
//		}
//		if(dp[p][weight] == null) {
//			if(p_weight[p] > weight) {
//				dp[p][weight] = findMaxValue(p - 1, weight);
//			} else {
//				dp[p][weight] = Math.max(findMaxValue(p - 1, weight), findMaxValue(p - 1, weight - p_weight[p]) + p_value[p]);
//			}
//		}
//		return dp[p][weight];
//	}
	
//	public int getMaxValue(int weight) {
//		dp = new Integer[p_weight.length][weight + 1];
//		return findMaxValue(p_weight.length - 1, weight);
//	}
	
	// Bottom-Up
	public int getMaxValue(int weight) {
		int[][] dp = new int[p_weight.length][weight + 1];
		for(int i = 1; i <= p_weight.length - 1; i++) {
			for(int j = 1; j <= weight; j++) {
				if(p_weight[i] > j) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - p_weight[i]] + p_value[i]);
				}
			}
		}
		return dp[p_weight.length - 1][weight];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int num = Integer.parseInt(input[0]);
		int weight = Integer.parseInt(input[1]);
		p_weight = new int[num + 1];
		p_value = new int[num + 1];
		for(int i = 1; i <= num; i++) {
			input = br.readLine().split(" ");
			p_weight[i] = Integer.parseInt(input[0]);
			p_value[i] = Integer.parseInt(input[1]);
		}
		br.close();
		baekjun12865 b = new baekjun12865();
		bw.write(b.getMaxValue(weight) + "\n");
		bw.flush();
		bw.close();
	}
}
