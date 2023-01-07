package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjun1958 {
	static final int SIZE = 3;
    static String[] strings;
    static void input() {
        Reader scanner = new Reader();
        strings = new String[SIZE];
        for(int idx = 0; idx < SIZE; idx++) strings[idx] = scanner.nextLine();
    }

    static void solution() {
        System.out.println(findLCS(strings[0], strings[1], strings[2]));
    }

    static int findLCS(String str1, String str2, String str3) {
        int[][][] dp = new int[str1.length() + 1][str2.length() + 1][str3.length() + 1];
        for(int idx1 = 1; idx1 <= str1.length(); idx1++) {
            char char1 = str1.charAt(idx1 - 1);

            for(int idx2 = 1; idx2 <= str2.length(); idx2++) {
                char char2 = str2.charAt(idx2 - 1);

                for(int idx3 = 1; idx3 <= str3.length(); idx3++) {
                    char char3 = str3.charAt(idx3 - 1);

                    if(char1 == char2 && char2 == char3) {
                        dp[idx1][idx2][idx3] = dp[idx1 - 1][idx2 - 1][idx3 - 1] + 1;
                    } else {
                        dp[idx1][idx2][idx3] = Math.max(dp[idx1 -1][idx2][idx3],
                                Math.max(dp[idx1][idx2 - 1][idx3],
                                dp[idx1][idx2][idx3 - 1]));
                    }
                }
            }
        }
        return dp[str1.length()][str2.length()][str3.length()];
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
