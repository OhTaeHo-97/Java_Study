package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class baekjun16234 {
	static int[][] map;
	boolean[][] visited;
	int[] dx = {-1, 0, 1, 0};
	int[] dy = {0, -1, 0, 1};
	ArrayList<Point> list;
	static int l, r;
	
	public int bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<Point>();
		list = new ArrayList<Point>();
		queue.offer(new Point(x, y));
		list.add(new Point(x, y));
		visited[x][y] = true;
		int total = map[x][y];
		while(!queue.isEmpty()) {
			Point cur_point = queue.poll();
			for(int i = 0; i < 4; i++) {
				int cx = cur_point.x + dx[i];
				int cy = cur_point.y + dy[i];
				if(cx >= 0 && cx < map.length && cy >= 0 && cy < map[0].length && !visited[cx][cy]) {
					int difference = Math.abs(map[cur_point.x][cur_point.y] - map[cx][cy]);
					if(l <= difference && difference <= r) {
						queue.offer(new Point(cx, cy));
						list.add(new Point(cx, cy));
						total += map[cx][cy];
						visited[cx][cy] = true;
					}
				}
			}
		}
		return total;
	}
	
	public int getTimes() {
		int count = 0;
		while(true) {
			visited = new boolean[map.length][map[0].length];
			boolean canMove = false;
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map[i].length; j++) {
					if(!visited[i][j]) {
						int total = bfs(i, j);
						if(list.size() > 1) {
							int avg = total / list.size();
							for(Point p : list) {
								map[p.x][p.y] = avg;
							}
							canMove = true;
						}
					}
				}
			}
			if(!canMove) {
				return count;
			}
			count++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int num = Integer.parseInt(input[0]);
		l = Integer.parseInt(input[1]);
		r = Integer.parseInt(input[2]);
		map = new int[num][num];
		for(int i = 0; i < num; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < num; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		br.close();
		baekjun16234 b = new baekjun16234();
		bw.write(b.getTimes() + "\n");
		bw.flush();
		bw.close();
	}
}

class Point {
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}