package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun15824 {
	static final int DIVISOR = 1_000_000_007;
    static int N;
    static int[] scoville;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        scoville = new int[N];

        for(int idx = 0; idx < N; idx++)
            scoville[idx] = scanner.nextInt();
    }

    static void solution() {
        Arrays.sort(scoville);

        long[] dp = new long[N];
        dp[0] = 1;
        // idx번째 수가 최대인 경우의 개수와 같음
        for(int idx = 1; idx < N; idx++)
            dp[idx] = (dp[idx - 1] * 2) % DIVISOR;

        // 모든 조합에서의 최댓값의 합 - 모든 조합에서의 최솟값의 합을 구하는 것과 같다
        // idx번째 수가 최댓값일 때 -> 2^idx
        // idx번째 수가 최솟값일 때 -> 2^(N - 1 - idx)
        long answer = 0;
        for(int idx = 0; idx < N; idx++)
            answer = (answer + (scoville[idx] * (dp[idx] - dp[(N - 1) - idx]))) % DIVISOR;

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
