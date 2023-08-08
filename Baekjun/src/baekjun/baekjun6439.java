package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun6439 {
    static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();

    static Point[] linePoints;
    static Point[] rectanglePoints;

    static void input() {
        linePoints = new Point[2];
        rectanglePoints = new Point[4];

        for(int idx = 0; idx < linePoints.length; idx++) {
            int x = scanner.nextInt(), y = scanner.nextInt();
            linePoints[idx] = new Point(x, y);
        }

        // 사각형의 네 점을 구한다
        int x1 = scanner.nextInt(), y1 = scanner.nextInt();
        int x2 = scanner.nextInt(), y2 = scanner.nextInt();
        rectanglePoints[0] = new Point(Math.min(x1, x2), Math.min(y1, y2));
        rectanglePoints[1] = new Point(Math.min(x1, x2), Math.max(y1, y2));
        rectanglePoints[2] = new Point(Math.max(x1, x2), Math.max(y1, y2));
        rectanglePoints[3] = new Point(Math.max(x1, x2), Math.min(y1, y2));
    }

    static void solution() {
        boolean isCross = false;

        int index = 0;
        // 사각형의 모든 선분을 순회하면서 주어진 선분과 사각형의 선분이 교차하는지, 교차하지 않는다면 사각형 내부에 있는지 확인한다
        for(int count = 0; count < 4; count++) {
            // 주어진 선분과 사각형의 선분이 교차하는지 확인한다
            boolean result = isCross(linePoints[0], linePoints[1], rectanglePoints[index % 4], rectanglePoints[++index % 4]);
            if(result) { // 만약 교차한다면
                // 교차하는 것을 확인했기 때문에 교차 여부를 true로 변경하고 반복문을 빠져나간다
                isCross = true;
                break;
            } else { // 교차하지 않는다면
                // 주어진 선분이 사각형 내부에 존재하는지 확인하고 만약 그렇다면
                if((rectanglePoints[0].x < linePoints[0].x && rectanglePoints[0].x < linePoints[1].x && linePoints[0].x < rectanglePoints[2].x && linePoints[1].x < rectanglePoints[2].x) &&
                        (rectanglePoints[0].y < linePoints[0].y && rectanglePoints[0].y < linePoints[1].y && linePoints[0].y < rectanglePoints[2].y && linePoints[1].y < rectanglePoints[2].y)) {
                    // T를 출력해야 하니 교차 여부를 true로 변경하고 반복문을 빠져나간다
                    isCross = true;
                    break;
                }
            }
        }

        // 교차 여부가 true라면 T를 아니라면 F를 출력한다
        sb.append(isCross ? 'T' : 'F').append('\n');
    }

    // 주어진 두 선분에 대해서 CCW 알고리즘을 이용하여 교차하는지 확인하는 메서드
    static boolean isCross(Point point1, Point point2, Point point3, Point point4) {
        int ccw1 = ccw(point1, point2, point3);
        int ccw2 = ccw(point1, point2, point4);
        int ccw3 = ccw(point3, point4, point1);
        int ccw4 = ccw(point3, point4, point2);

        boolean compare1 = Math.min(point1.x, point2.x) <= Math.max(point3.x, point4.x);
        boolean compare2 = Math.min(point3.x, point4.x) <= Math.max(point1.x, point2.x);
        boolean compare3 = Math.min(point1.y, point2.y) <= Math.max(point3.y, point4.y);
        boolean compare4 = Math.min(point3.y, point4.y) <= Math.max(point1.y, point2.y);

        boolean isFinish = false, isCross = false;
        if (ccw1 * ccw2 == 0 && ccw3 * ccw4 == 0) {
            isFinish = true;
            if (compare1 && compare2 && compare3 && compare4)
                isCross = true;
        }

        if(ccw1 * ccw2 <= 0 && ccw3 * ccw4 <= 0) {
            if(!isFinish) isCross = true;
        }

        return isCross;
    }

    static int ccw(Point point1, Point point2, Point point3) {
        int result = ((point1.x * point2.y) + (point2.x * point3.y) + (point3.x * point1.y)) - ((point1.y * point2.x) + (point2.y * point3.x) + (point3.y * point1.x));

        if(result > 0) return 1; // 반시계
        else if(result < 0) return -1; // 시계
        return 0; // 일직선
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
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
