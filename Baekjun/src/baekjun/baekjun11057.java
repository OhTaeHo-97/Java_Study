package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun11057 {
	public int getAscendingNum(int num) {
		if(num == 1)
			return 10;
		else if(num == 2)
			return 55;
		int[][] dp = new int[num + 1][10];
		Arrays.fill(dp[1], 1);
		for(int i = 0; i < 10; i++) {
			dp[2][i] = i + 1;
		}
		for(int i = 3; i <= num; i++) {
			for(int j = 0; j < 10; j++) {
				for(int l = 0; l <= j; l++) {
					dp[i][j] += (dp[i - 1][l] % 10007);
				}
			}
		}
		long ascending_num = 0;
		for(int i = 0; i < dp[num].length; i++) {
			ascending_num += dp[num][i];
		}
		return (int)(ascending_num % 10007);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		br.close();
		baekjun11057 b = new baekjun11057();
		bw.write(b.getAscendingNum(num) + "\n");
		bw.flush();
		bw.close();
	}
}
