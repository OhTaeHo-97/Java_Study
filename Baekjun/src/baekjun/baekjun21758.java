package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun21758 {
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int[] honey;
	static int[] accumulate;
	
	static void input() {
		Reader scanner = new Reader();
		n = scanner.nextInt();
		honey = new int[n];
		accumulate = new int[n];
		for(int i = 0; i < n; i++) {
			honey[i] = scanner.nextInt();
		}
	}
	
	static void solution() {
		accumulation();
		int max = Integer.MIN_VALUE;
		for(int i = 1; i < n - 1; i++) {
			max = Math.max(max, leftToRight(i));
		}
		for(int i = 1; i < n - 1; i++) {
			max = Math.max(max, rightToLeft(i));
		}
		for(int i = 1; i < n - 1; i++) {
			max = Math.max(max, bothSides(i));
		}
		sb.append(max);
	}
	
	static void accumulation() {
		accumulate = honey.clone();
		for(int i = 1; i < n; i++) {
			accumulate[i] += accumulate[i - 1];
		}
	}
	
	static int leftToRight(int bee) {
		return ((accumulate[n - 1] - honey[0] - honey[bee]) + (accumulate[n - 1] - accumulate[bee]));
	}
	
	static int rightToLeft(int bee) {
		return ((accumulate[n - 2] - honey[bee]) + accumulate[bee - 1]);
	}
	
	static int bothSides(int hive) {
		return ((accumulate[hive] - honey[0]) + (accumulate[n - 1] - accumulate[hive - 1] - honey[n - 1]));
	}
	
	public static void main(String[] args) {
		input();
		solution();
		System.out.println(sb.toString());
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
