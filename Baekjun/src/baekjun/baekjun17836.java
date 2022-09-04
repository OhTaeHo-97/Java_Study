package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun17836 {
	static int N, M, T;
	static int[][] map;
	static boolean[][][] visited;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		T = scanner.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M][2];
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) map[row][col] = scanner.nextInt();
		}
	}
	
	static void solution() {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		int min = Integer.MAX_VALUE;
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(0, 0, 0, false));
		while(!queue.isEmpty()) {
			Point cur_point = queue.poll();
			if(cur_point.time > T) {
				break;
			}
			if(cur_point.x == N - 1 && cur_point.y == M - 1) {
				min = Math.min(min, cur_point.time);
				break;
			}
			for(int i = 0; i < 4; i++) {
				int cx = cur_point.x + dx[i];
				int cy = cur_point.y + dy[i];
				if(cx >= 0 && cx < N && cy >= 0 && cy < M) {
					if(map[cx][cy] == 0) {
						if(!cur_point.hasGram && !visited[cx][cy][0]) {
							queue.offer(new Point(cx, cy, cur_point.time + 1, cur_point.hasGram));
							visited[cx][cy][0] = true;
						} else if(cur_point.hasGram && !visited[cx][cy][1]) {
							queue.offer(new Point(cx, cy, cur_point.time + 1, cur_point.hasGram));
							visited[cx][cy][1] = true;
						}
					} else if(map[cx][cy] == 2 && !visited[cx][cy][0] && !visited[cx][cy][1]) {
						queue.offer(new Point(cx, cy, cur_point.time + 1, true));
						visited[cx][cy][0] = true;
						visited[cx][cy][1] = true;
					} else if(map[cx][cy] == 1) {
						if(cur_point.hasGram && !visited[cx][cy][1]) {							
							queue.offer(new Point(cx, cy, cur_point.time + 1, cur_point.hasGram));
							visited[cx][cy][1] = true;
						}
					}
				}
			}
		}
		System.out.println(min == Integer.MAX_VALUE ? "Fail" : min);
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
	
	static class Point {
		int x, y, time;
		boolean hasGram;
		public Point(int x, int y, int time, boolean hasGram) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.hasGram = hasGram;
		}
	}
}
