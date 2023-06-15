package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun11442 {
	static final int DIVISOR = 1_000_000_007;
    static long n;

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextLong();
    }

    // 1. n이 홀수일 때
    // f(n) = f(n - 1) + f(n - 2)를 이용하면
    // f(1) = 1
    // f(3) = f(4) - f(2)
    // f(5) = f(6) - f(4)
    // f(7) = f(8) - f(6)
    // ...
    // f(2n + 1) = f(2n + 2) - f(2n)
    // 위 식들을 모두 더해보면
    // sum(f(2n + 1)) = f(2n + 2) - f(2) + f(1) = f(2n + 2)
    // 즉, f(n + 1)을 구하면 원하는 정답을 얻을 수 있다
    // 2. n이 짝수라면
    // sum(f(2n)) = sum(f(2n - 1)) = f(2n)
    // 즉, f(n)을 구하면 원하는 정답을 얻을 수 있다

    static void solution() {
        System.out.println(findFibo(n));
    }

    static int findFibo(long size) {
        int[][] mat = new int[][] {{1, 1}, {1, 0}};
        int[][] result = powMat(mat, size);

        if(size % 2 == 1) return result[0][0];
        else return result[0][1];
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

                for(int idx = 0; idx < size; idx++)
                    temp += ((long)mat1[row][idx] * mat2[idx][col]) % DIVISOR;

                result[row][col] = (int)(temp % DIVISOR);
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
