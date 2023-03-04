package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2482 {
	// dp[i][j] = k
    //  -> i개의 색에서 j개의 색을 고르는 경우의 수 = k
    // Ex. 10개의 색에서
    //  1. 10을 고르는 경우 -> 1, 2, 3, 4, 5, 6, 7, 8 8개 색에서 k - 1개를 고름
    //      -> dp[n - 2][k - 1]
    //  2. 10을 고르지 않는 경우 -> 1, 2, 3, 4, 5, 6, 7, 8, 9 9개의 색에서 k개를 고름
    //      -> dp[n - 1][k]
    //  -> 답 : dp[n][k] = dp[n - 1][k] + dp[n - 2][k - 1]
    static final int MOD = 1_000_000_003;
    static int N, K;

    static void input() {
        Reader scanner =  new Reader();

        N = scanner.nextInt();
        K = scanner.nextInt();
    }

    static void solution() {
        if(K > N / 2) {
            System.out.println(0);
            return;
        }

        int[][] dp = new int[N + 1][N + 1];
        init(dp);

        for(int colorNum = 4; colorNum <= N; colorNum++) {
            for(int chosenNum = 2; chosenNum <= (K < (colorNum / 2) ? K : (colorNum / 2)); chosenNum++) {
                dp[colorNum][chosenNum] = (dp[colorNum - 2][chosenNum - 1] + dp[colorNum - 1][chosenNum]) % MOD;
            }
        }

        System.out.println(dp[N][K]);
    }

    static void init(int[][] dp) {
        for(int colorNum = 1; colorNum <= N; colorNum++)
            dp[colorNum][1] = colorNum;
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
