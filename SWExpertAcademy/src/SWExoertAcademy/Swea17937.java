package src.SWExoertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea17937 {
    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static int testNumber;
    static String firstNumber;
    static String secondNumber;

    static void input() {
        firstNumber = scanner.next();
        secondNumber = scanner.next();
    }

    static void solution() {
        if(firstNumber.equals(secondNumber)) {
            answer.append(firstNumber).append('\n');
            return;
        }

        answer.append(1).append('\n');
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        testNumber = 1;

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
