package programmers;

import java.util.*;

public class Level3_WayToSchool {
	static final int MAX = 1000000007;
	static int[][] map;
	public static int solution(int m, int n, int[][] puddles) {
		map = new int[n][m];
		for(int[] puddle : puddles) {
			map[puddle[1] - 1][puddle[0] - 1] = -1;
		}
		int idx = -1;
		for(int row = 0; row < n; row++) {
			if(map[row][0] == -1) {
				idx = row + 1;
				break;
			}
			map[row][0] = 1;
		}
		idx = idx == -1 ? n : idx;
		for(int row = idx; row < n; row++) map[row][0] = -1;
		idx = -1;
		for(int col = 0; col < m; col++) {
			if(map[0][col] == -1) {
				idx = col + 1;
				break;
			}
			map[0][col] = 1;
		}
		idx = idx == -1 ? m : idx;
		Arrays.fill(map[0], idx, m, -1);
		for(int row = 1; row < n; row++) {
			for(int col = 1; col < m; col++) {
				if(map[row][col] == -1) continue;
				int left = map[row][col - 1] == -1 ? 0 : map[row][col - 1];
				int up = map[row - 1][col] == -1 ? 0 : map[row - 1][col];
				map[row][col] = Math.max(map[row][col], (left + up) % MAX);
			}
		}
		return map[n - 1][m - 1];
	}
	
	public static void main(String[] args) {
		int m = 4, n = 3;
		int[][] puddles = {{2, 2}};
		System.out.println(solution(m, n, puddles));
	}
}
