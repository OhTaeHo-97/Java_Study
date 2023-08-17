package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun15791 {
    static final int DIVISOR = 1_000_000_007;
    static long N, M;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
    }

    static void solution() {
        long nFact = factorial(N) % DIVISOR, mFact = factorial(M) % DIVISOR;
        long base = mFact * factorial(N - M) % DIVISOR;

        long result = nFact * combination(base, DIVISOR - 2) % DIVISOR;
        System.out.println(result);
    }

    static long combination(long base, long exponent) {
        if(exponent == 1)
            return base % DIVISOR;

        long temp = combination(base, exponent / 2);

        long result = temp * temp % DIVISOR;
        if(exponent % 2 == 1)
            result = result * base % DIVISOR;

        return result;
    }

    static long factorial(long number) {
        long result = 1L;

        for(int num = 2; num <= number; num++)
            result = (result * num) % DIVISOR;

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
