package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun20412 {
    static long m;
    static long seed;
    static long x1;
    static long x2;

    static void input() {
        Reader scanner = new Reader();

        m = scanner.nextInt();
        seed = scanner.nextInt();
        x1 = scanner.nextInt();
        x2 = scanner.nextInt();
    }

    static void solution() {
        if (x1 == seed) {
            System.out.println(0 + " " + x1);
            return;
        }

        long a = (x2 - x1) % m;
        if (a < 0) {
            a += m;
        }
        a = (a * function(x1 - seed < 0 ? x1 - seed + m : x1 - seed, m - 2, m)) % m;
        long c = (x2 - a * x1) % m;
        c = c < 0 ? c + m : c;
        System.out.println(a + " " + c);
    }

    static long function(long x, long n, long mod) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x % mod;
        }
        if (n % 2 == 0) {
            long result = function(x, n / 2, mod) % mod;
            result = result * result;
            return result % mod;
        }
        long result = function(x, n / 2, mod) % mod;
        result = (result * result) % mod;
        return (x * result) % mod;
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
