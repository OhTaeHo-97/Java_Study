package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2819 {
    static final int BASE = 1_000_000;

    static int investigationPointCount;
    static int orderCount;
    static int cx;
    static int cy;
    static int positiveXCount;
    static int zeroXCount;
    static int negativeXCount;
    static int positiveYCount;
    static int zeroYCount;
    static int negativeYCount;
    static long answer;
    static char[] orders;
    static int[] investigationPointX;
    static int[] investigationPointY;

    static void input() {
        Reader scanner = new Reader();

        investigationPointCount = scanner.nextInt();
        orderCount = scanner.nextInt();
        investigationPointX = new int[BASE * 2 + 1];
        investigationPointY = new int[BASE * 2 + 1];

        for (int idx = 0; idx < investigationPointCount; idx++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            investigationPointX[BASE + x]++;
            investigationPointY[BASE + y]++;
            answer += (Math.abs(x) + Math.abs(y));

            if (x > 0) {
                positiveXCount++;
            } else if (x < 0) {
                negativeXCount++;
            } else {
                zeroXCount++;
            }

            if (y > 0) {
                positiveYCount++;
            } else if (y < 0) {
                negativeYCount++;
            } else {
                zeroYCount++;
            }
        }

        orders = scanner.nextLine().toCharArray();
    }

    static void solution() {
        StringBuilder result = new StringBuilder();
        for (char order : orders) {
            if (order == 'S') {
                cy++;
                answer += (negativeYCount + zeroYCount - positiveYCount);
                negativeYCount += zeroYCount;
                zeroYCount = investigationPointY[cy + BASE];
                positiveYCount -= investigationPointY[cy + BASE];
            } else if (order == 'J') {
                cy--;
                answer += (positiveYCount + zeroYCount - negativeYCount);
                positiveYCount += zeroYCount;
                zeroYCount = investigationPointY[cy + BASE];
                negativeYCount -= investigationPointY[cy + BASE];
            } else if (order == 'I') {
                cx++;
                answer += (negativeXCount + zeroXCount - positiveXCount);
                negativeXCount += zeroXCount;
                zeroXCount = investigationPointX[cx + BASE];
                positiveXCount -= investigationPointX[cx + BASE];
            } else {
                cx--;
                answer += (positiveXCount + zeroXCount - negativeXCount);
                positiveXCount += zeroXCount;
                zeroXCount = investigationPointX[cx + BASE];
                negativeXCount -= investigationPointX[cx + BASE];
            }
            result.append(answer).append('\n');
        }
        System.out.print(result);
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
