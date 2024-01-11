package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun15486 {
    static int workDay;
    static int[][] counsels;

    static void input() {
        Reader scanner = new Reader();

        workDay = scanner.nextInt();
        counsels = new int[workDay + 2][2];

        for (int day = 1; day <= workDay; day++) {
            counsels[day][0] = scanner.nextInt();
            counsels[day][1] = scanner.nextInt();
        }
    }

    static void solution() {
        int[] dp = new int[workDay + 2];

        int maxProfit = 0;
        for (int day = 1; day <= workDay + 1; day++) {
            if (maxProfit < dp[day]) {
                maxProfit = dp[day];
            }

            int endDay = day + counsels[day][0];
            if (endDay > workDay + 1) {
                continue;
            }

            dp[endDay] = Math.max(dp[endDay], counsels[day][1] + maxProfit);
        }

        System.out.println(dp[workDay + 1]);
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
