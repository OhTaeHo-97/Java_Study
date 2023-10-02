package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class baekjun25315 {
    static int n;
    static Cutting[] cuttings;

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextInt();
        cuttings = new Cutting[n];

        for(int idx = 0; idx < n; idx++) {
            cuttings[idx] = new Cutting(
                    new Point(scanner.nextInt(), scanner.nextInt()),
                    new Point(scanner.nextInt(), scanner.nextInt()),
                    scanner.nextInt()
            );
        }
    }

    static void solution() {
        Arrays.sort(cuttings, new Comparator<Cutting>() {
            @Override
            public int compare(Cutting o1, Cutting o2) {
                if(o1.weight != o2.weight) {
                    if(o1.weight < o2.weight) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    if(o1.start.x != o2.start.x) {
                        return o1.start.x - o2.start.x;
                    } else if(o1.start.y != o2.start.y) {
                        return o1.start.y - o2.start.y;
                    } else if(o1.end.x != o2.end.x) {
                        return o1.end.x - o2.end.x;
                    }
                }
                return o1.end.y - o2.end.y;
            }
        });

        long answer = 0L;

        for(int idx = 0; idx < cuttings.length - 1; idx++) {
            long count = 1L;
            for(int idx2 = idx + 1; idx2 < cuttings.length; idx2++) {
                if(isCross(cuttings[idx], cuttings[idx2])) {
                    count++;
                }
            }
            answer += cuttings[idx].weight * count;
        }

        System.out.println(answer);
    }

//    static void findCrossNum() {
//        for(int idx = 0; idx < cuttings.length - 1; idx++) {
//            long count = 1;
//            for(int idx2 = idx + 1; idx2 < cuttings.length; idx2++) {
//                if(isCross(cuttings[idx], cuttings[idx2])) {
//                    count++;
//                }
//            }
//        }
//    }

    static boolean isCross(Cutting cutting1, Cutting cutting2) {
        boolean isEnd = false;
        boolean result = false;

        int ccw123 = ccw(cutting1.start, cutting1.end, cutting2.start);
        int ccw124 = ccw(cutting1.start, cutting1.end, cutting2.end);
        int ccw341 = ccw(cutting2.start, cutting2.end, cutting1.start);
        int ccw342 = ccw(cutting2.start, cutting2.end, cutting1.end);

        boolean c1 = Math.min(cutting1.start.x, cutting1.end.x) <= Math.max(cutting2.start.x, cutting2.end.x);
        boolean c2 = Math.min(cutting2.start.x, cutting2.end.x) <= Math.max(cutting1.start.x, cutting1.end.x);
        boolean c3 = Math.min(cutting1.start.y, cutting1.end.y) <= Math.max(cutting2.start.y, cutting2.end.y);
        boolean c4 = Math.min(cutting2.start.y, cutting2.end.y) <= Math.max(cutting1.start.y, cutting1.end.y);

        if(ccw123 * ccw124 == 0 && ccw341 * ccw342 == 0) {
            isEnd = true;
            if(c1 && c2 && c3 && c4) {
                result = true;
            }
        }

        if(ccw123 * ccw124 <= 0 && ccw341 * ccw342 <= 0) {
            if(!isEnd) {
                result = true;
            }
        }

        return result;
    }

    static int ccw(Point point1, Point point2, Point point3) {
        int a = point1.x * point2.y + point2.x + point3.y + point3.x * point1.y;
        int b = point1.y * point2.x + point2.y * point3.x + point3.y * point1.x;

        if(a - b > 0) {
            return 1;
        } else if(a - b < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    static class Cutting {
        Point start;
        Point end;
        long weight;

        public Cutting(Point start, Point end, long weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
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
