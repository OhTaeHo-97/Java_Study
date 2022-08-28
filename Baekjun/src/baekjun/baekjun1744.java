package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class baekjun1744 {
	static int N;
	static ArrayList<Integer> positive, negative;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		positive = new ArrayList<Integer>();
		negative = new ArrayList<Integer>();
		for(int i = 0; i < N; i++) {
			int temp = scanner.nextInt();
			if(temp > 0) positive.add(temp);
			else negative.add(temp);
		}
	}
	
	static void solution() {
		Collections.sort(positive, Collections.reverseOrder());
		Collections.sort(negative);
		int total = 0, idx = 0;
		while(idx < positive.size()) {
			if(idx + 1 < positive.size() && positive.get(idx) != 1 && positive.get(idx + 1) != 1)
				total += positive.get(idx++) * positive.get(idx++);
			else total += positive.get(idx++);
		}
		idx = 0;
		boolean flag = true;
		while(idx < negative.size()) {
			if(idx + 1 < negative.size()) total += negative.get(idx++) * negative.get(idx++);
			else total += negative.get(idx++);
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
