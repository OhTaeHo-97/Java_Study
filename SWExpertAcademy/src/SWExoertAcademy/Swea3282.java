package src.SWExoertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea3282 {
    static final int STUFF_VOLUME_INDEX = 0;
    static final int STUFF_VALUE_INDEX = 1;
    static final StringBuilder answer = new StringBuilder();
    static final Reader scanner = new Reader();

    static int stuffCount;
    static int capacity;
    static int[][] stuffs;
    static int[][] dp;

    static void input() {
        stuffCount = scanner.nextInt();
        capacity = scanner.nextInt();
        stuffs = new int[stuffCount][2];
        dp = new int[stuffCount + 1][capacity + 1];

        for (int idx = 0; idx < stuffCount; idx++) {
            stuffs[idx][STUFF_VOLUME_INDEX] = scanner.nextInt();
            stuffs[idx][STUFF_VALUE_INDEX] = scanner.nextInt();
        }
    }

    static void solution() {
        answer.append(findMaxValue()).append('\n');
    }

    static int findMaxValue() {
        for (int stuff = 1; stuff <= stuffCount; stuff++) {
            calculateEachStuffMaxValue(stuff);
        }

        return dp[stuffCount][capacity];
    }

    static void calculateEachStuffMaxValue(int stuffIndex) {
        for (int volume = 1; volume <= capacity; volume++) {
            dp[stuffIndex][volume] = findEachCaseMaxValue(stuffIndex, volume);
        }
    }

    static int findEachCaseMaxValue(int stuffIndex, int volume) {
        if (stuffs[stuffIndex - 1][STUFF_VOLUME_INDEX] > volume) {
            return dp[stuffIndex - 1][volume];
        }
        return Math.max(dp[stuffIndex - 1][volume],
                dp[stuffIndex - 1][volume - stuffs[stuffIndex - 1][STUFF_VOLUME_INDEX]]
                        + stuffs[stuffIndex - 1][STUFF_VALUE_INDEX]);
    }

    public static void main(String args[]) {
        int T = scanner.nextInt();
        for (int test = 1; test <= T; test++) {
            answer.append(String.format("#%d ", test));
            input();
            solution();
        }
        System.out.print(answer);
        scanner.close();
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

        void close() {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
