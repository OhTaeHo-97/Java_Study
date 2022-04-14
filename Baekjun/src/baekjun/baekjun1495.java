package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1495 {
	int[][] dp;
	static int start_vol, max_vol, song_num;
	static int[] dif_volume;
	
	public int findVolume(int sum, int index) {
		if(0 > sum || sum > max_vol) {
			return -1;
		}
		if(index == song_num) {
			return sum;
		}
		if(dp[sum][index] != -10) {
			return dp[sum][index];
		}
		return dp[sum][index] = Math.max(dp[sum][index], Math.max(findVolume(sum + dif_volume[index], index + 1), findVolume(sum - dif_volume[index], index + 1)));
	}
	
	public int getMaxVolume() {
		dp = new int[max_vol + 1][dif_volume.length];
		for(int i = 0; i < dp.length; i++) {
			for(int j = 0; j < dp[i].length; j++) {
				dp[i][j] = -10;
			}
		}
		return findVolume(start_vol, 0);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		song_num = Integer.parseInt(st.nextToken());
		start_vol = Integer.parseInt(st.nextToken());
		max_vol = Integer.parseInt(st.nextToken());
		dif_volume = new int[song_num];
		input = br.readLine();
		br.close();
		st = new StringTokenizer(input);
		for(int i = 0; i < dif_volume.length; i++) {
			dif_volume[i] = Integer.parseInt(st.nextToken());
		}
		baekjun1495 b = new baekjun1495();
		bw.write(b.getMaxVolume() + "\n");
		bw.flush();
		bw.close();
	}
}
