package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1120 {
	public int findMinDif(String a, String b) {
		if(a.length() == b.length()) {
			int count = 0;
			for(int i = 0; i < a.length(); i++) {
				if(a.charAt(i) != b.charAt(i)) {
					count++;
				}
			}
			return count;
		}
		int diff = Integer.MAX_VALUE;
		int length_dif = b.length() - a.length() + 1;
		for(int i = 0; i < length_dif; i++) {
			int count = 0;
			for(int j = 0; j < a.length(); j++) {
				if(a.charAt(j) != b.charAt(i + j)) {
					count++;
				}
			}
			if(diff > count) {
				diff = count;
			}
		}
		return diff;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		String a = st.nextToken();
		String b = st.nextToken();
		baekjun1120 m = new baekjun1120();
		System.out.println(m.findMinDif(a, b));
	}
}
