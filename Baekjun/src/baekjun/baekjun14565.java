package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun14565 {
	static long N, A;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextLong();
        A = scanner.nextLong();
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();
        sb.append(getSum()).append(' ').append(getMultiple());
        System.out.println(sb);
    }

    static long getSum() {
        return N - A;
    }

    static long getMultiple() {
        long r1 = A, r2 = N;
        long x1 = 1, x2 = 0;

        while(r2 > 0) {
            long quotient = r1 / r2;

            long temp = r1;
            r1 = r2;
            r2 = temp - quotient * r2;

            temp = x1;
            x1 = x2;
            x2 = temp - quotient * x2;
        }

        if(r1 == 1) {
            if(x1 <= 0) x1 += N;
            return x1;
        } else {
            return -1;
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
