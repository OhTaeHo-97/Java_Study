package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun21757 {
    static int seriesCount;
    static int[] series;
    static int[] prefixSum;

    static void input() {
        Reader scanner = new Reader();

        seriesCount = scanner.nextInt();
        series = new int[seriesCount + 1];
        prefixSum = new int[seriesCount + 1];

        for(int idx = 1; idx <= seriesCount; idx++) {
            series[idx] = scanner.nextInt();
            prefixSum[idx] = prefixSum[idx - 1] + series[idx];
        }
    }

    static void solution() {
        if(prefixSum[seriesCount] % 4 != 0) {
            System.out.println(0);
            return;
        }

        int diff = prefixSum[seriesCount] / 4;
        // dp[i][j] = i번째까지 j번으로 나누는 경우의 수
        long[][] dp = new long[seriesCount + 1][5];
        dp[0][0] = 1;
        for(int idx = 1; idx <= seriesCount; idx++) {
            dp[idx][0] = 1;
            for(int idx2 = 1; idx2 < 4; idx2++) {
                dp[idx][idx2] = dp[idx - 1][idx2];
                if(diff * idx2 == prefixSum[idx]) {
                    dp[idx][idx2] += dp[idx - 1][idx2 - 1];
                }
            }
        }

        System.out.println(dp[seriesCount - 1][3]);
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
