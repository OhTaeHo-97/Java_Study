package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun10714 {
    static int pieceCount;
    static int[] size;
    static long[][][] dp;

    static void input() {
        Reader scanner = new Reader();

        pieceCount = scanner.nextInt();
        size = new int[pieceCount * 2];

        for (int idx = 0; idx < pieceCount; idx++) {
            size[idx] = scanner.nextInt();
            size[pieceCount + idx] = size[idx];
        }
    }

    static void solution() {
        if (pieceCount == 1) {
            System.out.println(size[0]);
            return;
        }

        long answer = 0;
        dp = new long[2][pieceCount * 2][pieceCount * 2];
        for (int idx = 0; idx < pieceCount; idx++) {
            int totalSize = size[idx];
            answer = Math.max(answer, findMaxTotalSize(idx + 1, pieceCount - 1 + idx, 1) + totalSize);
        }

        System.out.println(answer);
    }

    static long findMaxTotalSize(int leftIdx, int rightIdx, int turn) {
        if (leftIdx == rightIdx) {
            if (turn == 0) {
                return dp[turn][leftIdx][rightIdx] = size[leftIdx];
            } else {
                return dp[turn][leftIdx][rightIdx] = 0;
            }
        }
        if (dp[turn][leftIdx][rightIdx] != 0) {
            return dp[turn][leftIdx][rightIdx];
        }

        if (turn == 0) {
            return dp[turn][leftIdx][rightIdx] = Math.max(findMaxTotalSize(leftIdx + 1, rightIdx, 1) + size[leftIdx],
                    findMaxTotalSize(leftIdx, rightIdx - 1, 1) + size[rightIdx]);
        } else {
            if (size[leftIdx] > size[rightIdx]) {
                return dp[turn][leftIdx][rightIdx] = findMaxTotalSize(leftIdx + 1, rightIdx, 0);
            } else {
                return dp[turn][leftIdx][rightIdx] = findMaxTotalSize(leftIdx, rightIdx - 1, 0);
            }
        }
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
