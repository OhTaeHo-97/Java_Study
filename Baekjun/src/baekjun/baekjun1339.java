package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjun1339 {
	static int N;
	static String[] words;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		words = new String[N];
		for(int i = 0; i < N; i++) words[i] = scanner.nextLine();
	}
	
	static void solution() {
		Stack<Integer> nums = new Stack<Integer>();
		for(int num = 0; num < 10; num++) nums.add(num);
		HashMap<Character, Integer> match = new HashMap<Character, Integer>();
		for(int index = 0; index < N; index++) {
			for(int str_index = 0; str_index < words[index].length(); str_index++) {
				char cur_char = words[index].charAt(str_index);
				if(!match.containsKey(cur_char)) match.put(cur_char, 0);
				match.put(cur_char, match.get(cur_char) + (int)Math.pow(10, (words[index].length() - 1) - str_index));
			}
		}
		ArrayList<Entry<Character, Integer>> entryList = new ArrayList<Entry<Character, Integer>>(match.entrySet());
		Collections.sort(entryList, new Comparator<Entry<Character, Integer>>() {
			public int compare(Entry<Character, Integer> e1, Entry<Character, Integer> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});
		int result = 0;
		for(Entry<Character, Integer> entry : entryList) {
			result += entry.getValue() * nums.pop();
		}
		System.out.println(result);
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
		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
