package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun14289 {
    static final int DIVISOR = 1_000_000_007;

    static int n, m, D;
    static long[][] moveNum;

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextInt();
        m = scanner.nextInt();
        moveNum = new long[n + 1][n + 1];

        for(int road = 0; road < m; road++) {
            int building1 = scanner.nextInt(), building2 = scanner.nextInt();
            moveNum[building1][building2] = 1;
            moveNum[building2][building1] = 1;
        }

        D = scanner.nextInt();
    }

    static void solution() {
        long[][] result = calculateMoveNum(D, moveNum);
        System.out.println(result[1][1]);
    }

    static long[][] calculateMoveNum(int exponent, long[][] moveNum) {
        if(exponent == 1) return moveNum;

        long[][] temp = calculateMoveNum(exponent / 2, moveNum);
        long[][] result = multiplyMatrix(temp, temp);
        if(exponent % 2 == 1)
            result = multiplyMatrix(result, moveNum);

        return result;
    }

    static long[][] multiplyMatrix(long[][] mat1, long[][] mat2) {
        long[][] result = new long[n + 1][n + 1];

        for(int row = 0; row <= n; row++) {
            for(int col = 0; col <= n; col++) {
                for(int idx = 0; idx <= n; idx++) {
                    result[row][col] += (mat1[row][idx] * mat2[idx][col]);
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
            while(st == null || !st.hasMoreElements()) {
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
