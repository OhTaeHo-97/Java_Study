package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1082 {
	static int N, M, min, index;
    static int[] prices;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        prices = new int[N + 1];
        min = 50;
        index = -1;

        for(int idx = 1; idx <= N; idx++) {
            prices[idx] = scanner.nextInt();

            if(min >= prices[idx]) {
                min = prices[idx];
                index = idx;
            }
        }

        M = scanner.nextInt();
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();
        char[] digits = new char[51];
        int count = 0;
        while(M >= min) {
            digits[count++] = (char) (index + '0');
            M -= min;
        }

        int start = 0;
        for(int digitIdx = 0; digitIdx < count; digitIdx++) {
            for(int idx = N - 1; idx >= 0; idx--) {
                if(prices[idx] <= M + min) {
                    digits[digitIdx] = (char)(idx + '0');
                    M += min - prices[idx];
                    break;
                }
            }

            if(digits[start] == '0') {
                start++;
                M += min;
            }
        }

        if(start == count) {
            System.out.println(0);
            return;
        }

        for(int idx = start; idx < count; idx++)
            sb.append(digits[idx]);

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
