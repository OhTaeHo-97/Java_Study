package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun25378 {
    static int N;
    static int[] pebbleNums;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        pebbleNums = new int[N + 1];

        for(int idx = 1; idx <= N; idx++) {
            pebbleNums[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        // canDoWork1[start][end] = start번째 장소부터 end번째 장소까지 작업1만을 이용하여 모든 조약돌을 가져올 수 있는지의 여부
        boolean[][] canDoWork1 = getCanDoWork1();
        // dp[N] = 첫 번째 장소부터 N번째 장소까지 모든 조약돌을 가져올 때의 최소 작업 횟수
        int[] dp = new int[N + 1];
        dp[1] = 1; // 초기값 세팅

        // 작업1만을 통해 start부터 end까지 모든 조약돌을 가져오는 작업 횟수 -> end - start
        // 작업2만을 통해 start부터 end까지 모든 조약돌을 가져오는 작업 횟수 -> end - start + 1
        // 두 가지 경우를 생각해볼 수 있다
        //  1. 첫 번째 장소부터 N - 1번쨰 장소까지는 이전까지 구한 최소 작업 횟수로 모든 조약돌을 가져온 후 N번째 장소는 작업2를 통해 조약돌을 가져오는 경우
        //      - dp[N - 1] + 1
        //  2. x부터 N까지 범위를 작업1만으로 모든 조약돌을 가져올 수 있을 때, 첫 번째 장소부터 x - 1번째 장소까지는 이전까지 구한 최소 작업 횟수로 모든 조약돌을 가져온 후, x부터 N까지는 작업2를 통해 모든 조약돌을 가져오는 경우
        //      - dp[x - 1] + (N - x)
        //  - 두 경우를 통해 최종적으로 아래와 같은 점화식이 생성된다
        //      - dp[N] = min(dp[N - 1] + 1, dp[x - 1] + (N - x))
        for(int end = 2; end <= N; end++) {
            dp[end] = dp[end - 1] + 1;
            for(int start = 1; start < end; start++) {
                if(!canDoWork1[start][end]) {
                    continue;
                }

                dp[end] = Math.min(dp[end], dp[start - 1] + (end - start));
            }
        }

        System.out.println(dp[N]);
    }

    private static boolean[][] getCanDoWork1() {
        boolean[][] canDoWork1 = new boolean[N + 1][N + 1];

        for(int start = 1; start < N; start++) {
            int pebbleNum = pebbleNums[start];
            boolean work1 = true;

            for(int end = start + 1; end <= N; end++) {
                if(pebbleNum == pebbleNums[end]) {
                    canDoWork1[start][end] = work1;
                } else {
                    canDoWork1[start][end] = false;
                    if(pebbleNum > pebbleNums[end]) {
                        work1 = false;
                    }
                }

                pebbleNum = Math.abs(pebbleNum - pebbleNums[end]);
            }
        }

        return canDoWork1;
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
