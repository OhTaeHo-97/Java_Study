package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun2591 {
	// DFS 이용
//	static int result = 0;
//	static String input;
//	public void getCaseNum(String str, int index) {
//		if(str.equals(input)) {
//			result++;
//			return;
//		}
//		for(int i = 1; i <= 2; i++) {
//			if(input.length() < index + i) {
//				break;
//			}
//			String card = input.substring(index, index + i);
//			int n = Integer.parseInt(card);
//			if(1 <= n && n <= 34) {
//				getCaseNum(str + card, index + i);
//			}
//		}
//	}

	public int getCaseNum(char[] input) {
		int[][] dp = new int[41][2];
		int prev_num = (input[0] - '0') * 10;
		dp[1][0] = 1;
		for(int i = 2; i <= input.length; i++) {
			int n = input[i - 1] - '0';
			if(n == 0) {
				if(prev_num + n <= 34) {
					dp[i][1] = dp[i - 1][0];
				}
				continue;
			}
			if(prev_num + n <= 34) {
				dp[i][0] = dp[i - 1][1] + dp[i - 1][0];
				dp[i][1] = dp[i - 1][0];
			} else {
				dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
			}
			prev_num = n * 10;
		}
		return dp[input.length][0] + dp[input.length][1];
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char[] input = br.readLine().toCharArray();
		br.close();
		baekjun2591 b = new baekjun2591();
		bw.write(b.getCaseNum(input) + "\n");
		bw.flush();
		bw.close();
	}
}
