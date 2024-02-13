package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun20181 {
    static int larvaCount;
    static int minSatisfactionLevel;
    static int[] satisfactionLevels;

    static void input() {
        Reader scanner = new Reader();

        larvaCount = scanner.nextInt();
        minSatisfactionLevel = scanner.nextInt();
        satisfactionLevels = new int[larvaCount];

        for (int idx = 0; idx < larvaCount; idx++) {
            satisfactionLevels[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        System.out.println(twoPointer());
    }

    static long twoPointer() {
        int left = 0;
        int right = 1;
        int sum = satisfactionLevels[left];
        long[] dp = new long[larvaCount + 1];

        while (right <= larvaCount) {
            if (sum >= minSatisfactionLevel) {
                while (sum >= minSatisfactionLevel) {
                    dp[right] = Math.max(dp[right], dp[left] + sum - minSatisfactionLevel);
                    sum -= satisfactionLevels[left];
                    left++;
                }
            } else {
                dp[right] = Math.max(dp[right], dp[right - 1]);
                if (right == larvaCount) {
                    break;
                }
                sum += satisfactionLevels[right];
                right++;
            }
        }

        return dp[larvaCount];
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

        public String next() {
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
