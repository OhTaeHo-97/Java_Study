package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class baekjun9015 {
    static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();

    static int N;
    static int answer;
    static Set<Point> pointsSet;
    static Point[] points;

    static void input() {
        N = scanner.nextInt();
        answer = 0;
        pointsSet = new HashSet<>();
        points = new Point[N];

        for(int idx = 0; idx < N; idx++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            points[idx] = new Point(x, y);
            pointsSet.add(new Point(x, y));
        }
    }

    static void solution() {
        for(int point1Idx = 0; point1Idx < N; point1Idx++) {
            for(int point2Idx = 0; point2Idx < N; point2Idx++) {
                if(point1Idx == point2Idx) {
                    continue;
                }

                Point point1 = points[point1Idx];
                Point point2 = points[point2Idx];
                int xGap = point1.x - point2.x;
                int yGap = point1.y - point2.y;
                if(answer >= (Math.pow(xGap, 2) + Math.pow(yGap, 2))) {
                    continue;
                }

                if(pointsSet.contains(new Point(point1.x - yGap, point1.y + xGap)) &&
                        pointsSet.contains(new Point(point2.x - yGap, point2.y + xGap))) {
                    answer = Math.max(answer, (int)Math.pow(xGap, 2) + (int)Math.pow(yGap, 2));
                }
            }
        }

        sb.append(answer).append('\n');
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            if(x != p.x) {
                return x - p.x;
            }
            return y - p.y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        while(T-- > 0) {
            input();
            solution();
        }

        System.out.print(sb);
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
