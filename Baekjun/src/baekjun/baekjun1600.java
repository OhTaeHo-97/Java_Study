package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1600 {
	static int K, H, W;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	static int[] hdx = {-2, -1, 1, 2, 2, 1, -1, -2}, hdy = {1, 2, 2, 1, -1, -2, -2, -1};
	static void input() {
		Reader scanner = new Reader();
		K = scanner.nextInt();
		W = scanner.nextInt();
		H = scanner.nextInt();
		map = new int[H][W];
		for(int row = 0; row < H; row++) {
			for(int col = 0; col < W; col++) map[row][col] = scanner.nextInt();
		}
	}
	
	static void solution() {
		int result = bfs();
		System.out.println(result);
	}
	
	static int bfs() {
		Queue<Monkey> queue = new LinkedList<>();
		queue.offer(new Monkey(0, 0, 0, K));
		boolean[][][] visited = new boolean[H][W][K + 1];
		visited[0][0][K] = true;
		int min = -1;
		while(!queue.isEmpty()) {
			Monkey cur = queue.poll();
			if(cur.x == (H - 1) && cur.y == (W - 1)) {
				min = cur.move;
				break;
			}
			for(int dir = 0; dir < 4; dir++) {
				int cx = cur.x + dx[dir], cy = cur.y + dy[dir];
				if(isInMap(cx, cy)) {
					if(!visited[cx][cy][cur.horseMove] && map[cx][cy] == 0) {
						visited[cx][cy][cur.horseMove] = true;
						queue.offer(new Monkey(cx, cy, cur.move + 1, cur.horseMove));
					}
				}
			}
			if(cur.horseMove > 0) {
				for(int dir = 0; dir < 8; dir++) {
					int cx = cur.x + hdx[dir], cy = cur.y + hdy[dir];
					if(isInMap(cx, cy)) {
						if(!visited[cx][cy][cur.horseMove - 1] && map[cx][cy] == 0) {
							visited[cx][cy][cur.horseMove - 1] = true;
							queue.offer(new Monkey(cx, cy, cur.move + 1, cur.horseMove - 1));
						}
					}
				}
			}
		}
		return min;
	}
	
	static boolean isInMap(int x, int y) {
		if(x >= 0 && x < H && y >= 0 && y < W) return true;
		return false;
	}
	
	static class Monkey {
		int x, y, move, horseMove;
		public Monkey(int x, int y, int move, int horseMove) {
			this.x = x;
			this.y = y;
			this.move = move;
			this.horseMove = horseMove;
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
