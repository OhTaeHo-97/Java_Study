package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class baekjun5052 {
	// Set 이용
//	static StringBuilder sb = new StringBuilder();
//	static int n;
//	static String[] phoneNumbers;
//	static HashSet<String> numbers;
//	static HashSet<Integer> nums;
//	static void input() {
//		Reader scanner = new Reader();
//		int testNum = scanner.nextInt();
//		for(int test = 0; test < testNum; test++) {
//			n = scanner.nextInt();
//			phoneNumbers = new String[n];
//			for(int count = 0; count < n; count++) phoneNumbers[count] = scanner.nextLine();
//			numbers = new HashSet<String>();
//			nums = new HashSet<Integer>();
//			solution();
//		}
//	}
//	
//	static void solution() {
//		Arrays.sort(phoneNumbers, new Comparator<String>() {
//			public int compare(String s1, String s2) {
//				if(s1.length() != s2.length()) return s1.length() - s2.length();
//				else {
//					for(int index = 0; index < s1.length(); index++) {
//						if(s1.charAt(index) != s2.charAt(index)) {
//							return s1.charAt(index) - s2.charAt(index);
//						}
//					}
//					return 0;
//				}
//			}
//		});
//		for(String num : phoneNumbers) {
//			if(nums.size() == 0) {
//				numbers.add(num);
//				nums.add(num.length());
//			} else {
//				Iterator iter = nums.iterator();
//				while(iter.hasNext()) {
//					int len = (int)iter.next();
//					if(!numbers.add(num.substring(0, len))) {
//						sb.append("NO").append('\n');
//						return;
//					} else {
//						numbers.remove(num.substring(0, len));
//					}
//				}
//				numbers.add(num);
//				nums.add(num.length());
//			}
//		}
//		sb.append("YES").append('\n');
//	}
	
	static StringBuilder sb = new StringBuilder();
	static int n;
	static String[] phoneNumbers;
	static void input() {
		Reader scanner = new Reader();
		int testNum = scanner.nextInt();
		for(int test = 0; test < testNum; test++) {
			n = scanner.nextInt();
			phoneNumbers = new String[n];
			for(int count = 0; count < n; count++) {
				phoneNumbers[count] = scanner.nextLine();
			}
			solution();
		}
	}
	
	static void solution() {
		Arrays.sort(phoneNumbers);
		if(isDuplicate()) sb.append("NO").append('\n');
		else sb.append("YES").append('\n');
	}
	
	static boolean isDuplicate() {
		for(int index = 0; index < n - 1; index++) {
			if(phoneNumbers[index + 1].startsWith(phoneNumbers[index])) return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		input();
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
