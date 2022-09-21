package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun16236 {
	static int N, size = 2;
	static int[][] map, time;
	static Point shark;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		map = new int[N][N];
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				map[row][col] = scanner.nextInt();
				if(map[row][col] == 9) {
					map[row][col] = 0;
					shark = new Point(row, col);
				}
			}
		}
	}
	
	static void solution() {
		int eatNum = 0, answer = 0;
		Point fish;
		while(true) {
			fish = bfs(shark);
			if(fish.x != 20 && fish.y != 20) {
				eatNum++;
				answer += time[fish.x][fish.y];
				map[fish.x][fish.y] = 0;
				shark = fish;
				if(eatNum == size) {
					size++;
					eatNum = 0;
				}
			} else break;
 		}
		System.out.println(answer);
	}
	
	static Point bfs(Point curSharkLoc) {
		Queue<Point> sharkLoc = new LinkedList<Point>();
		sharkLoc.offer(curSharkLoc);
		time = new int[N][N];
		int min = Integer.MAX_VALUE;
		Point answer = new Point(20, 20);
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		while(!sharkLoc.isEmpty()) {
			Point loc = sharkLoc.poll();
			for(int index = 0; index < 4; index++) {
				int cx = loc.x + dx[index];
				int cy = loc.y + dy[index];
				if(isInMap(cx, cy) && canMove(cx, cy)) {
					time[cx][cy] = time[loc.x][loc.y] + 1;
					if(time[cx][cy] <= min) {
						if(map[cx][cy] != 0 && map[cx][cy] < size) {
							if(min > time[cx][cy]) {
								min = time[cx][cy];
								answer = new Point(cx, cy);
							} else if(answer.x > cx || ((answer.x == cx) && (answer.y > cy))) {
								answer = new Point(cx, cy);
							}
						}
					}
					sharkLoc.offer(new Point(cx, cy));
				}
			}
		}
		return answer;
	}
	
	static boolean isInMap(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < N) return true;
		return false;
	}
	
	static boolean canMove(int x, int y) {
		if(map[x][y] <= size && time[x][y] == 0) return true;
		return false;
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
	
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
