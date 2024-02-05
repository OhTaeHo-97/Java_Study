package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class baekjun14466 {
    static int size;
    static int cowCount;
    static int roadCount;
    static int[][] cows;
    static Map<Point, Set<Point>> roads;

    static void input() {
        Reader scanner = new Reader();

        size = scanner.nextInt();
        cowCount = scanner.nextInt();
        roadCount = scanner.nextInt();
        cows = new int[cowCount][2];
        roads = new HashMap<>();

        for (int count = 0; count < roadCount; count++) {
            int startX = scanner.nextInt() - 1;
            int startY = scanner.nextInt() - 1;
            int endX = scanner.nextInt() - 1;
            int endY = scanner.nextInt() - 1;

            Point startPoint = new Point(startX, startY);
            Point endPoint = new Point(endX, endY);

            if (!roads.containsKey(startPoint)) {
                roads.put(startPoint, new HashSet<>());
            }
            if (!roads.containsKey(endPoint)) {
                roads.put(endPoint, new HashSet<>());
            }

            roads.get(startPoint).add(endPoint);
            roads.get(endPoint).add(startPoint);
        }

        for (int cowIdx = 0; cowIdx < cowCount; cowIdx++) {
            cows[cowIdx][0] = scanner.nextInt() - 1;
            cows[cowIdx][1] = scanner.nextInt() - 1;
        }
    }

    static void solution() {
        int answer = 0;

        for (int basisIdx = 0; basisIdx < cowCount - 1; basisIdx++) {
            boolean[][] visited = bfs(new Point(cows[basisIdx][0], cows[basisIdx][1]));
            for (int otherIdx = basisIdx + 1; otherIdx < cowCount; otherIdx++) {
                if (!visited[cows[otherIdx][0]][cows[otherIdx][1]]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    static boolean[][] bfs(Point startPoint) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[size][size];

        queue.offer(startPoint);
        visited[startPoint.x][startPoint.y] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int dir = 0; dir < dx.length; dir++) {
                int cx = cur.x + dx[dir];
                int cy = cur.y + dy[dir];

                if (isInMap(cx, cy) && !visited[cx][cy]) {
                    if (roads.containsKey(new Point(cur.x, cur.y)) && roads.get(new Point(cur.x, cur.y))
                            .contains(new Point(cx, cy))) {
                        continue;
                    }
                    visited[cx][cy] = true;
                    queue.offer(new Point(cx, cy));
                }
            }
        }

        return visited;
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point road = (Point) o;
            return x == road.x && y == road.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
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
