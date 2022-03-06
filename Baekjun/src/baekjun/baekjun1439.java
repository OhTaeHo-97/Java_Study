package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun1439 {
	public int reverse(String input) {
		String allOne = "";
		String allZero = "";
		for(int i = 0; i < input.length(); i++) {
			allOne += "1";
			allZero += "0";
		}
		if(input.equals(allOne) || input.equals(allZero)) {
			return 0;
		}
		int[] count = new int[2];
		for(int i = 1; i < input.length(); i++) {
			if(input.charAt(i - 1) != input.charAt(i)) {
				if(input.charAt(i - 1) == '0') {
					count[0]++;
				} else {
					count[1]++;
				}
			}
			if(i == (input.length() - 1)) {
				if(input.charAt(i) == '0') {
					count[0]++;
				} else {
					count[1]++;
				}
			}
		}
		if(count[0] > count[1]) {
			return count[1];
		} else {
			return count[0];
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		br.close();
		baekjun1439 b = new baekjun1439();
		bw.write(b.reverse(input) + "\n");
		bw.flush();
		bw.close();
	}
}
