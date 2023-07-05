package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun17435 {
	private final static int LOG = 18;

    static int m, Q;
    static int[][] dp;
    static int[][] queries;

    static void input() {
        Reader scanner = new Reader();

        m = scanner.nextInt();
        dp = new int[LOG + 1][m + 1]; // dp[row][col] = f_2^row(col)

        for(int idx = 1; idx <= m; idx++)
            dp[0][idx] = scanner.nextInt();

        Q = scanner.nextInt();
        queries = new int[Q][2];

        for(int idx = 0; idx < Q; idx++) {
            int n = scanner.nextInt(), x = scanner.nextInt();
            queries[idx][0] = n;
            queries[idx][1] = x;
        }
    }

    static void solution() {
        // f_2^row(col)에 해당하는 값 구하기
        for(int row = 1; row <= LOG; row++) {
            for(int col = 1; col <= m; col++)
                dp[row][col] = dp[row - 1][dp[row - 1][col]];
        }

        for(int idx = 0; idx < Q; idx++) {
            for(int row = LOG; row >= 0; row--) {
                int cur = (1 << row);
                if(queries[idx][0] >= cur) {
                    queries[idx][1] = dp[row][queries[idx][1]];
                    queries[idx][0] -= cur;
                    if(queries[idx][0] == 0) break;
                }
            }
            System.out.println(queries[idx][1]);
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
