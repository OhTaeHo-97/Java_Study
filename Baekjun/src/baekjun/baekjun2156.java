package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun2156 {
	static int[] size;
	static Integer[] dp;
//	public int getMaxSize(int[] size) {
		// bottom-up 방식
//		int[] dp = new int[size.length];
//		dp[1] = size[1];
//		if(size.length - 1 >= 2) {
//			dp[2] = size[1] + size[2];
//		}
//		for(int i = 3; i < dp.length; i++) {
//			dp[i] = Math.max(Math.max(dp[i - 2], dp[i - 3] + size[i - 1]) + size[i], dp[i - 1]);
//		}
//		return dp[dp.length - 1];
//	}
	public int findMaxSize(int num) {
		if(dp[num] == null) {
			dp[num] = Math.max(Math.max(findMaxSize(num - 2), findMaxSize(num - 3) + size[num - 1]) + size[num], findMaxSize(num - 1));
		}
		return dp[num];
	}
	
	public int getMaxSize() {
		dp = new Integer[size.length];
		dp[0] = size[0];
		dp[1] = size[1];
		if(size.length - 1 >= 2) {
			dp[2] = size[1] + size[2];
		}
		return findMaxSize(dp.length - 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
//		int[] size = new int[num + 1]; // bottom-up 방식
		size = new int[num + 1];
		for(int i = 1; i <= num; i++) {
			size[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun2156 b = new baekjun2156();
//		bw.write(b.getMaxSize(size) + "\n"); // bottom-up 방식
		bw.write(b.getMaxSize() + "\n");
		bw.flush();
		bw.close();
	}
}
