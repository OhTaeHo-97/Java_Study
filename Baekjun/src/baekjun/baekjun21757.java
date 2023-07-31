package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun21757 {
    static int N;
    static long[] sums;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        sums = new long[N + 1];
        for(int idx = 1; idx <= N; idx++) {
            sums[idx] = scanner.nextInt();
            sums[idx] += sums[idx - 1];
        }
    }

    static void solution() {

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
