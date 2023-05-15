package programmers.kakaoBlindRecruitment2023;

public class Level3_EscapeMazeCommand {
	static final char[] DIRECTION = {'d', 'l', 'r', 'u'};
    static final int[][] DIRECTION_MOVE = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    int rowNum, colNum, endX, endY;
    String answer;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        rowNum = n; colNum = m;
        endX = r - 1; endY = c - 1;
        answer = "";
        int difference = Math.abs(x - r) + Math.abs(y - c);

        dfs(x - 1, y - 1, k, "", difference);

        if(answer.equals("")) answer = "impossible";
        return answer;
    }

    public boolean dfs(int x, int y, int k, String moves, int difference) {
        if(k == 0 && difference == 0) {
            answer = moves;
            return true;
        }
        for(int dir = 0; dir < 4; dir++) {
            int cx = x + DIRECTION_MOVE[dir][0], cy = y + DIRECTION_MOVE[dir][1];

            if(isInMap(cx, cy) && difference <= k) {
                if(difference % 2 == k % 2) {
                    if(dfs(cx, cy, k - 1, moves + DIRECTION[dir], Math.abs(cx - endX) + Math.abs(cy - endY)))
                        return true;
                }
            }
        }

        return false;
    }

    public boolean isInMap(int x, int y) {
        if(x >= 0 && x < rowNum && y >= 0 && y < colNum) return true;
        return false;
    }

    public static void main(String[] args) {
        Level3_EscapeMazeCommand l = new Level3_EscapeMazeCommand();
        System.out.println(l.solution(3, 4, 2, 3, 3, 1, 5));
        System.out.println(l.solution(2, 2, 1, 1, 2, 2, 2));
        System.out.println(l.solution(3, 3, 1, 2, 3, 3, 4));
    }
}
