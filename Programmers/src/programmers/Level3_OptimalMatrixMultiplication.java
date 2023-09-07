package src.programmers;

import java.util.Arrays;

public class Level3_OptimalMatrixMultiplication {
    public int solution(int[][] matrix_sizes) {
        int[][] dp = new int[matrix_sizes.length][matrix_sizes.length];
        init(dp, matrix_sizes);
        return getOptimalMultiplicationNum(dp, matrix_sizes);
    }

    public int getOptimalMultiplicationNum(int[][] dp, int[][] matrix_sizes) {
        for(int length = 2; length < matrix_sizes.length; length++) {
            for(int startIdx = 0; startIdx + length < matrix_sizes.length; startIdx++) {
                int endIdx = startIdx + length;
                for(int middleIdx = startIdx; middleIdx < endIdx; middleIdx++) {
                    int multiplicationNum = dp[startIdx][middleIdx] + dp[middleIdx + 1][endIdx] +
                            (matrix_sizes[startIdx][0] * matrix_sizes[middleIdx][1] * matrix_sizes[endIdx][1]);
                    dp[startIdx][endIdx] = Math.min(dp[startIdx][endIdx], multiplicationNum);
                }
            }
        }

        return dp[0][matrix_sizes.length - 1];
    }

    public void init(int[][] dp, int[][] matrix_sizes) {
        for(int idx = 0; idx < matrix_sizes.length - 1; idx++) {
            Arrays.fill(dp[idx], Integer.MAX_VALUE);
            dp[idx][idx] = 0;
            dp[idx][idx + 1] = matrix_sizes[idx][0] * matrix_sizes[idx][1] * matrix_sizes[idx + 1][1];
        }
    }

    public static void main(String[] args) {
        Level3_OptimalMatrixMultiplication l =  new Level3_OptimalMatrixMultiplication();
        System.out.println(l.solution(new int[][] {{5,3},{3,10},{10,6}}));
    }
}
