package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun2917 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int height;
    static int width;
    static int answer;
    static int[] wolf;
    static int[] cabin;
    static char[][] forest;
    static int[][] nearestTreeDistances;
    static Queue<Point> trees;

    static void input() {
        Reader scanner = new Reader();

        height = scanner.nextInt();
        width = scanner.nextInt();
        forest = new char[height][width];
        nearestTreeDistances = new int[height][width];
        trees = new PriorityQueue<>();

        for (int row = 0; row < height; row++) {
            String info = scanner.nextLine();
            for (int col = 0; col < width; col++) {
                nearestTreeDistances[row][col] = Integer.MAX_VALUE;
                forest[row][col] = info.charAt(col);

                if (forest[row][col] == 'V') {
                    wolf = new int[]{row, col};
                }
                if (forest[row][col] == 'J') {
                    cabin = new int[]{row, col};
                }
                if (forest[row][col] == '+') {
                    nearestTreeDistances[row][col] = 0;
                    trees.offer(new Point(row, col, 0));
                }
            }
        }
    }

    static void solution() {
        calculateNearestTreeDistance();
        answer = Math.min(nearestTreeDistances[wolf[0]][wolf[1]], nearestTreeDistances[cabin[0]][cabin[1]]);
        calculateMinDistance(wolf);
        System.out.println(answer);
    }

    static void calculateMinDistance(int[] start) {
        Queue<Point> queue = new PriorityQueue<>();
        boolean[][] visited = new boolean[height][width];

        queue.offer(new Point(start[0], start[1], nearestTreeDistances[start[0]][start[1]]));
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cur.distance < answer) {
                answer = cur.distance;
            }

            for (int dir = 0; dir < dx.length; dir++) {
                int cx = cur.x + dx[dir];
                int cy = cur.y + dy[dir];

                if (isInForest(cx, cy) && !visited[cx][cy]) {
                    if (cx == cabin[0] && cy == cabin[1]) {
                        return;
                    }

                    visited[cx][cy] = true;
                    queue.offer(new Point(cx, cy, nearestTreeDistances[cx][cy]));
                }
            }
        }
    }

    static void calculateNearestTreeDistance() {
        while (!trees.isEmpty()) {
            Point cur = trees.poll();
            int nextDistance = cur.distance + 1;

            for (int dir = 0; dir < dx.length; dir++) {
                int cx = cur.x + dx[dir];
                int cy = cur.y + dy[dir];

                if (isInForest(cx, cy)) {
                    if (nearestTreeDistances[cx][cy] > nextDistance) {
                        nearestTreeDistances[cx][cy] = nextDistance;
                        trees.offer(new Point(cx, cy, nextDistance));
                    }
                }
            }
        }
    }

    static boolean isInForest(int x, int y) {
        return x >= 0 && x < height && y >= 0 && y < width;
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;
        int distance;

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Point o) {
            return o.distance - distance;
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
