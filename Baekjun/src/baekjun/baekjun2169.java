package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2169 {
	static int N, M;
    static int[][] map;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new int[N + 1][M + 1];

        for(int row = 1; row <= N; row++) {
            for(int col = 1; col <= M; col++)
                map[row][col] = scanner.nextInt();
        }
    }

    static void solution() {
        int[][] dp = new int[N + 1][M + 1];

        dp[1][1] = map[1][1];
        for(int col = 2; col <= M; col++)
            dp[1][col] = dp[1][col - 1] + map[1][col];

        int[][] temp = new int[2][M + 2];
        for(int row = 2; row <= N; row++) {
            temp[0][0] = dp[row - 1][1];
            for(int col = 1; col <= M; col++) {
                temp[0][col] = Math.max(temp[0][col - 1], dp[row - 1][col]) + map[row][col];
            }

            temp[1][M + 1] = dp[row - 1][M];
            for(int col = M; col >= 1; col--) {
                temp[1][col] = Math.max(temp[1][col + 1], dp[row - 1][col]) + map[row][col];
            }

            for(int col = 1; col <= M; col++) {
                dp[row][col] = Math.max(temp[0][col], temp[1][col]);
            }
        }

        System.out.println(dp[N][M]);
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
