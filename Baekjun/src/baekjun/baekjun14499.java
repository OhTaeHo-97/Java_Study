package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun14499 {
	static StringBuilder sb = new StringBuilder();
	static int N, M, x, y, K, front = 2, left = 4, right = 3, behind = 5, bottom = 6, top = 1;
	static int[][] map;
	static int[] order, values = new int[7], opposite = new int[] {0, 6, 5, 4, 3, 2, 1};
	static ArrayList<Integer> top_values;
	static void input() {
		Reader scanner = new Reader();
		top_values = new ArrayList<>();
		N = scanner.nextInt();
		M = scanner.nextInt();
		map = new int[N][M];
		x = scanner.nextInt();
		y = scanner.nextInt();
		K = scanner.nextInt();
		order = new int[K];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = scanner.nextInt();
			}
		}
		for(int i = 0; i < K; i++) order[i] = scanner.nextInt();
	}
	
	static void solution() {
		for(int i = 0; i < K; i++) {
			if(order[i] == 1) {
				if(canMove(x, y + 1)) {
					moveRight(x, y + 1);
					y++;
				}
			} else if(order[i] == 2) {
				if(canMove(x, y - 1)) {
					moveLeft(x, y - 1);
					y--;
				}
			} else if(order[i] == 3) {
				if(canMove(x - 1, y)) {
					moveUp(x - 1, y);
					x--;
				}
			} else if(order[i] == 4) {
				if(canMove(x + 1, y)) {
					moveDown(x + 1, y);
					x++;
				}
			}
		}
		for(int value : top_values) sb.append(value).append('\n');
		System.out.print(sb);
	}
	
	static void moveRight(int x, int y) {
		changeDiceDirection(front, bottom, top, behind, right, left);
		if(map[x][y] == 0) changeMapValue(x, y);
		else changeDiceValue(x, y);
		findTopValue();
	}
	
	static void moveLeft(int x, int y) {
		changeDiceDirection(front, top, bottom, behind, left, right);
		if(map[x][y] == 0) changeMapValue(x, y);
		else changeDiceValue(x, y);
		findTopValue();
	}
	
	static void moveUp(int x, int y) {
		changeDiceDirection(bottom, left, right, top, behind, front);
		if(map[x][y] == 0) changeMapValue(x, y);
		else changeDiceValue(x, y);
		findTopValue();
	}
	
	static void moveDown(int x, int y) {
		changeDiceDirection(top, left, right, bottom, front, behind);
		if(map[x][y] == 0) changeMapValue(x, y);
		else changeDiceValue(x, y);
		findTopValue();
	}
	
	static boolean canMove(int x, int y) {
		if(0 <= x && x < N && 0 <= y && y < M) return true;
		return false;
	}
	
	static void changeDiceDirection(int next_front, int next_left, int next_right, int next_behind, int next_bottom, int next_top) {
		front = next_front;
		left = next_left;
		right = next_right;
		behind = next_behind;
		bottom = next_bottom;
		top = next_top;
	}
	
	static void changeDiceValue(int x, int y) {
		values[bottom] = map[x][y];
		map[x][y] = 0;
	}
	
	static void changeMapValue(int x, int y) {
		map[x][y] = values[bottom];
	}
	
	static void findTopValue() {
		top_values.add(values[top]);
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
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
