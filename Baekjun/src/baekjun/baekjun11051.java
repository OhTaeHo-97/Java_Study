package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun11051 {
	final int divisor = 10007;
	public int getCombination(int num, int k) {
		if(k > num / 2) {
			k = num - k;
		}
		if(k == 0) {
			return 1;
		} else if(k == 1) {
			return num;
		}
		int[][] dp = new int[num + 1][num + 1];
		for(int i = 0; i < dp.length; i++) {
			for(int j = 0; j <= i; j++) {
				if(i == j || j == 0) {
					dp[i][j] = 1;
				} else {
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007;
				}
			}
		}
		return dp[num][k];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		br.close();
		int num = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]);
		baekjun11051 b = new baekjun11051();
		bw.write(b.getCombination(num, k) + "\n");
		bw.flush();
		bw.close();
	}
}
