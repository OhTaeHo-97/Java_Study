package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun1983 {
    static int size;
    static List<Integer> upperRow;
    static List<Integer> lowerRow;
    static int[][][] dp;

    static void input() {
        Reader scanner = new Reader();

        size = scanner.nextInt();
        upperRow = new ArrayList<>();
        lowerRow = new ArrayList<>();

        for (int idx = 0; idx < size; idx++) {
            int number = scanner.nextInt();
            if (number != 0) {
                upperRow.add(number);
            }
        }

        for (int idx = 0; idx < size; idx++) {
            int number = scanner.nextInt();
            if (number != 0) {
                lowerRow.add(number);
            }
        }

        dp = new int[upperRow.size() + 1][lowerRow.size() + 1][size + 1];
        for (int upperIdx = 0; upperIdx <= upperRow.size(); upperIdx++) {
            for (int lowerIdx = 0; lowerIdx <= lowerRow.size(); lowerIdx++) {
                Arrays.fill(dp[upperIdx][lowerIdx], Integer.MIN_VALUE);
            }
        }
    }

    static void solution() {
        int answer = calculate(upperRow.size(), lowerRow.size(), size);
        System.out.println(answer);
    }

    static int calculate(int upperIdx, int lowerIdx, int index) {
        if (upperIdx > index || lowerIdx > index) {
            return Integer.MIN_VALUE;
        }
        if (upperIdx == 0 || lowerIdx == 0 || index == 0) {
            return 0;
        }
        if (dp[upperIdx][lowerIdx][index] != Integer.MIN_VALUE) {
            return dp[upperIdx][lowerIdx][index];
        }

        int temp = Math.max(calculate(upperIdx - 1, lowerIdx, index - 1), calculate(upperIdx, lowerIdx - 1, index - 1));
        return dp[upperIdx][lowerIdx][index] = Math.max(temp,
                calculate(upperIdx - 1, lowerIdx - 1, index - 1) + (upperRow.get(upperIdx - 1) * lowerRow.get(
                        lowerIdx - 1)));
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
