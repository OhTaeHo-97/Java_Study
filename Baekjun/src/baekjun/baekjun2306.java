package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjun2306 {
    static char[] dna;

    static void input() {
        Reader scanner = new Reader();
        dna = scanner.nextLine().toCharArray();
    }

    static void solution() {
        int[][] dp = new int[dna.length][dna.length];
        for (int length = 1; length < dna.length; length++) {
            for (int startIdx = 0; startIdx + length < dna.length; startIdx++) {
                int endIdx = startIdx + length;

                if (isKOI(startIdx, endIdx)) {
                    dp[startIdx][endIdx] = dp[startIdx + 1][endIdx - 1] + 2;
                }

                for (int midIdx = startIdx; midIdx < endIdx; midIdx++) {
                    dp[startIdx][endIdx] = Math.max(dp[startIdx][endIdx],
                            dp[startIdx][midIdx] + dp[midIdx + 1][endIdx]);
                }
            }
        }

        System.out.println(dp[0][dna.length - 1]);
    }

    static boolean isKOI(int startIdx, int endIdx) {
        return (dna[startIdx] == 'a' && dna[endIdx] == 't') || (dna[startIdx] == 'g' && dna[endIdx] == 'c');
    }

    public static void main(String[] args) {
        input();
        solution();
    }

    static class Reader {
        BufferedReader br;

        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
