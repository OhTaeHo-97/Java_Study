package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2923 {
    static final int MAX_NUMBER = 100;

    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static int[] aSeries;
    static int[] bSeries;

    static void input() {
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        aSeries[a]++;
        bSeries[b]++;
    }

    static void solution() {
        int[] copyASeries = aSeries.clone();
        int[] copyBSeries = bSeries.clone();

        int aNumber = 0;
        int bNumber = MAX_NUMBER - 1;
        int minSum = 0;

        while (aNumber < MAX_NUMBER && bNumber >= 0) {
            aNumber = findMaxOrMinNumber(aNumber, false, copyASeries);
            bNumber = findMaxOrMinNumber(bNumber, true, copyBSeries);

            if (aNumber == -1 || bNumber == -1) {
                break;
            }

            minSum = Math.max(minSum, aNumber + bNumber);

            int minCount = Math.min(copyASeries[aNumber], copyBSeries[bNumber]);
            copyASeries[aNumber] -= minCount;
            copyBSeries[bNumber] -= minCount;
        }

        answer.append(minSum).append('\n');
    }

    static int findMaxOrMinNumber(int start, boolean isMax, int[] series) {
        if (isMax) {
            for (int number = start; number >= 0; number--) {
                if (series[number] > 0) {
                    return number;
                }
            }

            return -1;
        }

        for (int number = start; number < MAX_NUMBER; number++) {
            if (series[number] > 0) {
                return number;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int roundCount = scanner.nextInt();
        aSeries = new int[MAX_NUMBER];
        bSeries = new int[MAX_NUMBER];
        for (int round = 0; round < roundCount; round++) {
            input();
            solution();
        }
        System.out.print(answer);
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
