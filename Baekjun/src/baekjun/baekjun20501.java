package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun20501 {
    static int userCount;
    static int questionCount;
    static int[][] friendship;
    static int[][] questions;

    static void input() {
        Reader scanner = new Reader();

        userCount = scanner.nextInt();
        friendship = new int[userCount + 1][64];
        for (int user = 1; user <= userCount; user++) {
            String status = scanner.nextLine();
            for (int idx = 0; idx < status.length(); idx++) {
                if (status.charAt(idx) == '1') {
                    friendship[user][(idx + 1) / 32] |= (1 << ((idx + 1) % 32));
                }
            }
        }

        questionCount = scanner.nextInt();
        questions = new int[questionCount][2];
        for (int idx = 0; idx < questionCount; idx++) {
            questions[idx][0] = scanner.nextInt();
            questions[idx][1] = scanner.nextInt();
        }
    }

    static void solution() {
        StringBuilder answer = new StringBuilder();
        for (int idx = 0; idx < questionCount; idx++) {
            int user1 = questions[idx][0];
            int user2 = questions[idx][1];
            long sum = 0;
            for (int idx2 = 0; idx2 < 64; idx2++) {
                sum += Integer.bitCount(friendship[user1][idx2] & friendship[user2][idx2]);
            }

            answer.append(sum).append('\n');
        }

        System.out.print(answer);
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
