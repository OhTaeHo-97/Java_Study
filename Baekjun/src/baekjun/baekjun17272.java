package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun17272 {
    static final int DIVISOR = 1_000_000_007;

    static long fightTime;
    static int skillTime;

    static long[] dp;

    static void input() {
        Reader scanner = new Reader();

        fightTime = scanner.nextLong();
        skillTime = scanner.nextInt();
    }

    static void solution() {
        if(fightTime < skillTime) {
            System.out.println(1);
        } else if(fightTime == skillTime) {
            System.out.println(2);
        } else if(fightTime == skillTime + 1) {
            System.out.println(3);
        } else {
            int matSize = skillTime + 1;
            long powNum = fightTime - skillTime - 1;
            long[][] mat = new long[matSize][matSize];
            for(int row = 0; row < matSize - 1; row++) {
                mat[row][row + 1] = 1L;
            }
            mat[matSize - 1][1] = mat[matSize - 1][matSize - 1] = 1L;

            mat = power(powNum, mat);

            long answer = 0;
            for(int idx = 0; idx < matSize - 2; idx++) {
                answer = (answer + mat[matSize - 1][idx]) % DIVISOR;
            }
            answer = (answer + mat[matSize - 1][matSize - 2] * 2) % DIVISOR;
            answer = (answer + mat[matSize - 1][matSize - 1] * 3) % DIVISOR;

            System.out.println(answer);
        }
    }

    static long[][] power(long exponent, long[][] mat) {
        if(exponent == 1) {
            return mat;
        }

        long[][] temp = power(exponent / 2, mat);
        long[][] result = multiplyMatrix(temp, temp);
        if(exponent % 2 == 1) {
            result = multiplyMatrix(result, mat);
        }

        return result;
    }

    static long[][] multiplyMatrix(long[][] mat1, long[][] mat2) {
        long[][] result = new long[mat1.length][mat1[0].length];

        for(int row = 0; row < mat1.length; row++) {
            for(int col = 0; col < mat1[row].length; col++) {
                for(int idx = 0; idx < mat1[0].length; idx++) {
                    result[row][col] += ((mat1[row][idx] * mat2[idx][col]) % DIVISOR);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
