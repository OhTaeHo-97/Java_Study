package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1234 {
    static int N, red, green, blue;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        red = scanner.nextInt();
        green = scanner.nextInt();
        blue = scanner.nextInt();
    }

    static void solution() {
        long[][][][] dp = new long[N + 1][red + 1][green + 1][blue + 1];

        for(int level = 0; level <= N; level++) {
            for(int r = 0; r <= red; r++) {
                for(int g = 0; g <= green; g++) {
                    for(int b = 0; b <= blue; b++) {
                        if(level == 0) {
                            dp[level][r][g][b] = 1;
                            continue;
                        }

                        // level에 해당하는 위치를 하나의 색으로만 장식할 때
                        // 이전 레벨까지의 경우의 수와 같다
                        // 그러므로 이전 레벨까지의 경우의 수를 현재 레벨에 누적하며 경우의 수를 구한다
                        if(r - level >= 0) dp[level][r][g][b] += dp[level - 1][r - level][g][b];
                        if(g - level >= 0) dp[level][r][g][b] += dp[level - 1][r][g - level][b];
                        if(b - level >= 0) dp[level][r][g][b] += dp[level - 1][r][g][b - level];

                        // level에 해당하는 위치를 두 개의 색으로 장식할 때
                        // level이 짝수가 아니면 두 색의 개수가 같아질 수 없으므로 우선 짝수인지 확인한다
                        // 두 색의 개수가 같아야 하기 때문에 level개 중에 (level / 2)개의 자리를 정해주면 된다
                        // 그러므로 이전 레벨까지의 경우의 수 * {level}C{level / 2}를 구하여 누적하며 경우의 수를 구한다
                        if(level % 2 == 0) {
                            int half = level / 2;

                            if(r - half >= 0 && g - half >= 0) dp[level][r][g][b] += dp[level - 1][r - half][g - half][b] * combination(level, half);
                            if(r - half >= 0 && b - half >= 0) dp[level][r][g][b] += dp[level - 1][r - half][g][b - half] * combination(level, half);
                            if(g - half >= 0 && b - half >= 0) dp[level][r][g][b] += dp[level - 1][r][g - half][b - half] * combination(level, half);
                        }

                        // level에 해당하는 위치를 세 개의 색으로 장식할 때
                        // level이 3의 배수가 아니라면 세 색의 개수가 같아질 수 없으므로 우선 3의 배수인지 확인한다
                        // 세 색의 개수가 같아야 하기 때문에 level개 중에 (level / 3)개의 자리를 먼저 구하고, (level - (level / 3))개 중에 (level / 3)개의 자리를 구해주면 된다
                        // 그러므로 이전 레벨까지의 경우의 수 * {level}C{level / 3} * {level - (level / 3)}C{level / 3}을 구하여 누적하며 경우의 수를 구한다
                        if(level % 3 == 0) {
                            int tripartition = level / 3;
                            if(r - tripartition >= 0 && g - tripartition >= 0 && b - tripartition >= 0)
                                dp[level][r][g][b] += dp[level - 1][r - tripartition][g - tripartition][b - tripartition] * combination(level, tripartition) * combination(level - tripartition, tripartition);
                        }
                    }
                }
            }
        }

        System.out.println(dp[N][red][green][blue]);
    }

    static int combination(int total, int num) {
        return factorial(total) / (factorial(num) * factorial(total - num));
    }

    static int factorial(int num) {
        if(num == 1) return 1;
        return num * factorial(num - 1);
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
