package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun9527 {
	static long A, B;
    static long[] oneNumByBit;

    static void input() {
        Reader scanner = new Reader();

        A = scanner.nextLong();
        B = scanner.nextLong();
        oneNumByBit = new long[55];
    }

    static void solution() {
        calcAllOneNum();

        System.out.println(getOneNum(B) - getOneNum(A - 1));
    }

    static long getOneNum(long num) {
        long answer = num & 1;

        for(int idx = oneNumByBit.length - 1; idx > 0; idx--) {
            if((num & (1L << idx)) > 0L) {
                answer += oneNumByBit[idx - 1] + (num - (1L << idx) + 1);
                num -= (1L << idx);
            }
        }

        return answer;
    }

    static void calcAllOneNum() {
        oneNumByBit[0] = 1L;

        for(int idx = 1; idx < oneNumByBit.length; idx++)
            oneNumByBit[idx] = oneNumByBit[idx - 1] * 2 + (1L << idx);
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
