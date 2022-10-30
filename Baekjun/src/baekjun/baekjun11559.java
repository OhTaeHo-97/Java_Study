package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun11559 {
	static final HashMap<Character, Integer> colorToInt = new HashMap<>() {{
		put('R', 0);
		put('G', 1);
		put('B', 2);
		put('Y', 3);
	}};
	static int N = 12, M = 6, size;
	static char[][] field;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0}, dy= {0, -1, 0, 1};
	static ArrayList<int[]>[] loc;
	static void input() {
		Reader scanner = new Reader();
		field = new char[N][M];
		for(int row = 0; row < N; row++) {
			String info = scanner.nextLine();
			for(int col = 0; col < M; col++) field[row][col] = info.charAt(col);
		}
		loc = new ArrayList[4];
		for(int index = 0; index < 4; index++) loc[index] = new ArrayList<>();
	}
	
	static void solution() {
		visited = new boolean[N][M];
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				if(field[row][col] != '.') {
					size = 0;
					dfs(row, col);
				}
				
			}
		}
	}
	
	static void dfs(int x, int y) {
		if(size >= 4) return;
		size++;
		visited[x][y] = true;
		for(int dir = 0; dir < 4; dir++) {
			int cx = x + dx[dir];
			int cy = y + dy[dir];
			if(isInField(cx, cy)) {
				if(!visited[cx][cy] && field[x][y] == field[cx][cy])
					dfs(cx, cy);
			}
		}
	}
	
	static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {x, y});
		boolean[][] visit = new boolean[N][M];
		visit[x][y] = true;
		loc[colorToInt.get(field[x][y])].add(new int[] {x, y});
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			for(int dir = 0; dir < 4; dir++) {
				int cx = cur[0] + dx[dir];
				int cy = cur[1] + dy[dir];
				if(isInField(cx, cy)) {
					if(!visit[cx][cy] && field[cur[0]][cur[1]] == field[cx][cy]) {
						visit[cx][cy] = true;
						queue.offer(new int[] {cx, cy});
					}
				}
			}
		}
	}
	
	static boolean isInField(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < M) return true;
		return false;
	}
	
	public static void main(String[] args) {
		
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
