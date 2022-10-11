package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun10830 {
	static StringBuilder sb = new StringBuilder();
	static long B;
	static int[][] matrix;
	static int N;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		B = scanner.nextLong();
		matrix = new int[N][N];
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++)
				matrix[row][col] = scanner.nextInt();
		}
	}
	
	static void solution() {
		int[][] result = powMatrix(matrix, B);
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				sb.append(result[row][col] % 1000).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static int[][] powMatrix(int[][] mat, long involution) {
		if(involution == 1)
			return mat;
		else {
			int[][] temp = powMatrix(mat, involution / 2);
			if(involution % 2 == 0)
				return mulMatrix(temp, temp);
			else
				return mulMatrix(mulMatrix(temp, temp), mat);
		}
	}
	
	static int[][] mulMatrix(int[][] mat1, int[][] mat2) {
		int[][] result = new int[N][N];
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				int temp = 0;
				for(int index = 0; index < N; index++)
					temp += ((mat1[row][index] * mat2[index][col]) % 1000);
				result[row][col] = temp;
			}
		}
		return result;
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
		String next() {
			while(st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
		long nextLong() {
			return Long.parseLong(next());
		}
	}
}
