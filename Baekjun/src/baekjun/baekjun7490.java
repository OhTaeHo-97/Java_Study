package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class baekjun7490 {
	static int n;
	ArrayList<String> expression;
	static StringBuilder sb;
//	public boolean calculate(String exp) {
//		StringTokenizer st = new StringTokenizer(exp, "+|-", true);
//		int result = Integer.parseInt(st.nextToken());
//		while(st.hasMoreTokens()) {
//			String temp = st.nextToken();
//			if(temp.equals("+")) {
//				result += Integer.parseInt(st.nextToken());
//			} else {
//				result -= Integer.parseInt(st.nextToken());
//			}
//		}
//		if(result == 0) {
//			return true;
//		}
//		return false;
//	}
//	
//	public void getAllCase(int num, String e) {
//		if(num == n) {
//			String exp = e.replaceAll(" ", "");
//			if(calculate(exp)) {
//				expression.add(e);
//			}
//			return;
//		}
//		for(int i = 0; i < 3; i++) {
//			getAllCase(num + 1, e + operator[i] + Integer.toString(num + 1));
//		}
//	}
	
	public void getAllCase(int idx, int num, int operator, int result, String exp) {
		if(idx == n) {
			result += (num * operator);
			if(result == 0) {
				sb.append(exp + "\n");
			}
			return;
		}
		getAllCase(idx + 1, num * 10 + (idx + 1), operator, result, exp + " " + Integer.toString(idx + 1));
		getAllCase(idx + 1, idx + 1, 1, result + (num * operator), exp + "+" + Integer.toString(idx + 1));
		getAllCase(idx + 1, idx + 1, -1, result + (num * operator), exp + "-" + Integer.toString(idx + 1));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		baekjun7490 b = new baekjun7490();
		int test_num = Integer.parseInt(br.readLine());
		for(int i = 0; i < test_num; i++) {
			n = Integer.parseInt(br.readLine());
			sb = new StringBuilder();
			b.getAllCase(1, 1, 1, 0, "1");
			System.out.println(sb);
		}
		br.close();
	}
}
