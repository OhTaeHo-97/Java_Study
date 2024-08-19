package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2698 {
    private static StringBuilder sb = new StringBuilder();
    private static Reader scanner = new Reader();
    private static int bitLength;
    private static int bitCount;
    private static int[][][] dp;

    private static void init() {
        dp = new int[101][101][2];
        dp[1][0][0] = 1;
        dp[1][0][1] = 1;

        for (int bitCount = 0; bitCount <= 100; bitCount++) {
            for (int bitLength = 2; bitLength <= 100; bitLength++) {
                if (bitCount == 0) {
                    dp[bitLength][bitCount][1] = dp[bitLength - 1][bitCount][0];
                } else {
                    dp[bitLength][bitCount][1] = dp[bitLength - 1][bitCount - 1][1] + dp[bitLength - 1][bitCount][0];
                }
                dp[bitLength][bitCount][0] = dp[bitLength - 1][bitCount][0] + dp[bitLength - 1][bitCount][1];
            }
        }
    }

    private static void input() {
        bitLength = scanner.nextInt();
        bitCount = scanner.nextInt();
    }

    private static void solution() {
        sb.append(dp[bitLength][bitCount][0] + dp[bitLength][bitCount][1] + "\n");
    }

    public static void main(String[] args) {
        init();
        int testCount = scanner.nextInt();
        for (int count = 0; count < testCount; count++) {
            input();
            solution();
        }
        System.out.println(sb);
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
