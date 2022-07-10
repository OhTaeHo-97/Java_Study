package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun5582 {
	public int getLCS(String str1, String str2) {
		int[][] dp = new int[str1.length() + 1][str2.length() + 1];
		int max = 0;
		for(int i = 1; i < dp.length; i++) {
			for(int j = 1; j < dp[i].length; j++) {
				if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str1 = br.readLine();
		String str2 = br.readLine();
		br.close();
		baekjun5582 b = new baekjun5582();
		bw.write(b.getLCS(str1, str2) + "\n");
		bw.flush();
		bw.close();
	}
}
