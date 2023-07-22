package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class baekjun2086 {
    static final int DIVISOR = 1_000_000_000;
    static long a, b;
    static Map<Long, Long> fibonacciNums;

    static void input() {
        Reader scanner = new Reader();

        a = scanner.nextLong();
        b = scanner.nextLong();

        // 0번째부터 n번째까지 피보나치 수의 합 = F(n + 2) - 1
        //  시그마(k = 1 ~ b)Fk = F(b + 2) - 1
        //  시그마(k = 1 ~ a)Fk = F(a + 2) - 1
        //  -> F(b + 2) - 1 - (F(a + 2) - 1) + F(a) = F(b + 2) - F(a + 2) + F(a) = F(b + 2) - (F(a + 1) + F(a)) + F(a) = F(b + 2) - F(a + 1)
        fibonacciNums = new HashMap<>();
        fibonacciNums.put(1L, 1L);
        fibonacciNums.put(2L, 1L);
        fibonacciNums.put(3L, 2L);
    }

    static void solution() {
        long f1 = getFibonacciNum(a + 1);
        long f2 = getFibonacciNum(b + 2);
        long sum = (f2 - f1 + DIVISOR) % DIVISOR;

        System.out.println(sum);
    }

    static long getFibonacciNum(long loc) {
        if(fibonacciNums.containsKey(loc)) return fibonacciNums.get(loc);
        else if(loc % 2 == 0) {
            long nextLoc = loc / 2;
            long f1 = getFibonacciNum(nextLoc - 1);
            long f2 = getFibonacciNum(nextLoc);
            long sum = ((2 * f1) + f2) * f2;

            sum = sum % DIVISOR;
            fibonacciNums.put(loc, sum);

            return sum;
        } else {
            long nextLoc = (loc + 1) / 2;
            long f1 = getFibonacciNum(nextLoc);
            long f2 = getFibonacciNum(nextLoc - 1);
            long sum = (f1 * f1) + (f2 * f2);

            sum = sum % DIVISOR;
            fibonacciNums.put(loc, sum);
            return sum;
        }
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
