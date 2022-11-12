package programmers;

import java.util.Stack;

public class Level3_Move110 {
	public static String[] solution(String[] s) {
		String[] answer = new String[s.length];
		StringBuilder sb = new StringBuilder();
		for(int index = 0; index < s.length; index++) {
			String str = s[index];
			Stack<Character> stack = new Stack<>();
			int count = 0;
			
			for(int idx = 0; idx < str.length(); idx++) {
				char temp = str.charAt(idx);
				if(stack.size() > 1) {
					char second = stack.pop(), first = stack.pop();
					if(first == '1' && second == '1' && temp == '0') {
						count++;
					} else {
						stack.push(first);
						stack.push(second);
						stack.push(temp);
					}
				} else {
					stack.push(temp);
				}
			}
			
			int idx = stack.size();
			if(index == 2) System.out.println(idx);
			boolean flag = false;
			sb = new StringBuilder();
			
			while(!stack.isEmpty()) {
				if(!flag) {
					if(stack.peek() == '1')
						idx--;
					else flag = true;
				}
				sb.insert(0, stack.pop());
			}
			if(index == 2) System.out.println(idx);
			if(count > 0) {
				while(count-- > 0) {
					sb.insert(idx, "110");
					idx += 3;
				}
				answer[index] = sb.toString();
			} else {
				answer[index] = s[index];
			}
		}
		return answer;
	}
	
	public static void main(String[] args) {
		String[] s = {"1110","100111100","0111111010"};
		String[] answer = solution(s);
		for(String str : answer) System.out.println(str);
	}
}
