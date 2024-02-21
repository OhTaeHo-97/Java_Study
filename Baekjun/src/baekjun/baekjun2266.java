package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2266 {
    static int buildingHeight;
    static int safeCount;

    static void input() {
        Reader scanner = new Reader();

        buildingHeight = scanner.nextInt();
        safeCount = scanner.nextInt();
    }

    static void solution() {
        int[][] dp = new int[safeCount + 1][buildingHeight + 1];
        for (int height = 0; height <= buildingHeight; height++) {
            dp[1][height] = height;
        }
        for (int safe = 0; safe <= safeCount; safe++) {
            dp[safe][1] = 1;
        }

        for (int safe = 2; safe <= safeCount; safe++) {
            for (int height = 2; height <= buildingHeight; height++) {
                dp[safe][height] = Integer.MAX_VALUE;
            }
        }

        for (int safe = 2; safe <= safeCount; safe++) {
            for (int height = 2; height <= buildingHeight; height++) {
                for (int usedSafe = 1; usedSafe < height; usedSafe++) {
                    dp[safe][height] = Math.min(dp[safe][height],
                            Math.max(dp[safe - 1][usedSafe - 1], dp[safe][height - usedSafe]) + 1);
                }
            }
        }

        System.out.println(dp[safeCount][buildingHeight]);
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
