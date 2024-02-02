package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun13325 {
    static int height;
    static int answer;
    static int[] weights;

    static void input() {
        Reader scanner = new Reader();

        height = scanner.nextInt();
        int totalNodeCount = (int) Math.pow(2, height + 1) - 1;
        weights = new int[totalNodeCount + 1];
        for (int idx = 2; idx <= totalNodeCount; idx++) {
            weights[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        calculateWeightSum(1, 0);
        System.out.println(answer);
    }

    static int calculateWeightSum(int curIdx, int curHeight) {
        if (curHeight == height) {
            answer += weights[curIdx];
            return weights[curIdx];
        }

        int left = calculateWeightSum(curIdx * 2, curHeight + 1);
        int right = calculateWeightSum(curIdx * 2 + 1, curHeight + 1);
        answer += (weights[curIdx] + Math.abs(right - left));
        return weights[curIdx] + Math.max(left, right);
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
