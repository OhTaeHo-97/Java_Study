package programmers.kakaoCodeQualifying;

import java.util.*;

public class Level3_WalkerHeaven {
	// 1 <= m, n <= 500
	// city_map => m x n
	// 출발점 -> (0, 0), 도착점 -> (m - 1, n - 1)
	// 출발점 및 도착점의 값은 0
	
	static int MOD = 20170805;
	public static int solution(int m, int n, int[][] cityMap) {
		if(m == 1 && n == 1) return 1;
		int[][][] dp = new int[m][n][2];
		for(int row = 1; row < m; row++) {
			if(cityMap[row][0] == 1) break;
			dp[row][0][1] = 1;
		}
		for(int col = 1; col < n; col++) {
			if(cityMap[0][col] == 1) break;
			dp[0][col][0] = 1;
		}
		for(int row = 1; row < m; row++) {
			for(int col = 1; col < n; col++) {
				if(cityMap[row][col] == 1) continue;
				dp[row][col][0] = cityMap[row][col - 1] == 2 ? dp[row][col - 1][0] % MOD : (dp[row][col - 1][0] + dp[row][col - 1][1]) % MOD;
				dp[row][col][1] = cityMap[row - 1][col] == 2 ? dp[row - 1][col][1] % MOD : (dp[row - 1][col][0] + dp[row - 1][col][1]) % MOD;
			}
		}
		return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
	}
	
	public static void main(String[] args) {
//		int m = 3, n = 6;
//		int[][] city_map = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
//		int m = 3, n = 3;
//		int[][] city_map = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
		int m = 500, n = 500;
		int[][] city_map = new int[500][500];
//		Random random = new Random();
//		for(int row = 0; row < 500; row++) {
//			for(int col = 0; col < 500; col++) city_map[row][col] = random.nextInt(3);
//		}
//		city_map[0][0] = city_map[499][499] = 0;
//		for(int col = 0; col < 500; col++) System.out.print(city_map[0][col]);
//		System.out.println();
//		for(int row = 0; row < 500; row++) {
//			for(int col = 0; col < 500; col++) {
//				System.out.print(city_map[row][col] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(solution(m, n, city_map));
	}
}
