package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun12781 {
    /*
     * 피자의 모양 -> 볼록 다각형, 피자를 네 등분할 것임
     * 다음과 같이 피자를 나눔
     *  1. 한 명씩 피자의 가장자리의 한 점을 선택(같은 점 선택하지 않음)
     *  2. 선택한 순서대로 첫 번째 점과 두 번째 점을 이어 선분을 만들고 세 번째 점과 네 번째 점을 이은 선분을 만듬
     *  3. 만들어진 두 선분을 따라 피자를 자름
     *
     * 잘라진 피자 크기에 상관 없이 네 조각으로만 나눠지면 먹기로 함
     * 네 조각으로 나눠지지 않는다면 싸우게 됨
     * 사이좋게 피자를 나눠 먹을 수 있는지 알아보는 프로그램 짜기
     *
     * 입력
     *  - 도윤이 및 친구들이 선택한 점의 좌표 x, y가 순서대로 4개 주어짐
     *      -> -10,000 <= x, y <= 10,000
     * 출력
     *  - 주어진 4개읜 점으로 도윤이가 친구들과 사이좋게 피자를 나눠 먹을 수 있다면 1, 아니면 0
     */

    /*
     * CCW
     *  - (x1, y1) - (x2, y2), (x3, y3) - (x4, y4)
     *  - x1 * y2 +
     */

    private static final int POINT_COUNT = 4;
    private static List<Point> points;

    private static void input() {
        Reader scanner = new Reader();
        points = new ArrayList<>();

        for(int count = 0; count < POINT_COUNT; count++) {
            points.add(new Point(scanner.nextInt(), scanner.nextInt()));
        }
    }

    private static void solution() {
        int p123 = ccw(points.get(0), points.get(1), points.get(2));
        int p124 = ccw(points.get(0), points.get(1), points.get(3));
        int p341 = ccw(points.get(2), points.get(3), points.get(0));
        int p342 = ccw(points.get(2), points.get(3), points.get(1));

        if(p123 * p124 < 0 && p341 * p342 < 0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static int ccw(Point p1, Point p2, Point p3) {
        long result = ((long)(p1.x * p2.y) + (p2.x * p3.y) + (p3.x * p1.y)) - ((long)(p1.y * p2.x) + (p2.y * p3.x) + (p3.y * p1.x));
        if(result > 0) {
            return 1;
        } else if(result < 0) {
            return -1;
        }
        return 0;
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
