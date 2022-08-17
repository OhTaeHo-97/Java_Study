package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class baekjun1484 {
	static StringBuilder sb = new StringBuilder();
	static int G;
	
	static void input() {
		Reader scanner = new Reader();
		G = scanner.nextInt();
	}
	
	static void solution() {
		TreeSet<Integer> cur_weight = new TreeSet<>();
		int l = 1, r = 1;
		while(r <= 100000) {
			long dif = (long)r * r - (long)l * l;
			if(dif > G) {
				l++;
			} else {
				if(dif == G) {
					cur_weight.add(r);
				}
				r++;
			}
		}
		if(cur_weight.size() == 0) {
			sb.append(-1 + "\n");
		} else {			
			for(int w : cur_weight) {
				sb.append(w + "\n");
			}
		}
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
