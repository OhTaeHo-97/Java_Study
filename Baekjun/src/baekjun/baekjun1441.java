package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1441 {
    static int seriesCount;
    static int minNumber;
    static int maxNumber;
    static int answer;
    static int bit;
    static int[] series;
    static boolean[] visited;

    static void input() {
        Reader scanner = new Reader();

        seriesCount = scanner.nextInt();
        minNumber = scanner.nextInt();
        maxNumber = scanner.nextInt();
        bit = 1 << seriesCount - 1;
        series = new int[seriesCount];
        visited = new boolean[1 << seriesCount];

        for (int idx = 0; idx < seriesCount; idx++) {
            series[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        for (int idx = 0; idx < seriesCount; idx++) {
            findDivisibleNumberCount(1 << idx, series[idx], 1);
        }
        System.out.println(answer);
    }

    static void findDivisibleNumberCount(int state, long number, int count) {
        if (visited[state]) {
            return;
        }

        visited[state] = true;
        if (number > maxNumber) {
            return;
        }

        long divisibleNumberCount = maxNumber / number - (minNumber - 1) / number;
        if (count % 2 == 0) {
            answer -= divisibleNumberCount;
        }
        if (count % 2 != 0) {
            answer += divisibleNumberCount;
        }

        if (state == bit) {
            return;
        }

        for (int idx = 0; idx < seriesCount; idx++) {
            if ((state & (1 << idx)) == 0) {
                findDivisibleNumberCount(state | (1 << idx),
                        calculateLcm(Math.max(number, series[idx]), Math.min(number, series[idx])), count + 1);
            }
        }
    }

    static long calculateGcd(long num1, long num2) {
        if (num2 == 0) {
            return num1;
        }
        return calculateGcd(num2, num1 % num2);
    }

    static long calculateLcm(long num1, long num2) {
        long gcd = calculateGcd(num1, num2);
        return (num1 * num2) / gcd;
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
