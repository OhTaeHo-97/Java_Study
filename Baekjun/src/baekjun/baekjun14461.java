package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun14461 {
    static final int MAX_CROSS_COUNT = 3;

    static int size;
    static int crossTime;
    static int[][] pastures;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void input() {
        Reader scanner = new Reader();

        size = scanner.nextInt();
        crossTime = scanner.nextInt();
        pastures = new int[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                pastures[row][col] = scanner.nextInt();
            }
        }
    }

    static void solution() {
        System.out.println(calculateCrossingTimes());
    }

    static long calculateCrossingTimes() {
        Queue<Pasture> queue = new PriorityQueue<>();
        long[][] times = new long[size][size];
        for (int row = 0; row < size; row++) {
            Arrays.fill(times[row], Integer.MAX_VALUE);
        }

        queue.offer(new Pasture(0, 0, 0, 0));
        times[0][0] = 0;

        while (!queue.isEmpty()) {
            Pasture cur = queue.poll();

            if (times[cur.x][cur.y] < cur.time) {
                continue;
            }

            for (int dir = 0; dir < dx.length; dir++) {
                int cx = cur.x + dx[dir];
                int cy = cur.y + dy[dir];

                if (isInMap(cx, cy)) {
                    long nextTime = cur.time + crossTime;
                    int nextCrossCount = cur.moveCount + 1;

                    if (nextCrossCount == MAX_CROSS_COUNT) {
                        nextTime += pastures[cx][cy];
                        nextCrossCount = 0;
                    }

                    if (times[cx][cy] > nextTime) {
                        times[cx][cy] = nextTime;
                        queue.offer(new Pasture(cx, cy, nextCrossCount, nextTime));
                    }
                }
            }
        }

        return times[size - 1][size - 1];
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    static class Pasture implements Comparable<Pasture> {
        int x;
        int y;
        int moveCount;
        long time;

        public Pasture(int x, int y, int moveCount, long time) {
            this.x = x;
            this.y = y;
            this.moveCount = moveCount;
            this.time = time;
        }

        @Override
        public int compareTo(Pasture p) {
            if (time < p.time) {
                return -1;
            }
            if (time > p.time) {
                return 1;
            }
            return 0;
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
