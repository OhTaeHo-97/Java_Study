package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1062 {
	// visit check를 통한 구현
//	static int N, K, possibleWords;
//	static String[] words;
//	static boolean[] alphabets;
//	static void input() {
//		Reader scanner = new Reader();
//		N = scanner.nextInt();
//		K = scanner.nextInt();
//		words = new String[N];		
//		for(int word = 0; word < N; word++) {
//			String temp = scanner.nextLine();
//			temp = temp.replace("anta", "");
//			temp = temp.replace("tica", "");
//			words[word] = temp;
//		}
//		alphabets = new boolean[26];
//		alphabets['a' - 'a'] = true;
//		alphabets['n' - 'a'] = true;
//		alphabets['t' - 'a'] = true;
//		alphabets['i' - 'a'] = true;
//		alphabets['c' - 'a'] = true;
//		possibleWords = 0;
//	}
//	
//	static void solution() {
//		if(K < 5) {
//			System.out.println(0);
//			return;
//		}
//		if(K == 26) {
//			System.out.println(N);
//			return;
//		}
//		rec_func(0, 0);
//		System.out.println(possibleWords);
//	}
//	
//	static void rec_func(int idx, int depth) {
//		if(depth == K - 5) {
//			int num = 0;
//			for(int word = 0; word < N; word++) {
//				boolean flag = true;
//				for(int index = 0; index < words[word].length(); index++) {
//					if(!alphabets[words[word].charAt(index) - 'a']) {
//						flag = false;
//						break;
//					}
//				}
//				if(flag) num++;
//			}
//			possibleWords = Math.max(possibleWords, num);
//		}
//		
//		for(int index = idx; index < 26; index++) {
//			if(!alphabets[index]) {
//				alphabets[index] = true;
//				rec_func(index + 1, depth + 1);
//				alphabets[index] = false;
//			}
//		}
//	}
	
	// 비트마스킹을 통한 구현
	static int N, K, possibleWords, flag;
	static String[] words;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		K = scanner.nextInt();
		words = new String[N];		
		for(int word = 0; word < N; word++) {
			String temp = scanner.nextLine();
			temp = temp.replace("anta", "");
			temp = temp.replace("tica", "");
			words[word] = temp;
		}
		possibleWords = 0;
		flag = 0;
		flag |= 1 << ('a' - 'a');
		flag |= 1 << ('n' - 'a');
		flag |= 1 << ('t' - 'a');
		flag |= 1 << ('i' - 'a');
		flag |= 1 << ('c' - 'a');
	}
	
	static void solution() {
		if(K < 5) {
			System.out.println(0);
			return;
		}
		if(K == 26) {
			System.out.println(N);
			return;
		}
		rec_func(0, 0, flag);
		System.out.println(possibleWords);
	}
	
	static void rec_func(int idx, int depth, int flag) {
		if(depth == K - 5) {
			int num = 0;
			for(int word = 0; word < N; word++) {
				boolean isValid = true;
				for(int index = 0; index < words[word].length(); index++) {
					if((flag & 1 << words[word].charAt(index) - 'a') == 0) {
						isValid = false;
						break;
					}
				}
				if(isValid) num++;
			}
			possibleWords = Math.max(possibleWords, num);
		}
		
		for(int index = idx; index < 26; index++) {
			if((flag & 1 << index) == 0)
				rec_func(index + 1, depth + 1, flag | 1 << index);
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
