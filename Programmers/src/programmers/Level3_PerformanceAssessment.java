package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class Level3_PerformanceAssessment {
	public int solution(int[][] scores) {
        int answer = 1;
        int[] basis = scores[0].clone();

        Arrays.sort(scores, new Comparator<int[]>() {
            @Override
            public int compare(int[] s1, int[] s2) {
                if(s1[0] != s2[0]) return s2[0] - s1[0];
                return s1[1] - s2[1];
            }
        });

        int maxNum = Integer.MIN_VALUE;
        for(int idx = 0; idx < scores.length; idx++) {
            boolean isContinue = false;

            if(maxNum > scores[idx][1]) {
                isContinue = true;
            } else if(maxNum < scores[idx][1]) {
                maxNum = scores[idx][1];
            }

            if(isContinue && basis[0] == scores[idx][0] && basis[1] == scores[idx][1])
                return -1;

            if(!isContinue && (basis[0] + basis[1]) < (scores[idx][0] + scores[idx][1]))
                answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        Level3_PerformanceAssessment l = new Level3_PerformanceAssessment();
        System.out.println(l.solution(new int[][] {{2, 2}, {1, 4}, {3, 2}, {3, 2}, {2, 1}}));
        System.out.println(l.solution(new int[][] {{1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1}}));
        System.out.println(l.solution(new int[][] {{3, 1}, {1, 4}, {2, 3}, {2, 3}, {1, 5}, {1, 0}, {1, 0}}));
    }
}
