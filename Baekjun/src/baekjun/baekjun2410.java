package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun2410 {
	public long getReciprocalNum(int num) {
		int binary_num = Integer.toBinaryString(num).length();
		int[] reciprocal = new int[binary_num + 1];
		long[] dp = new long[num + 1];
		int N = 1000000000;
		dp[0] = 1;
		reciprocal[1] = 1;
		for(int i = 2; i <= binary_num; i++) {
			reciprocal[i] = reciprocal[i - 1] * 2;
		}
		for(int i = 1; i <= binary_num; i++) {
			for(int j = reciprocal[i]; j <= num; j++) {
				dp[j] = dp[j] % N + dp[j - reciprocal[i]] % N;
				if(dp[j] > N)
					dp[j] %= N;
			}
		}
		return dp[num];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		br.close();
		baekjun2410 b = new baekjun2410();
		bw.write(b.getReciprocalNum(num) + "\n");
		bw.flush();
		bw.close();
	}
}
