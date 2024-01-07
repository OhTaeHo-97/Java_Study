package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun3066 {
    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static int portCount;
    static int[] portNumbers;

    static void input() {
        portCount = scanner.nextInt();
        portNumbers = new int[portCount];

        for (int idx = 0; idx < portCount; idx++) {
            portNumbers[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        List<Integer> lis = new ArrayList<>();
        lis.add(0);

        for (int idx = 0; idx < portCount; idx++) {
            if (lis.get(lis.size() - 1) < portNumbers[idx]) {
                lis.add(portNumbers[idx]);
                continue;
            }

            int index = binarySearch(portNumbers[idx], lis);
            lis.set(index, portNumbers[idx]);
        }

        if (lis.size() - 1 == 0) {
            answer.append(1).append('\n');
            return;
        }
        answer.append(lis.size() - 1).append('\n');
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
        int T = scanner.nextInt();
        for (int test = 0; test < T; test++) {
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
