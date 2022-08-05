package programmers;

import java.util.*;

public class Level3_UnbrokenBuilding {
	public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] prefixSum = new int[board.length + 1][board[0].length + 1];
        for(int[] s : skill) {
            int x1 = s[1], y1 = s[2], x2 = s[3], y2 = s[4];
            int degree = (s[0] == 2) ? s[5] : -s[5];
            prefixSum[x1][y1] += degree;
            prefixSum[x1][y2 + 1] += -degree;
            prefixSum[x2 + 1][y1] += -degree;
            prefixSum[x2 + 1][y2 + 1] += degree;
        }
        for(int i = 0; i < prefixSum.length - 1; i++) {
            for(int j = 1; j < prefixSum[i].length - 1; j++) {
                prefixSum[i][j] += prefixSum[i][j - 1];
            }
        }
        for(int i = 0; i < prefixSum[0].length - 1; i++) {
            for(int j = 1; j < prefixSum.length - 1; j++) {
                prefixSum[j][i] += prefixSum[j - 1][i];
            }
        }
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] + prefixSum[i][j] > 0)
                    answer++;
            }
        }
        return answer;
    }
}
