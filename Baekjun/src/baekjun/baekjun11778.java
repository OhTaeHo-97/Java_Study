package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun11778 {
    static final long DIVISOR = 1_000_000_007;
    static long n, m;

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextLong();
        m = scanner.nextLong();
    }

    static void solution() {
        long gcd = getGCD(n, m) % DIVISOR;
        long[][] fibo = getFiboNum(gcd - 1, new long[][] {{1L, 1L}, {1L, 0L}});
        System.out.println(fibo[0][0] % DIVISOR);
    }

    static long getGCD(long n1, long n2) {
        if(n2 == 0) return n1;
        return getGCD(n2, n1 % n2);
    }

    static long[][] getFiboNum(long num, long[][] mat) {
        if(num == 0 || num == 1) return mat;

        long[][] result = getFiboNum(num / 2, mat);
        result = multiplyMat(result, result);

        if(num % 2 == 1L) result = multiplyMat(result, mat);

        return result;
    }

    static long[][] multiplyMat(long[][] mat1, long[][] mat2) {
        long[][] result = new long[2][2];

        result[0][0] = (mat1[0][0] * mat2[0][0] + mat1[0][1] * mat2[1][0]) % DIVISOR;
        result[0][1] = (mat1[0][0] * mat2[0][1] + mat1[0][1] * mat2[1][1]) % DIVISOR;
        result[1][0] = (mat1[1][0] * mat2[0][0] + mat1[1][1] * mat2[1][0]) % DIVISOR;
        result[1][1] = (mat1[1][0] * mat2[1][0] + mat1[1][1] * mat2[1][1]) % DIVISOR;

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

        Long nextLong() {
            return Long.parseLong(next());
        }
    }
}
