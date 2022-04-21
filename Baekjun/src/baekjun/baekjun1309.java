package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class baekjun1309 {
	public long getArrangeNum(int num) {
		long[][] dp = new long[num + 1][3];
		dp[1][0] = dp[1][1] = dp[1][2] = 1;
		for(int i = 2; i <= num; i++) {
			dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901;
			dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901;
			dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
		}
		long arrangeNum = (dp[num][0] + dp[num][1] + dp[num][2]) % 9901;
		return arrangeNum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		br.close();
		baekjun1309 b = new baekjun1309();
		bw.write(b.getArrangeNum(num) + "\n");
		bw.flush();
		bw.close();
	}
}
