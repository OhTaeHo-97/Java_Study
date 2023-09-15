package src.baekjun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class baekjun6988 {
    static final int MAX_NUMBER = 1_000_000;
    static int N;
    static int maxTileNum; // 타일에 적힌 자연수 중 가장 큰 자연수
    static long answer;
    static int[] tileNumbers; // 각 타일에 적힌 자연수
    static int[] numberIndexes; // 각 자연수에 해당하는 타일의 인덱스

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        answer = 0L;
        tileNumbers = new int[N + 1];
        numberIndexes = new int[MAX_NUMBER + 1];


        for(int idx = 1; idx <= N; idx++) {
            tileNumbers[idx] = scanner.nextInt();
            numberIndexes[tileNumbers[idx]] = idx;
        }
        maxTileNum = tileNumbers[N];
    }

    static void solution() {
        // dp[start][end] = (end번째 타일의 자연수 - start번째 타일의 자연수)만큼 일정하게 증가하는 등차수열에서 end번째 타일을 마지막 항으로 할 때, 최대 타일의 자연수의 합
        long[][] dp = new long[N + 1][N + 1];
        // 우선 2개의 타일에 대해서 초기화를 진행한다
        // 단, 3개 이상 건널 수 있는 경우에만 값을 초기화한다
        //  - 이는 (end번째 타일의 자연수 - start번째 타일의 자연수)만큼 일정하게 증가한다고 했을 때, 이 공차를 diff라고 한다면
        //  - end번째 타일에서 diff만큼 더했을 때 그 값이 주어진 타일에 존재한다면 최소 3개 이상의 타일을 건널 수 있음을 보장할 수 있다
        //  - 그러므로 이러한 조건을 통해 3개 이상 건널 수 있는지 확인하고 그렇다면 우선 dp[start][end]에 대한 값만 초기화한다
        calculateTimeNumberSum(dp, true);
        // dp 배열에는 3개 이상 건널 수 있는 곳에만 0이 아닌 값이 존재할 것이다
        // 그러므로 dp[start][end] 값이 0이 아닌 곳이 나온다면, end에서 (end번째 타일의 자연수 - start번째 타일의 자연수)만큼 증가한 수가 타일에 있는지 확인하고
        // 만약 존재한다면 그 위치를 last라고 할 때, dp[end][last]의 값을 dp[start][end] + (end번째 타일 값 + (end번째 타일의 자연수 - start번째 타일의 자연수))와 dp[end][last] 중 큰 값으로 갱신한다
        calculateTimeNumberSum(dp, false);
        System.out.println(answer);
    }

    static void calculateTimeNumberSum(long[][] dp, boolean isInit) {
        for(int startIdx = 1; startIdx < N; startIdx++) {
            for(int endIdx = startIdx + 1; endIdx <= N; endIdx++) {
                int diff = tileNumbers[endIdx] - tileNumbers[startIdx]; // 등차수열의 공차

                // 현재 과정이 초기화 과정이 아닌데, dp[startIdx][endIdx]가 0이라는 뜻은 3개 이상 건널 수 없는 공차라는 뜻이므로
                // 이 경우는 건너뛰고 다음 경우를 살핀다
                if(!isInit && dp[startIdx][endIdx] == 0)
                    continue;

                // endIdx부터 diff만큼 더했을 때의 수가 최대 타일의 수보다 작거나 같고 주어진 타일에 그 수가 존재한다면
                if (tileNumbers[endIdx] + diff <= maxTileNum &&
                        numberIndexes[tileNumbers[endIdx] + diff] != 0) {
                    if(isInit) { // 만약 초기화 과정일 시
                        // dp[startIdx][endIdx] 값을 startIdx번째 타일과 endIdx번째 타일의 수의 합으로 설정한다
                        init(startIdx, endIdx, dp);
                    } else { // 초기화 과정이 아니라면
                        // dp[endIdx][endIdx번째 타일의 수 + diff]의 값을 갱신한다
                        updateTileNumerSum(startIdx, endIdx, diff, dp);
                    }
                }
            }
        }
    }

    static void init(int startIdx, int endIdx, long[][] dp) {
        dp[startIdx][endIdx] = tileNumbers[startIdx] + tileNumbers[endIdx];
    }

    static void updateTileNumerSum(int startIdx, int endIdx, int diff, long[][] dp) {
        dp[endIdx][numberIndexes[tileNumbers[endIdx] + diff]] = Math.max(dp[endIdx][numberIndexes[tileNumbers[endIdx] + diff]],
                dp[startIdx][endIdx] + (tileNumbers[endIdx] + diff));

        answer = Math.max(answer,dp[endIdx][numberIndexes[tileNumbers[endIdx] + diff]]);
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
