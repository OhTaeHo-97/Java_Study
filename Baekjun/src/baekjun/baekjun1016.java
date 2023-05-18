package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1016 {
	static long min, max;

    static void input() {
        Reader scanner = new Reader();

        min = scanner.nextLong();
        max = scanner.nextLong();
    }

    static void solution() {
        int len = (int)(max - min + 1);
        int maxSqrt = (int)Math.sqrt(max);

        boolean[] isNotAnswer = new boolean[len];

        for(long num = 2; num <= maxSqrt; num++) {
            long square = num * num;
            long start = min % square == 0 ? min / square : (min / square) + 1;

            for(long multiply = start; multiply * square <= max; multiply++)
                isNotAnswer[(int)(multiply * square - min)] = true;
        }

        int answerNum = 0;
        for(boolean answer : isNotAnswer)
            if(!answer) answerNum++;

        System.out.println(answerNum);
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
