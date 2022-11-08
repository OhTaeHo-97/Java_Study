package programmers;

import java.util.*;

public class Level3_ExpressingAsN {
	static HashSet<Integer>[] lists;
	public static int solution(int N, int number) {
        if(N == number) return 1;
		int answer = 0;
		lists = new HashSet[9];
		for(int index = 0; index < 9; index++) lists[index] = new HashSet<>();
		lists[1].add(N);
		for(int size = 2; size <= 8; size++) {
			for(int first = 1; first <= size / 2; first++) {
				calcEachCase(size, first, size - first);
				calcEachCase(size, size - first, first);
			}
			String n = Integer.toString(N);
			lists[size].add(Integer.parseInt(n.repeat(size)));
			if(lists[size].contains(number)) {
				answer = size;
				break;
			}
		}
		return answer == 0 ? -1 : answer;
	}
	
	static void calcEachCase(int size, int first, int second) {
		for(int n1 : lists[first]) {
			for(int n2 : lists[second]) {
				lists[size].add(n1 + n2);
				lists[size].add(n1 - n2);
				lists[size].add(n1 * n2);
				try {
					lists[size].add(n1 / n2);
				} catch(Exception e) {}
			}
		}
	}
	
	public static void main(String[] args) {
		int N = 5, number = 12;
		System.out.println(solution(N, number));
	}
}
