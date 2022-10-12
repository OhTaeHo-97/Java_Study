package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun2146 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static HashMap<Integer, ArrayList<int[]>> edges;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];
		edges = new HashMap<>();
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				int temp = scanner.nextInt();
				if(temp == 0) map[row][col] = 0;
				else if(temp == 1) map[row][col] = -1;
			}
		}
	}
	
	static void solution() {
		int landNum = 0;
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				if(!visited[row][col] && map[row][col] == -1) {
					landNum++;
					dfs(row, col, landNum);
				}
			}
		}
		for(int land = 1; land <= landNum; land++) edges.put(land, new ArrayList<int[]>());
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				if(map[row][col] != 0) {
					for(int dir = 0; dir < 4; dir++) {
						int cx = row + dx[dir];
						int cy = col + dy[dir];
						if(cx >= 0 && cx < N && cy >= 0 && cy < N) {
							if(map[cx][cy] == 0) {
								edges.get(map[row][col]).add(new int[] {row, col});
								break;
							}
						}
					}
				}
			}
		}
		int answer = Integer.MAX_VALUE;
		for(int land = 1; land <= landNum; land++) {
			for(int[] pos : edges.get(land)) {
				int dist = bfs(pos[0], pos[1], map[pos[0]][pos[1]]);
				answer = Math.min(answer, dist);
			}
		}
		System.out.println(answer);
	}
	
	static int bfs(int x, int y, int land) {
		Queue<int[]> queue = new LinkedList<>();
		int[][] dist = new int[N][N];
		for(int row = 0; row < N; row++) Arrays.fill(dist[row], Integer.MAX_VALUE);
		queue.add(new int[] {x, y});
		dist[x][y] = 0;
		int answer = Integer.MAX_VALUE;
		while(!queue.isEmpty()) {
			int[] pos = queue.poll();
			if(map[pos[0]][pos[1]] != 0 && map[pos[0]][pos[1]] != land) {
				answer = Math.min(answer, dist[pos[0]][pos[1]]) - 1;
				break;
			}
			for(int dir = 0; dir < 4; dir++) {
				int cx = pos[0] + dx[dir];
				int cy = pos[1] + dy[dir];
				if(cx >= 0 && cx < N && cy >= 0 && cy < N && dist[cx][cy] == Integer.MAX_VALUE) {
					if(map[cx][cy] != land) {
						dist[cx][cy] = dist[pos[0]][pos[1]] + 1;
						queue.offer(new int[] {cx, cy});
					}
				}
			}
		}
		return answer;
	}
	
	static void dfs(int x, int y, int landNum) {
		visited[x][y] = true;
		map[x][y] = landNum;
		for(int dir = 0; dir < 4; dir++) {
			int cx = x + dx[dir];
			int cy = y + dy[dir];
			if(cx >= 0 && cx < N && cy >= 0 && cy < N && !visited[cx][cy]) {
				if(map[cx][cy] == -1) dfs(cx, cy, landNum);
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
	}
}
