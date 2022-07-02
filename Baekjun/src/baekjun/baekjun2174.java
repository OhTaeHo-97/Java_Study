package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2174 {
	static int A, B, N, M;
	static int[][] map;
	static int[] dir = {0, 1, 2, 3};
	static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
	static Robot[] robots;
	
	static class Robot {
		int x, y, dir;
		public Robot(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		map = new int[B][A];
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		robots = new Robot[N + 1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			char tmp = st.nextToken().charAt(0);
			int d = 0;
			if(tmp == 'N') {
				d = 2;
			} else if(tmp == 'E') {
				d = 1;
			} else if(tmp == 'S') {
				d = 0;
			} else {
				d = 3;
			}
			map[a][b] = 1;
			robots[i] = new Robot(a, b, d);
		}
		
		String res = "";
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			char m = st.nextToken().charAt(0);
			int g = Integer.parseInt(st.nextToken());
			res = go(a, m, g);
			if(!res.equals("OK")) {
				System.out.println(res);
				break;
			}
		}
		if(res.equals("OK")) {
			System.out.println(res);
		}
	}
	
	private static String go(int n, char motion, int move) {
		Robot now = robots[n];
		if(motion == 'L') {
			robots[n].dir = (now.dir + move) % 4;
		} else if(motion == 'R') {
			robots[n].dir = (now.dir + move * 3) % 4;
		} else {
			int xx = now.x, yy = now.y;
			for(int i = 0; i < move; i++) {
				xx += dx[now.dir];
				yy += dy[now.dir];
				if(xx < 0 || xx >= B || yy < 0 || yy >= A) {
					return "Robot " + n + " crashes into the wall";
				}
				if(map[xx][yy] != 0) {
					return "Robot " + n + " crashes into robot " + map[xx][yy];
				}
			}
			robots[n] = new Robot(xx, yy, now.dir);
			map[now.x][now.y] = 0;
			map[xx][yy] = n;
		}
		return "OK";
	}
}