package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1115 {
    static int seriesCount;
    static int[] series;

    static void input() {
        Reader scanner = new Reader();

        seriesCount = scanner.nextInt();
        series = new int[seriesCount];

        for (int idx = 0; idx < seriesCount; idx++) {
            series[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        boolean[] isVisited = new boolean[seriesCount];
        int cycleCount = 0;

        for (int idx = 0; idx < seriesCount; idx++) {
            if (isVisited[idx]) {
                continue;
            }
            cycleCount++;

            int curIdx = idx;
            while (!isVisited[curIdx]) {
                isVisited[curIdx] = true;
                curIdx = series[curIdx];
            }
        }

        if (cycleCount == 1) {
            System.out.println(0);
            return;
        }
        System.out.println(cycleCount);
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
