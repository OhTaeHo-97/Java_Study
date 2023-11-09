package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun2159 {
    static final int SIZE = 100_000;

    static int customerCount;
    static int[] start;
    static int[][] customers;
    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {0, 0, -1, 0, 1};

    static void input() {
        Reader scanner = new Reader();

        customerCount = scanner.nextInt();
        customers = new int[customerCount][2];
        start = new int[]{scanner.nextInt(), scanner.nextInt()};

        for (int idx = 0; idx < customerCount; idx++) {
            customers[idx] = new int[]{scanner.nextInt(), scanner.nextInt()};
        }
    }

    static void solution() {
        long[][] distances = new long[customerCount][dx.length];
        initDistances(distances);
        calculateMinimumDistances(distances);
        long min = Arrays.stream(distances[customerCount - 1]).min().getAsLong();
        System.out.println(min);
    }

    static void initDistances(long[][] distances) {
        for (int row = 0; row < customerCount; row++) {
            Arrays.fill(distances[row], Long.MAX_VALUE);
        }

        for (int dir = 0; dir < dx.length; dir++) {
            int cx = customers[0][0] + dx[dir];
            int cy = customers[0][1] + dy[dir];

            if (isInMap(cx, cy)) {
                distances[0][dir] = Math.abs(start[0] - cx) + Math.abs(start[1] - cy);
            }
        }
    }

    static void calculateMinimumDistances(long[][] distances) {
        for (int customerIdx = 1; customerIdx < customerCount; customerIdx++) {
            calculateEachCustomerMinimumDistances(customerIdx, distances);
        }
    }

    static void calculateEachCustomerMinimumDistances(int customerIdx, long[][] distances) {
        for (int prevDir = 0; prevDir < dx.length; prevDir++) {
            if (distances[customerIdx - 1][prevDir] == Integer.MAX_VALUE) {
                continue;
            }

            calculateEachLocationMinimumDistances(customerIdx, prevDir,
                    new int[]{customers[customerIdx - 1][0] + dx[prevDir], customers[customerIdx - 1][1] + dy[prevDir]},
                    distances);
        }
    }

    static void calculateEachLocationMinimumDistances(int customerIdx, int prevDir, int[] location,
                                                      long[][] distances) {
        for (int dir = 0; dir < dx.length; dir++) {
            int cx = customers[customerIdx][0] + dx[dir];
            int cy = customers[customerIdx][1] + dy[dir];
            int dist = Math.abs(location[0] - cx) + Math.abs(location[1] - cy);
            distances[customerIdx][dir] = Math.min(distances[customerIdx][dir],
                    distances[customerIdx - 1][prevDir] + dist);
        }
    }

    static boolean isInMap(int x, int y) {
        return 0 <= x && x <= SIZE && 0 <= y && y <= SIZE;
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