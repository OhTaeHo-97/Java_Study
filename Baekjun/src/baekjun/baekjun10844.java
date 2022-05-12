package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun10844 {
	Long[][] dp;
	final static long divisor = 1000000000;
	// top-down 방식
//	public long findStairNum(int digit, int n) {
//		if(digit == 1) {
//			return dp[digit][n];
//		}
//		if(dp[digit][n] == null) {
//			if(n == 0) {
//				dp[digit][n] = findStairNum(digit - 1, 1);
//			} else if(n == 9) {
//				dp[digit][n] = findStairNum(digit - 1, 8);
//			} else {
//				dp[digit][n] = findStairNum(digit - 1, n - 1) + findStairNum(digit - 1, n + 1);
//			}
//		}
//		return dp[digit][n] % divisor;
//	}
//	
//	public long getStairNum(int num) {
//		dp = new Long[num + 1][10];
//		Arrays.fill(dp[1], (long)1);
//		long result = 0;
//		for(int i = 1; i <= 9; i++) {
//			result += findStairNum(num, i);
//		}
//		return result % divisor;
//	}
	
	// bottom-up 방식
	public long getStairNum(int num) {
		dp = new Long[num + 1][10];
		Arrays.fill(dp[1], (long)1);
		long result = 0;
		for(int i = 2; i <= num; i++) {
			for(int j = 0; j < 10; j++) {
				if(j == 0) {
					dp[i][0] = dp[i - 1][1] % divisor;
				} else if(j == 9) {
					dp[i][9] = dp[i - 1][8] % divisor;
				} else {
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % divisor;
				}
			}
		}
		for(int i = 1; i <= 9; i++) {
			result += dp[num][i];
		}
		return result % 1000000000;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		br.close();
		baekjun10844 b = new baekjun10844();
		bw.write(b.getStairNum(num) + "\n");
		bw.flush();
		bw.close();
	}
}
