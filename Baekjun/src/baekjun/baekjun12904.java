package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun12904 {
	static String s, t;
//	public String reverse(String str) {
//		return new StringBuilder(str).reverse().toString();
//	}
//	
//	public void canChange(String str) {
//		if(str.length() == t.length()) {
//			if(str.equals(t)) {
//				System.out.println(1);
//				System.exit(0);
//			}
//		}
//		canChange(str + "A");
//		canChange(reverse(str) + "B");
//	}
	public String reverse(String str) {
		return new StringBuilder(str).reverse().toString();
	}
	
	public int canChange() {
		while(t.length() > s.length()) {
			if(t.charAt(t.length() - 1) == 'A') {
				t = t.substring(0, t.length() - 1);
			} else {
				t = t.substring(0, t.length() - 1);
				t = reverse(t);
			}
		}
		if(t.equals(s)) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		s = br.readLine();
		t = br.readLine();
		br.close();
		baekjun12904 b = new baekjun12904();
		bw.write(b.canChange() + "\n");
		bw.flush();
		bw.close();
	}
}
