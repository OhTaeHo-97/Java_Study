package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun2228 {
	static int N, M;
    static int[] nums;
    static int[][][] dp;
    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        nums = new int[N + 1];
        // dp[N][M][0] -> N개의 수를 M개의 구간을 이용하여 합하였을 때의 최댓값(N번째 수 미포함)
        // dp[N][M][1] -> N개의 수를 M개의 구간을 이용하여 합하였을 때의 최댓값(N번째 수 포함)
        dp = new int[N + 1][M + 1][2];
        for(int num = 0; num <= N; num++) {
            for(int partition = 0; partition <= M; partition++)
                Arrays.fill(dp[num][partition], Integer.MIN_VALUE);
        }

        for(int idx = 1; idx <= N; idx++)
            nums[idx] = scanner.nextInt();
    }

    static void solution() {
        dp[0][0][1] = dp[0][0][0] = 0;

        for(int num = 1; num <= N; num++) {
            // dp[N][M][0] -> N번째 수가 포함되지 않음
            // 그러므로 만들 수 있는 최대 구간 개수가 N / 2
            //  Ex. N = 2 -> 1개, N = 3 -> 1개(2개를 만들 수 있지만 현재 수가 포함되지 않으므로 1개)
            // 이 때, N - 1번째 수를 포함하는 것과 포함하지 않는 것 모두 가능하므로 dp[N - 1][M][0]과 dp[N - 1][M][1] 중 큰 값을 넣는다
            for(int partition = 0; partition <= Math.min(M, num / 2); partition++)
                dp[num][partition][0] = Math.max(dp[num - 1][partition][1], dp[num - 1][partition][0]);
            // dp[N][M][1] -> N번째 수가 포함됨
            // 그러므로 만들 수 있는 최대 구간 개수가 (N + 1) / 2
            //  Ex. N = 2 -> 1개, N = 3 -> 2개
            // 이 때, N - 1번째 수를 포함한 구간에 N번째 수가 포함되는 경우와 N - 1번째 수를 포함하지 않는 경우 2가지가 있다
            // N - 1번째 수를 포함한 구간에 N번째 수가 포함되는 경우는 dp[N - 1][M][1] + N
            // N - 1번째 수를 포함하지 않는 경우는 N - 1번째 수를 포함하지 않고 M - 1개의 구간을 이루므로 dp[N - 1][M - 1][0] + N
            //  - 두 구간은 인접할 수 없으므로 N - 1번째 수는 다른 구간에 속하면 안됨
            // 그래서 아래와 같은 점화식이 나옴
            for(int partition = 1; partition <= Math.min(M, (num + 1) / 2); partition++)
                dp[num][partition][1] = Math.max(dp[num - 1][partition - 1][0], dp[num - 1][partition][1]) + nums[num];
        }

        System.out.println(Math.max(dp[N][M][0], dp[N][M][1]));
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
