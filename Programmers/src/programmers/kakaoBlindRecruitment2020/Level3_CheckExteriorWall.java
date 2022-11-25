package programmers.kakaoBlindRecruitment2020;

public class Level3_CheckExteriorWall {
	static int[][] weakCases;
	static int size, answer;
	static int[] weakArr, distArr;
	public static int solution(int n, int[] weak, int[] dist) {
		weakCases = new int[weak.length][weak.length];
		size = n;
		answer = Integer.MAX_VALUE;
		weakArr = weak;
		distArr = dist;
		makeWeakCase();
		makeDistCase(new int[dist.length], new boolean[dist.length], 0);
		if(answer == Integer.MAX_VALUE) return -1;
		return answer;
	}
	
	static void makeWeakCase() {
		weakCases[0] = weakArr.clone();
		int[] weak = weakArr.clone();
		for(int start = 1; start < weak.length; start++) {
			int startWeak = weak[0];
			for(int idx = 1; idx < weak.length; idx++) weak[idx - 1] = weak[idx];
			weak[weak.length - 1] = startWeak + size;
			weakCases[start] = weak.clone();
		}
	}
	
	static void makeDistCase(int[] distCase, boolean[] visited, int idx) {
		if(idx == distArr.length) {
			for(int[] weak : weakCases) canCheck(distCase, weak);
			return;
		}
		for(int index = 0; index < distArr.length; index++) {
			if(!visited[index]) {
				visited[index] = true;
				distCase[idx] = distArr[index];
				makeDistCase(distCase, visited, idx + 1);
				visited[index] = false;
				distCase[idx] = 0;
			}
		}
	}
	
	static void canCheck(int[] distCase, int[] weak) {
		int cur = 0, next = 1;
		int idx = 0;
		while(idx < distCase.length && cur < weak.length) {
			next = cur + 1;
			while(next < weak.length && weak[cur] + distCase[idx] >= weak[next]) next++;
			cur = next;
			idx++;
		}
		if(cur >= weak.length) answer = Math.min(answer, idx);
	}
	
	public static void main(String[] args) {
//		int n = 12;
//		int[] weak = {1, 5, 6, 10}, dist = {1, 2, 3, 4};
		int n = 12;
		int[] weak = {1, 3, 4, 9, 10}, dist = {3, 5, 7};
		System.out.println(solution(n, weak, dist));
	}
}
