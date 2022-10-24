package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun17142 {
	static int N, M, emptyNum, minTime = Integer.MAX_VALUE;
	static int[][] map;
	static ArrayList<Point> viruses;
	static LinkedList<Point> active;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		emptyNum = 0;
		map = new int[N][N];
		viruses = new ArrayList<>();
		active = new LinkedList<>();
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				map[row][col] = scanner.nextInt();
				if(map[row][col] == 0) emptyNum++;
				else if(map[row][col] == 2) viruses.add(new Point(row, col, 0));
				
			}
		}
	}
	
	static void solution() {
		if(emptyNum == 0) {
			System.out.println(0);
			return;
		}
		findMinTime(0, 0);
		System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
	}
	
	static void findMinTime(int idx, int depth) {
		if(depth == M) {
			int[][] copy = new int[N][N];
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < N; col++) copy[row][col] = map[row][col];
			}
			bfs(copy);
			return;
		}
		for(int index = idx; index < viruses.size(); index++) {
			Point cur = viruses.get(index);
			active.add(cur);
			findMinTime(index + 1, depth + 1);
			active.remove(active.size() - 1);
		}
	}
	
	static void bfs(int[][] copy) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean[][] visited = new boolean[N][N];
		for(Point p : active) queue.offer(p);
		int num = emptyNum;
		int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			for(int dir = 0; dir < 4; dir++) {
				int cx = cur.x + dx[dir], cy = cur.y + dy[dir];
				if(cx >= 0 && cx < N && cy >= 0 && cy < N && !visited[cx][cy]) {
					if(copy[cx][cy] == 0) {
						num--;
						visited[cx][cy] = true;
						copy[cx][cy] = 2;
						queue.offer(new Point(cx, cy, cur.time + 1));
					}
				}
			}
			if(num == 0) {
				minTime = Math.min(minTime, cur.time + 1);
				return;
			}
		}
	}
	
	public static void main(String[] args) {
		input();
		solution();
	}
	
	static class Point {
		int x, y, time;
		public Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
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
