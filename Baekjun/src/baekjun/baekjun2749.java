package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2749 {
	// 피사노 주기
	static final int DIVIDE = 1000000;
    static long n;
    static void input() {
        Reader scanner = new Reader();
        n = scanner.nextLong();
    }

    static void solution() {
        int pisano = DIVIDE / 10 * 15, objectiveIdx = (int)(n % pisano);
        int[] fibo = new int[pisano + 1];

        fibo[0] = 0;
        fibo[1] = 1;

        for(int idx = 2; idx <= pisano; idx++) {
            fibo[idx] = (fibo[idx - 1] % DIVIDE + fibo[idx - 2] % DIVIDE) % DIVIDE;
        }

        System.out.println(fibo[objectiveIdx]);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
