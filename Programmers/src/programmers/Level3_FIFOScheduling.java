package programmers;

import java.util.*;

public class Level3_FIFOScheduling {
	// PriorityQueue 이용(시간 초과)
//		public static int solution(int n, int[] cores) {
//			if(n <= cores.length) return n;
//			PriorityQueue<Core> queue = new PriorityQueue<Core>();
//			for(int idx = 0; idx < cores.length; idx++) queue.offer(new Core(cores[idx], idx + 1));
//			n -= cores.length;
//			int time = 0, num = 0;
//			while(n > 0) {
//				Core core = queue.poll();
//				if(time == core.time) n--;
//				else if(time < core.time) {
//					time = core.time;
//					n--;
//				}
//				if(n == 0) num = core.coreNum;
//				else queue.offer(new Core(time + cores[core.coreNum - 1], core.coreNum));
//			}
//			return num;
//		}
	//	
//		static class Core implements Comparable<Core> {
//			int time, coreNum;
//			public Core(int time, int coreNum) {
//				this.time = time;
//				this.coreNum = coreNum;
//			}
//			public int compareTo(Core c) {
//				if(time != c.time) return time - c.time;
//				return coreNum - c.coreNum;
//			}
//		}
		
		// 이분 탐색 풀이
		public static int solution(int n, int[] cores) {
			int min = 0, max = cores[0] * n;
			int time = 0, workNum = 0;
			while(min <= max) {
				int mid = (min + max) / 2;
				int count = getWorkNum(mid, cores);
				if(count >= n) {
					max = mid - 1;
					time = mid;
					workNum = count;
				} else {
					min = mid + 1;
				}
			}
			workNum -= n;
			int result = 0;
			for(int idx = cores.length - 1; idx >= 0; idx--) {
				if(time % cores[idx] == 0) {
					if(workNum == 0) {
						result = idx + 1;
						break;
					}
					workNum--;
				}
			}
			return result;
		}
		
		static int getWorkNum(int time, int[] cores) {
			if(time == 0) return cores.length;
			int count = cores.length;
			for(int idx = 0; idx < cores.length; idx++) count += (time / cores[idx]);
			return count;
		}
		
		public static void main(String[] args) {
//			int n = 6;
//			int[] cores = {1, 2, 3};
			int n = 109;
			int[] cores = {1, 2, 3, 6, 10, 23};
//			int n = 50000;
//			int[] cores = {10000, 10000};
			System.out.println(solution(n, cores));
		}
}
