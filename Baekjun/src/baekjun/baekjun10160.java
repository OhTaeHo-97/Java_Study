package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun10160 {
    static final int DIVISOR = 1_000_000_009;
    static int N, K;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        K = scanner.nextInt();
    }

    static void solution() {
        // K개의 알파벳으로 N의 길이의 암호를 만드는 총 경우의 수는 K^N이다.
        // N이 5일 때에는 특정 패턴 2개를 제외한 나머지 모두 암호가 될 수 있으니 K^N - 2를 출력한다.
        if(N == 5) System.out.println(pow(K, N) - 2);

        // dp[n] = K개의 알파벳으로 길이가 n인 암호를 만드는 총 경우의 수
        long[] dp = new long[N + 1];
        dp[0] = 1;
        // 길이가 1인 경우부터 6인 경우까지 dp 배열을 초기화한다.
        for(int idx = 1; idx <= 5; idx++)
            dp[idx] = dp[idx - 1] * K;
        dp[5] -= 2;
        // 길이가 6인 경우에는 암호가 될 수 없는 경우의 수가 2 * 2 * K이기 때문에 전체 경우의 수에서 해당 경우의 수를 뺀다.
        // 2 * 2 * K인 이유는 암호가 될 수 없는 특정 패턴에 알파벳 하나를 더 붙이는 경우가 암호가 될 수 없는 경우가 될텐데
        // 알파벳을 특정 패턴 앞에 붙일 수도 있고 뒤에 붙일 수도 있으니 특정 패턴에 K개의 알파벳을 붙이는 경우의 수는 2 * K이다.
        // 특정 패턴이 2개기 때문에 해당 경우의 수에 2를 곱하여 2 * 2 * K가 된다.
        dp[6] = pow(K, 6) - (2 * 2 * K);

        // 길이가 7인 경우부터 N인 경우까지 dp 배열을 채운다.
        // dp 배열의 점화식은 dp[n] = dp[n - 1] * K - dp[n - 5] * 2 + dp[n - 7]이다
        //  - 현재 길이에서 만들 수 있는 암호들은 바로 전 길이에서 만든 암호에 알파벳 하나를 붙이는 경우이므로 dp[n - 1] * K가 된다.
        //  - 이때, 알파벳 하나를 붙였을 때의 5글자가 암호가 될 수 없는 특정 패턴인 경우가 존재하기 때문에 그러한 경우의 수를 빼주기 위해 dp[n - 5] * 2를 빼준다.
        //      - 특정 패턴인 5글자를 제외한 나머지 길이로 만들 수 있는 암호의 경우의 수를 구하면 이것이 특정 패턴을 포함하여 만들 수 있는 경우의 수가 되기 때문에 dp[n - 5]를 사용하고
        //      - 특정 패턴이 2개이기 때문에 dp[n - 5] * 2를 사용한다.
        //  - 그러나 암호가 될 수 없는 2개의 패턴이 ABABCBC로 겹칠 수 있으니, 이러한 중복되는 경우를 더해주기 위해 dp[n - 7]을 더해준다.
        for(int idx = 7; idx <= N; idx++) {
            dp[idx] = dp[idx - 1] * K - dp[idx - 5] * 2 + dp[idx - 7];
            dp[idx] %= DIVISOR;
            // dp 배열에 1,000,000,009로 나눈 나머지를 저장하고 있기 때문에 점화식을 통해 구한 수가 음수가 될 수 있다.
            // 그러한 경우는 양수로 변경해주어야 하기 때문에 1,000,000,009를 더해준다.(덧셈에 대해서는 모듈러 연산의 분배 법칙이 성립하기 때문)
            if(dp[idx] < 0) dp[idx] += DIVISOR;
        }

        System.out.println(dp[N] % DIVISOR);
    }

    static long pow(long num, long exponent) {
        long result = 1L;

        for(int idx = 0; idx < exponent; idx++) {
            result *= num;
            result %= DIVISOR;
        }

        return result;
    }

    public static void main(String[] args) {
        input();
        solution();
    }

    static class Reader {
        BufferedReader br;
        StringTokenizer st;

        public Reader(){
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
