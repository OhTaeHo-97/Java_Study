package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun1509 {
	static String str;
    static boolean[][] palindrome;
    static int[] dp;

    static void input() {
        Reader scanner = new Reader();
        str = scanner.next();

        int len = str.length();
        palindrome = new boolean[len + 1][len + 1];
        dp = new int[len + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
    }

    static void solution() {
        findPalindrome(str, str.length());

        for(int end = 1; end <= str.length(); end++) {
            for(int start = 1; start <= end; start++) {
                if(palindrome[start][end])
                    dp[end] = Math.min(dp[end], dp[start - 1] + 1);
            }
        }

        System.out.println(dp[str.length()]);
    }

    static void findPalindrome(String str, int len) {
        for(int start = 1; start <= len; start++) {
            for(int end = start; end <= len; end++) {
                boolean flag = true;

                int startIdx = start - 1, endIdx = end - 1;
                while(startIdx <= endIdx) {
                    if(str.charAt(startIdx++) != str.charAt(endIdx--)) {
                        flag = false;
                        break;
                    }
                }

                if(flag) palindrome[start][end] = true;
            }
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
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }
    }
}
