package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1105 {
	public int getLeastEight(int small, int big) {
		int eightNum = 0;
		if(Integer.toString(small).length() != Integer.toString(big).length()) {
			return 0;
		}
		if(small == big) {
			while(small > 0) {
				if(small % 10 == 8) {
					eightNum++;
				}
				small /= 10;
			}
			return eightNum;
		}
		String small_str = Integer.toString(small);
		String big_str = Integer.toString(big);
		for(int i = 0; i < small_str.length(); i++) {
			if(small_str.charAt(i) != big_str.charAt(i)) {
				break;
			}
			if(small_str.charAt(i) == big_str.charAt(i) && small_str.charAt(i) == '8') {
				eightNum++;
			}
		}
		return eightNum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		br.close();
		StringTokenizer st = new StringTokenizer(input);
		int small = Integer.parseInt(st.nextToken());
		int big = Integer.parseInt(st.nextToken());
		baekjun1105 b = new baekjun1105();
		bw.write(b.getLeastEight(small, big) + "\n");
		bw.flush();
		bw.close();
	}
}
