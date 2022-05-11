package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun2579 {
	static Integer dp[];
	static int scores[];
	public int findMaxScore(int num) {
		if(dp[num] == null) {
			dp[num] = Math.max(findMaxScore(num - 2), findMaxScore(num - 3) + scores[num - 1]) + scores[num];
		}
		return dp[num];
	}
	
	public int getMaxScore(int[] scores) {
		// bottom-up 방식
//		int[] dp = new int[scores.length];
//		dp[1] = scores[1];
//		// num이 1일 경우를 대비
//		if(scores.length - 1 >= 2) {			
//			dp[2] = scores[1] + scores[2];
//		}
//		for(int i = 3; i < dp.length; i++) {
//			dp[i] = Math.max(dp[i - 2], dp[i - 3] + scores[i - 1]) + scores[i];
//		}
//		return dp[dp.length - 1];
		
		// top-down 방식
		dp = new Integer[scores.length];
		dp[0] = scores[0]; // null이기 떄문에 0으로 초기화
		dp[1] = scores[1];
		if(scores.length - 1 >= 2) {
			dp[2] = scores[2] + scores[1];
		}
		return findMaxScore(scores.length - 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
//		int[] scores = new int[num + 1]; // bottom-up 방식
		scores = new int[num + 1]; // top-down 방식
		for(int i = 1; i <= num; i++) {
			scores[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun2579 b = new baekjun2579();
		bw.write(b.getMaxScore(scores) + "\n");
		bw.flush();
		bw.close();
	}
}
