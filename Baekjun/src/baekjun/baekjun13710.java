package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun13710 {
    static int seriesCount;
    static int[] series;
    static int[] xorSeries;

    static void input() {
        Reader scanner = new Reader();

        seriesCount = scanner.nextInt();
        series = new int[seriesCount];
        xorSeries = new int[seriesCount + 1];

        for (int idx = 0; idx < seriesCount; idx++) {
            series[idx] = scanner.nextInt();
            xorSeries[idx + 1] = xorSeries[idx] ^ series[idx];
        }
    }

    static void solution() {
        long[] temp = new long[30];
        for (int idx = 0; idx <= seriesCount; idx++) {
            for (int idx2 = 0; idx2 < 30; idx2++) {
                if ((xorSeries[idx] & (1 << idx2)) != 0) {
                    temp[idx2]++;
                }
            }
        }

        long answer = 0;
        for (int idx = 0; idx < 30; idx++) {
            answer += temp[idx] * (seriesCount + 1 - temp[idx]) * (1 << idx);
        }
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
