package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class baekjun3190 {
	static int[][] board;
	public int getEndTime(HashMap<Integer, Character> turns) {
		Deque<Point> snake = new ArrayDeque<Point>();
		board[0][0] = 1;
		snake.add(new Point(0, 0));
		int direction = 0, time = 0;
		while(true) {
			Point cur_head = snake.peekLast();
			int x = cur_head.x;
			int y = cur_head.y;
			if(direction == 0) { // 우측 방향
				if(y + 1 >= board.length) {
					time++;
					break;
				} else if(board[x][y + 1] == 1) {
					time++;
					break;
				} else {
					if(board[x][y + 1] == 2) {
						board[x][y + 1] = 1;
						snake.addLast(new Point(x, y + 1));
					} else {
						board[x][y + 1] = 1;
						snake.addLast(new Point(x, y + 1));
						Point remove_point = snake.pollFirst();
						board[remove_point.x][remove_point.y] = 0;
					}
				}
			} else if(direction == 1) { // 아래 방향
				if(x + 1 >= board.length) {
					time++;
					break;
				} else if(board[x + 1][y] == 1) {
					time++;
					break;
				} else {
					if(board[x + 1][y] == 2) {
						board[x + 1][y] = 1;
						snake.addLast(new Point(x + 1, y));
					} else {
						board[x + 1][y] = 1;
						snake.addLast(new Point(x + 1, y));
						Point remove_point = snake.pollFirst();
						board[remove_point.x][remove_point.y] = 0;
					}
				}
			} else if(direction == 2) { // 왼쪽 방향
				if(y - 1 < 0) {
					time++;
					break;
				} else if(board[x][y - 1] == 1) {
					time++;
					break;
				} else {
					if(board[x][y - 1] == 2) {
						board[x][y - 1] = 1;
						snake.addLast(new Point(x, y - 1));
					} else {
						board[x][y - 1] = 1;
						snake.addLast(new Point(x, y - 1));
						Point remove_point = snake.pollFirst();
						board[remove_point.x][remove_point.y] = 0;
					}
				}
			} else if(direction == 3) { // 위 방향
				if(x - 1 < 0) {
					time++;
					break;
				} else if(board[x - 1][y] == 1) {
					time++;
					break;
				} else {
					if(board[x - 1][y] == 2) {
						board[x - 1][y] = 1;
						snake.addLast(new Point(x - 1, y));
					} else {
						board[x - 1][y] = 1;
						snake.addLast(new Point(x - 1, y));
						Point remove_point = snake.pollFirst();
						board[remove_point.x][remove_point.y] = 0;
					}
				}
			}
			time++;
			if(turns.containsKey(time)) {
				switch(turns.get(time)) {
					case 'D':
						direction++;
						if(direction == 4) {
							direction = 0;
						}
						break;
					case 'L':
						if(direction == 0) {
							direction = 3;
						} else if(direction == 1) {
							direction = 0;
						} else if(direction == 2) {
							direction = 1;
						} else if(direction == 3) {
							direction = 2;
						}
						break;
					default:
						break;
				}
			}
		}
		return time;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int board_size = Integer.parseInt(br.readLine());
		int apple_num = Integer.parseInt(br.readLine());
		board = new int[board_size][board_size];
		for(int i = 0; i < apple_num; i++) {
			String[] input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]) - 1;
			int y = Integer.parseInt(input[1]) - 1;
			board[x][y] = 2;
		}
		int turn_num = Integer.parseInt(br.readLine());
		HashMap<Integer, Character> turns = new HashMap<>();
		for(int i = 0; i < turn_num; i++) {
			String[] input = br.readLine().split(" ");
			turns.put(Integer.parseInt(input[0]), input[1].charAt(0));
		}
		br.close();
		baekjun3190 b = new baekjun3190();
		bw.write(b.getEndTime(turns) + "\n");
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
