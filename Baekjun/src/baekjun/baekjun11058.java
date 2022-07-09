package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun11058 {
	public long getMaxNum(int num) {
		long[] dp = new long[num + 1];
		for(int i = 1; i < dp.length; i++) {
			dp[i] = dp[i - 1] + 1;
			if(i > 6) {				
				for(int j = 2; j < 5; j++) {
					dp[i] = Math.max(dp[i], dp[i - (j + 1)] * j);
				}
			}
		}
		return dp[num];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		br.close();
		baekjun11058 b = new baekjun11058();
		bw.write(b.getMaxNum(num) + "\n");
		bw.flush();
		bw.close();
	}
}
