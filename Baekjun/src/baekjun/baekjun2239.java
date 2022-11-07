package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjun2239 {
	static StringBuilder sb = new StringBuilder();
	static final int SIZE = 9;
	static int[][] board;
	static LinkedList<int[]> zeros;
	static void input() {
		Reader scanner = new Reader();
		board = new int[SIZE][SIZE];
		zeros = new LinkedList<>();
		for(int row = 0; row < SIZE; row++) {
			String info = scanner.nextLine();
			for(int col = 0; col < SIZE; col++) {				
				board[row][col] = info.charAt(col) - '0';
				if(board[row][col] == 0) zeros.add(new int[] {row, col});
			}
		}
	}
	
	static void solution() {
		rec_func(0, zeros.size());
	}
	
	static void rec_func(int idx, int size) {
		if(size == 0) {
			for(int row = 0; row < SIZE; row++) {
				for(int col = 0; col < SIZE; col++) sb.append(board[row][col]);
				sb.append('\n');
			}
			System.out.println(sb);
			System.exit(0);
		}
		int[] cur = zeros.get(idx);
		int startRow = (cur[0] / 3) * 3, startCol = (cur[1] / 3) * 3;
		for(int num = 1; num <= 9; num++) {
			board[cur[0]][cur[1]] = num;
			if(checkRow(cur) && checkCol(cur) && checkBoard(new int[] {startRow, startCol})) {					
				rec_func(idx + 1, size - 1);
			}
			board[cur[0]][cur[1]] = 0;
		}
	}
	
	static boolean checkRow(int[] position) {
		boolean[] nums = new boolean[10];
		for(int col = 0; col < SIZE; col++) {
			if(board[position[0]][col] != 0) {
				if(nums[board[position[0]][col]]) return false;
				nums[board[position[0]][col]] = true;
			}
		}
		return true;
	}
	
	static boolean checkCol(int[] position) {
		boolean[] nums = new boolean[10];
		for(int row = 0; row < SIZE; row++) {
			if(board[row][position[1]] != 0) {
				if(nums[board[row][position[1]]]) return false;
				nums[board[row][position[1]]] = true;
			}
		}
		return true;
	}
	
	static boolean checkBoard(int[] position) {
		boolean[] nums = new boolean[10];
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				if(board[position[0] + row][position[1] + col] != 0) {
					if(nums[board[position[0] + row][position[1] + col]]) return false;
					nums[board[position[0] + row][position[1] + col]] = true;
				}
			}
		}
		return true;
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
		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
