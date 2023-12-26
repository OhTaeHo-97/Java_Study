package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun14003 {
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
        List<Integer> lis = new ArrayList<>();

        for (int idx = 0; idx < seriesCount; idx++) {
            if (lis.isEmpty()) {
                lis.add(series[idx]);
                continue;
            }

            if (lis.get(lis.size() - 1) < series[idx]) {
                lis.add(series[idx]);
                continue;
            }

            int index = binarySearch(series[idx], lis);
            lis.set(index, series[idx]);
        }

        StringBuilder answer = new StringBuilder();
        answer.append(lis.size()).append('\n');
        for (int idx = 0; idx < lis.size(); idx++) {
            answer.append(lis.get(idx)).append(' ');
        }

        System.out.println(answer);
    }

    static int binarySearch(int target, List<Integer> lis) {
        int left = 0;
        int right = lis.size() - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (lis.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
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
