package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class baekjun15458 {
    static final int DIVISOR = 1_000_000_007;
    static final int COLOR_SIZE = 3;

    static int barnCount;
    static int paintedBarnCount;

    static Map<Integer, List<Integer>> tree;
    static int[] colors;
    static long[][] dp;

    static void input() {
        Reader scanner = new Reader();

        barnCount = scanner.nextInt();
        paintedBarnCount = scanner.nextInt();
        colors = new int[barnCount + 1];
        dp = new long[barnCount + 1][COLOR_SIZE + 1];
        tree = new HashMap<>();
        for (int barn = 1; barn <= barnCount; barn++) {
            tree.put(barn, new ArrayList<>());
        }

        for (int count = 0; count < barnCount - 1; count++) {
            int barn1 = scanner.nextInt();
            int barn2 = scanner.nextInt();

            tree.get(barn1).add(barn2);
            tree.get(barn2).add(barn1);
        }

        for (int count = 0; count < paintedBarnCount; count++) {
            colors[scanner.nextInt()] = scanner.nextInt();
        }
    }

    /*
     * 한 barn을 기준으로 1부터 3까지 색을 칠하고
     * 인접한 barn들을 탐색하며 각각을 1부터 3까지 색으로 칠하며 전체 경우의 수를 구한다
     * 이때 한 barn에서 다른 barn으로 이동하면서 나올 수 있는 경우의 수들은 곱해준다
     *  - 칠할 수 있는 경우의 수는 가능한 여러가지 색 중 하나를 칠하고, 그 색에 대해 다음 barn에서 가능한 여러가지 색 중 하나를 칠하고...
     *  - 이 과정을 반복하는 것이기 때문에 곱셈을 통해 경우의 수를 구해야 한다
     */
    static void solution() {
        long answer = 0;
        for (int color = 1; color <= COLOR_SIZE; color++) {
            answer += dfs(1, color, -1, -1);
            answer %= DIVISOR;
        }
        System.out.println(answer);
    }

    static long dfs(int curBarn, int curColor, int prevBarn, int prevColor) {
        // 인접한 barn과 현재 barn의 색이 같다면
        // 이미 칠해진 색(처음에 입력을 통해 칠해진 색)이 있는데, 현재 색상이 그 색과 다르다면
        // 문제의 조건과 맞지 않으므로 0 반환
        if (curColor == prevColor || (colors[curBarn] >= 1 && curColor != colors[curBarn])) {
            return 0;
        }
        // 이미 현재 barn을 현재 색으로 칠했을 때의 경우의 수를 구했다면
        // 그 값을 그대로 반환
        if (dp[curBarn][curColor] >= 1) {
            return dp[curBarn][curColor];
        }

        // 우선 현재 barn을 현재 색으로 칠했을 때의 경우의 수를 1로 변환
        dp[curBarn][curColor] = 1;
        // 인접한 곳을 순회하며 경우의 수 찾기
        for (int next : tree.get(curBarn)) {
            // 인접한 곳이 이전에 방문한 곳이라면 다음 barn으로 넘어감
            if (next == prevBarn) {
                continue;
            }

            long count = 0;
            // 인접한 곳을 1부터 3까지의 색으로 칠해보며
            // 인접한 barn을 칠했을 때의 경우의 수를 구함
            for (int color = 1; color <= COLOR_SIZE; color++) {
                count += dfs(next, color, curBarn, curColor);
                count %= DIVISOR;
            }

            // 현재 barn을 현재 색으로 칠했을 때의 경우의 수를 누적
            dp[curBarn][curColor] *= count;
            dp[curBarn][curColor] %= DIVISOR;
        }

        return dp[curBarn][curColor];
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
