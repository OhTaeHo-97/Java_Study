package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun11565 {
    static String startStr;
    static String endStr;

    static void input() {
        Reader scanner = new Reader();

        startStr = scanner.nextLine();
        endStr = scanner.nextLine();
    }

    static void solution() {
        int startStrOneCount = findOneCount(startStr);
        int endStrOneCount = findOneCount(endStr);

        if ((startStrOneCount + 1) / 2 >= (endStrOneCount + 1) / 2) {
            System.out.println("VICTORY");
        } else {
            System.out.println("DEFEAT");
        }
    }

    static int findOneCount(String str) {
        int oneCount = 0;
        for (int idx = 0; idx < str.length(); idx++) {
            if (str.charAt(idx) == '1') {
                oneCount++;
            }
        }

        return oneCount;
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
