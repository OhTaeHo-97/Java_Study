package src.SWExoertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea17642 {
    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static long a0;
    static long b0;

    static void input() {
        a0 = scanner.nextLong();
        b0 = scanner.nextLong();
    }

    static void solution() {
        if(a0 > b0) {
            answer.append(-1).append('\n');
            return;
        }
        if(a0 == b0) {
            answer.append(0).append('\n');
            return;
        }
        if(b0 - a0 == 1) {
            answer.append(-1).append('\n');
            return;
        }

        long diff = b0 - a0;
        if(diff % 2 == 0) {
            answer.append(diff / 2).append('\n');
            return;
        }

        long primeNumberCount = 0;
        long twoCount = diff / 2 - 1;
        primeNumberCount += twoCount;
        diff -= twoCount * 2;
        primeNumberCount += diff / 3;
        answer.append(primeNumberCount).append('\n');
    }

    public static void main(String args[]) {
        int T = scanner.nextInt();
        for(int test = 1; test <= T; test++) {
            answer.append('#').append(test).append(' ');
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
