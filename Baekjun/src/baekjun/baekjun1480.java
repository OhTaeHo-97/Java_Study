package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1480 {
    static int jewelryNum;
    static int bagNum;
    static int bagCapacity;

    static int[] jewelryWeights;

    static void input() {
        Reader scanner = new Reader();

        jewelryNum = scanner.nextInt();
        bagNum = scanner.nextInt();
        bagCapacity = scanner.nextInt();
        jewelryWeights = new int[jewelryNum];

        for(int idx = 0; idx < jewelryNum; idx++) {
            jewelryWeights[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        // dp[capacity][bag][status] = 현재 가방 용량이 capacity만큼 남아있고, bag만큼 보석을 넣을 수 있는 가방이 남았으며, status 상태로 보석을 넣었을 때 훔칠 수 있는 보석의 최대 개수
        int[][][] dp = new int[bagCapacity + 1][bagNum][1 << jewelryNum];
        getMaxJewelryNum(bagCapacity, bagNum - 1, 0, dp);
        System.out.println(dp[bagCapacity][bagNum - 1][0]);
    }

    static int getMaxJewelryNum(int currentBag, int bagCnt, int status, int[][][] dp) {
        // 모든 가방을 사용하였거나 모든 보석을 가방에 담았다면 0을 반환한다
        if(bagCnt == -1 || status == 1 << jewelryNum - 1) {
            return 0;
        }
        // 이미 방문한 상태라면 그때의 dp값, 즉 훔칠 수 있는 최대 보석 개수를 반환한다
        if(dp[currentBag][bagCnt][status] != 0) {
            return dp[currentBag][bagCnt][status];
        }

        int result = 0;
        // 각 보석들을 순회하며 해당 보석을 가방에 넣을 수 있다면 가방에 넣고 다음 경우를 재귀를 통해 확인한다
        for(int idx = 0; idx < jewelryNum; idx++) {
            // 현재 보석이 아직 가방에 담기지 않은 보석이고 현재 가방의 남은 용량에 현재 보석을 넣을 수 있는 경우
            // 현재 가방에 해당 보석을 넣고 다음 경우를 재귀를 통해 확인하여 현재 보석을 현재 가방에 넣었을 때의 훔칠 수 있는 최대 보석 개수를 얻어온다
            // 얻어온 수를 기반으로 최댓값으로 result를 갱신한다
            if((status & 1 << idx) == 0 && currentBag - jewelryWeights[idx] >= 0) {
                result = Math.max(result, 1 + getMaxJewelryNum(currentBag - jewelryWeights[idx], bagCnt, status | 1 << idx, dp));
            }
            // 현재 보석이 아직 가방에 담기지 않은 보석이고 아직 남은 가방이 있으며, 한 가방 안에 현재 보석을 담을 수 있는 경우
            // 새로운 가방에 보석을 넣고 다음 경우를 재귀를 통해 확인하여 현재 보석을 새로운 가방에 넣을 때의 훔칠 수 있는 최대 보석 개수를 얻어온다
            // 얻어온 수를 기반으로 최댓값으로 result를 갱신한다
            if((status & 1 << idx) == 0 && bagCapacity - jewelryWeights[idx] >= 0 && bagCnt > 0) {
                result = Math.max(result, 1 + getMaxJewelryNum(bagCapacity - jewelryWeights[idx], bagCnt - 1, status | 1 << idx, dp));
            }
        }
        // result에는 현재 재귀의 상태에서 훔칠 수 있는 최대 보석 개수가 담겨있기 때문에 이를 dp에 설정한다
        dp[currentBag][bagCnt][status] = result;

        // 그리고 설정된 최대 보석 개수를 반환한다
        return dp[currentBag][bagCnt][status];
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
            while (st == null || !st.hasMoreElements()) {
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
