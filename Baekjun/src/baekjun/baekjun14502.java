package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class baekjun14502 {
	static int[][] map;
	static int maxArea;
	public void findMaxArea(int wallNum) {
		if(wallNum == 3) {
			bfs();
			return;
		}
		// 벽 세우기
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					findMaxArea(wallNum + 1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	public void bfs() {
		Queue<Point> queue = new LinkedList<Point>();
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 2) {
					queue.offer(new Point(i, j));
				}
			}
		}
		int[][] copy = new int[map.length][map[0].length];
		for(int i = 0; i < map.length; i++) {
			copy[i] = map[i].clone();
		}
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		while(!queue.isEmpty()) {
			Point cur_point = queue.poll();
			for(int i = 0; i < 4; i++) {
				int cx = cur_point.x + dx[i];
				int cy = cur_point.y + dy[i];
				if(cx >= 0 && cx < map.length && cy >= 0 && cy < map[0].length) {
					if(copy[cx][cy] == 0) {
						copy[cx][cy] = 2;
						queue.offer(new Point(cx, cy));
					}
				}
			}
		}
		findSafeArea(copy);
	}
	
	public void findSafeArea(int[][] copy) {
		int num = 0;
		for(int i = 0; i < copy.length; i++) {
			for(int j = 0; j < copy[i].length; j++) {
				if(copy[i][j] == 0) {
					num++;
				}
			}
		}
		maxArea = maxArea < num ? num : maxArea;
	}
	
	public void getMaxArea() {
		maxArea = 0;
		findMaxArea(0);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int row = Integer.parseInt(input[0]);
		int col = Integer.parseInt(input[1]);
		map = new int[row][col];
		for(int i = 0; i < row; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		br.close();
		baekjun14502 b = new baekjun14502();
		b.getMaxArea();
		bw.write(maxArea + "\n");
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
