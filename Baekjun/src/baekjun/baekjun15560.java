package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun15560 {
    static int seriesLength;
    static int queryCount;
    static int u;
    static int v;
    static int[] series;
    static int[] seriesPrefixSum;
    static int[] prefixSum;
    static int[][] queries;

    static void input() {
        Reader scanner = new Reader();

        seriesLength = scanner.nextInt();
        queryCount = scanner.nextInt();
        u = scanner.nextInt();
        v = scanner.nextInt();

        series = new int[seriesLength + 1];
        seriesPrefixSum = new int[seriesLength + 1];
        prefixSum = new int[seriesLength + 1];
        for (int idx = 1; idx <= seriesLength; idx++) {
            series[idx] = scanner.nextInt();
            seriesPrefixSum[idx] = seriesPrefixSum[idx - 1] + series[idx];
            prefixSum[idx] = u * seriesPrefixSum[idx] + v * idx;
        }

        queries = new int[queryCount][3];
        for (int queryIdx = 0; queryIdx < queryCount; queryIdx++) {
            queries[queryIdx][0] = scanner.nextInt();
            queries[queryIdx][1] = scanner.nextInt();
            queries[queryIdx][2] = scanner.nextInt();
        }
    }

    static void solution() {
        StringBuilder answer = new StringBuilder();
        for (int queryIdx = 0; queryIdx < queryCount; queryIdx++) {
            if (queries[queryIdx][0] == 0) {
                answer.append(findMaxSum(queries[queryIdx][1], queries[queryIdx][2])).append('\n');
                continue;
            }
            changeSeries(queries[queryIdx][1], queries[queryIdx][2]);
        }

        System.out.print(answer);
    }

    static int findMaxSum(int startIdx, int endIdx) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int idx = startIdx; idx <= endIdx; idx++) {
            min = Math.min(min, prefixSum[idx - 1]);
            max = Math.max(max, prefixSum[idx] - min);
        }
        return max - v;
    }

    static void changeSeries(int seriesIdx, int value) {
        int diff = value - series[seriesIdx];
        for (int idx = seriesIdx; idx <= seriesLength; idx++) {
            seriesPrefixSum[idx] += diff;
            prefixSum[idx] += u * diff;
        }
        series[seriesIdx] = value;
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
