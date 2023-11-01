package src.SWExoertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea3032 {
    static final StringBuilder answer = new StringBuilder();
    static final Reader scanner = new Reader();

    static int a;
    static int b;

    static void input() {
        a = scanner.nextInt();
        b = scanner.nextInt();
    }

    static void solution() {
        int[] result = extendedEuclidean(a, b);
        if (result == null) {
            answer.append(-1).append('\n');
        } else {
            answer.append(result[0]).append(' ').append(result[1]).append('\n');
        }
    }

    static int[] extendedEuclidean(int a, int b) {
        int r0 = a, r1 = b;
        int s0 = 1, s1 = 0;
        int t0 = 0, t1 = 1;
        int temp = 0, q = 0;

        while (r1 > 0) {
            q = r0 / r1;
            temp = r0;
            r0 = r1;
            r1 = temp - r1 * q;
            temp = s0;
            s0 = s1;
            s1 = temp - s1 * q;
            temp = t0;
            t0 = t1;
            t1 = temp - t1 * q;
        }

        if (r0 != 1) {
            return null;
        } else {
            return new int[]{s0, t0};
        }
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        for (int testNum = 1; testNum <= T; testNum++) {
            answer.append('#').append(testNum).append(' ');
            input();
            solution();
        }

        System.out.print(answer);
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
