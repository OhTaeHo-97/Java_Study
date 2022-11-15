package programmers;

import java.util.*;

public class Level3_DiskController {
	public static int solution(int[][] jobs) {
		Arrays.sort(jobs, (j1, j2) -> j1[0] - j2[0]);
		PriorityQueue<int[]> queue = new PriorityQueue<>((j1, j2) -> j1[1] - j2[1]);
		int answer = 0, end = 0, idx = 0, count = 0;
		while(count < jobs.length) {
			while(idx < jobs.length && jobs[idx][0] <= end) {
				queue.offer(jobs[idx++]);
			}
			if(queue.isEmpty()) {
				end = jobs[idx][0];
			} else {
				int[] temp = queue.poll();
				answer += temp[1] + end - temp[0];
				end += temp[1];
				count++;
			}
		}
		return answer / jobs.length;
	}
	
	public static void main(String[] args) {
		int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
		System.out.println(solution(jobs));
	}
}
