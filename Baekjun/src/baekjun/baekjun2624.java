package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class baekjun2624 {
	public int getCaseNum(int target, int[][] coins) {
		Arrays.sort(coins, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[0] - o2[0] > 0) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		int[][] dp = new int[coins.length][target + 1];
		for(int i = 0; i < dp.length; i++) {
			dp[i][0] = 1;
		}
		for(int i = 1; i <= coins[0][1]; i++) {
            if(coins[0][0] * i > target) {
				break;
			}
			dp[0][coins[0][0] * i] = 1;
 		}
		for(int i = 1; i < coins.length; i++) {
			for(int j = 1; j <= target; j++) {
				for(int l = 0; l <= coins[i][1]; l++) {
					if(j - (coins[i][0] * l) < 0) {
						break;
					} else {
						dp[i][j] += dp[i - 1][j - (coins[i][0] * l)];
					}
				}
			}
		}
		return dp[coins.length - 1][target];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int target = Integer.parseInt(br.readLine());
		int coin_type = Integer.parseInt(br.readLine());
		int[][] coins = new int[coin_type][2];
		for(int i = 0; i < coin_type; i++) {
			String[] input = br.readLine().split(" ");
			coins[i][0] = Integer.parseInt(input[0]);
			coins[i][1] = Integer.parseInt(input[1]);
		}
		br.close();
		baekjun2624 b = new baekjun2624();
		bw.write(b.getCaseNum(target, coins) + "\n");
		bw.flush();
		bw.close();
	}
}