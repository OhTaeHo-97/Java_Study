package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1149 {
	static int[][] dp;
	static int[][] cost;
	public int getCost(int house, int color) {
		if(dp[house][color] == 0) {
			if(color == 0) {
				dp[house][color] = Math.min(getCost(house - 1, 1), getCost(house - 1, 2)) + cost[house][color];
			} else if(color == 1) {
				dp[house][color] = Math.min(getCost(house - 1, 0), getCost(house - 1, 2)) + cost[house][color];
			} else if(color == 2) {
				dp[house][color] = Math.min(getCost(house - 1, 0), getCost(house - 1, 1)) + cost[house][color];
			}
		}
		return dp[house][color];
	}
	
	public int getMinCost() {
		dp[0][0] = cost[0][0];
		dp[0][1] = cost[0][1];
		dp[0][2] = cost[0][2];
		int min = Math.min(getCost(dp.length - 1, 0), getCost(dp.length - 1, 1));
		return Math.min(min, getCost(dp.length - 1, 2));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int house_num = Integer.parseInt(br.readLine());
		cost = new int[house_num][3];
		dp = new int[house_num][3];
		for(int i = 0; i < cost.length; i++) {
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input);
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		br.close();
		baekjun1149 b = new baekjun1149();
		bw.write(b.getMinCost() + "\n");
		bw.flush();
		bw.close();
	}
}
