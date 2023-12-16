package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun12916 {
    static final int DIVISOR = 1_000_000_007;

    static int villageCount;
    static int targetDistance;
    static int[][] connectivity;

    static void input() {
        Reader scanner = new Reader();

        villageCount = scanner.nextInt();
        targetDistance = scanner.nextInt();
        connectivity = new int[villageCount][villageCount];

        for (int row = 0; row < villageCount; row++) {
            for (int col = 0; col < villageCount; col++) {
                connectivity[row][col] = scanner.nextInt();
            }
        }
    }

    static void solution() {
        int[][] numberOfWay = findNumberOfWay(targetDistance, connectivity);
        System.out.println(findAllNumberOfWayInKthDistance(numberOfWay));
    }

    static int findAllNumberOfWayInKthDistance(int[][] numberOfWay) {
        int answer = 0;
        for (int row = 0; row < villageCount; row++) {
            for (int col = 0; col < villageCount; col++) {
                answer += numberOfWay[row][col];
                answer %= DIVISOR;
            }
        }

        return answer;
    }

    static int[][] findNumberOfWay(int exponent, int[][] matrix) {
        if (exponent == 1) {
            return matrix;
        }

        int[][] temp = findNumberOfWay(exponent / 2, matrix);
        int[][] result = multiplyMatrix(temp, temp);
        if (exponent % 2 == 1) {
            result = multiplyMatrix(result, matrix);
        }
        return result;
    }

    static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2) {
        int[][] result = new int[villageCount][villageCount];

        for (int row = 0; row < villageCount; row++) {
            for (int col = 0; col < villageCount; col++) {
                for (int idx = 0; idx < villageCount; idx++) {
                    long temp = (long) matrix1[row][idx] * matrix2[idx][col];
                    result[row][col] += (int) (temp % DIVISOR);
                    result[row][col] %= DIVISOR;
                }
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
    }
}
