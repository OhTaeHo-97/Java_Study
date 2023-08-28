package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun4355 {
    static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();

    static int n;

    static void input() {
        n = scanner.nextInt();
        if(n == 0) {
            System.out.print(sb);
            System.exit(0);
        }
    }

    static void solution() {
        if(n == 1) {
            sb.append(0).append('\n');
            return;
        }
        sb.append(eulerPhi(n)).append('\n');
    }

    static int eulerPhi(int num) {
        int result = num;
        for(int divisor = 2; divisor * divisor <= num; divisor++) {
            if(num % divisor == 0) {
                while(num % divisor == 0) {
                    num /= divisor;
                }
                result -= result / divisor;
            }
        }

        if(num > 1) {
            result -= result / num;
        }

        return result;
    }

    public static void main(String[] args) {
        while(true) {
            input();
            solution();
        }
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
