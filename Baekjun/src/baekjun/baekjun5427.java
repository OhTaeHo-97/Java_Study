package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun5427 {
	static StringBuilder sb = new StringBuilder();
	static Reader scanner = new Reader();
	static int r, c, T;
	static char[][] map;
	static int[] start, dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	static LinkedList<int[]> fires;
	static int[][] time, fireTime;
	static void input() {
		c = scanner.nextInt();
		r = scanner.nextInt();
		map = new char[r][c];
		time = new int[r][c];
		fireTime = new int[r][c];
		fires = new LinkedList<>();
		for(int row = 0; row < r; row++) {
			Arrays.fill(time[row], Integer.MAX_VALUE);
			Arrays.fill(fireTime[row], Integer.MAX_VALUE);
			String input = scanner.nextLine();
			for(int col = 0; col < c; col++) {
				map[row][col] = input.charAt(col);
				if(map[row][col] == '@') {
					map[row][col] = '.';
					time[row][col] = 0;
					start = new int[] {row, col};
				}
				else if(map[row][col] == '*') {
					fireTime[row][col] = 0;
					fires.add(new int[] {row, col});
				}
			}
		}
	}
	
	static void solution() {
		Queue<int[]> loc = new LinkedList<>();
		loc.offer(start);
		int t = 0;
		while(!loc.isEmpty()) {
			int[] cur = loc.poll();
			if(time[cur[0]][cur[1]] == t) {
				spreadFire();
				t++;
			}
			for(int dir = 0; dir < 4; dir++) {
				int cx = cur[0] + dx[dir], cy = cur[1] + dy[dir];
				if(cx < 0 || cx >= r || cy < 0 || cy >= c) {
					sb.append(time[cur[0]][cur[1]] + 1).append('\n');
					return;
				}
				if(map[cx][cy] == '.') {
					if(time[cx][cy] > (time[cur[0]][cur[1]] + 1)) {
						time[cx][cy] = time[cur[0]][cur[1]] + 1;
						loc.offer(new int[] {cx, cy});
					}
				}
			}
		}
		sb.append("IMPOSSIBLE").append('\n');
	}
	
	static void spreadFire() {
		int size = fires.size();
		if(size == 0) return;
		int t = fireTime[fires.getFirst()[0]][fires.getFirst()[1]];
		Queue<int[]> fireLoc = new LinkedList<int[]>();
		for(int count = 0; count < size; count++) {
			fireLoc.offer(fires.getFirst());
			fires.remove(0);
 		}
		while(!fireLoc.isEmpty()) {
			int[] cur = fireLoc.poll();
			if(fireTime[cur[0]][cur[1]] > t) return;
			for(int dir = 0; dir < 4; dir++) {
				int cx = cur[0] + dx[dir], cy = cur[1] + dy[dir];
				if(cx >= 0 && cx < r && cy >= 0 && cy < c) {
					if(map[cx][cy] == '.' && fireTime[cx][cy] > fireTime[cur[0]][cur[1]] + 1) {
						map[cx][cy] = '*';
						fireTime[cx][cy] = fireTime[cur[0]][cur[1]] + 1;
						fireLoc.offer(new int[] {cx, cy});
						fires.add(new int[] {cx, cy});
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		T = scanner.nextInt();
		while(T-- > 0) {
			input();
			solution();
		}
		System.out.println(sb);
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
