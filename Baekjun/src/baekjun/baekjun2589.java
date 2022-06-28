package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class baekjun2589 {
	static int row, col;
	static char[][] map;
	boolean[][] visited;
	public int bfs(int x, int y) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		Queue<Point> queue = new LinkedList<>();
		visited[x][y] = true;
		queue.offer(new Point(x, y, 0));
		int len = 0;
		while(!queue.isEmpty()) {
			Point cur_point = queue.poll();
			for(int i = 0; i < 4; i++) {
				int cx = cur_point.x + dx[i];
				int cy = cur_point.y + dy[i];
				if(cx >= 0 && cx < row && cy >= 0 && cy < col) {
					if(!visited[cx][cy] && map[cx][cy] == 'L') {
						visited[cx][cy] = true;
						queue.add(new Point(cx, cy, cur_point.len + 1));
						len = Math.max(len, cur_point.len + 1);
					}
				}
			}
		}
		return len;
	}
	
	public int getTime() {
		int max = Integer.MIN_VALUE;
		visited = new boolean[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(map[i][j] == 'L') {
					visited = new boolean[row][col];
					int len = bfs(i, j);
					max = Math.max(max, len);
				}
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		row = Integer.parseInt(input[0]);
		col = Integer.parseInt(input[1]);
		map = new char[row][col];
		for(int i = 0; i < row; i++) {
			String str = br.readLine();
			for(int j = 0; j < col; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		br.close();
		baekjun2589 b = new baekjun2589();
		bw.write(b.getTime() + "\n");
		bw.flush();
		bw.close();
	}
	
	public static class Point {
		int x, y, len;
		public Point(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}
}
