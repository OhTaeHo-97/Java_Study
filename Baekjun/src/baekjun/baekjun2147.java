package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class baekjun2147 {
	static int[][] map;
	static char[] direction;
	static ArrayList<String> commands;
	public String simulate(HashMap<Integer, Point> location) {
		for(int i = 0; i < commands.size(); i++) {
			String command = commands.get(i);
			String[] input = command.split(" ");
			int robot = Integer.parseInt(input[0]);
			char c = input[1].charAt(0);
			int repeat_num = Integer.parseInt(input[2]);
			Point cur_location = location.get(robot);
			int x = cur_location.x;
			int y = cur_location.y;
			switch(direction[robot]) {
				case 'E':
					if(c == 'F') {
						y += repeat_num;
					} else if(c == 'R') {
						direction[robot] = 'S';
					} else if(c == 'L') {
						direction[robot] = 'N';
					}
					break;
				case 'W':
					if(c == 'F') {
						y -= repeat_num;
					} else if(c == 'R') {
						direction[robot] = 'N';
					} else if(c == 'L') {
						direction[robot] = 'S';
					}
					break;
				case 'N':
					if(c == 'F') {
						x -= repeat_num;
					} else if(c == 'R') {
						direction[robot] = 'R';
					} else if(c == 'L') {
						direction[robot] = 'L';
					}
					break;
				case 'S':
					if(c == 'F') {
						x += repeat_num;
					} else if(c == 'R') {
						direction[robot] = 'W';
					} else if(c == 'L') {
						direction[robot] = 'E';
					}
					break;
				default:
					break;
			}
			int prev_x = cur_location.x;
			int prev_y = cur_location.y;
			if(prev_x == x) {
				for(int j = 1; j < direction.length; j++) {
					if(j == robot)
						continue;
					int jth_x = location.get(j).x;
					int jth_y = location.get(j).y;
					if(jth_x != x) {
						continue;
					}
					int max = Math.max(prev_y, y);
					int min = Math.min(prev_y, y);
					if(jth_y >= min && jth_y <= max) {
						return "Robot " + robot + " crashes into robot " + j;
					}
					
				}
			} else if(prev_y == y) {
				for(int j = 1; j < direction.length; j++) {
					if(j == robot)
						continue;
					int jth_x = location.get(j).x;
					int jth_y = location.get(j).y;
					if(jth_y != y) {
						continue;
					}
					int max = Math.max(prev_x, x);
					int min = Math.min(prev_x, x);
					if(jth_x >= min && jth_x <= max) {
						return "Robot " + robot + " crashes into robot " + j;
					}
				}
			}
			if(x < 0 || x >= map.length || y < 0 || y >= map[0].length) {
				return "Robot " + robot + " crashes into the wall";
			}
			location.put(robot, new Point(x, y));
		}
		return "OK";
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int col = Integer.parseInt(input[0]);
		int row = Integer.parseInt(input[1]);
		map = new int[row][col];
		input = br.readLine().split(" ");
		int robot_num = Integer.parseInt(input[0]);
		int command_num = Integer.parseInt(input[1]);
		direction = new char[robot_num + 1];
		commands = new ArrayList<String>();
		HashMap<Integer, Point> location = new HashMap<>();
		for(int i = 1; i <= robot_num; i++) {
			input = br.readLine().split(" ");
			int y = Integer.parseInt(input[0]) - 1;
			int x = row - Integer.parseInt(input[1]);
			direction[i] = input[2].charAt(0);
			location.put(i, new Point(x, y));
			map[x][y] = i;
		}
		for(int i = 0; i < command_num; i++) {
			commands.add(br.readLine());
		}
		br.close();
		baekjun2147 b = new baekjun2147();
		bw.write(b.simulate(location) + "\n");
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
