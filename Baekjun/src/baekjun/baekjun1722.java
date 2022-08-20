package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1722 {
	static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer> nums;
	static int N;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		int type = scanner.nextInt();
		nums = new ArrayList<>();
		for(int i = 1; i <= N; i++) {
			nums.add(i);
		}
		if(type == 1) {
			long numberth = scanner.nextLong();
			findSeries(numberth);
		} else if(type == 2) {
			ArrayList<Integer> series = new ArrayList<>();
			for(int i = 0; i < N; i++) {
				series.add(scanner.nextInt());
			}
			findNumberth(series);
		} else {
			System.exit(1);
		}
	}
	
	static void findSeries(long numberth) {
		ArrayList<Integer> series = new ArrayList<>();
		numberth -= 1;
		int cipher = N - 1;
		while(cipher > 0 || nums.size() > 0) {
			long fact = calcFactorial(cipher);
			long quotient = numberth / fact;
			numberth %= fact;
			series.add(nums.get((int)quotient));
			nums.remove((int)quotient);
			cipher--;
		}
		for(int s : series) sb.append(s).append(' ');
	}
	
	static void findNumberth(ArrayList<Integer> series) {
		int cipher = N - 1;
		long numberth = 1;
		for(int i = 0; i < series.size() - 1; i++) {
			int cur_num = series.get(i);
			int idx = nums.indexOf(cur_num);
			if(idx != 0) {
				long fact = calcFactorial(cipher);
				numberth += fact * idx;
			}
			nums.remove(idx);
			cipher--;
		}
		sb.append(numberth).append('\n');
	}
	
	static long calcFactorial(long num) {
		long result = 1;
		for(long i = 2; i <= num; i++) {
			result *= i;
		}
		return result;
	}
	
	public static void main(String[] args) {
		input();
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
		long nextLong() {
			return Long.parseLong(next());
		}
	}
}
