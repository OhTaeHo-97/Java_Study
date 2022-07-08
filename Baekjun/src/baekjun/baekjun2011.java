package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun2011 {
	public long getInterpretNum(String code) {
		if(code.charAt(0) == '0') {
			return 0;
		}
		long[] dp = new long[code.length() + 1];
		dp[0] = 1;
		dp[1] = 1;
		for(int i = 2; i <= code.length(); i++) {
			char cur_char = code.charAt(i - 1);
			char prev_char = code.charAt(i - 2);
			if(cur_char == '0') {
				if(prev_char == '1' || prev_char == '2') {
					dp[i] = dp[i - 2] % 1000000;
				} else {
					break;
				}
			} else {
				if(prev_char == '0') {
					dp[i] = dp[i - 1] % 1000000;
				} else {
					int temp = (prev_char - '0') * 10 + (cur_char - '0');
					if(1 <= temp && temp <= 26) {
						dp[i] = (dp[i - 2] + dp[i - 1]) % 1000000;
					} else {
						dp[i] = dp[i - 1] % 1000000;
					}
				}
			}
		}
		return dp[code.length()] % 1000000;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String code = br.readLine();
		br.close();
		baekjun2011 b = new baekjun2011();
		bw.write(b.getInterpretNum(code) + "\n");
		bw.flush();
		bw.close();
	}
}
