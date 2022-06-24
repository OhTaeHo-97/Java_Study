package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun9251 {
	public int getLCSLength(String first_str, String second_str) {
		int[][] dp = new int[first_str.length() + 1][second_str.length() + 1];
		for(int i = 0; i < dp.length; i++) {
			for(int j = 0; j < dp[i].length; j++) {
				if(i == 0 || j == 0) {
					continue;
				} else if(first_str.charAt(i - 1) == second_str.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[first_str.length()][second_str.length()];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String first_str = br.readLine();
		String second_str = br.readLine();
		br.close();
		baekjun9251 b = new baekjun9251();
		bw.write(b.getLCSLength(first_str, second_str) + "\n");
		bw.flush();
		bw.close();
	}
}