package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun1106 {
	static int c, city_num, ans;
	static int[][] arr;
	static int[] dp;
	
	public void dfs(int count, int cost) {
		if(dp[count] > cost) {
			dp[count] = cost;
		} else {
			return;
		}
		if(cost >= ans) {
			return;
		}
		if(count >= c) {
			ans = Math.min(ans, cost);
		}
		for(int i = 0; i < city_num; i++) {
			dfs(count + arr[i][1], cost + arr[i][0]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		c = Integer.parseInt(st.nextToken());
		city_num = Integer.parseInt(st.nextToken());
		arr = new int[city_num][2];
		int max = 0;
		for(int i = 0; i < city_num; i++) {
			input = br.readLine();
			st = new StringTokenizer(input);
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i][0]);
		}
		ans = Integer.MAX_VALUE;
		dp = new int[10000];
		Arrays.fill(dp, Integer.MAX_VALUE);
		baekjun1106 b = new baekjun1106();
		b.dfs(0, 0);
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}
}
