package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun3037 {
    static final int DIVISOR = 1_000_000_007;

    static int seriesCount;
    static int confusion;

    static void input() {
        Reader scanner = new Reader();

        seriesCount = scanner.nextInt();
        confusion = scanner.nextInt();
    }

    static void solution() {
        if (seriesCount == 1) {
            System.out.println(0);
            return;
        }
        if (confusion == 0) {
            System.out.println(1);
            return;
        }

        int[][] dp = new int[seriesCount + 1][confusion + 1];
        init(dp);
        fillAllNumberOfSeries(dp);

        System.out.println((dp[seriesCount][confusion] - dp[seriesCount][confusion - 1] + DIVISOR) % DIVISOR);
    }

    static void init(int[][] dp) {
        dp[2][0] = 1;
        for (int idx = 1; idx <= confusion; idx++) {
            dp[2][idx] = 2;
        }
    }

    static void fillAllNumberOfSeries(int[][] dp) {
        for (int series = 3; series <= seriesCount; series++) {
            dp[series][0] = 1;
            fillNumberOfSeriesByConfusion(series, dp);
        }
    }

    static void fillNumberOfSeriesByConfusion(int series, int[][] dp) {
        for (int c = 1; c <= confusion; c++) {
            int firstElementConfusion = c - (series - 1);
            calculateNumberOfSeries(series, firstElementConfusion, c, dp);
        }
    }

    static void calculateNumberOfSeries(int series, int firstElementConfusion, int confusion, int[][] dp) {
        if (firstElementConfusion <= 0) {
            dp[series][confusion] = (dp[series][confusion - 1] + dp[series - 1][confusion]) % DIVISOR;
            return;
        }

        dp[series][confusion] = (dp[series][confusion - 1]
                + (dp[series - 1][confusion] - dp[series - 1][firstElementConfusion - 1]) % DIVISOR) % DIVISOR;
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
