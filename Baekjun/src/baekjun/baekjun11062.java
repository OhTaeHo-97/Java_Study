package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun11062 {
	static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();

    static int N;
    static int[] cards;
    static int[][][] dp;

    static void input() {
        N = scanner.nextInt();
        cards = new int[N];
        dp = new int[2][N][N];

        for(int idx = 0; idx < N; idx++)
            cards[idx] = scanner.nextInt();
    }

    static void solution() {
        sb.append(rec_func(0, N - 1, 0)).append('\n');
    }

    // turn 0 -> 근우, 1 -> 명우
    // 근우 -> 얻을 수 있는 최대 점수 구하기
    // 명우 -> 얻을 수 있는 최소 점수 구하기
    static int rec_func(int leftIdx, int rightIdx, int turn) {
        if(leftIdx > rightIdx) return 0;

        if(dp[turn][leftIdx][rightIdx] != 0) return dp[turn][leftIdx][rightIdx];

        if(turn % 2 == 0)
            dp[turn][leftIdx][rightIdx] = Math.max(
                    rec_func(leftIdx + 1, rightIdx, 1) + cards[leftIdx],
                    rec_func(leftIdx, rightIdx - 1, 1) + cards[rightIdx]
            );
        else
            dp[turn][leftIdx][rightIdx] = Math.min(
                    rec_func(leftIdx + 1, rightIdx, 0),
                    rec_func(leftIdx, rightIdx - 1, 0)
            );

        return dp[turn][leftIdx][rightIdx];
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        while(T-- > 0) {
            input();
            solution();
        }
        System.out.print(sb.toString());
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
