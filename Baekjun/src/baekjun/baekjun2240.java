package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun2240 {
	public int getMaxNum(int t, int w, int[] tree_nums) {
		int result = 0;
		int[][][] dp = new int[3][t + 1][w + 2];
		for(int i = 1; i <= t; i++) {
			for(int j = 1; j <= w + 1; j++) {
				if(tree_nums[i] == 1) {
					dp[1][i][j] = Math.max(dp[1][i - 1][j], dp[2][i - 1][j - 1]) + 1;
					dp[2][i][j] = Math.max(dp[2][i - 1][j], dp[1][i - 1][j - 1]);
				} else {
					if(i == 1 && j == 1) {
						continue;
					}
					dp[1][i][j] = Math.max(dp[1][i - 1][j], dp[2][i - 1][j - 1]);
					dp[2][i][j] = Math.max(dp[2][i - 1][j], dp[1][i - 1][j - 1]) + 1;
				}
			}
		}
		for(int i = 1; i <= w + 1; i++) {
			result = Math.max(result, Math.max(dp[1][t][i], dp[2][t][i]));
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int t = Integer.parseInt(input[0]);
		int w = Integer.parseInt(input[1]);
		int[] tree_nums = new int[t + 1];
		for(int i = 1; i < tree_nums.length; i++) {
			tree_nums[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun2240 b = new baekjun2240();
		bw.write(b.getMaxNum(t, w, tree_nums) + "\n");
		bw.flush();
		bw.close();
	}
}
