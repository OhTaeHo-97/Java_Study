package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2854 {
    static final int DIVISOR = 1_000_000_007;
    static int questionCount;
    static int[] exactPointQuestionCount;
    static int[] ambiguousPointQuestionCount;

    static void input() {
        Reader scanner = new Reader();

        questionCount = scanner.nextInt();
        exactPointQuestionCount = new int[questionCount + 1];
        ambiguousPointQuestionCount = new int[questionCount + 1];

        for (int point = 1; point <= questionCount; point++) {
            exactPointQuestionCount[point] = scanner.nextInt();
        }

        for (int point = 1; point < questionCount; point++) {
            ambiguousPointQuestionCount[point] = scanner.nextInt();
        }
    }

    static void solution() {
        long[][] dp = new long[questionCount + 1][2];
        dp[0][0] = 1;

        for (int point = 1; point <= questionCount; point++) {
            dp[point][0] = (dp[point - 1][0] + dp[point - 1][1]) *
                    (exactPointQuestionCount[point] + ambiguousPointQuestionCount[point - 1] - 1)
                    + dp[point - 1][0] % DIVISOR;

            dp[point][1] = (dp[point - 1][0] + dp[point - 1][1]) * ambiguousPointQuestionCount[point] % DIVISOR;
        }

        System.out.println(dp[questionCount][0]);
    }

    public static void main(String[] args) {
        input();
        solution();
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
