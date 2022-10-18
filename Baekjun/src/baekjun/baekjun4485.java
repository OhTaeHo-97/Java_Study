package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun4485 {
	static StringBuilder sb = new StringBuilder();
	static int N, min;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	static void input() {
		Reader scanner = new Reader();
		int cnt = 1;
		while(true) {
			N = scanner.nextInt();
			if(N == 0) break;
			map = new int[N][N];
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < N; col++) map[row][col] = scanner.nextInt();
			}
			min = Integer.MAX_VALUE;
			solution(cnt);
			cnt++;
		}
	}
	
	static void solution(int cnt) {
		bfs();
		sb.append("Problem ").append(cnt).append(": ").append(min).append('\n');
	}
	
	static void bfs() {
		Queue<int[]> loc = new LinkedList<int[]>();
		int[][] cost = new int[N][N];
		for(int row = 0; row < N; row++) Arrays.fill(cost[row], Integer.MAX_VALUE);
		loc.offer(new int[] {0, 0});
		cost[0][0] = map[0][0];
		while(!loc.isEmpty()) {
			int[] curLoc = loc.poll();
			if(curLoc[0] == N - 1 && curLoc[1] == N - 1) {
				min = Math.min(min, cost[curLoc[0]][curLoc[1]]);
			}
			for(int dir = 0; dir < 4; dir++) {
				int cx = curLoc[0] + dx[dir];
				int cy = curLoc[1] + dy[dir];
				if(cx >= 0 && cx < N && cy >= 0 && cy < N) {
					if(cost[cx][cy] > cost[curLoc[0]][curLoc[1]] + map[cx][cy]) {
						cost[cx][cy] = cost[curLoc[0]][curLoc[1]] + map[cx][cy];
						loc.offer(new int[] {cx, cy});
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		input();
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
	}
}