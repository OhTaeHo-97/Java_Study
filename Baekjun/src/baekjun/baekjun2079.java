package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun2079 {
    static String word;
    static boolean[][] palindrome;

    static void input() {
        Reader scanner = new Reader();

        word = scanner.nextLine();
        palindrome = new boolean[word.length() + 1][word.length() + 1];
    }

    static void solution() {
        findPalindrome();
        System.out.println(getMinNumOfPartialPalindrome());
    }

    static int getMinNumOfPartialPalindrome() {
        int[] dp = new int[word.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int end = 1; end <= word.length(); end++) {
            for(int start = 1; start <= end; start++) {
                if(palindrome[start][end])
                    dp[end] = Math.min(dp[end], dp[start - 1] + 1);
            }
        }

        return dp[word.length()];
    }

    static void findPalindrome() {
        for(int start = 1; start <= word.length(); start++) {
            for(int end = start; end <= word.length(); end++) {
                int startIdx = start - 1, endIdx = end - 1;
                boolean flag = true;
                while(startIdx <= endIdx) {
                    if(word.charAt(startIdx++) != word.charAt(endIdx--)) {
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
