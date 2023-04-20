package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1947 {
	static final int MAX = 1_000_000;
    static final int DIVISOR = 1_000_000_000;
    static int N;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
    }

    static void solution() {
        if(N == 1) System.out.println(0);
        else if(N == 2) System.out.println(1);
        else {
            int[] dp = new int[MAX + 1];
            dp[1] = 0;
            dp[2] = 1;

            // 완전순열
            for(int idx = 3; idx <= N; idx++) {
                // 1번 사람이 idx번째 사람에게 선물을 준다 가정
                // 만약 idx번째 사람이 1번 사람의 선물을 선택하면 1번과 n번이 서로 교환한 상태이므로 dp[idx - 2]가지 경우를 가질 수 있음
                // 만약 idx번째 사람이 1번 사람의 선물을 선택하지 않으면 1번이 idx번 사람에게만 선물을 준 상태이므로 dp[idx - 1]가지 경우를 가질 수 있음
                // 지금은 가정상 1번과 idx번 사이에만 생각했는데, 1번 사람이 n - 1번 사람의 선물을 아무거나 선택해도 된다
                // 그러므로 dp[idx] = (idx - 1)(dp[idx - 1] + dp[idx - 2])
                long caseNum = ((long)(idx - 1) * (dp[idx - 1] + dp[idx - 2])) % DIVISOR;
                dp[idx] = (int) caseNum;
            }

            System.out.println(dp[N]);
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
