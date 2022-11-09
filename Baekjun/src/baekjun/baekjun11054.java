package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun11054 {
	static int N;
	static int[] arr;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		arr = new int[N];
		for(int index = 0; index < N; index++) arr[index] = scanner.nextInt();
	}
	
	static void solution() {
		int[] ascending = new int[N], descending = new int[N];
		LIS(ascending);
		LDS(descending);
		int[] sum = new int[N];
		int len = 0;
		for(int index = 0; index < N; index++) {
			sum[index] = ascending[index] + descending[index] - 1;
			len = Math.max(len, sum[index]);
		}
		System.out.println(len);
	}
	
	static void LIS(int[] ascending) {
		for(int index = 0; index < N; index++) {
			ascending[index] = 1;
			for(int idx = 0; idx < index; idx++) {
				if(arr[index] > arr[idx])
					ascending[index] = Math.max(ascending[index], ascending[idx] + 1);
			}
		}
	}
	
	static void LDS(int[] descending) {
		for(int index = N - 1; index >= 0; index--) {
			descending[index] = 1;
			for(int idx = N - 1; idx > index; idx--) {
				if(arr[index] > arr[idx])
					descending[index] = Math.max(descending[index], descending[idx] + 1);
			}
		}
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
