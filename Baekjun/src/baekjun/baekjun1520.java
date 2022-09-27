package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjun1520 {
	static int[][] map, dp;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	static int N, M, possibleNum;
	static void input() {
		Reader scanner = new Reader();
		M = scanner.nextInt();
		N = scanner.nextInt();
		possibleNum = 0;
		map = new int[M][N];
		dp = new int[M][N];
		for(int row = 0; row < M; row++) {
			for(int col = 0; col < N; col++) {
				map[row][col] = scanner.nextInt();
				dp[row][col] = -1;
			}
		}
	}
	
	static void solution() {
		System.out.println(dfs(0, 0));
	}
	
	static int dfs(int x, int y) {
		if(x == M - 1 && y == N - 1) {
			return 1;
		}
		if(dp[x][y] != -1) {
			return dp[x][y];
		}
		dp[x][y] = 0;
		for(int index = 0; index < 4; index++) {
			int cx = x + dx[index];
			int cy = y + dy[index];
			if(cx >= 0 && cx < M && cy >= 0 && cy < N) {
				if(map[x][y] > map[cx][cy]) {
					dp[x][y] += dfs(cx, cy);
				}
			}
		}
		return dp[x][y];
	}
	
	public static void main(String[] args) {
		input();
		solution();
	}
	
	static class Reader {
		BufferedReader br;
		StringTokenizer st;
		public Reader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		String next() {
			while(st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
