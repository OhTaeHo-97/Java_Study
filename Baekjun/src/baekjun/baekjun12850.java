package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun12850 {
    static final int DIVISOR = 1_000_000_007;
    static int N;
    static long[][] map;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        map = new long[8][8];
        map[0][1] = map[0][2] = 1;
        map[1][0] = map[1][2] = map[1][3] = 1;
        map[2][0] = map[2][1] = map[2][3] = map[2][5] = 1;
        map[3][1] = map[3][2] = map[3][4] = map[3][5] = 1;
        map[4][3] = map[4][5] = map[4][6] = 1;
        map[5][2] = map[5][3] = map[5][4] = map[5][7] = 1;
        map[6][4] = map[6][7] = 1;
        map[7][5] = map[7][6] = 1;
    }

    static void solution() {
        long[][] dp = new long[8][8];
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++)
                dp[row][col] = map[row][col];
        }

        dp = myPow(dp, N);
        System.out.println(dp[0][0]);
    }

    static long[][] myPow(long[][] dp, long N) {
        if(N == 1) return dp;
        else {
            if(N % 2 == 1) {
                long[][] result = new long[8][8];
                result = multiply(dp, dp);
                return multiply(dp, myPow(result, N / 2));
            } else {
                long[][] result = new long[8][8];
                result = multiply(dp, dp);
                return myPow(result, N / 2);
            }
        }
    }

    static long[][] multiply(long[][] mat1, long[][] mat2) {
        long[][] result = new long[8][8];

        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                for(int mid = 0; mid < 8; mid++)
                    result[row][col] = (result[row][col] + mat1[row][mid] * mat2[mid][col]) % DIVISOR;
            }
        }

        return result;
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
