package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class baekjun20208 {
	static int[][] map;
	static int h, maxNum = 0;
	static Point home;
	static ArrayList<Point> milks;
	boolean[] visited;
	public void findMaxNum(Point cur_point, int idx, int strength, int count) {
		visited[idx] = true;
		for(int i = 0; i < milks.size(); i++) {
			if(!visited[i]) {
				Point next_milk = milks.get(i);
				int distance = Math.abs(cur_point.x - next_milk.x) + Math.abs(cur_point.y - next_milk.y);
				if(distance <= strength) {
					findMaxNum(next_milk, i, strength - distance + h, count + 1);
				}
			}
		}
		int distance = Math.abs(home.x - cur_point.x) + Math.abs(home.y - cur_point.y);
		if(distance <= strength) {
			maxNum = Math.max(maxNum, count);
		}
		visited[idx] = false;
	}
	
	public void getMaxNum(int n, int init_strength) {
		visited = new boolean[milks.size()];
		for(int i = 0; i < milks.size(); i++) {
			Point milk = milks.get(i);
			int distance = Math.abs(home.x - milk.x) + Math.abs(home.y - milk.y);
			if(distance <= init_strength) {
				findMaxNum(milk, i, init_strength - distance + h, 1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		map = new int[n][n];
		int init_strength = Integer.parseInt(input[1]);
		h = Integer.parseInt(input[2]);
		milks = new ArrayList<>();
		int x = -1, y = -1;
		for(int i = 0; i < n; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				if(map[i][j] == 1) {
					home = new Point(i, j);
				} else if(map[i][j] == 2) {
					milks.add(new Point(i, j));
				}
			}
		}		
		br.close();
		baekjun20208 b = new baekjun20208();
		b.getMaxNum(n, init_strength);
		bw.write(maxNum + "\n");
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
