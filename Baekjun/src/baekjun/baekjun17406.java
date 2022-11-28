package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun17406 {
	static int N, M, K, answer;
	static int[][] arr, operation;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		K = scanner.nextInt();
		arr = new int[N][M];
		operation = new int[K][3];
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++)
				arr[row][col] = scanner.nextInt();
		}
		for(int row = 0; row < K; row++) {
			for(int col = 0; col < 3; col++)
				operation[row][col] = scanner.nextInt();
		}
		answer = Integer.MAX_VALUE;
	}
	
	static void solution() {
		int[][] copy = arr.clone();
		func(0, new boolean[K], copy);
		System.out.println(answer);
	}
	
	static void func(int depth, boolean[] visited, int[][] A) {
		if(depth == K) {
			answer = Math.min(answer, findMinSum(A));
			return;
		}
		for(int index = 0; index < K; index++) {
			if(!visited[index]) {
				visited[index] = true;
				func(depth + 1, visited, rotate(A, operation[index]));
				visited[index] = false;
			}
		}
	}
	
	static int findMinSum(int[][] A) {
		int min = Integer.MAX_VALUE;
		for(int row = 0; row < N; row++) {
			int total = 0;
			for(int col = 0; col < M; col++) total += A[row][col];
			min = Math.min(min, total);
		}
		return min;
	}
	
	static int[][] rotate(int[][] A, int[] operate) {
		int startX = operate[0] - operate[2] - 1, startY = operate[1] - operate[2] - 1;
		int endX = operate[0] + operate[2] - 1, endY = operate[1] + operate[2] - 1;
		int[][] rotateArr = new int[N][M];
		for(int row = 0; row < N; row++) rotateArr[row] = A[row].clone();
		while(true) {
			if(startX >= endX && startY >= endY) break;
			rotateOnce(rotateArr, A, startX, startY, endX, endY);
			startX++;
			startY++;
			endX--;
			endY--;
		}
		return rotateArr;
	}
	
	static void rotateOnce(int[][] rotateArr, int[][] A, int startX, int startY, int endX, int endY) {
		for(int col = startY + 1; col <= endY; col++)
			rotateArr[startX][col] = A[startX][col - 1];
		for(int row = startX + 1; row <= endX; row++)
			rotateArr[row][endY] = A[row - 1][endY];
		for(int col = endY - 1; col >= startY; col--)
			rotateArr[endX][col] = A[endX][col + 1];
		for(int row = endX - 1; row >= startX; row--)
			rotateArr[row][startY] = A[row + 1][startY];	
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
