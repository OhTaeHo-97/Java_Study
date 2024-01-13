package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun10978 {
    static final int MAX_SIZE = 20;

    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();
    static int studentCount;
    static long[] reassignmentCounts;

    static void init() {
        reassignmentCounts = new long[MAX_SIZE + 1];
        reassignmentCounts[2] = 1;

        for (int studentCount = 3; studentCount <= MAX_SIZE; studentCount++) {
            reassignmentCounts[studentCount] =
                    (reassignmentCounts[studentCount - 1] + reassignmentCounts[studentCount - 2]) * (studentCount - 1);
        }
    }

    static void input() {
        studentCount = scanner.nextInt();
    }

    static void solution() {
        answer.append(reassignmentCounts[studentCount]).append('\n');
    }

    public static void main(String[] args) {
        init();
        int T = scanner.nextInt();
        for (int test = 0; test < T; test++) {
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
