package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun14728 {
//	public int getMaxScore(int t, Unit[] units) {
//		int[][] dp = new int[units.length][t + 1];
//		for(int i = units[0].time; i <= t; i++) {
//			dp[0][i] = units[0].score;
//		}
//		for(int i = 1; i < dp.length; i++) {
//			for(int j = 0; j <= t; j++) {
//				if(j < units[i].time) {
//					dp[i][j] = dp[i - 1][j];
//				} else {
//					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - units[i].time] + units[i].score);
//				}
//			}
//		}
//		return dp[units.length - 1][t];
//	}
	
	public int getMaxScore(int t, Unit[] units) {
		int[] dp = new int[t + 1];
		for(int i = 0; i < units.length; i++) {
			for(int j = t; j >= units[i].time; j--) {
				dp[j] = Math.max(dp[j], dp[j - units[i].time] + units[i].score);
			}
		}
		return dp[t];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int t = Integer.parseInt(input[1]);
		Unit[] units = new Unit[n];
		for(int i = 0; i < n; i++) {
			input = br.readLine().split(" ");
			units[i] = new Unit(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
		}
		br.close();
		baekjun14728 b = new baekjun14728();
		bw.write(b.getMaxScore(t, units) + "\n");
		bw.flush();
		bw.close();
	}
	
	public static class Unit {
		int time;
		int score;
		public Unit(int time, int score) {
			this.time = time;
			this.score = score;
		}
	}
}
