package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1806 {
	static int N, S;
	static int[] seq;
	static int[] ac_sum;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		S = scanner.nextInt();
		seq = new int[N + 1];
		ac_sum = new int[N + 1];
		for(int i = 1; i <= N; i++) seq[i] = scanner.nextInt();
	}
	
	static void accumulate_sum() {
		ac_sum = seq.clone();
		for(int i = 2; i <= N; i++) {
			ac_sum[i] += ac_sum[i - 1];
		}
	}
	
	static void solution() {
		int L = 0, R = 1, len = Integer.MAX_VALUE;
		while(L < R && R <= N) {
			int sum = ac_sum[R] - ac_sum[L];
			if(sum >= S) {
				len = Math.min(len, R - L);
				L += 1;
			} else {
				R += 1;
			}
		}
		System.out.println(len == Integer.MAX_VALUE ? 0 : len);
	}
	
	public static void main(String[] args) {
		input();
		accumulate_sum();
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
