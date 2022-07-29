package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun2133 {
	public int getCaseNum(int n) {
		if(n % 2 == 1) {
			return 0;
		}
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[2] = 3;
		for(int i = 4; i <= n; i += 2) {
			dp[i] = dp[i - 2] * dp[2];
			for(int j = i - 4; j >= 0; j -= 2) {
				dp[i] += dp[j] * 2;
			}
		}
		return dp[n];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		br.close();
		baekjun2133 b = new baekjun2133();
		bw.write(b.getCaseNum(n) + "\n");
		bw.flush();
		bw.close();
	}
}
