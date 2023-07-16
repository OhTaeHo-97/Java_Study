package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1311 {
    static int N;
    static int[][] costs, dp;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        costs = new int[N][N];

        // dp[worker][work] -> worker까지 일을 work처럼 배정했을 때 최소 비용
        // work -> 각 자리가 각 일을 했는지 안했는지를 나타내는 비트
        dp = new int[N][1 << N];

        for(int person = 0; person < N; person++) {
            for(int work = 0; work < N; work++)
                costs[person][work] = scanner.nextInt();
        }
    }

    static void solution() {
        System.out.println(findMinCost(0, 0));
    }

    static int findMinCost(int now, int bit) {
        if(now == N) return 0; // 모든 사람에게 일을 배정하여 필요한 비용을 구했다면 0을 반환
        if(dp[now][bit] != 0) return dp[now][bit]; // 이전에 해당 사람까지 bit만큼 일을 진행했었다면 그 비용을 반환

        int result = Integer.MAX_VALUE;
        for(int work = 0; work < N; work++) { // 1번 일부터 N번 일까지 현재 살마에게 배정해보면서 최소 비용을 구함
            // 만약 현재 일이 아직 배정되지 않았다면
            // 현재 일을 현재 사람에게 배정하고 재귀를 통해 모든 사람에게 일을 배정한 뒤 각 경우에 대해 (각 배정에 대한 비용 + 재귀를 통해 구한 이후의 최소 비용) 중 가장 작은 값으로 result를 갱신한다
            if((bit & (1 << work)) == 0)
                result = Math.min(result, costs[now][work] + findMinCost(now + 1, bit | (1 << work)));
        }

        // 최소 비용을 dp에 넣고 그 값을 반환한다
        dp[now][bit] = result;
        return dp[now][bit];
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
