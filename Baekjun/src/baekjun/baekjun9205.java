package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class baekjun9205 {
    /*
     * 출발 => 상근이네 집, 맥주 한 박스를 들고 출발(20개)
     * 50미터에 한 병씩 마심
     *  - 50미터를 가려면 그 직전에 맥주 한 병을 마셔야 한다
     * 편의점에 들렸을 때 빈 병은 버리고 새 맥주병을 살 수 있음
     *  - 박스에 들어있는 맥주는 20병을 넘을 수 없음
     *  - 편의점을 나선 직후에도 50미터를 가기 전에 맥주 한 병을 마셔야 함
     * 편의점, 상근이네 집, 펜타포트 락 페스티벌 좌표가 주어짐 => 페스티벌에 도착할 수 있는지 구하기
     *
     * 0 <= 편의점 개수(n) <= 100
     * 집, 편의점, 펜타포트 락 페스티벌 좌표가 주어짐
     * (-32768 <= x, y <= 32767)
     * 두 좌표 사이 거리는 |x1 - x2| + |y1 - y2| (맨해튼 거리)
     *
     * 도착하면 happy, 안되면 sad
     */

    private static final int MAX_BEER = 20;
    private static final int MAX_DISTANCE = 50;

    private static Reader scanner;
    private static StringBuilder sb;

    private static int convenienceStoreCount;
    private static int[] home;
    private static int[] festival;
    private static int[][] convenienceStores;

    private static void input() {
        convenienceStoreCount = scanner.nextInt();
        home = new int[2];
        festival = new int[2];

        home[0] = scanner.nextInt();
        home[1] = scanner.nextInt();

        convenienceStores = new int[convenienceStoreCount + 1][2];
        for(int count = 0; count < convenienceStoreCount; count++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            convenienceStores[count][0] = x;
            convenienceStores[count][1] = y;
        }

        int x = scanner.nextInt();
        int y = scanner.nextInt();
        festival[0] = x;
        festival[1] = y;
        convenienceStores[convenienceStoreCount][0] = x;
        convenienceStores[convenienceStoreCount][1] = y;
    }

    private static void solution() {
        bfs(home);
    }

    private static void bfs(int[] start) {
        Queue<Sangkeun> queue = new LinkedList<>();
        Set<Point> visited = new HashSet<>();

        queue.offer(new Sangkeun(start[0], start[1], MAX_DISTANCE, MAX_BEER - 1));
        visited.add(new Point(start[0], start[1]));

        while(!queue.isEmpty()) {
            Sangkeun cur = queue.poll();
            if(cur.x == festival[0] && cur.y == festival[1]) {
                sb.append("happy").append('\n');
                return;
            }

            for(int c = 0; c <= convenienceStoreCount; c++) {
                int dx = convenienceStores[c][0];
                int dy = convenienceStores[c][1];
                int distance = Math.abs(cur.x - dx) + Math.abs(cur.y - dy);

                if (!visited.contains(new Point(dx, dy))) {
                    if(cur.distance >= distance) {
                        queue.offer(new Sangkeun(dx, dy, cur.distance - distance, MAX_BEER));
                        visited.add(new Point(dx, dy));
                    } else {
                        int needBeer = (int) Math.ceil((double) (distance - cur.distance) / MAX_DISTANCE);
                        if(cur.beer <= 0 || cur.beer < needBeer) { continue; }

                        int newDistance = cur.distance + (needBeer * MAX_DISTANCE);
                        queue.offer(new Sangkeun(dx, dy, newDistance - distance, MAX_BEER));
                        visited.add(new Point(dx, dy));
                    }
                }
            }
        }

        sb.append("sad").append('\n');
    }

    static class Sangkeun {
        int x;
        int y;
        int distance;
        int beer;

        public Sangkeun(int x, int y, int distance, int beer) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.beer = beer;
        }
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
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        scanner = new Reader();
        sb = new StringBuilder();

        int t = scanner.nextInt();
        for(int caseCount = 0; caseCount < t; caseCount++) {
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
                } catch(IOException e) {
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
