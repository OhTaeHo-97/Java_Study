package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class baekjun2229 {
	public int getMaxScoreDif(int[] scores) {
		int[] dp = new int[scores.length];
		for(int i = 2; i < scores.length; i++) {
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int j = i; j >= 1; j--) {
				max = Math.max(max, scores[j]);
				min = Math.min(min, scores[j]);
				dp[i] = Math.max(dp[i], max - min + dp[j - 1]);
			}
		}
		return dp[scores.length - 1];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int people_num = Integer.parseInt(br.readLine());
		int[] scores = new int[people_num + 1];
		String[] score = br.readLine().split(" ");
		br.close();
		for(int i = 0; i < people_num; i++) {
			scores[i + 1] = Integer.parseInt(score[i]);
		}
		baekjun2229 b = new baekjun2229();
		bw.write(b.getMaxScoreDif(scores) + "\n");
		bw.flush();
		bw.close();
	}
}
