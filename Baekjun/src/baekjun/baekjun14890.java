package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun14890 {
	static int N, L;
	static int[][] map;
	static boolean[][] slopeWay;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		L = scanner.nextInt();
		map = new int[N][N];
		slopeWay = new boolean[N][N];
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) map[row][col] = scanner.nextInt();
		}
	}
	
	static void solution() {
		int answer = 0;
		for(int row = 0; row < N; row++) {
			if(isRowRoad(row)) {
				answer++;
			} else {
				if(checkRows(row)) answer++;
			}
		}
		slopeWay = new boolean[N][N];
		for(int col = 0; col < N; col++) {
			if(isColRoad(col)) {
				answer++;
			} else {
				if(checkColumns(col)) answer++;
			}
		}
		System.out.println(answer);
	}
	
	static boolean isRowRoad(int row) {
		int num = map[row][0];
		for(int col = 1; col < N; col++) {
			if(map[row][col] != num) return false;
		}
		return true;
	}
	
	static boolean isColRoad(int col) {
		int num = map[0][col];
		for(int row = 1; row < N; row++) {
			if(map[row][col] != num) return false;
		}
		return true;
	}
	
	static boolean checkRows(int row) {
		int num = map[row][0];
		for(int col = 1; col < N; col++) {
			if(num == map[row][col]) continue;
			if(num > map[row][col]) { // 높이가 낮아졌을 때
				if(map[row][col] < num - 1) return false;
				if(col + L - 1 >= N) return false;
				num = map[row][col];
				for(int col2 = col; col2 <= col + L - 1; col2++) {
					if(slopeWay[row][col2]) return false;
					slopeWay[row][col2] = true;
					if(num != map[row][col2]) {
						return false;
					}
				}
			} else if(num < map[row][col]) { // 높이가 높아졌을 때
				if(map[row][col] > num + 1) return false;
				if(col - L < 0) return false;
				num = map[row][col - 1];
				for(int col2 = col - L; col2 < col; col2++) {
					if(slopeWay[row][col2]) return false;
					slopeWay[row][col2] = true;
					if(num != map[row][col2]) {
						return false;
					}
				}
				num = map[row][col];
			}
		}
		return true;
	}
	
	static boolean checkColumns(int col) {
		int num = map[0][col];
		for(int row = 1; row < N; row++) {
			if(num == map[row][col]) continue;
			if(num > map[row][col]) { // 높이가 낮아졌을 때
				if(map[row][col] < num - 1) return false;
				if(row + L - 1 >= N) return false;
				num = map[row][col];
				for(int row2 = row; row2 <= row + L - 1; row2++) {
					if(slopeWay[row2][col]) return false;
					slopeWay[row2][col] = true;
					if(num != map[row2][col]) {
						return false;
					}
				}
			} else if(num < map[row][col]) { // 높이가 높아졌을 때
				if(map[row][col] > num + 1) return false;
				if(row - L < 0) return false;
				num = map[row - 1][col];
				for(int row2 = row - L; row2 < row; row2++) {
					if(slopeWay[row2][col]) return false;
					slopeWay[row2][col] = true;
					if(num != map[row2][col]) {
						return false;
					}
				}
				num = map[row][col];
			}
		}
		return true;
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
