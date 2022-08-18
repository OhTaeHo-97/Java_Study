package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2447 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static char[][] stars;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		stars = new char[N][N];
	}
	
	static void rec_func(int x, int y, int cur_size, boolean blank) {
		if(blank) {
			for(int i = x; i < x + cur_size; i++) {
				for(int j = y; j < y + cur_size; j++) {
					stars[i][j] = ' ';
				}
			}
			return;
		}
		if(cur_size == 1) {
			stars[x][y] = '*';
			return;
		}
		int size = cur_size / 3;
		int count = 0;
		for(int i = x; i < x + cur_size; i += size) {
			for(int j = y; j < y + cur_size; j += size) {
				count++;
				if(count == 5) {
					rec_func(i, j, size, true);
				} else {
					rec_func(i, j, size, false);
				}
			}
		}
	}
	
	static void print() {
		for(int i = 0; i < stars.length; i++) {
			for(int j = 0; j < stars[i].length; j++) {
				sb.append(stars[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {
		input();
		rec_func(0, 0, N, false);
		print();
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
