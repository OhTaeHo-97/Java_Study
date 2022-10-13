package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun15684 {
	static int N, M, H, min;
	static boolean[][][] horizontalLine;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		H = scanner.nextInt();
		horizontalLine = new boolean[N + 1][H][2];
		for(int count = 0; count < M; count++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			horizontalLine[b][a - 1][0] = true;
			horizontalLine[b + 1][a - 1][1] = true;
		}
	}
	
	static void solution() {
		if(isRight()) {
			System.out.println(0);
			return;
		}
		min = Integer.MAX_VALUE;
		rec_func(1, 0);
		min = (min == Integer.MAX_VALUE) ? -1 : min;
		System.out.println(min);
	}
	
	static void rec_func(int idx, int depth) {
		if(depth > 3) return;
		if(depth <= 3) {
			if(isRight()) {
				min = Math.min(min, depth);
				return;
			}
		}
		for(int index = idx; index < N; index++) {
			for(int col = 0; col < H; col++) {
				if(!horizontalLine[index][col][0] && !horizontalLine[index + 1][col][0]) {
					horizontalLine[index][col][0] = true;
					horizontalLine[index + 1][col][1] = true;
					rec_func(index, depth + 1);
					horizontalLine[index][col][0] = false;
					horizontalLine[index + 1][col][1] = false;
				}
			}
		}
	}
	
	static boolean isRight() {
		for(int index = 1; index <= N; index++) {
			int destination = index;
			for(int col = 0; col < H; col++) {
				if(horizontalLine[destination][col][0]) destination += 1;
				else if(horizontalLine[destination][col][1]) destination -= 1;
			}
			if(destination != index) return false;
		}
		return true;
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
