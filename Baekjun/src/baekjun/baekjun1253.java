package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class baekjun1253 {
	static int N;
	static ArrayList<Integer> seq;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		seq = new ArrayList<>();
		for(int i = 1; i <= N; i++) seq.add(scanner.nextInt());
	}
	
	static void pro() {
		Collections.sort(seq);
		ArrayList<Integer> temp = new ArrayList<>();
		temp.addAll(seq);
		int ans = 0;
		for(int i = 0; i < seq.size(); i++) {
			temp.remove(i);
			int L = 0, R = N - 2;
			while(L < R) {
				int sum = temp.get(L) + temp.get(R);
				if(sum  == seq.get(i)) {
					ans++;
					break;
				}
				if(sum > seq.get(i)) {
					R--;
				} else {
					L++;
				}
			}
			temp.add(i, seq.get(i));
		}
		System.out.println(ans);
	}
		
	public static void main(String[] args) {
		input();
		pro();
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
