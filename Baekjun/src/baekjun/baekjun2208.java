package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class baekjun2208 {
    static int jewelryCount;
    static int consecutiveJewelryCount;
    static int[] values;
    static int[] prefixSum;
    static TreeSet<Integer> sums;

    static void input() {
        Reader scanner = new Reader();

        jewelryCount = scanner.nextInt();
        consecutiveJewelryCount = scanner.nextInt();
        values = new int[jewelryCount + 1];
        prefixSum = new int[jewelryCount + 1];
        sums = new TreeSet<>();

        for (int idx = 1; idx <= jewelryCount; idx++) {
            values[idx] = scanner.nextInt();
            prefixSum[idx] = prefixSum[idx - 1] + values[idx];
            if (idx >= consecutiveJewelryCount) {
                sums.add(prefixSum[idx]);
            }
        }
    }

    static void solution() {
        int answer = 0;
        for (int idx = 1; idx <= jewelryCount - consecutiveJewelryCount + 1; idx++) {
            int max = sums.last();
            answer = Math.max(answer, max - prefixSum[idx - 1]);
            sums.remove(prefixSum[idx + consecutiveJewelryCount - 1]);
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
