package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun7476 {
    static int sSeriesCount;
    static int aSeriesCount;
    static int[] sSeries;
    static int[] aSeries;

    static void input() {
        Reader scanner = new Reader();

        sSeriesCount = scanner.nextInt();
        sSeries = new int[sSeriesCount];
        for (int idx = 0; idx < sSeriesCount; idx++) {
            sSeries[idx] = scanner.nextInt();
        }

        aSeriesCount = scanner.nextInt();
        aSeries = new int[aSeriesCount];
        for (int idx = 0; idx < aSeriesCount; idx++) {
            aSeries[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        int[][] dp = new int[aSeriesCount + 1][sSeriesCount + 1];

        for (int aSeriesIdx = 1; aSeriesIdx <= aSeriesCount; aSeriesIdx++) {
            for (int sSeriesIdx = 1; sSeriesIdx <= sSeriesCount; sSeriesIdx++) {
                dp[aSeriesIdx][sSeriesIdx] = Math.max(dp[aSeriesIdx][sSeriesIdx - 1], dp[aSeriesIdx - 1][sSeriesIdx]);
                if (aSeries[aSeriesIdx - 1] == sSeries[sSeriesIdx - 1]) {
                    dp[aSeriesIdx][sSeriesIdx] = Math.max(dp[aSeriesIdx][sSeriesIdx],
                            dp[aSeriesIdx - 1][sSeriesIdx - 1] + 1);
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        answer.append(dp[aSeriesCount][sSeriesCount]).append('\n');

        int row = aSeriesCount;
        int col = sSeriesCount;
        List<Integer> list = new ArrayList<>();
        while (true) {
            if (dp[row - 1][col] == dp[row][col] && row > 0) {
                row--;
                continue;
            }
            if (dp[row][col - 1] == dp[row][col] && col > 0) {
                col--;
                continue;
            }

            list.add(aSeries[row - 1]);
            if (dp[row][col] == 1) {
                break;
            }
            row--;
            col--;
        }

        for (int idx = list.size() - 1; idx >= 0; idx--) {
            answer.append(list.get(idx)).append(' ');
        }

        System.out.println(answer);
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
