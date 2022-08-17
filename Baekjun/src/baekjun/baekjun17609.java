package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun17609 {
//	static StringBuilder sb = new StringBuilder();
//	static String palindrome;
//	
//	static void input() {
//		Reader scanner = new Reader();
//		int test_num = scanner.nextInt();
//		for(int i = 0; i < test_num; i++) {
//			palindrome = scanner.nextLine();
//			isPalindrome();
//		}
//	}
//	
//	static void isPalindrome() {
//		int l = 0, r = palindrome.length() - 1;
//		boolean isPalin = true;
//		while(l < r) {
//			if(palindrome.charAt(l) == palindrome.charAt(r)) {
//				l++;
//				r--;
//			} else {
//				isPalin = false;
//				if(deleteLeft(l + 1, r) || deleteRight(l, r - 1)) {
//					sb.append(1).append('\n');
//					break;
//				} else {
//					sb.append(2).append('\n');
//					break;
//				}
//			}
//		}
//		if(isPalin) {
//			sb.append(0).append('\n');
//		}
//	}
//	
//	static boolean deleteLeft(int l, int r) {
//		while(l < r) {
//			if(palindrome.charAt(l) == palindrome.charAt(r)) {
//				l++;
//				r--;
//			} else {
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	static boolean deleteRight(int l, int r) {
//		while(l < r) {
//			if(palindrome.charAt(l) == palindrome.charAt(r)) {
//				l++;
//				r--;
//			} else {
//				return false;
//			}
//		}
//		return true;
//	}
	
	static StringBuilder sb = new StringBuilder();
	static String palindrome;
	
	static void input() {
		Reader scanner = new Reader();
		int test_num = scanner.nextInt();
		for(int i = 0; i < test_num; i++) {
			palindrome = scanner.nextLine();
			sb.append(isPalindrome(0, palindrome.length() - 1, 0)).append('\n');
		}
	}
	
	static int isPalindrome(int l, int r, int cnt) {
		if(cnt == 2)
			return 2;
		int result = cnt;
		while(l < r) {
			if(palindrome.charAt(l) != palindrome.charAt(r)) {
				int left = isPalindrome(l + 1, r, cnt + 1);
				int right = isPalindrome(l, r - 1, cnt + 1);
				result = Math.min(left, right);
				break;
			}
			l++;
			r--;
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
		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str;
		}
	}
}
