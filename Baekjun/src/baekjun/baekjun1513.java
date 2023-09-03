package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1513 {
    static final int DIVISOR = 1_000_007;

    static int N, M, C;
    static int[][] arcades = new int[51][51]; // 각 위치의 오락실 번호를 나타내기 위한 배열
    // dp[x][y][arcadeNum][visitedNum] = (x, y)까지 오는 동안 visitedNum개만큼의 오락실을 방문했고, 가장 마지막에 방문한 오락실 번호가 arcadeNum일 때의 이동 경로 개수
    static int[][][][] dp = new int[51][51][51][51];

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        C = scanner.nextInt();

        dp[1][1][0][0] = 1; // 초기값 설정
        for(int arcadeNum = 1; arcadeNum <= C; arcadeNum++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            // 만약 시작 위치인 (1, 1)이 오락실이라면
            // 항상 해당 오락실을 방문한 상태로 시작하기 때문에 dp[1][1][(1, 1)의 오락실 번호][1]을 1로 초기화한다
            if(x == 1 && y == 1) {
                dp[1][1][0][0] = 0;
                dp[1][1][arcadeNum][1] = 1;
            }
            arcades[x][y] = arcadeNum;
        }

    }

    static void solution() {
        for(int x = 1; x <= N; x++) {
            for(int y = 1; y <= M; y++) {
                if(x == 1 && y == 1) continue;
                if(arcades[x][y] > 0) { // 만약 현재 위치가 오락실이라면
                    // 현재 위치의 오락실보다 더 낮은 번호의 오락실들을 순회하면서 경로 수를 구한다
                    // arcadeNum이 0인 경우는 이전에 오락실을 방문하지 않은 경우에 대해 경로 수를 구하기 위해 필요하다
                    for(int arcadeNum = 0; arcadeNum < arcades[x][y]; arcadeNum++) {
                        // 현재 위치가 오락실이라면 이전에는 현재 위치의 오락실보다 더 작은 번호의 오락실들을 방문한다
                        // 그러므로 현재 위치로 올 수 있는 바로 왼쪽 위치와 바로 위쪽 위치에서 현재 위치의 오락실보다 더 작은 번호의 오락실들을 마지막으로 방문했을 때의 경로 수를 모두 더한다
                        // 즉, 아래와 같은 점화식을 이용한다
                        //  - for(arcadeNum = 0 ~ arcades[x][y] - 1)
                        //      for(visitedNum = 0 ~ arcadeNum)
                        //          dp[x][y][arcadeNum][visitedNum] = sum(dp[x - 1][y][arcadeNum][visitedNum])
                        for(int visitedNum = 0; visitedNum <= arcadeNum; visitedNum++) {
                            dp[x][y][arcades[x][y]][visitedNum + 1] += dp[x - 1][y][arcadeNum][visitedNum] + dp[x][y - 1][arcadeNum][visitedNum];
                            dp[x][y][arcades[x][y]][visitedNum + 1] %= DIVISOR;
                        }
                    }
                } else { // 현재 위치가 오락실이 아니라면
                    // 현재 위치가 오락실이 아니라면 이전에 어떤 오락실들을 방문해야한다는 제한을 둘 수 없기 떄문에
                    // 모든 오락실들에 대해서 현재 위치까지 올 수 있는 모든 경로의 수를 구한다
                    // 즉, 아래와 같은 점화식을 이용한다
                    //  - for(arcadeNum = 0 ~ C)
                    //      for(visitedNum = 0 ~ arcadeNum)
                    //          dp[x][y][arcadeNum][visitedNum] = sum(dp[x - 1][y][arcadeNum][visitedNum] + dp[x][y - 1][arcadeNum][visitedNum])
                    for(int arcadeNum = 0; arcadeNum <= C; arcadeNum++) {
                        for(int visitedNum = 0; visitedNum <= arcadeNum; visitedNum++) {
                            dp[x][y][arcadeNum][visitedNum] = dp[x - 1][y][arcadeNum][visitedNum] + dp[x][y - 1][arcadeNum][visitedNum];
                            dp[x][y][arcadeNum][visitedNum] %= DIVISOR;
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int visitedNum = 0; visitedNum <= C; visitedNum++) {
            int sum = 0;
            for(int arcadeNum = 0; arcadeNum <= C; arcadeNum++) {
                sum += dp[N][M][arcadeNum][visitedNum];
            }
            sum %= DIVISOR;
            sb.append(sum).append(' ');
        }
        System.out.println(sb);
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
