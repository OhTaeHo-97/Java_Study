package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1300 {
	static int N, k;
    static long answer;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        k = scanner.nextInt();
    }

    static void solution() {
        binarySearch(1, k);
        System.out.println(answer);
    }

    static void binarySearch(int min, int max) {
        int left = min, right = max;
        while(left <= right) {
            int mid = (left + right) / 2;
            long count = 0;

            for(int row = 1; row <= N; row++) {
                count += Math.min(mid / row, N);
            }

            if(count < k) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }
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
