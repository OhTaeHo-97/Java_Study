package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun1937 {
	static int n, max = Integer.MIN_VALUE, temp = Integer.MIN_VALUE;
	static int[][] map, dp;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	static void input() {
		Reader scanner = new Reader();
		n = scanner.nextInt();
		map = new int[n][n];
		dp = new int[n][n];
		for(int row = 0; row < n; row++) {
			Arrays.fill(dp[row], Integer.MIN_VALUE);
			for(int col = 0; col < n; col++)
				map[row][col] = scanner.nextInt();
		}
	}
	
	static void solution() {
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				temp = Integer.MIN_VALUE;
				visited = new boolean[n][n];
				dfs(row, col, 1);
				dp[row][col] = temp;
				max = Math.max(max, temp);
			}
		}
		System.out.println(max);
	}
	
	static void dfs(int x, int y, int step) {
		temp = Math.max(temp, step);
		visited[x][y] = true;
		for(int direction = 0; direction < 4; direction++) {
			int cx = x + dx[direction];
			int cy = y + dy[direction];
			if(cx >= 0 && cx < n && cy >= 0 && cy < n && !visited[cx][cy]) {
				if(map[cx][cy] > map[x][y]) {
					if(dp[cx][cy] != Integer.MIN_VALUE) temp = Math.max(temp, step + dp[cx][cy]);
					else dfs(cx, cy, step + 1);
				}
			}
		}
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
