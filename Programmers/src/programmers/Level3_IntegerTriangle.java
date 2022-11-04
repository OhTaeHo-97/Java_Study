package programmers;

public class Level3_IntegerTriangle {
	public int solution(int[][] triangle) {
        if(triangle.length == 1) return triangle[0][0];
        int len = triangle.length;
        int[][] dp = new int[len][len];
        for(int row = 0; row < len; row++) {
        	for(int col = 0; col < triangle[row].length; col++)
        		dp[row][col] = triangle[row][col];
        }
        for(int row = len - 2; row >= 0; row--) {
        	for(int col = 0; col < triangle[row].length; col++)
        		dp[row][col] += Math.max(dp[row + 1][col], dp[row + 1][col + 1]);
        }
        return dp[0][0];
    }
}
