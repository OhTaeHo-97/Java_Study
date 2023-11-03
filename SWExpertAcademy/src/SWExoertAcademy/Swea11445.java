package src.SWExoertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea11445 {
    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static String P;
    static String Q;

    static void input() {
        P = scanner.nextLine();
        Q = scanner.nextLine();
    }

    static void solution() {
        if(Q.equals(P + 'a')) {
            answer.append('N').append('\n');
            return;
        }
        answer.append('Y').append('\n');
    }

    public static void main(String[] args) {
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
