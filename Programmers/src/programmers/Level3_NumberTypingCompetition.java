package src.programmers;

import java.util.Arrays;

public class Level3_NumberTypingCompetition {
    public int[][] minCost = {
            { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
            { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
            { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
            { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
            { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
            { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
            { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
            { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
            { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
            { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };

    public int[][][] dp;
    public int length;
    public String number;

    public int solution(String numbers) {
        number = numbers;
        length = numbers.length();

        dp =  new int[numbers.length() + 1][10][10];
        for(int idx = 0; idx < dp.length; idx++) {
            for(int leftNum = 0; leftNum < dp[idx].length; leftNum++) {
                Arrays.fill(dp[idx][leftNum], -1);
            }
        }

        return getMinCost(0, 4, 6);
    }

    public int getMinCost(int index, int left, int right) {
        if(index == length) {
            return 0;
        }
        if(dp[index][left][right] != -1)
            return dp[index][left][right];

        int num = number.charAt(index) - '0';
        int result = Integer.MAX_VALUE;

        if(num != right) result = Math.min(getMinCost(index + 1, num, right) + minCost[left][num], result);
        if(num != left) result = Math.min(getMinCost(index + 1, left, num) + minCost[right][num], result);
        return dp[index][left][right] = result;
    }

    public static void main(String[] args) {
        Level3_NumberTypingCompetition l = new Level3_NumberTypingCompetition();
        System.out.println(l.solution("1756"));
    }
}
