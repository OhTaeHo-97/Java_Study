package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjun1759 {
	static int l, c;
	static char[] alphabets;
	static char[] code;
	public boolean isValid() {
		int consonant = 0; // 자음
		int vowel = 0; // 모음
		for(char i : code) {
			if(i == 'a' || i == 'e' || i == 'i' || i == 'o' || i == 'u') {
				vowel++;
			} else {
				consonant++;
			}
		}
		if(vowel >= 1 && consonant >= 2) {
			return true;
		}
		return false;
	}
	
	public void findCode(int x, int index) {
		if(index == l) {
			if(isValid()) {
				System.out.println(code);
			}
			return;
		}
		for(int i = x; i < c; i++) {
			code[index] = alphabets[i];
			findCode(i + 1, index + 1);
		}
	}
	
	public void getAllPW() {
		Arrays.sort(alphabets);
		code = new char[l];
		findCode(0, 0);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		l = Integer.parseInt(input[0]);
		c = Integer.parseInt(input[1]);
		alphabets = new char[c];
		input = br.readLine().split(" ");
		br.close();
		for(int i = 0; i < c; i++) {
			alphabets[i] = input[i].charAt(0);
		}
		baekjun1759 b = new baekjun1759();
		b.getAllPW();
	}
}
