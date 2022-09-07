package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun3055 {
	static int R, C;
	static char[][] map, water_map;
	static boolean[][] visited, water_visited;
	static Point hedgehog;
	static Queue<Point> water;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static void input() {
		Reader scanner = new Reader();
		R = scanner.nextInt();
		C = scanner.nextInt();
		map = new char[R][C];
		water_map = new char[R][C];
		visited = new boolean[R][C];
		water_visited = new boolean[R][C];
		water = new LinkedList<Point>();
		for(int i = 0; i < R; i++) {
			String info = scanner.nextLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = info.charAt(j);
				if(map[i][j] == 'S') hedgehog = new Point(i, j, 0);
				else if(map[i][j] == '*') {
					water.offer(new Point(i, j, 0));
					water_map[i][j] = '*';
					water_visited[i][j] = true;
				}
			}
 		}
	}
	
	static void solution() {
		Queue<Point> hedgehog_loc = new LinkedList<Point>();
		visited[hedgehog.x][hedgehog.y] = true;
		hedgehog_loc.offer(hedgehog);
		int time = -1;
		while(!hedgehog_loc.isEmpty()) {
			Point cur_point = hedgehog_loc.poll();
			if(map[cur_point.x][cur_point.y] == 'D') {
				System.out.println(cur_point.time);
				return;
			}
			if(cur_point.time > time) {
				extend();
				time++;
			}
			for(int i = 0; i < 4; i++) {
				int cx = cur_point.x + dx[i];
				int cy = cur_point.y + dy[i];
				if(cx >= 0 && cx < R && cy >= 0 && cy < C && !visited[cx][cy]) {
					if(water_map[cx][cy] != '*' && map[cx][cy] != 'X') {
						visited[cx][cy] = true;
						hedgehog_loc.offer(new Point(cx, cy, cur_point.time + 1));
					}
				}
			}
		}
		System.out.println("KAKTUS");
	}
	
	static void extend() {
		int cur_time = 0;
		if(!water.isEmpty()) cur_time = water.peek().time;
		while(!water.isEmpty()) {
			Point cur_point;
			if(water.peek().time > cur_time)
				break;
			cur_point = water.poll();
			for(int i = 0; i < 4; i++) {
				int cx = cur_point.x + dx[i];
				int cy = cur_point.y + dy[i];
				if(cx >= 0 && cx < R && cy >= 0 && cy < C && !water_visited[cx][cy]) {
					if(map[cx][cy] != 'D' && map[cx][cy] != 'X') {
						water.offer(new Point(cx, cy, cur_time + 1));
						water_map[cx][cy] = '*';
						water_visited[cx][cy] = true;
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
				} catch(IOException e) {
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
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
	
	static class Point {
		int x, y, time;
		public Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}
