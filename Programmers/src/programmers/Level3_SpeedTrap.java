package programmers;

import java.util.*;

public class Level3_SpeedTrap {
	public static int solution(int[][] routes) {
        Arrays.sort(routes, new Comparator<int[]>() {
            public int compare(int[] r1, int[] r2) {
				if(r1[1] != r2[1]) return r1[1] - r2[1];
				return r1[0] - r2[0];
			}
		});
		int end = Integer.MIN_VALUE, count = 0;
		for(int[] rout : routes) {
			if(rout[0] > end) {
				end = rout[1];
				count++;
			}
		}
		return count;
    }
	
	public static void main(String[] args) {
		int[][] routes = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
		System.out.println(solution(routes));
	}
}
