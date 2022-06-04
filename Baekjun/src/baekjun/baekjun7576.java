package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class baekjun7576 {
//	static int[][] map;
//	static boolean[][] visited;
//	static int[][] days;
//	static Queue<Point> queue;
//	public static int bfs() {
//		int max = 0;
//		int[] dx = {-1, 0, 1, 0};
//		int[] dy = {0, -1, 0, 1};
//		while(!queue.isEmpty()) {
//			Point cur_point = queue.poll();
//			visited[cur_point.x][cur_point.y] = true;
//			for(int i = 0; i < 4; i++) {
//				int cx = cur_point.x + dx[i];
//				int cy = cur_point.y + dy[i];
//				if(cx >= 0 && cx < map.length && cy >= 0 && cy < map[0].length) {
//					if(!visited[cx][cy] && map[cx][cy] == 0) {
//						visited[cx][cy] = true;
//						queue.offer(new Point(cx, cy));
//						map[cx][cy] = 1;
//						days[cx][cy] = Math.max(days[cx][cy], days[cur_point.x][cur_point.y] + 1);
//						max = Math.max(max, days[cx][cy]);
//					}
//				}
//			}
//		}
//		for(int i = 0; i < map.length; i++) {
//			if(Arrays.stream(map[i]).boxed().collect(Collectors.toList()).contains(0)) {
//				return -1;
//			}
//		}
//		return max;
//	}
	
	static int[][] map;
	static Queue<Point> queue;
	public static int bfs() {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		while(!queue.isEmpty()) {
			Point cur_point = queue.poll();
			for(int i = 0; i < 4; i++) {
				int cx = cur_point.x + dx[i];
				int cy = cur_point.y + dy[i];
				if(cx >= 0 && cx < map.length && cy >= 0 && cy < map[0].length) {
					if(map[cx][cy] == 0) {
						queue.offer(new Point(cx, cy));
						map[cx][cy] = map[cur_point.x][cur_point.y] + 1;
					}
				}
			}
		}
		int result = Integer.MIN_VALUE;
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 0) {
					return -1;
				}
				result = Math.max(result, map[i][j]);
			}
		}
		if(result == 1) {
			return 0;
		} else {
			return result - 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int col = Integer.parseInt(input[0]);
		int row = Integer.parseInt(input[1]);
		map = new int[row][col];
		queue = new LinkedList<Point>();
		for(int i = 0; i < row; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				if(map[i][j] == 1) {
					queue.offer(new Point(i, j));
				}
			}
		}
		br.close();
		bw.write(bfs() + "\n");
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
