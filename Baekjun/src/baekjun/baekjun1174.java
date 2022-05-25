package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun1174 {
	public long getDecreasingNum(int num) {
		if(num <= 10) {
			return num - 1;
		}
		int[][] dp = new int[11][10];
		Arrays.fill(dp[1], 1);
		int sum = 10;
		int start = 1;
		int len = 2;
		int first = 0;
		boolean isBig = false;
		while(len <= 10) {
			int count = 0;
			isBig = false;
			for(int i = start; i < 10; i++) {
				count += dp[len - 1][start - 1];
				dp[len][i] = count;
				sum += count;
				if(sum > num) {
					first = i - 1;
					sum -= count;
					isBig = true;
					break;
				}
			}
			if(isBig) {
				break;
			} else {
				start++;
				len++;
			}
		}
		if(isBig) {
			String result = Integer.toString(first);
			
		} else {
			return -1;
		}
		return 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		br.close();
	}
}
