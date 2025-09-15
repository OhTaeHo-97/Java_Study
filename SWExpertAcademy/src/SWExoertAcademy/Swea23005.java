package SWExoertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea23005 {
    private static Reader reader;
    private static StringBuilder sb;
    private static String s;
    private static int answer;

    private static void input() {
        s = reader.nextLine();
        answer = 0;
    }

    private static void solution() {
        if (s.indexOf('x') < 0) {
            if (!isPalindrome(s)) {
                sb.append(-1).append('\n');
            } else {
                sb.append(0).append('\n');
            }
            return;
        }

        String sWithoutX = s.replaceAll("x", "");
        if (isPalindrome(sWithoutX)) {
            int left = 0, right = s.length() - 1;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    if (s.charAt(left) != 'x' && s.charAt(right) != 'x') {
                        sb.append(-1).append('\n');
                        return;
                    }

                    answer++;
                    if (s.charAt(left) == 'x') {
                        left++;
                    } else if (s.charAt(right) == 'x') {
                        right--;
                    }
                } else {
                    left++;
                    right--;
                }
            }

            sb.append(answer).append('\n');
        } else {
            sb.append(-1).append('\n');
        }
    }

    private static boolean isPalindrome(String s) {
        int totalLength = s.length() - 1;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(totalLength - i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        reader = new Reader();
        sb = new StringBuilder();

        int t = reader.nextInt();
        for (int i = 0; i < t; i++) {
            input();
            solution();
        }
        System.out.println(sb);
    }

    static class Reader {
        BufferedReader br;
        StringTokenizer st;

        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public String nextLine() {
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
