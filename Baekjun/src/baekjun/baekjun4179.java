package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun4179 {
	static int R, C;
	static char[][] map;
	static int[] start, dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	static int[][] times;
	static LinkedList<int[]> fires;
	static void input() {
		Reader scanner = new Reader();
		R = scanner.nextInt();
		C = scanner.nextInt();
		map = new char[R][C];
		times = new int[R][C];
		fires = new LinkedList<>();
		for(int row = 0; row < R; row++) {
			String info = scanner.nextLine();
			for(int col = 0; col < C; col++) {
				map[row][col] = info.charAt(col);
				times[row][col] = Integer.MAX_VALUE;
				if(map[row][col] == 'J') {
					start = new int[] {row, col};
					map[row][col] = '.';
					times[row][col] = 0;
				} else if(map[row][col] == 'F') fires.add(new int[] {row, col});	
			}
		}
	}
	
	static void solution() {
		Queue<int[]> loc = new LinkedList<>();
		loc.offer(start);
		int time = 0;
		while(!loc.isEmpty()) {
			int[] cur = loc.poll();
			if(times[cur[0]][cur[1]] == time) {
				spreadFire();
				time++;
			}
			for(int dir = 0; dir < 4; dir++) {
				int cx = cur[0] + dx[dir], cy = cur[1] + dy[dir];
				if(cx < 0 || cx >= R ||  cy < 0 || cy >= C) {
					System.out.println(times[cur[0]][cur[1]] + 1);
					return;
				}
				if(isInMap(cx, cy)) {
					if(map[cx][cy] == '.' && times[cx][cy] > times[cur[0]][cur[1]] + 1) {
						times[cx][cy] = times[cur[0]][cur[1]] + 1;
						loc.offer(new int[] {cx, cy});
					}
				}
			}
		}
		System.out.println("IMPOSSIBLE");
	}
	
	static void spreadFire() {
		int size = fires.size();
		for(int count = 0; count < size; count++) {
			int[] cur = fires.get(0);
			fires.remove(0);
			for(int dir = 0; dir < 4; dir++) {
				int cx = cur[0] + dx[dir], cy = cur[1] + dy[dir];
				if(isInMap(cx, cy)) {
					if(map[cx][cy] == '.') {
						fires.add(new int[] {cx, cy});
						map[cx][cy] = 'F';
					}
				}
			}
		}
	}
   
	static boolean isInMap(int x, int y) {
		if(x >= 0 && x < R && y >= 0 && y < C) return true;
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
}
