package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun15897 {
	static long n;

    static void input() {
        Reader scanner = new Reader();
        n = scanner.nextLong();
    }

    static void solution() {
        long num = 1, answer = n;

        for(long i = 2; i < n; i = num + 1) {
            // (N-1)/i 가 같은 범위의 최댓값
            num = (n - 1) / ((n - 1) / i);
            // (N-1)/i 일 때, 더해지는 횟수
            long count = 1 + (n - 1) / i;

            answer += (num - i + 1) * count;
        }

        if(n != 1) answer++;
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
