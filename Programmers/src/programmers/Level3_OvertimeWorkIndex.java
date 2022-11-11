package programmers;

import java.util.*;

public class Level3_OvertimeWorkIndex {
	public static long solution(int n, int[] works) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
		for(int work : works) queue.offer(work);
		for(int remain = n; remain > 0; remain--) {
			int work = queue.poll();
			if(work <= 0) break;
			queue.offer(work - 1);
		}
		return getOvertimeIndex(queue);
	}
	
	static long getOvertimeIndex(PriorityQueue<Integer> queue) {
		int size = queue.size();
		long sum = 0;
		for(int count = 0; count < size; count++) {
			int work = queue.poll();
			sum += (long)Math.pow(work, 2);
		}
		return sum;
	}
	
	public static void main(String[] args) {
//		int n = 4;
//		int[] works = {4, 3, 3};
		int n = 1;
		int[] works = {2, 1, 2};
		System.out.println(solution(n, works));
	}
}
