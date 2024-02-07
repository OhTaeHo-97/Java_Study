package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun24678 {
    static final int ROCK_NUM = 3;

    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static int[] rockCounts;

    static void input() {
        rockCounts = new int[ROCK_NUM];
        for (int idx = 0; idx < ROCK_NUM; idx++) {
            rockCounts[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        int evenCount = 0;
        for (int idx = 0; idx < ROCK_NUM; idx++) {
            if (rockCounts[idx] % 2 == 0) {
                evenCount++;
            }
        }

        if (evenCount >= 2) {
            answer.append('R').append('\n');
            return;
        }
        answer.append('B').append('\n');
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        for (int count = 0; count < T; count++) {
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
