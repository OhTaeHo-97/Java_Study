package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun2291 {
    static final int MAX_SUM = 220;

    static int length;
    static int targetSum;
    static int targetSequence;

    static void input() {
        Reader scanner = new Reader();
        length = scanner.nextInt();
        targetSum = scanner.nextInt();
        targetSequence = scanner.nextInt();
    }

    static void solution() {
        int[][][] dp = new int[length + 1][targetSum + 1][MAX_SUM + 1];
        for (int l = 0; l < length; l++) {
            for (int sum = 0; sum < targetSum; sum++) {
                Arrays.fill(dp[l][sum], -1);
            }
        }
        Arrays.fill(dp[length][targetSum], 1);
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
