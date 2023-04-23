package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun2515 {
	static int N, S;
    static int[][] paints;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        S = scanner.nextInt();
        paints = new int[N + 1][2];

        for(int idx = 1; idx <= N; idx++) {
            int height = scanner.nextInt(), price = scanner.nextInt();

            paints[idx][0] = height;
            paints[idx][1] = price;
        }
    }

    static void solution() {
        Arrays.sort(paints, (p1, p2) -> p1[0] - p2[0]);

        int[][] dp = new int[N + 1][2];
        dp[1][0] = paints[1][1];

        for(int idx = 2; idx <= N; idx++) {
            int index = upperBound(1, idx, paints[idx][0] - S);

            dp[idx][0] = Math.max(dp[index - 1][0], dp[index - 1][1]) + paints[idx][1];
            dp[idx][1] = Math.max(dp[idx - 1][0], dp[idx - 1][1]);
        }

        System.out.println(Math.max(dp[N][0], dp[N][1]));
    }

    static int upperBound(int left, int right, int target) {
        while(left < right) {
            int mid = (left + right) / 2;

            if(paints[mid][0] <= target) left = mid + 1;
            else right = mid;
        }

        return right;
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
    }
}
