package baekjun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun9663 {
	static StringBuilder sb = new StringBuilder();
	static int N, ans;
	static int[] col;
	static int[][] board;
	
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		board = new int[N][N];
		col = new int[N + 1];
	}
	
	static boolean attackable(int r1, int c1, int r2, int c2) {
		if(c1 == c2) return true;
		if(r1 + c1 == r2 + c2) return true;
		if(r1 - c1 == r2 - c2) return true;
		return false;
	}
	
	static void rec_func(int row) {
		if(row == N + 1) {
			ans++;
		} else {
			for(int c = 1; c <= N; c++) {
				boolean possible = true;
				for(int i = 1; i <= row - 1; i++) {
					if(attackable(row, c, i, col[i])) {
						possible = false;
						break;
					}
				} 
				if(possible) {
					col[row] = c;
					rec_func(row + 1);
					col[row] = 0;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		input();
		rec_func(1);
		System.out.println(ans);
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
