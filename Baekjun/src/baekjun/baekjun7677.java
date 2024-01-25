package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun7677 {
    static final int DIVISOR = 1_0000;
    static final int[][] FIBONACCI_MATRIX = new int[][]{{1, 1}, {1, 0}};

    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static int digit;

    static boolean input() {
        digit = scanner.nextInt();
        return digit == -1;
    }

    static void solution() {
        if (digit <= 1) {
            answer.append(digit).append('\n');
            return;
        }

        int[][] result = findFibonacci(FIBONACCI_MATRIX, digit);
        answer.append(result[0][1]).append('\n');
    }

    static int[][] findFibonacci(int[][] matrix, int exponent) {
        if (exponent == 1) {
            return matrix;
        }

        int[][] temp = findFibonacci(matrix, exponent / 2);
        int[][] result = multiplyMatrix(temp, temp);
        if (exponent % 2 != 0) {
            result = multiplyMatrix(result, matrix);
        }
        return result;
    }

    static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2) {
        int[][] result = new int[FIBONACCI_MATRIX.length][FIBONACCI_MATRIX[0].length];

        for (int row = 0; row < FIBONACCI_MATRIX.length; row++) {
            for (int col = 0; col < FIBONACCI_MATRIX[row].length; col++) {
                for (int idx = 0; idx < FIBONACCI_MATRIX[row].length; idx++) {
                    result[row][col] += matrix1[row][idx] * matrix2[idx][col];
                    result[row][col] %= DIVISOR;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        while (true) {
            if (input()) {
                break;
            }
            solution();
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
