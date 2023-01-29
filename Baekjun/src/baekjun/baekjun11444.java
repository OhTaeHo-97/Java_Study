package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun11444 {
	static final int DIVIDE = 1000000007;
    static long n;

    static void input() {
        Reader scanner = new Reader();
        n = scanner.nextLong();
    }

    static void solution() {
        System.out.println(findFibo(n - 1));
    }

    static int findFibo(long size) {
        int[][] mat = new int[][] {{1, 1}, {1, 0}};
        int[][] result = powMat(mat, size);

        return result[0][0];
    }

    static int[][] powMat(int[][] mat, long power) {
        if(power == 1 || power == 0) return mat;

        int[][] temp = powMat(mat, power / 2);

        if(power % 2 == 0)
            return mulMat(temp, temp);
        else
            return mulMat(mulMat(temp, temp), mat);
    }

    static int[][] mulMat(int[][] mat1, int[][] mat2) {
        int size = 2;
        int[][] result = new int[size][size];

        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                long temp = 0;

                for(int idx = 0; idx < size; idx++) {
                    temp += ((long)mat1[row][idx] * mat2[idx][col]) % DIVIDE;
                }

                result[row][col] = (int)(temp % DIVIDE);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
