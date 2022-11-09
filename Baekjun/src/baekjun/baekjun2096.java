package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2096 {
//	static StringBuilder sb = new StringBuilder();
//	static int N;
//	static int[][] arr;
//	static void input() {
//		Reader scanner = new Reader();
//		N = scanner.nextInt();
//		arr = new int[N][3];
//		for(int row = 0; row < N; row++) {
//			for(int col = 0; col < 3; col++) arr[row][col] = scanner.nextInt();
//		}
//	}
//	
//	static void solution() {
//		int[][] max = new int[N][3], min = new int[N][3];
//		int maxVal = Integer.MIN_VALUE, minVal = Integer.MAX_VALUE;
//		for(int col = 0; col < 3; col++)
//			max[0][col] = min[0][col] = arr[0][col];
//		for(int row = 1; row < N; row++) {
//			for(int col = 0; col < 3; col++) {
//				if(col - 1 < 0) {
//					max[row][col] = Math.max(max[row - 1][col] + arr[row][col], max[row - 1][col + 1] + arr[row][col]);
//					min[row][col] = Math.min(min[row - 1][col] + arr[row][col], min[row - 1][col + 1] + arr[row][col]);
//				} else if(col + 1 >= 3) {
//					max[row][col] = Math.max(max[row - 1][col] + arr[row][col], max[row - 1][col - 1] + arr[row][col]);
//					min[row][col] = Math.min(min[row - 1][col] + arr[row][col], min[row - 1][col - 1] + arr[row][col]);
//				} else {
//					max[row][col] = Math.max(max[row - 1][col] + arr[row][col], Math.max(max[row - 1][col + 1] + arr[row][col], max[row - 1][col - 1] + arr[row][col]));
//					min[row][col] = Math.min(min[row - 1][col] + arr[row][col], Math.min(min[row - 1][col + 1] + arr[row][col], min[row - 1][col - 1] + arr[row][col]));
//				}
//			}
//		}
//		for(int col = 0; col < 3; col++) {
//			maxVal = Math.max(maxVal, max[N - 1][col]);
//			minVal = Math.min(minVal, min[N - 1][col]);
//		}
//		sb.append(maxVal).append(' ').append(minVal).append('\n');
//		System.out.println(sb);
// 	}
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] arr;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		arr = new int[N][3];
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < 3; col++) arr[row][col] = scanner.nextInt();
		}
	}
	
	static void solution() {
		int[] maxDp = new int[3], minDp = new int[3];
		for(int row = 0; row < N; row++) {
			if(row == 0) {
				maxDp[0] = minDp[0] = arr[row][0];
				maxDp[1] = minDp[1] = arr[row][1];
				maxDp[2] = minDp[2] = arr[row][2];
			} else {
				int lMaxDp = maxDp[0], rMaxDp = maxDp[2];
				maxDp[0] = Math.max(lMaxDp, maxDp[1]) + arr[row][0];
				maxDp[2] = Math.max(rMaxDp, maxDp[1]) + arr[row][2];
				maxDp[1] = Math.max(maxDp[1], Math.max(lMaxDp, rMaxDp)) + arr[row][1];
				int lMinDp = minDp[0], rMinDp = minDp[2];
				minDp[0] = Math.min(lMinDp, minDp[1]) + arr[row][0];
				minDp[2] = Math.min(rMinDp, minDp[1]) + arr[row][2];
				minDp[1] = Math.min(minDp[1], Math.min(lMinDp, rMinDp)) + arr[row][1];
			}
		}
		sb.append(Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]))).append(' ');
		sb.append(Math.min(minDp[0], Math.min(minDp[1], minDp[2]))).append('\n');
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
