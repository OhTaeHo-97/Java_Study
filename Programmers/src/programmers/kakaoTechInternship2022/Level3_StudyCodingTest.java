package programmers.kakaoTechInternship2022;

import java.util.*;

public class Level3_StudyCodingTest {
	public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int maxAlp = Integer.MIN_VALUE, maxCop = Integer.MIN_VALUE;
        for(int[] p : problems) {
        	maxAlp = Math.max(maxAlp, p[0]);
        	maxCop = Math.max(maxCop, p[1]);
        }
        if(alp >= maxAlp && cop >= maxCop) answer = 0;
        else {
        	if(alp >= maxAlp) alp = maxAlp;
        	if(cop >= maxCop) cop = maxCop;
        	int[][] dp = new int[maxAlp + 2][maxCop + 2];
        	for(int row = 0; row <= maxAlp; row++) Arrays.fill(dp[row], Integer.MAX_VALUE);
        	dp[alp][cop] = 0;
        	for(int a = alp; a <= maxAlp; a++) {
        		for(int c = cop; c <= maxCop; c++) {
        			dp[a][c + 1] = Math.min(dp[a][c + 1], dp[a][c] + 1);
        			dp[a + 1][c] = Math.min(dp[a + 1][c], dp[a][c] + 1);
        			for(int[] problem : problems) {
        				if(a >= problem[0] && c >= problem[1]) {
        					if(a + problem[2] > maxAlp && c + problem[3] > maxCop) {
        						dp[maxAlp][maxCop] = Math.min(dp[maxAlp][maxCop], dp[a][c] + problem[4]);
        					} else if(a + problem[2] > maxAlp) {
        						dp[maxAlp][c + problem[3]] = Math.min(dp[maxAlp][c + problem[3]], dp[a][c] + problem[4]);
        					} else if(c + problem[3] > maxCop) {
        						dp[a + problem[2]][maxCop] = Math.min(dp[a + problem[2]][maxCop], dp[a][c] + problem[4]);
        					} else {
        						dp[a + problem[2]][c + problem[3]] = Math.min(dp[a + problem[2]][c + problem[3]], dp[a][c] + problem[4]);
        					}
        				}
        			}
        		}
        	}
        	answer = dp[maxAlp][maxCop];
        }
        return answer;
    }
}
