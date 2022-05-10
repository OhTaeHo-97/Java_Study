package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun1932 {
	static int num;
	static int[][] triangle;
	static Integer[][] dp;
//	public int getMaxNum(int[][] triangle) {
//		int[][] dp = new int[triangle.length][triangle.length];
//		for(int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		dp[0][0] = triangle[0][0];
//		for(int i = 1; i < triangle.length; i++) {
//			int left, right = -1;
//			for(int j = 0; j < triangle[i].length - 1; j++) {
//				if(triangle[i][j + 1] == -1) {
//					dp[i][j] = Math.max(dp[i][j], triangle[i][j] + dp[i - 1][j - 1]);
//					break;
//				}
//				left = triangle[i][j] + dp[i - 1][j];
//				right = triangle[i][j + 1] + dp[i - 1][j];
//				if(dp[i][j] == -1) {
//					dp[i][j] = left;
//				} else {
//					dp[i][j] = Math.max(dp[i][j], left);
//				}
//				if(dp[i][j + 1] == -1) {
//					dp[i][j + 1] = right;
//				} else {
//					dp[i][j + 1] = Math.max(dp[i][j + 1], right);
//				}
//			}
//		}
//		Arrays.sort(dp[dp.length - 1]);
//		return dp[dp.length - 1][dp.length - 1];
//	}
	public int findMaxNum(int depth, int index) {
		if(depth == num - 1) {
			return dp[depth][index];
		}
		if(dp[depth][index] == null) {
			dp[depth][index] = Math.max(findMaxNum(depth + 1, index), findMaxNum(depth + 1, index + 1)) + triangle[depth][index];
		}
		return dp[depth][index];
	}
	
	public int getMaxNum() {
		for(int i = 0; i < triangle.length; i++) {
			dp[dp.length - 1][i] = triangle[dp.length - 1][i];
		}
		return findMaxNum(0, 0);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		num = Integer.parseInt(br.readLine());
		triangle = new int[num][num];
		dp = new Integer[num][num];
		for(int i = 0; i < num; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < input.length; j++) {
				triangle[i][j] = Integer.parseInt(input[j]);
			}
		}
		br.close();
		baekjun1932 b = new baekjun1932();
		bw.write(b.getMaxNum() + "\n");
		bw.flush();
		bw.close();
	}
}
