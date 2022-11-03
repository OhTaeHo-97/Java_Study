package programmers.kakaoTechInternship2022;

import java.util.*;

public class Level2_makeTwoQueuesSumSame {
	public int solution(int[] queue1, int[] queue2) {
		long sum1 = 0, sum2 = 0;
		Queue<Integer> q1 = new LinkedList<Integer>();
		Queue<Integer> q2 = new LinkedList<Integer>();
		for(int len = 0; len < queue1.length; len++) {
			sum1 += queue1[len];
			q1.offer(queue1[len]);
			sum2 += queue2[len];
			q2.offer(queue2[len]);
		}
		int answer = -2;
		if((sum1 + sum2) % 2 != 0) answer = -1;
		else if(sum1 == sum2) answer = 0;
		else answer = makeSameQueue(q1, q2, sum1, sum2);
        return answer;
    }
	
	public int makeSameQueue(Queue<Integer> q1, Queue<Integer> q2, long sum1, long sum2) {
		int time = 0, size = q1.size() * 3;
		while(time <= size) {
			if(sum1 == sum2) break;	
			if(sum1 > sum2) {
				int temp = q1.poll();
				q2.offer(temp);
				sum1 -= temp;
				sum2 += temp;
			} else {
				int temp = q2.poll();
				q1.offer(temp);
				sum1 += temp;
				sum2 -= temp;
			}
			time++;
		}
		return (time > size) ? -1 : time;
	}
	
	public static void main(String[] args) {
//		int[] queue1 = new int[] {3, 2, 7, 2};
//		int[] queue2 = new int[] {4, 6, 5, 1};
//		int[] queue1 = new int[] {1, 2, 1, 2};
//		int[] queue2 = new int[] {1, 10, 1, 2};
		int[] queue1 = new int[] {1, 1};
		int[] queue2 = new int[] {1, 5};
		Level2_makeTwoQueuesSumSame l = new Level2_makeTwoQueuesSumSame();
		System.out.println(l.solution(queue1, queue2));
	}
}
