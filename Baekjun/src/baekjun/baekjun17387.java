package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun17387 {
	static Point[] points;

    static void input() {
        Reader scanner = new Reader();
        points = new Point[4];

        for(int idx = 0; idx < 4; idx++) {
            long x = scanner.nextLong(), y = scanner.nextLong();
            points[idx] = new Point(x, y);
        }
    }

    static void solution() {
        boolean isEnd = false;
        int result = 0;

        int ccw123 = ccw(points[0], points[1], points[2]);
        int ccw124 = ccw(points[0], points[1], points[3]);
        int ccw341 = ccw(points[2], points[3], points[0]);
        int ccw342 = ccw(points[2], points[3], points[1]);

        if(ccw123 * ccw124 == 0 && ccw341 * ccw342 == 0) {
            isEnd = true;

            boolean c1 = Math.min(points[0].x, points[1].x) <= Math.max(points[2].x, points[3].x);
            boolean c2 = Math.min(points[2].x, points[3].x) <= Math.max(points[0].x, points[1].x);
            boolean c3 = Math.min(points[0].y, points[1].y) <= Math.max(points[2].y, points[3].y);
            boolean c4 = Math.min(points[2].y, points[3].y) <= Math.max(points[0].y, points[1].y);

            if(c1 && c2 && c3 && c4)
                result = 1;
        }
        
        if(!isEnd) {
            if(ccw123 * ccw124 <= 0 && ccw341 * ccw342 <= 0) {
                result = 1;
            }
        }

        System.out.println(result);
    }

    static int ccw(Point p1, Point p2, Point p3) {
        long num1 = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
        long num2 = p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
        
        // 반시계
        if(num1 - num2 > 0) {
            return 1;
        } else if(num1 == num2) { // 평행
            return 0;
        } else { // 시계방향
            return -1;
        }
    }

    static class Point {
        long x, y;

        public Point(long x, long y) {
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
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
