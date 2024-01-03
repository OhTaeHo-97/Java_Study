package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun17386 {
    static int[][] line1;
    static int[][] line2;

    static void input() {
        Reader scanner = new Reader();

        line1 = new int[2][2];
        line2 = new int[2][2];

        line1[0][0] = scanner.nextInt();
        line1[0][1] = scanner.nextInt();
        line1[1][0] = scanner.nextInt();
        line1[1][1] = scanner.nextInt();

        line2[0][0] = scanner.nextInt();
        line2[0][1] = scanner.nextInt();
        line2[1][0] = scanner.nextInt();
        line2[1][1] = scanner.nextInt();
    }

    static void solution() {
        System.out.println(checkCCW(line1, line2));
    }

    static int checkCCW(int[][] line1, int[][] line2) {
        boolean isCross = false;
        int result = 0;
        int ccw1 = ccw(line1[0][0], line1[1][0], line2[0][0], line1[0][1], line1[1][1], line2[0][1]);
        int ccw2 = ccw(line1[0][0], line1[1][0], line2[1][0], line1[0][1], line1[1][1], line2[1][1]);
        int ccw3 = ccw(line2[0][0], line2[1][0], line1[0][0], line2[0][1], line2[1][1], line1[0][1]);
        int ccw4 = ccw(line2[0][0], line2[1][0], line1[1][0], line2[0][1], line2[1][1], line1[1][1]);

        boolean compare1 = Math.min(line1[0][0], line1[1][0]) <= Math.max(line2[0][0], line2[1][0]);
        boolean compare2 = Math.min(line2[0][0], line2[1][0]) <= Math.max(line1[0][0], line1[1][0]);
        boolean compare3 = Math.min(line1[0][1], line1[1][1]) <= Math.max(line2[0][1], line2[1][1]);
        boolean compare4 = Math.min(line2[0][1], line2[1][1]) <= Math.max(line1[0][1], line1[1][1]);

        if (ccw1 * ccw2 == 0 && ccw3 * ccw4 == 0) {
            isCross = true;
            if (compare1 && compare2 && compare3 && compare4) {
                result = 1;
            }
        }

        if (ccw1 * ccw2 <= 0 && ccw3 * ccw4 <= 0) {
            if (!isCross) {
                result = 1;
            }
        }

        return result;
    }

    static int ccw(long x1, long x2, long x3, long y1, long y2, long y3) {
        long a = (x1 * y2) + (x2 * y3) + (x3 * y1);
        long b = (x2 * y1) + (x3 * y2) + (x1 * y3);

        if (a - b > 0) {
            return 1;
        }
        if (a - b < 0) {
            return -1;
        }
        return 0;
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