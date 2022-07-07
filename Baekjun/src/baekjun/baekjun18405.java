package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class baekjun18405 {
	static int[][] map;
	static boolean[][] visited;
	static int second;
	static ArrayList<Point>[] virus_points;
	
	public void findVirusType() {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		Queue<Point> queue = new LinkedList<Point>();
		for(int i = 1; i < virus_points.length; i++) {
			for(int j = 0; j < virus_points[i].size(); j++) {
				queue.add(virus_points[i].get(j));
			}
		}
		int len = queue.size();
		for(int i = 0; i < second; i++) {
			for(int j = 0; j < len; j++) {
				Point cur_point = queue.poll();
				for(int l = 0; l < 4; l++) {
					int cx = cur_point.x + dx[l];
					int cy = cur_point.y + dy[l];
					if(cx >= 0 && cx < map.length && cy >= 0 && cy < map.length) {
						if(!visited[cx][cy] && map[cx][cy] == 0) {
							map[cx][cy] = map[cur_point.x][cur_point.y];
							visited[cx][cy] = true;
							queue.add(new Point(cx, cy));
						}
					}
				}
			}
			len = queue.size();
		}
	}
   
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
      	int size = Integer.parseInt(input[0]);
      	int type = Integer.parseInt(input[1]);
      	virus_points = new ArrayList[type + 1];
      	for(int i = 0; i <= type; i++) {
      		virus_points[i] = new ArrayList<Point>();
      	}
      	map = new int[size][size];
      	visited = new boolean[size][size];
      	for(int i = 0; i < size; i++) {
      		input = br.readLine().split(" ");
      		for(int j = 0; j < size; j++) {
      			map[i][j] = Integer.parseInt(input[j]);
      			if(map[i][j] != 0) {
      				visited[i][j] = true;
      				virus_points[map[i][j]].add(new Point(i, j));
      			}
      		}
      	}
      	input = br.readLine().split(" ");
      	br.close();
      	second = Integer.parseInt(input[0]);
      	int obj_x = Integer.parseInt(input[1]);
      	int obj_y = Integer.parseInt(input[2]);
      	baekjun18405 b = new baekjun18405();
      	b.findVirusType();
      	bw.write(map[obj_x - 1][obj_y - 1] + "\n");
      	bw.flush();
      	bw.close();
	}
   
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
