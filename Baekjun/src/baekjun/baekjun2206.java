package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun2206 {
	static int N, M;
	static int[][] map;
	static int[][] count;
	static boolean[][][] visited;
	static int min;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		min = Integer.MAX_VALUE;
		map = new int[N][M];
		visited = new boolean[N][M][2];
		for(int i = 0; i < N; i++) {
			String input = scanner.nextLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
	}
	
	static void solution() {
		if(N == 1 && M == 1) {
			System.out.println(1);
			return;
		}
		bfs();
		min = min == Integer.MAX_VALUE ? -1 : min;
		System.out.println(min);
	}
	
	static void bfs() {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(0, 0, 1, false));
		while(!queue.isEmpty()) {
			Point cur_point = queue.poll();
			if(cur_point.x == N - 1 && cur_point.y == M - 1) {
				min = cur_point.count;
				return;
			}
			for(int i = 0; i < 4; i++) {
				int cx = cur_point.x + dx[i];
				int cy = cur_point.y + dy[i];
				if(cx >= 0 && cx < N && cy >= 0 && cy < M) {
					if(map[cx][cy] == 0) {
						if(!cur_point.destroy && !visited[cx][cy][0]) {
							queue.offer(new Point(cx, cy, cur_point.count + 1, false));
							visited[cx][cy][0] = true;
						} else if(cur_point.destroy && !visited[cx][cy][1]) {
							queue.offer(new Point(cx, cy, cur_point.count + 1, true));
							visited[cx][cy][1] = true;
						}
					} else if(map[cx][cy] == 1) {
						if(!cur_point.destroy) {
							queue.offer(new Point(cx, cy, cur_point.count + 1, true));
							visited[cx][cy][1] = true;
						}
					}
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
		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str;
		}
	}
	
	static class Point {
		int x, y, count;
		boolean destroy;
		public Point(int x, int y, int count, boolean destroy) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.destroy = destroy;
		}
	}
}
