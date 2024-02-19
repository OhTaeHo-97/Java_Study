package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjun5875 {
    static char[] brackets;

    static void input() {
        Reader scanner = new Reader();
        brackets = scanner.nextLine().toCharArray();
    }

    static void solution() {
        int openBracket = 0;
        int closeBracket = 0;
        int totalBracket = 0;
        int answer = 0;

        for (int idx = 0; idx < brackets.length; idx++) {
            if (brackets[idx] == '(') {
                openBracket++;
                totalBracket++;
            } else {
                closeBracket++;
                totalBracket--;
            }

            if (totalBracket <= 1) {
                openBracket = 0;
            }
            if (totalBracket == -1) {
                answer = closeBracket;
                break;
            }
        }

        if (totalBracket > 0) {
            answer = openBracket;
        }

        System.out.println(answer);
    }

    public static void main(String[] args) {
        input();
        solution();
    }

    static class Reader {
        BufferedReader br;

        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
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
