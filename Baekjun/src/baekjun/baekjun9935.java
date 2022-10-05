package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class baekjun9935 {
	static StringBuilder sb = new StringBuilder();
	static String str, explosion;
	static void input() {
		Reader scanner = new Reader();
		str = scanner.nextLine();
		explosion = scanner.nextLine();
	}
	
	static void solution() {
		Stack<Character> strStack = new Stack<Character>();
		for(int index = 0; index < str.length(); index++) {
			strStack.push(str.charAt(index));
			if(strStack.size() >= explosion.length()) {
				boolean flag = true;
				for(int index2 = 0; index2 < explosion.length(); index2++) {
					if(strStack.get(strStack.size() - explosion.length() + index2) != explosion.charAt(index2)) {
						flag = false;
						break;
					}
				}
				if(flag) {
					for(int index2 = 0; index2 < explosion.length(); index2++)
						strStack.pop();
				}
			}
		}
		for(Character c : strStack) sb.append(c);
		if(sb.length() == 0) System.out.println("FRULA");
		else System.out.println(sb);
	}
	
	public static void main(String[] args) {
		input();
		solution();
	}
	
	static class Reader {
		BufferedReader br;
		public Reader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
