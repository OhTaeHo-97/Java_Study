package programmers;

import java.util.*;

public class Level3_NumberGame {
	public static int solution(int[] A, int[] B) {
        Arrays.sort(A);
		Arrays.sort(B);
		int a = 0, score = 0;
		for(int b = 0; b < B.length; b++) {
			if(A[a] < B[b]) {
				a++;
				score++;
			}
		}
		return score;
    }
	
	public static void main(String[] args) {
		int[] A = {5,1,3,7}, B = {2,2,6,8};
//		int[] A = {2,2,2,2}, B = {1,1,1,1};
		System.out.println(solution(A, B));
	}
}
