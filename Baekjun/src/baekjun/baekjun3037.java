package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun3037 {
    static final int MAX_CONFUSION = 10_000;

    static int seriesCount;
    static int confusion;

    static void input() {
        Reader scanner = new Reader();

        seriesCount = scanner.nextInt();
        confusion = scanner.nextInt();
    }

    static void solution() {
        int maxConfusion = 1;
        int[][] dp = new int[seriesCount + 1][MAX_CONFUSION + 1];
        dp[1][0] = 1;
        dp[2][0] = dp[2][1] = 1;

        for (int idx = 3; idx <= seriesCount; idx++) {
            maxConfusion += (idx - 1);
            dp[idx][0] = 1;

            for (int confusionIdx = 1; confusionIdx <= maxConfusion / 2; confusionIdx++) {
                dp[idx][confusionIdx] = dp[idx][confusionIdx - 1] + dp[idx - 1][confusionIdx];
            }

            int otherIdx = maxConfusion / 2;
            if (maxConfusion % 2 == 0) {
                otherIdx--;
            }
            for (int confusionIdx = maxConfusion / 2 + 1; confusionIdx <= Math.min(MAX_CONFUSION, maxConfusion);
                 confusionIdx++) {
                dp[idx][confusionIdx] = dp[idx][otherIdx];
                otherIdx--;
            }
        }

        for (int idx = 0; idx <= seriesCount; idx++) {
            System.out.println(Arrays.toString(dp[idx]));
        }

        System.out.println(dp[seriesCount][confusion]);
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
