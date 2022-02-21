package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1343 {
	public String isPolyomino(String input) {
		StringTokenizer st = new StringTokenizer(input, ".");
		ArrayList<String> polyomino = new ArrayList<String>();
		while(st.hasMoreTokens()) {
			polyomino.add(st.nextToken());
		}
		st = new StringTokenizer(input, "X");
		ArrayList<String> classifier = new ArrayList<String>();
		while(st.hasMoreTokens()) {
			classifier.add(st.nextToken());
		}
		String result = "";
		for(int i = 0; i < polyomino.size(); i++) {
			int len = polyomino.get(i).length();
			int remain = len % 4;
			int quotient = len / 4;
			if(remain % 2 != 0) {
				return "-1";
			} else {
				String temp = "";
				for(int j = 0; j < quotient; j++) {
					temp += "AAAA";
				}
				for(int j = remain / 2; j > 0; j /= 2) {
					temp += "BB";
				}
				result += temp;
			}
			if(i != polyomino.size() - 1) {
				result += classifier.get(i);
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		baekjun1343 m = new baekjun1343();
		System.out.println(m.isPolyomino(input));
	}
}
