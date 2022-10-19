package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjun14002 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] A;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		A = new int[N];
		for(int index = 0; index < N; index++) A[index] = scanner.nextInt();
	}
	
	static void solution() {
		int[] dp = new int[N];
		dp[0] = 1;
		int result = 1;
		for(int count = 1; count < N; count++) {
			dp[count] = 1;
			for(int index = 0; index < count; index++) {
				if(A[count] > A[index]) {
					dp[count] = Math.max(dp[count], dp[index] + 1);
					result = Math.max(result, dp[count]);
				}
			}
		}
		sb.append(result).append('\n');
		Stack<Integer> stack = new Stack<>();
		for(int index = N - 1; index >= 0; index--) {
			if(dp[index] == result) {
				stack.push(A[index]);
				result--;
			}
		}
		while(!stack.isEmpty()) sb.append(stack.pop()).append(' ');
		System.out.println(sb);
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
	}
}
