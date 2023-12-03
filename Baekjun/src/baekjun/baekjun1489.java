package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun1489 {
    static int peopleCount;
    static int[] aScores;
    static int[] bScores;

    static void input() {
        Reader scanner = new Reader();

        peopleCount = scanner.nextInt();
        aScores = new int[peopleCount];
        bScores = new int[peopleCount];

        for (int idx = 0; idx < peopleCount; idx++) {
            aScores[idx] = scanner.nextInt();
        }
        for (int idx = 0; idx < peopleCount; idx++) {
            bScores[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        Arrays.sort(aScores);
        Arrays.sort(bScores);
        int[][] dp = new int[peopleCount + 1][peopleCount + 1];

        for (int row = 1; row <= peopleCount; row++) {
            for (int col = 1; col <= peopleCount; col++) {
                int score = 0;
                if (aScores[row - 1] > bScores[col - 1]) {
                    score = 2;
                }
                if (aScores[row - 1] == bScores[col - 1]) {
                    score = 1;
                }

                dp[row][col] = dp[row - 1][col - 1] + score;
            }
        }

        int answer = 0;
        for (int idx = 1; idx <= peopleCount; idx++) {
            answer = Math.max(answer, dp[peopleCount][idx]);
        }
        System.out.println(answer);
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
