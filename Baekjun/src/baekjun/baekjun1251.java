package baekjun;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class baekjun1251 {
	public String reverse(String[] divWord) {
		String reverseWord = "";
		for(int i = 0; i < divWord.length; i++) {
			for(int j = divWord[i].length(); j > 0; j--) {
				reverseWord += divWord[i].substring(j - 1, j);
			}
		}
		
		return reverseWord;
	}
	
	public String divideWord(String word) {
		if(word.length() == 3) {
			return word;
		}
		
		ArrayList<String> divWords = new ArrayList<String>();
		String[] divWord = new String[3];
		for(int i = 1; i < word.length() - 1; i++) {
			for(int j = i + 1; j < word.length(); j++) {
				divWord[0] = word.substring(0, i);
				divWord[1] = word.substring(i, j);
				divWord[2] = word.substring(j, word.length());
				String reverseWord = reverse(divWord);
				divWords.add(reverseWord);
			}
		}
		
		Collections.sort(divWords);
		return divWords.get(0);
//		String[] divWord = new String[3];
//		int temp = 0;
//		char min_char = word.charAt(0);
//		
//		for(int i = 1; i < word.length() - 2; i++) {
//			if(min_char > word.charAt(i)) {
//				min_char = word.charAt(i);
//				temp = i;
//			}
//		}
//		
//		divWord[0] = word.substring(0, temp + 1);
//		divWord[1] = word.substring(temp + 1);
//		min_char = divWord[1].charAt(0);
//		temp = 0;
//		for(int i = 1; i < divWord[1].length() - 1; i++) {
//			if(min_char > divWord[1].charAt(i)) {
//				min_char = divWord[1].charAt(i);
//				temp = i;
//			}
//		}
//		
//		divWord[2] = divWord[1].substring(temp + 1);
//		divWord[1] = divWord[1].substring(0, temp + 1);
//		
//		for(int i = 0; i < divWord.length; i++) {
//			String reverseWord = "";
//			for(int j = divWord[i].length(); j > 0; j--) {
//				reverseWord += divWord[i].substring(j - 1, j);
//			}
//			divWord[i] = reverseWord;
//		}
		
//		String result = divWord[0] + divWord[1] + divWord[2];
//		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		baekjun1251 b = new baekjun1251();
		String result = b.divideWord(word);
		System.out.println(result);
	}
}
