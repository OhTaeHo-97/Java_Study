package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun16467 {
    static final int DIVISOR = 100_000_007;

    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static int birthPeriod;
    static int targetDay;
    static int[][] matrix;

    static void input() {
        birthPeriod = scanner.nextInt();
        targetDay = scanner.nextInt();
        initMatrix(birthPeriod + 2);
    }

    static void initMatrix(int size) {
        matrix = new int[size][size];
        matrix[0][0] = matrix[0][birthPeriod] = 1;
        for (int idx = 1; idx < size; idx++) {
            matrix[idx][idx - 1] = 1;
        }
    }

    static void solution() {
        if (birthPeriod == 0) {
            answer.append((long) Math.pow(2, targetDay) % DIVISOR).append('\n');
            return;
        }
        if (targetDay <= birthPeriod) {
            answer.append(1).append('\n');
            return;
        }
        if (targetDay == birthPeriod + 1) {
            answer.append(2).append('\n');
            return;
        }
        int[][] finalResult = findChickCount(targetDay - birthPeriod - 1, matrix);
        int chickCount = finalResult[0][0] * 2;
        for (int idx = 1; idx < finalResult[0].length; idx++) {
            chickCount += finalResult[0][idx];
            chickCount %= DIVISOR;
        }
        answer.append(chickCount).append('\n');
    }

    static int[][] findChickCount(int exponent, int[][] matrix) {
        if (exponent <= 1) {
            return matrix;
        }

        int[][] temp = findChickCount(exponent / 2, matrix);
        int[][] result = multiplyMatrix(temp, temp);
        if (exponent % 2 == 1) {
            result = multiplyMatrix(result, temp);
        }
        return result;
    }

    static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2) {
        int[][] matrix = new int[matrix1.length][matrix1[0].length];

        for (int row = 0; row < matrix1.length; row++) {
            for (int col = 0; col < matrix1[row].length; col++) {
                for (int moveIdx = 0; moveIdx < matrix1[row].length; moveIdx++) {
                    matrix[row][col] += matrix1[row][moveIdx] * matrix2[moveIdx][col];
                    matrix[row][col] %= DIVISOR;
                }
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        for (int count = 0; count < T; count++) {
            input();
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
