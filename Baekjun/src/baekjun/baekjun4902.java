package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun4902 {
    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static int lineCount;
    static int maxTriangleValue;
    static int[][] triangles;
    static int[][] prefixSum;

    static boolean input() {
        lineCount = scanner.nextInt();
        if (lineCount == 0) {
            return false;
        }

        triangles = new int[lineCount + 1][lineCount * 2];
        prefixSum = new int[lineCount + 1][lineCount * 2];

        for (int row = 1; row <= lineCount; row++) {
            for (int col = 1; col < row * 2; col++) {
                triangles[row][col] = scanner.nextInt();
                prefixSum[row][col] = prefixSum[row][col - 1] + triangles[row][col];
                maxTriangleValue = Math.max(maxTriangleValue, triangles[row][col]);
            }
        }

        return true;
    }

    static void solution() {
        int max = Math.max(findMaxTriangleValueAsc(), findMaxTriangleValueDesc());
        answer.append(max).append('\n');
    }

    static int findMaxTriangleValueAsc() {
        int max = Integer.MIN_VALUE;
        for (int row = 1; row <= lineCount; row++) {
            for (int col = 1; col < row * 2; col += 2) {
                for (int size = 0, totalTriangleValue = 0; size < lineCount - row + 1; size++) {
                    totalTriangleValue += prefixSum[row + size][col + 2 * size] - prefixSum[row + size][col - 1];
                    max = Math.max(max, totalTriangleValue);
                }
            }
        }

        return max;
    }

    static int findMaxTriangleValueDesc() {
        int max = Integer.MIN_VALUE;
        for (int row = lineCount; row > 0; row--) {
            for (int col = 2; col < row * 2; col += 2) {
                for (int size = 0, totalTriangleValue = 0; size < Math.min(col / 2, row - col / 2); size++) {
                    totalTriangleValue += prefixSum[row - size][col] - prefixSum[row - size][col - 2 * size - 1];
                    max = Math.max(max, totalTriangleValue);
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int test = 1;
        while (true) {
            if (!input()) {
                break;
            }
            answer.append(test).append(". ");
            solution();
            test++;
        }

        System.out.print(answer);
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
