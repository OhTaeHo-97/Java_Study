package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class baekjun1744 {
	static int N;
	static Integer[][] seq;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		seq = new Integer[3][N];
		int[] idx = new int[3];
		for(int i = 0; i < N; i++) {
			int temp = scanner.nextInt();
			if(temp > 0) {
				seq[0][idx[0]] = temp;
				idx[0]++;
			} else if(temp < 0) {
				seq[0][idx[2]] = temp;
				idx[2]++;
			} else {
				seq[0][idx[1]] = temp;
				idx[1]++;
			}
		}
	}
	
	static void solution() {
		if(N == 1) {
			System.out.println();
		}
		Arrays.sort(seq[0], Collections.reverseOrder());
		Arrays.sort(seq[2]);
		int total = 0;
		for(int i = 0; i < seq[0].length - 2; i += 2) {
			if(seq[0][i] == 1) {
				i--;
				total += 1;
			} else if(seq[0][i + 1] == 1) {
				total += seq[0][i] + 1;
			} else {
				total += seq[0][i] * seq[0][i + 1];
			}
		}
		if(seq[2].length % 2 == 0) {
			for(int i = 0; i < seq[2].length - 2; i += 2) total += seq[2][i] * seq[2][i + 1];
		} else {
			for(int i = 0; i < seq[2].length - 3; i += 2) total += seq[2][i] * seq[2][i + 1];
		}
		System.out.println(total);
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
