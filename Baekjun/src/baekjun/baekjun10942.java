package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun10942 {
	// 그냥 palindrome 구현
	static StringBuilder sb = new StringBuilder();
	static Reader scanner = new Reader();
	static int N, M, S, E;
	static int[] series;
	static void input() {
		N = scanner.nextInt();
		series = new int[N];
		for(int index = 0; index < N; index++) series[index] = scanner.nextInt();
		M = scanner.nextInt();
		for(int question = 0; question < M; question++) {
			S = scanner.nextInt();
			E = scanner.nextInt();
			isPalindrome();
		}
	}
	
	static void isPalindrome() {
		if(S == E) {
			sb.append(1).append('\n');
			return;
		}
		for(int start = S - 1, end = E - 1; start < end; start++, end--) {
			if(series[start] != series[end]) {
				sb.append(0).append('\n');
				return;
			}
		}
		sb.append(1).append('\n');
		return;
	}
	
	public static void main(String[] args) {
		input();
		System.out.println(sb);
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
	}
}
