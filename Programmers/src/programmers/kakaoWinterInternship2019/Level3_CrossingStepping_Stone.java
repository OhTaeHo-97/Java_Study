package programmers.kakaoWinterInternship2019;

import java.util.*;

public class Level3_CrossingStepping_Stone {
	// 이분탐색 풀이
//		public static int solution(int[] stones, int k) {
//			int maxPeopleNum = 0;
//			int min = 1, max = 200000000;
//			while(min <= max) {
//				int mid = (min + max) / 2;
//				if(canCross(stones, mid, k)) {
//					min = mid + 1;
//					maxPeopleNum = Math.max(maxPeopleNum, mid);
//				} else {
//					max = mid - 1;
//				}
//			}
//			return maxPeopleNum;
//		}
	//	
//		static boolean canCross(int[] stones, int mid, int k) {
//			int count = 0;
//			for(int index = 0; index < stones.length; index++) {
//				if(stones[index] < mid) count++;
//				else count = 0;
//				if(count >= k) return false;
//			}
//			return true;
//		}
		// sliding window 이용한 풀이
		public static int solution(int[] stones, int k) {
			// 처음에 window size만큼 반복문을 돈 다음부터 각 window size 내에 있는 원소  가장 큰 값을 maxNums에 넣어준 후
			// 그 중 가장 작은 값이 정답이 됨
			// window를 채울 때는 Deque에 각 index를 넣어줌
			// 만약 반복문에서 현재 탐색 중인 원소보다 Deque에 있는 원소들이 작거나 같다면 그것들을 최대값이 될 수 없으므로
			// Deque에서 pop해줌
			// 이 때, 우리는 최댓값을 Deque의 가장 처음에 남겨줄 것이기 때문에 뒤에서부터 확인하고 뒤에서부터 pop 해줌
			// Deque에 현재 index를 넣고 만약 Deque의 처음 원소가 (현재 index - windows size)와 같다면
			// 해당 원소는 더이상 window의 원소가 아니기 때문에 pop해줌
			// 처음에 windows size만큼 돌았다면 그 이후부터는 Deque의 처음에 있는 원소가 최댓값이 되므로 해당 값을 maxNumx에 넣음
			Deque<Integer> candidate = new ArrayDeque<>();
			LinkedList<Integer> maxNums = new LinkedList<>();
			for(int index = 0; index < stones.length; index++) {
				while(candidate.size() != 0 && stones[candidate.getLast()] <= stones[index])
					candidate.pollLast();
				candidate.offer(index);
				if(candidate.getFirst() == index - k) candidate.pollFirst();
				if(index >= k - 1) maxNums.add(stones[candidate.getFirst()]);
			}
			Collections.sort(maxNums);
			return maxNums.get(0);
		}
		
		public static void main(String[] args) {
			int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
			int k = 3;
			System.out.println(solution(stones, k));
		}

}
