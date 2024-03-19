package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun13246 {
    static final int DIVISOR = 1_000;

    static int matrixSize;
    static long exponent;
    static long[][] matrix;
    static long[][] matrixSum;

    static void input() {
        Reader scanner = new Reader();

        matrixSize = scanner.nextInt();
        exponent = scanner.nextLong();
        matrix = new long[matrixSize][matrixSize];
        matrixSum = new long[matrixSize][matrixSize];

        for (int row = 0; row < matrixSize; row++) {
            for (int col = 0; col < matrixSize; col++) {
                matrix[row][col] = scanner.nextInt();
                matrixSum[row][col] = matrix[row][col];
            }
        }
    }

    static void solution() {
        powerMatrix(exponent, matrix);
        for (int row = 0; row < matrixSize; row++) {
            for (int col = 0; col < matrixSize; col++) {
                System.out.print(matrixSum[row][col] + " ");
            }
            System.out.println();
        }
    }

    static long[][] powerMatrix(long exponent, long[][] matrix) {
        if (exponent == 1) {
//            matrixSum = multiply(matrix, sumUnitMatrix(matrix));
            return matrix;
        }

        long[][] temp = powerMatrix(exponent / 2, matrix);
        long[][] result = multiply(temp, temp);
        matrixSum = multiply(matrixSum, sumUnitMatrix(result)); // S(B - 1)
        if (exponent % 2 != 0) {
            result = multiply(result, matrix);
            matrixSum = sumMatrix(matrixSum, result);
        }

        return result;

    }

    static long[][] multiply(long[][] matrix1, long[][] matrix2) {
        long[][] result = new long[matrixSize][matrixSize];

        for (int row = 0; row < matrixSize; row++) {
            for (int col = 0; col < matrixSize; col++) {
                for (int idx = 0; idx < matrixSize; idx++) {
                    result[row][col] += matrix1[row][idx] * matrix2[idx][col];
                    result[row][col] %= DIVISOR;
                }
            }
        }

        return result;
    }

    static long[][] sumUnitMatrix(long[][] matrix) {
        long[][] result = new long[matrixSize][matrixSize];
        for (int row = 0; row < matrixSize; row++) {
            result[row] = Arrays.copyOf(matrix[row], matrixSize);
            result[row][row] += 1;
            result[row][row] %= DIVISOR;
        }

        return result;
    }

    static long[][] sumMatrix(long[][] matrix1, long[][] matrix2) {
        long[][] result = new long[matrixSize][matrixSize];
        for (int row = 0; row < matrixSize; row++) {
            for (int col = 0; col < matrixSize; col++) {
                result[row][col] = matrix1[row][col] + matrix2[row][col];
                result[row][col] %= DIVISOR;
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
