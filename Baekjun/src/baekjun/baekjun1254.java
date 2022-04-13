package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun1254 {
	public boolean isPalindrome(String str) {
		int start = 0;
		int end = str.length() - 1;
		while(start <= end) {
			if(str.charAt(start) != str.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
	
	public int getPalindromeNum(String input) {
		int len = input.length();
		for(int i = 0; i < len; i++) {
			if(isPalindrome(input.substring(i))) {
				return len + i;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		br.close();
		baekjun1254 b = new baekjun1254();
		bw.write(b.getPalindromeNum(input) + "\n");
		bw.flush();
		bw.close();
	}
}