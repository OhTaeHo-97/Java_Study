package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun15732 {
	static int N, K, D;
    static int[][] rules;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        K = scanner.nextInt();
        D = scanner.nextInt();
        rules = new int[K][3];

        for(int idx = 0; idx < K; idx++) {
            int A = scanner.nextInt(), B = scanner.nextInt(), C = scanner.nextInt();

            rules[idx][0] = A;
            rules[idx][1] = B;
            rules[idx][2] = C;
        }
    }

    static void solution() {
        System.out.println(binarySearch());
    }

    static int binarySearch() {
        int min = 0, max = 1_000_000;

        while(min < max) {
            int mid = (min + max) / 2;

            int sum = 0;
            for(int idx = 0; idx < K; idx++) {
                int[] rule = rules[idx];

                int boxNum = mid - rule[0];
                if(boxNum == 0) sum++;
                else if(boxNum > 0) sum += (boxNum / rule[2]) + 1;
            }

            if(sum < D) min = mid;
            else max = mid - 1;
        }

        return max + 1;
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
