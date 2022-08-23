package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2580 {
	static int[][] sudoku;
	static void input() {
		Reader scanner = new Reader();
		sudoku = new int[9][9];
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				sudoku[i][j] = scanner.nextInt();
			}
		}
	}
	
	static void solution(int row, int col) {
		if(col == 9) {
			solution(row + 1, 0);
			return;
		}
		if(row == 9) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					System.out.print(sudoku[i][j] + " ");
				}
				System.out.println();
			}
			System.exit(0);
		}
		if(sudoku[row][col] == 0) {
			for(int i = 1; i <= 9; i++) {
				if(possible(row, col, i)) {
					sudoku[row][col] = i;
					solution(row, col + 1);
				}
			}
			sudoku[row][col] = 0;
			return;
		}
		solution(row, col + 1);
	}
	
	static boolean possible(int row, int col, int value) {
		if(isRowPossible(row, col, value) && isColumnPossible(row, col, value) && isSquarePossible(row, col, value))
			return true;
		return false;
	}
	
	static boolean isRowPossible(int row, int col, int value) {
		for(int i = 0; i < 9; i++) {
			if(sudoku[row][i] == value) return false;
		}
		return true;
	}
	
	static boolean isColumnPossible(int row, int col, int value) {
		for(int i = 0; i < 9; i++) {
			if(sudoku[i][col] == value) return false;
		}
		return true;
	}
	
	static boolean isSquarePossible(int row, int col, int value) {
		row = (row / 3) * 3;
		col = (col / 3) * 3;
		for(int i = row; i < row + 3; i++) {
			for(int j = col; j < col + 3; j++) {
				if(sudoku[i][j] == value) return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		input();
		solution(0, 0);
	}
	
	static class Reader {
		BufferedReader br;
		StringTokenizer st;
		public Reader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		String next() {
			while(st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
