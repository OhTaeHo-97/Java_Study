package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class baekjun2320 {
    static final int VOWEL_NUM = 5;
    static final Map<Character, Integer> VOWEL_TO_INT = new HashMap<>() {{
        put('A', 1);
        put('E', 2);
        put('I', 3);
        put('O', 4);
        put('U', 5);
    }};

    static int wordNum;
    static String[] words;
    static int[][] dp;

    static void input() {
        Reader scanner = new Reader();

        wordNum = scanner.nextInt();
        words = new String[wordNum];

        for (int idx = 0; idx < wordNum; idx++) {
            words[idx] = scanner.nextLine();
        }

        dp = new int[VOWEL_NUM + 1][1 << wordNum];
        for(int vowel = 0; vowel <= VOWEL_NUM; vowel++)
            Arrays.fill(dp[vowel], -1);
    }

    static void solution() {
        System.out.println(findMaxLength(0, 0));
    }

    static int findMaxLength(int lastIdx, int useWordStatus) {
        if(dp[lastIdx][useWordStatus] != -1) {
            return dp[lastIdx][useWordStatus];
        }

        dp[lastIdx][useWordStatus] = 0;
        for(int idx = 0; idx < wordNum; idx++) {
            String word = words[idx];
            if((useWordStatus & 1 << idx) == 0 && (lastIdx == 0 || lastIdx == VOWEL_TO_INT.get(word.charAt(0)))) {
                dp[lastIdx][useWordStatus] = Math.max(dp[lastIdx][useWordStatus],
                        word.length() + findMaxLength(VOWEL_TO_INT.get(word.charAt(word.length() - 1)), useWordStatus | 1 << idx));
            }
        }

        return dp[lastIdx][useWordStatus];
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
