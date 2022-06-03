package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class baekjun7562 {
//	static int[][] chessboard;
//	
//	static int bfs(Point start, Point end) {
//		Queue<Point> queue = new LinkedList<Point>();
//		queue.offer(start);
//		int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
//		int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
//		chessboard[start.x][start.y] = 1;
//		while(!queue.isEmpty()) {
//			Point cur_point = queue.poll();
//			if(cur_point.x == end.x && cur_point.y == end.y) {
//				return chessboard[end.x][end.y] - 1;
//			}
//			for(int i = 0; i < dx.length; i++) {
//				int cx = cur_point.x + dx[i];
//				int cy = cur_point.y + dy[i];
//				if(cx >= 0 && cx < chessboard.length && cy >= 0 && cy < chessboard.length && chessboard[cx][cy] == 0) {
//					chessboard[cx][cy] = chessboard[cur_point.x][cur_point.y] + 1;
//					queue.offer(new Point(cx, cy));
//				}
//			}
//		}
//		return -1;
//	}
	
	static int[][] chessboard;
	static boolean[][] visited;
	
	static int bfs(Point start, Point end) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(start);
		int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
		int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
		visited[start.x][start.y] = true;
		while(!queue.isEmpty()) {
			Point cur_point = queue.poll();
			if(cur_point.x == end.x && cur_point.y == end.y) {
				return chessboard[cur_point.x][cur_point.y];
			}
			for(int i = 0; i < dx.length; i++) {
				int cx = cur_point.x + dx[i];
				int cy = cur_point.y + dy[i];
				if(cx >= 0 && cx < chessboard.length && cy >= 0 && cy < chessboard.length && !visited[cx][cy]) {
					visited[cx][cy] = true;
					chessboard[cx][cy] = chessboard[cur_point.x][cur_point.y] + 1;
					queue.offer(new Point(cx, cy));
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_num = Integer.parseInt(br.readLine());
		for(int i = 0; i < test_num; i++) {
			int num = Integer.parseInt(br.readLine());
			visited = new boolean[num][num];
			chessboard = new int[num][num];
			String[] input = br.readLine().split(" ");
			Point start = new Point(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
			input = br.readLine().split(" ");
			Point end = new Point(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
			bw.write(bfs(start, end) + "\n");
		}
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}