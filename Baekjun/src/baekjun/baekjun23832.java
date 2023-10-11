package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun23832 {
    static int nodeNum;

    static void input() {
        Reader scanner = new Reader();

        nodeNum = scanner.nextInt();
    }

    static void solution() {
        int answer = 0;

        for(int num = nodeNum; num > 1; num--) {
            answer += eulerPhi(num);
        }

        System.out.println(answer);
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
