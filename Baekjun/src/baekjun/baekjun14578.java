package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun14578 {
    static final int DIVISOR = 1_000_000_007;

    static int size;

    static void input() {
        Reader scanner = new Reader();

        size = scanner.nextInt();
    }

    static void solution() {
        if(size == 1) {
            System.out.println(0);
        } else if(size == 2) {
            System.out.println(2);
        } else {
            long[] dp = new long[size + 1];
            long caseNum = 2;

            dp[0] = dp[1] = 0L;
            dp[2] = 1L;

            for(int length = 3; length <= size; length++) {
                dp[length] = (length - 1) * (dp[length - 1] + dp[length - 2]);
                dp[length] %= DIVISOR;

                caseNum *= length;
                caseNum %= DIVISOR;
            }

            System.out.println((caseNum * dp[size]) % DIVISOR);
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
