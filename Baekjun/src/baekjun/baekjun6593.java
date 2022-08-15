package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun6593 {
	// class 사용 없이 구현
//	static StringBuilder sb = new StringBuilder();
//	static char[][][] map;
//	static int l, r, c;
//	
//	static void input() {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		try {
//			while(true) {
//				st = new StringTokenizer(br.readLine());
//				l = Integer.parseInt(st.nextToken());
//				r = Integer.parseInt(st.nextToken());
//				c = Integer.parseInt(st.nextToken());
//				map = new char[l][r][c];
//				if(l == 0 && r == 0 && c == 0) break;
//				int x = 0, y = 0, z = 0;
//				for(int i = 0; i < l; i++) {
//					for(int j = 0; j < r; j++) {
//						String input = br.readLine();
//						for(int k = 0; k < c; k++) {
//							map[i][j][k] = input.charAt(k);
//							if(map[i][j][k] == 'S') {
//								x = j;
//								y = k;
//								z = i;
//							}
//						}
//					}
//					br.readLine();
//				}
//				bfs(z, x, y);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	static void bfs(int z, int x, int y) {
//		int[] dx = {0, 0, 1, -1, 0, 0};
//		int[] dy = {1, -1, 0, 0, 0, 0};
//		int[] dz = {0, 0, 0, 0, 1, -1};
//		boolean[][][] visited = new boolean[l][r][c];
//		Queue<int[]> queue = new LinkedList<>();
//		queue.offer(new int[]{z, x, y, 0});
//		visited[z][x][y] = true;
//		while(!queue.isEmpty()) {
//			int[] cur_position = queue.poll();
//			if(map[cur_position[0]][cur_position[1]][cur_position[2]] == 'E') {
//				sb.append("Escaped in " + cur_position[3] + " minute(s).\n");
//				return;
//			}
//			for(int i = 0; i < 6; i++) {
//				int cz = cur_position[0] + dz[i];
//				int cx = cur_position[1] + dx[i];
//				int cy = cur_position[2] + dy[i];
//				if(cz >= 0 && cz < l && cx >= 0 && cx < r && cy >= 0 && cy < c
//						&& map[cur_position[0]][cur_position[1]][cur_position[2]] != '#') {
//					if(!visited[cz][cx][cy]) {
//						visited[cz][cx][cy] = true;
//						queue.offer(new int[] {cz, cx, cy, cur_position[3] + 1});
//					}
//				}
//			}
//		}
//		sb.append("Trapped!\n");
//	}
//	
//	public static void main(String[] args) {
//		input();
//		System.out.println(sb.toString());
//	}
	
	// class 사용한 구현
	static StringBuilder sb = new StringBuilder();
	static char[][][] map;
	static boolean[][][] visited;
	static Point start, end;
	static int l, r, c;
	static int[] dx = {0, 0, 1, -1, 0, 0};
	static int[] dy = {1, -1, 0, 0, 0, 0};
	static int[] dz = {0, 0, 0, 0, 1, -1};
	
	static void input() {
		Reader scanner = new Reader();
		l = scanner.nextInt();
		r = scanner.nextInt();
		c = scanner.nextInt();
		while(true) {
			map = new char[l][r][c];
			visited = new boolean[l][r][c];
			if(l == 0 && r == 0 && c == 0) break;			
			for(int i = 0; i < l; i++) {
				for(int j = 0; j < r; j++) {
					String input = scanner.nextLine();
					for(int k = 0; k < c; k++) {
						map[i][j][k] = input.charAt(k);
						if(map[i][j][k] == 'S') {
							start = new Point(i, j, k);
						} else if(map[i][j][k] == 'E') {
							end = new Point(i, j, k);
						}
					}
				}
				scanner.nextLine();
			}
			bfs();
			l = scanner.nextInt();
			r = scanner.nextInt();
			c = scanner.nextInt();
		}
	}
	
	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(start);
		visited[start.page][start.row][start.column] = true;
		while(!queue.isEmpty()) {
			Point cur_position = queue.poll();
			if(map[cur_position.page][cur_position.row][cur_position.column] == 'E') {
				sb.append("Escaped in " + cur_position.time + " minute(s).\n");
				return;
			}
			for(int i = 0; i < 6; i++) {
				int cz = cur_position.page + dz[i];
				int cx = cur_position.row + dx[i];
				int cy = cur_position.column + dy[i];
				if(cz >= 0 && cz < l && cx >= 0 && cx < r && cy >= 0 && cy < c
						&& map[cur_position.page][cur_position.row][cur_position.column] != '#') {
					if(!visited[cz][cx][cy]) {
                        visited[cz][cx][cy] = true;
						queue.offer(new Point(cz, cx, cy, cur_position.time + 1));
					}
				}
			}
		}
		sb.append("Trapped!\n");
	}
	
	public static void main(String[] args) {
		input();
		System.out.println(sb.toString());
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
		int page, row, column, time;
		public Point(int page, int row, int column) {
			this.page = page;
			this.row = row;
			this.column = column;
			this.time = 0;
		}
		public Point(int page, int row, int column, int time) {
			this.page = page;
			this.row = row;
			this.column = column;
			this.time = time;
		}
	}
}
