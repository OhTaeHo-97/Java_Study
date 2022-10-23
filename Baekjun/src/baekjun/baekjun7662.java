package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class baekjun7662 {
	static StringBuilder sb = new StringBuilder();
	static Reader scanner = new Reader();
	static int T, k;
	static ArrayList<Integer> list;
	static void input() {
		k = scanner.nextInt();
		list = new ArrayList<>();
		for(int order = 1; order <= k; order++) {
			String operator = scanner.next();
			int num = scanner.nextInt();
			if(operator.equals("I")) insert(num);
			else if(operator.equals("D")) delete(num);
		}
	}
	
	static void insert(int num) {
		list.add(num);
	}
	
	static void delete(int num) {
		if(list.size() == 0) return;
		Collections.sort(list);
		if(num == 1) list.remove(list.size() - 1);
		else if(num == -1) list.remove(0);
	}
	
	static void printList() {
		if(list.size() == 0) sb.append("EMPTY").append('\n');
		else {
			Collections.sort(list);
			sb.append(list.get(list.size() - 1)).append(' ').append(list.get(0)).append('\n');
		}
	}
	
	public static void main(String[] args) {
		T = scanner.nextInt();
		while(T-- > 0) {
			input();
			printList();
		}
		System.out.println(sb);
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
