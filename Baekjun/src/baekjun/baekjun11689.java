package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun11689 {
	static long n;

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextLong();
    }

    static void solution() {
        long pi = n;
        for(long num = 2; num * num <= n; num++) {
            if(n % num == 0) pi = pi / num * (num - 1);
            while(n % num == 0) n /= num;
        }

        if(n != 1) pi = pi / n * (n - 1);
        System.out.println(pi);
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
