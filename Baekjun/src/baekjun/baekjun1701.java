package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjun1701 {
	static String str;

    static void input() {
        Reader scanner = new Reader();
        str = scanner.nextLine();
    }

    static void solution() {
        int index = 0;
        int len = str.length(), max = 0;
        int[] pi = new int[len];

        for(int idx = 1; idx < len; idx++) {
            while(index > 0 && str.charAt(idx) != str.charAt(index)) {
                index = pi[index - 1];
            }

            if(str.charAt(idx) == str.charAt(index)) {
                pi[idx] = ++index;
                max = Math.max(max, pi[idx]);
            }
        }

        System.out.println(max);
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
