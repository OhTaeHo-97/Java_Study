package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun20543 {
    static int landSize;
    static int bombRange;
    static long[][] heights;
    static long[][] prefixSum;
    static long[][] answer;

    static void input() {
        Reader scanner = new Reader();

        landSize = scanner.nextInt();
        bombRange = scanner.nextInt();
        heights = new long[landSize][landSize];
        prefixSum = new long[landSize][landSize];
        answer = new long[landSize][landSize];

        for (int row = 0; row < landSize; row++) {
            for (int col = 0; col < landSize; col++) {
                heights[row][col] = scanner.nextInt();
            }
        }
    }

    static void solution() {
        if (bombRange == 1) {
            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < landSize; row++) {
                for (int col = 0; col < landSize; col++) {
                    sb.append(-heights[row][col]).append(' ');
                }
                sb.append('\n');
            }
            System.out.println(sb);
            return;
        }
        calculatePrefixSum();
        print();
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < landSize; row++) {
            for (int col = 0; col < landSize; col++) {
                sb.append(answer[row][col]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static void calculatePrefixSum() {
        for (int row = 0; row < landSize; row++) {
            for (int col = 0; col < landSize; col++) {
                int[] bombLoc = findBombLoc(row, col);
                int[] unnecessaryLoc = findUnnecessaryLoc(row, col);
                if (bombLoc[0] >= landSize || bombLoc[1] >= landSize) {
                    continue;
                }

                prefixSum[bombLoc[0]][bombLoc[1]] =
                        prefixSum[bombLoc[0] - 1][bombLoc[1]] + prefixSum[bombLoc[0]][bombLoc[1] - 1] - prefixSum[
                                bombLoc[0] - 1][bombLoc[1] - 1];
                long bombCount = -(prefixSum[bombLoc[0]][bombLoc[1]] - (
                        prefixSum[unnecessaryLoc[0]][bombLoc[1]] + prefixSum[bombLoc[0]][unnecessaryLoc[1]]
                                - prefixSum[unnecessaryLoc[0]][unnecessaryLoc[1]]) + heights[row][col]);
                answer[bombLoc[0]][bombLoc[1]] = bombCount;
                prefixSum[bombLoc[0]][bombLoc[1]] += bombCount;
            }
        }
    }

    static int[] findBombLoc(int row, int col) {
        return new int[]{row + bombRange / 2, col + bombRange / 2};
    }

    static int[] findUnnecessaryLoc(int row, int col) {
        int unnecessaryLocX = row - (bombRange / 2 + 1);
        int unnecessaryLocY = col - (bombRange / 2 + 1);
        if (unnecessaryLocX < 0) {
            unnecessaryLocX = 0;
        }
        if (unnecessaryLocY < 0) {
            unnecessaryLocY = 0;
        }

        return new int[]{unnecessaryLocX, unnecessaryLocY};
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
