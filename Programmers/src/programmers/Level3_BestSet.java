package programmers;

import java.util.*;

public class Level3_BestSet {
	public static int[] solution(int n, int s) {
		if(n > s) return new int[] {-1};
		int[] answer = new int[n];
		int quotient = s / n;
		Arrays.fill(answer, quotient);
		int remainder = s % n;
		for(int count = 0; count < remainder; count++)
			answer[answer.length - 1 - count]++;
		return answer;
	}
	
	public static void main(String[] args) {
		int n = 2, s = 8;
		int[] answer = solution(n, s);
		for(int index = 0; index < answer.length; index++) System.out.println(answer[index]);
	}
}
