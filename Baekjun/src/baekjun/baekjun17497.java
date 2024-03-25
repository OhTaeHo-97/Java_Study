package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjun17497 {
    static long number;

    static void input() {
        Reader scanner = new Reader();
        number = scanner.nextLong();
    }

    static void solution() {
        Stack<String> operators = new Stack<>();
        while (number != 0) {
            if ((number & 1) == 1) {
                number *= 2;
                operators.push("[/]");
            } else if ((number & 2) != 0) {
                number -= 2;
                operators.push("[+]");
            } else {
                number /= 2;
                operators.push("[*]");
            }
        }

        if (operators.size() > 99) {
            System.out.println(-1);
        } else {
            StringBuilder answer = new StringBuilder();
            answer.append(operators.size()).append('\n');
            while (!operators.isEmpty()) {
                answer.append(operators.pop()).append(' ');
            }
            System.out.println(answer);
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
            while (st == null || !st.hasMoreElements()) {
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
