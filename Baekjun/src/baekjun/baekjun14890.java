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
			if(isRoad(row)) {
				answer++;
			} else {
				if(checkDown(row)) answer++;
			}
		}
		
	}
	
	static boolean isRoad(int row) {
		int num = map[row][0];
		for(int col = 1; col < N; col++) {
			if(map[row][col] != num) return false;
		}
		return true;
	}
	
	static boolean checkDown(int row) {
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
						for(int col3 = col; col3 <= col + L - 1; col3++)
							slopeWay[row][col3] = false;
						return false;
					}
				}
			} else if(num < map[row][col]) { // 높이가 높아졌을 때
				if(map[row][col] > num + 1) return false;
				if(col - L < 0) return false;
				for(int col2 = col - L; col2 < col; col2++) {
					if(slopeWay[row][col2]) return false;
					slopeWay[row][col2] = true;
					if(num != map[row][col2]) {
						for(int col3 = col - L; col3 < col; col3++)
							slopeWay[row][col3] = false;
						return false;
					}
				}
				num = map[row][col];
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		
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
