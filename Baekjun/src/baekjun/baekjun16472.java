package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class baekjun16472 {
	static int N;
	static char[] alphabets;
	static int[] count;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		alphabets = scanner.nextLine().toCharArray();
		count = new int[26];
	}
	
	static void pro() {
		int ans = 1, cnt = 1;
		HashSet<Character> kinds = new HashSet<>();
		count[alphabets[0] - 'a']++;
		kinds.add(alphabets[0]);
		for(int L = 0, R = 0; L < alphabets.length; L++) {
			while(R + 1 < alphabets.length) {
				if(!kinds.contains(alphabets[R + 1])) {
					if(cnt + 1 >= (N + 1)) break;
					kinds.add(alphabets[R + 1]);
					count[alphabets[R + 1] - 'a']++;
					cnt++;
				} else {
					count[alphabets[R + 1] - 'a']++;
				}
				R++;
			}
			ans = Math.max(ans, R - L + 1);
			count[alphabets[L] - 'a']--;
			if(count[alphabets[L] - 'a'] == 0) {
				kinds.remove(alphabets[L]);
				cnt--;
			}
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
