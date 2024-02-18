package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun10564 {
    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static int pushupCount;
    static int scoreTypeCount;
    static int[] scores;
    static int[][] dp;

    static void input() {
        pushupCount = scanner.nextInt();
        scoreTypeCount = scanner.nextInt();
        scores = new int[scoreTypeCount];

        for (int idx = 0; idx < scoreTypeCount; idx++) {
            scores[idx] = scanner.nextInt();
        }

        dp = new int[pushupCount + 1][101];
        for (int row = 0; row < dp.length; row++) {
            Arrays.fill(dp[row], -1);
        }
    }

    static void solution() {
        int maxPoint = findMaxPoint(pushupCount, 1);
        if (maxPoint <= 0) {
            answer.append(-1).append('\n');
            return;
        }
        answer.append(maxPoint).append('\n');
    }

    static int findMaxPoint(int count, int turn) {
        if (count == 0) {
            return 0;
        }
        if (dp[count][turn] != -1) {
            return dp[count][turn];
        }

        dp[count][turn] = Integer.MIN_VALUE;
        for (int score = 0; score < scoreTypeCount; score++) {
            if (count - (turn * scores[score]) >= 0) {
                dp[count][turn] = Math.max(dp[count][turn],
                        findMaxPoint(count - (turn * scores[score]), turn + 1) + scores[score]);
            }
        }

        return dp[count][turn];
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        for (int test = 0; test < T; test++) {
            input();
            solution();
        }

        System.out.print(answer);
    }

    static class Reader {
        BufferedReader br;
        StringTokenizer st;

        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
