package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun11401 {
	final static long DIVISOR = 1_000_000_007;
    static long N, K;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextLong();
        K = scanner.nextLong();
    }

    static void solution() {
        long numerator = factorial(N);
        long denominator = factorial(K) * factorial(N - K) % DIVISOR;

        System.out.println(numerator * pow(denominator, DIVISOR - 2) % DIVISOR);
    }

    public static long factorial(long num) {
        long multiply = 1L;

        while(num > 1) {
            multiply = (multiply * num) % DIVISOR;
            num--;
        }

        return multiply;
    }

    static long pow(long base, long exponent) {
        if(exponent == 1) return base % DIVISOR;

        long multiply = pow(base, exponent / 2);
        if(exponent % 2 == 1) return (multiply * multiply % DIVISOR) * base % DIVISOR;

        return multiply * multiply % DIVISOR;
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
