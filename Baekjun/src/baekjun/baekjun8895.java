package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun8895 {
    static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();

    static int n, l, r;
    static long[][][] dp;

    static void input() {
        n = scanner.nextInt();
        l = scanner.nextInt();
        r = scanner.nextInt();

        dp = new long[n + 1][n + 1][n + 1];
    }

    static void solution() {
        dp[1][1][1] = 1;

        for(int stickNum = 2; stickNum <= n; stickNum++) {
            for(int left = 1; left <= stickNum; left++) {
                for(int right = 1; right <= stickNum; right++) {
                    dp[stickNum][left][right] = dp[stickNum - 1][left - 1][right] + dp[stickNum - 1][left][right - 1]
                            + (dp[stickNum - 1][left][right] * (stickNum - 2));
                }
            }
        }

        sb.append(dp[n][l][r]).append('\n');
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        while(T-- > 0) {
            input();
            solution();
        }
        System.out.print(sb);
    }

    static class Reader {
        BufferedReader br;
        StringTokenizer st;

        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
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
