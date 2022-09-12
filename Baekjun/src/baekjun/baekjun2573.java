package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun2573 {
	static int N, M, mass;
	static int[][] icebergs;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};;
	static boolean[][] visited;
	static ArrayList<Integer>[] icebergLoc;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		icebergs = new int[N][M];
		icebergLoc = new ArrayList[N];
		for(int index = 0; index < N; index++) icebergLoc[index] = new ArrayList<Integer>();
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				icebergs[row][col] = scanner.nextInt();
				if(icebergs[row][col] != 0) icebergLoc[row].add(col);
			}
		}
	}
	
	static void solution() {
		int time = 0;
		while(true) {
			mass = 0;
			visited = new boolean[N][M];
			melt();
			time++;
			for(int row = 0; row < icebergLoc.length; row++) {
				for(int col = 0; col < icebergLoc[row].size(); col++) {
					int y = icebergLoc[row].get(col);
					if(!visited[row][y]) {
						mass++;
						if(mass >= 2) {
							System.out.println(time);
							return;
						}
						dfs(row, y);
					}
				}
			}
			boolean flag = true;
			for(int i = 0; i < icebergLoc.length; i++) {
				if(icebergLoc[i].size() != 0) {
					flag = false;
					break;
				}
			}
			if(flag) {
				System.out.println(0);
				return;
			}
		}
	}
	
	static void melt() {
		int[][] meltAmount = new int[N][M];
		for(int row = 0; row < icebergLoc.length; row++) {
			for(int col = 0; col < icebergLoc[row].size(); col++) {
				int x = row;
				int y = icebergLoc[row].get(col);
				int count = 0;
				for(int direction = 0; direction < 4; direction++) {
					int cx = x + dx[direction];
					int cy = y + dy[direction];
					if(cx >= 0 && cx < N && cy >= 0 && cy < M && icebergs[cx][cy] == 0) count++;
				}
				meltAmount[x][y] += count;
			}
		}
		melt(meltAmount);
	}
	
	static void melt(int[][] meltAmount) {
		for(int row = 0; row < icebergLoc.length; row++) {
			for(int col = 0; col < icebergLoc[row].size(); col++) {
				int y = icebergLoc[row].get(col);
				if(icebergs[row][y] - meltAmount[row][y] <= 0) {
					icebergs[row][y] = 0;
					icebergLoc[row].remove(Integer.valueOf(y));
					col--;
				} else {
					icebergs[row][y] -= meltAmount[row][y];
				}
			}
		}
	}
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		for(int direction = 0; direction < 4; direction++) {
			int cx = x + dx[direction];
			int cy = y + dy[direction];
			if(cx >= 0 && cx < N && cy >= 0 && cy < M) {
				if(!visited[cx][cy] && icebergs[cx][cy] != 0) dfs(cx, cy);
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
