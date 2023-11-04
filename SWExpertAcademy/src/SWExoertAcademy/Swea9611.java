package src.SWExoertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Swea9611 {
    static final int SIZE = 10;
    static final int TURN_SIZE = 4;
    static final String IMPOSSIBLE = "NO";

    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static int questionCount;
    static int[] candidate;

    static void input() {
        questionCount = scanner.nextInt();
        candidate = new int[SIZE];
        Arrays.fill(candidate, 1);

        int[] numbers = new int[TURN_SIZE];
        for(int question = 0; question < questionCount; question++) {
            for(int idx = 0; idx < TURN_SIZE; idx++) {
                numbers[idx] = scanner.nextInt();
            }
            String status = scanner.next();

            updateCandidate(status, numbers);
        }
    }

    static void updateCandidate(String status, int[] numbers) {
        if(status.equals(IMPOSSIBLE)) {
            Arrays.stream(numbers).forEach(number -> candidate[number] = 0);
            return;
        }
        Arrays.stream(numbers).forEach(number -> candidate[number] *= 2);
    }

    static void solution() {
        int max = Integer.MIN_VALUE;
        int maxIdx = 0;
        for(int idx = 0; idx < SIZE; idx++) {
            if(candidate[idx] > max) {
                max = candidate[idx];
                maxIdx = idx;
            }
        }
        answer.append(maxIdx).append('\n');
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
    }
}
