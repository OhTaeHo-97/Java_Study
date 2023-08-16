package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun13392 {
    // 각 숫자 나사가 0 ~ 9까지의 숫자를 나타낼 수 있으니 총 10개의 숫자를 나타낼 수 있고
    // 면 역시 10개이니 회전수도 0 ~ 9까지 10개
    static final int MAX_NUMBER = 10;
    static int N;
    static int[] start, target;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        start = new int[N + 1];
        target = new int[N + 1];

        String startStr = scanner.nextLine();
        String targetStr = scanner.nextLine();

        for(int idx = 0; idx < N; idx++) {
            start[idx + 1] = startStr.charAt(idx) - '0';
            target[idx + 1] = targetStr.charAt(idx) - '0';
        }
    }

    static void solution() {
        // dp[idx][leftTunNum]
        // => idx번째 숫자까지를 타겟 숫자로 변경하기 위해 leftTurnNum만큼 왼쪽으로 회전시켰을 때, 최소 회전 횟수
        // ex. dp[3][2] = 5 => 3번째 숫자까지의 숫자들을 타겟 숫자와 똑같이 만들기 위해 5번의 회전이 최소로 필요하고 그 중 2번이 좌측 회전이다
        int[][] dp = new int[start.length][MAX_NUMBER];
        for(int idx = 0; idx < dp.length; idx++)
            Arrays.fill(dp[idx], Integer.MAX_VALUE);

        // 초기화
        // 0번 index는 0부터 시작한다 가정하고 0부터 9까지 왼쪽 회전으로 돌 때 필요한 횟수를 저장
        for(int idx = 0; idx < MAX_NUMBER; idx++)
            dp[0][idx] = idx;

        // 첫 번째 숫자부터 마지막 숫자까지 순회하며 최소 회전 횟수를 구한다
        for(int idx = 1; idx <= N; idx++) {
            // 각 숫자에 대해 왼쪽으로 회전한 횟수를 0일 때부터 9일 때까지 순회하며 그때 필요한 최소 회전 횟수를 구한다
            for(int leftTurnNum = 0; leftTurnNum < MAX_NUMBER; leftTurnNum++) {
                // 현재 숫자를 타겟 숫자로 변경시키기 위해 필요한 왼쪽 회전 횟수를 구할 때에는 아래와 같은 수식을 이용한다
                // => 현재 숫자는 이전까지 왼쪽으로 회전한 횟수만큼 마찬가지로 왼쪽으로 회전되어 있는 상태이므로 현재 숫자는 (start[idx] + leftTurnNum) 상태일 것이다
                //    왼쪽 회전 횟수는 (타겟 숫자 - 현재 숫자)를 통해 구할 수 있으므로 (target[idx] - (start[idx] + leftTurnNum))일 것이다
                //    그러나 저 수식만으로는 음수가 나올 수 있기 때문에 leftTurnNum까지 고려하여 20을 더해준 후에 10으로 나눈 나머지가 왼쪽으로 회전한 횟수가 된다
                int curLeftTurnNum = (target[idx] - start[idx] - leftTurnNum + 20) % 10;
                // 총 10번의 회전이 가능하므로 (10 - 왼쪽 회전수)가 오른쪽 회전수가 된다
                int curRightTurnNum = 10 - curLeftTurnNum;

                // 오른쪽으로 회전시켰을 때에는
                // 왼쪽으로 회전한 것이 아니기 때문에 leftTurnNum에는 영향을 미치지 않을 것이므로,
                // dp[idx][leftTurnNum]과 바로 이전에 leftTurnNum만큼 왼쪽으로 회전시켰을 때의 최소 회전 횟수에 현재 오른쪽으로 회전시킨 횟수를 더한 횟수 중 더 적은 횟수가 dp[idx][leftTurnNum]이 된다
                dp[idx][leftTurnNum] = Math.min(dp[idx][leftTurnNum], dp[idx - 1][leftTurnNum] + curRightTurnNum);
                // 왼쪽으로 회전시켰을 때에는
                // 현재 왼쪽으로 회전시킨 횟수는 (leftTurnNum + 왼쪽 회전 횟수) % 10이 될것이므로 이 값을 left라고 한다면 dp[idx][left]에 회전 횟수를 저장하면 된다
                // 그렇다면 dp[idx][left]와 바로 이전까지 leftTurnNum만큼 왼쪽으로 회전시켰을 때의 최소 회전 횟수에 현재 왼쪽으로 회전시킨 횟수를 더한 횟수 중 더 적은 횟수가 dp[idx][left]가 된다
                dp[idx][(leftTurnNum+ curLeftTurnNum) % MAX_NUMBER] = Math.min(dp[idx][(leftTurnNum + curLeftTurnNum) % MAX_NUMBER], dp[idx - 1][leftTurnNum] + curLeftTurnNum);
            }
        }

        // dp[N]에 있는 횟수들은 모두 타겟 숫자를 만든 회전 횟수들이므로 그 중 최소 횟수를 찾는다
        int answer = Arrays.stream(dp[N]).min().getAsInt();

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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
