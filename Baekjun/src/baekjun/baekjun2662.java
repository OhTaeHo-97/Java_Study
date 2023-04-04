package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2662 {
	static int N, M, max;
    static int[][] investigationReturn, dp;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        investigationReturn = new int[N + 1][M + 1];
        dp = new int[M + 1][N + 1];
        max = Integer.MIN_VALUE;

        for(int count = 0; count < N; count++) {
            int investigationAmount = scanner.nextInt();
            for(int idx = 1; idx <= M; idx++) {
                int returnAmount = scanner.nextInt();
                investigationReturn[investigationAmount][idx] = returnAmount;
                if(idx == 1) {
                    dp[1][investigationAmount] = returnAmount;
                    max = Math.max(max, returnAmount);
                }
            }
        }
    }

    static void solution() {
        if(M == 1) {
            System.out.println(max);
            return;
        }

        int tempMax;
        int[][][] investigationAmount = new int[M + 1][N + 1][M + 1];
        for(int idx = 1; idx <= N; idx++)
            investigationAmount[1][idx][1] = idx;

        for(int idx = 2; idx <= M; idx++) {
            tempMax = Integer.MIN_VALUE;
            for(int investigation = 0; investigation <= N; investigation++) {
                if(investigation == 0) {
                    dp[idx][investigation] = max;
                    continue;
                }

                dp[idx][investigation] = Math.max(dp[idx][investigation - 1], dp[idx - 1][N - investigation] + investigationReturn[investigation][idx]);
                tempMax = Math.max(tempMax, dp[idx][investigation]);
            }

            max = Math.max(max, tempMax);
        }

        System.out.println(max);
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
