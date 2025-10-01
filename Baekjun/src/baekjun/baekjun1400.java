package baekjun;

import java.io.*;
import java.util.*;

public class baekjun1400 {
    /*
     * 출발지 창고에서 짐을 싣고 배송지 창고까지 짐을 운반
     * 차 => 동, 서, 남, 북의 방향으로만 이동 가능
     * 지도의 각 문자 의미
     *  1. 'A' : 출발자 칭고, 지도에서 유일
     *  2. 'B' : 배송지 창고, 지도에서 유일
     *  3. '.' : 차가 들어갈 수 없는 곳
     *  4. '#' : 도로 셀을 나타냄
     *      - '#'은 기껏해야 두 개의 다른 도로 셀, 또는 교차로, 창고와 인접함
     *  5. 숫자 0-9 : 신호등에 의해 제어되는 교차로
     *      - 교차로는 적어도 세 개의 도로 셀과 인접
     *      - 교차로들은 0부터 9까지의 번호로 표시됨
     *      - 번호 k를 가진 교차로가 있으면, 반드시 0부터 k까지 번호를 가진 교차로가 존재함
     *
     * 차량의 이동은 다음과 같은 방식으로 분석됨
     *  1. 화물차가 인접한 도로 셀, 또는 교차로, 창고로 이동하는 데 걸리는 시간을 단위 시간이라고 가정
     *      - 차량이 어떤 위치에서 멈춰 서 있는 시간도 단위 시간으로 측정됨
     *  2. 화물차가 진입하려는 방향으로 파란불이 켜져 있을 때만 교차로로 들어갈 수 있음
     *      - 교차로에 들어간 차량은 언제든지 임의의 방향으로 나갈 수 있다
     *  3. 교차로의 신호등은 동서방향, 남북방향 두 개의 신호가 주기적으로 켜짐
     *      - 교차로의 신호는 초기에 동서 방향 또는 남북 방향이 될 수 있음
     *      - 교차로의 신호 주기를 나타내는 값 "a b" : 동서 방향의 신호가 a 시간 켜지고, 남북 방향의 신호가 b 시간 켜짐을 의미
     *
     * 출발지 창고에서 배송지 창고까지 최단 경로 구하기
     */

    private static final int MAX_INTERSECTION_NUMBER = 9;

    private static int[][] directions = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static Reader scanner;
    private static StringBuilder sb;

    private static int rowSize;
    private static int colSize;
    private static int maxIntersectionNumber = -1;
    private static int[] start, destination;
    private static Intersection[] intersections;
    private static char[][] map;

    private static void input() {
        map = new char[rowSize][colSize];
        intersections = new Intersection[MAX_INTERSECTION_NUMBER + 1];
        maxIntersectionNumber = -1;
        for(int row = 0; row < rowSize; row++) {
            String line = scanner.nextLine();
            for(int col = 0; col < colSize; col++) {
                map[row][col] = line.charAt(col);
                if(map[row][col] >= '0' && map[row][col] <= '9') {
                    maxIntersectionNumber = Math.max(maxIntersectionNumber, map[row][col] - '0');
                }
                if(map[row][col] == 'A') start = new int[] {row, col};
                if(map[row][col] == 'B') destination = new int[] {row, col};
            }
        }

        if(maxIntersectionNumber >= 0) {
            for(int number = 0; number <= maxIntersectionNumber; number++) {
                int intersectionNumber = scanner.nextInt();
                char direction = scanner.next().charAt(0);
                int horizontalTime = scanner.nextInt();
                int verticalTime = scanner.nextInt();

                intersections[intersectionNumber] = new Intersection(intersectionNumber, direction, horizontalTime, verticalTime);
            }
        }
    }

    private static void solution() {
        Queue<int[]> positions = new LinkedList<>();
        boolean isFinished = false;
        int answer = 1;
        int[][][] visited = new int[rowSize][colSize][1 << (MAX_INTERSECTION_NUMBER + 1)];
        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                Arrays.fill(visited[row][col], Integer.MAX_VALUE);
            }
        }

        positions.offer(new int[] {start[0], start[1], 0});
        visited[start[0]][start[1]][0] = 0;

        Outer:
        while(!positions.isEmpty()) {
            if(maxIntersectionNumber >= 0) {
                changeIntersection(answer);
            }

            int size = positions.size();
            for(int count = 0; count < size; count++) {
                int[] cur = positions.poll();
                if(cur[0] == destination[0] && cur[1] == destination[1]) {
                    isFinished = true;
                    break Outer;
                }

                // 0 : 북, 1 : 동, 2 : 남, 3 : 서
                move(answer, cur, positions, visited);
            }
            answer++;
        }

        if(isFinished) {
            sb.append(answer - 1).append('\n');
        } else {
            sb.append("impossible").append('\n');
        }
    }

    private static boolean isInMap(int x, int y) {
        return x >= 0 & x < rowSize && y >= 0 && y < colSize;
    }

    private static void move(int answer, int[] cur, Queue<int[]> positions, int[][][] visited) {
        for(int dir = 0; dir < directions.length; dir++) {
            int nx = cur[0] + directions[dir][0];
            int ny = cur[1] + directions[dir][1];

            if(!isInMap(nx, ny) || map[nx][ny] == 'A') continue;

            if(isInMap(nx, ny) && map[nx][ny] != '.') {
                if((map[nx][ny] == '#' || map[nx][ny] == 'B') && visited[nx][ny][cur[2]] > answer) {
                    visited[nx][ny][cur[2]] = answer;
                    positions.offer(new int[] {nx, ny, cur[2]});
                }
                if(map[nx][ny] >= '0' && map[nx][ny] <= '9') {
                    int intersectionNumber = map[nx][ny] - '0';
                    int flag = cur[2] | (1 << intersectionNumber);
                    if((dir == 0 || dir == 2) && intersections[intersectionNumber].direction == '|' && visited[nx][ny][flag] > answer) {
                        visited[nx][ny][cur[2]] = answer;
                        positions.offer(new int[] {nx, ny, flag});
                    }
                    if((dir == 0 || dir == 2) && intersections[intersectionNumber].direction == '-') {
                        positions.offer(new int[] {cur[0], cur[1], cur[2]});
                    }
                    if((dir == 1 || dir == 3) && intersections[intersectionNumber].direction == '-' && visited[nx][ny][flag] > answer) {
                        visited[nx][ny][cur[2]] = answer;
                        positions.offer(new int[] {nx, ny, flag});
                    }
                    if((dir == 1 || dir == 3) && intersections[intersectionNumber].direction == '|') {
                        positions.offer(new int[] {cur[0], cur[1], cur[2]});
                    }
                }
            }
        }
    }

    private static void changeIntersection(int time) {
        // 즉, time % (horizontalTime + verticalTime) 값이
        //  - 1 ~ firstDirectionTime => firstDirection
        //  - firstDirectionTime + 1 ~ 0 => secondDirection
        for(int number = 0; number <= maxIntersectionNumber; number++) {
            Intersection intersection = intersections[number];
            char firstDirection = intersection.firstDirection;
            int firstDirectionTime = intersection.firstDirection == '|' ? intersection.verticalTime : intersection.horizontalTime;
            int secondDirectionTime = intersection.firstDirection == '|' ? intersection.horizontalTime : intersection.verticalTime;
            int curTime = time % (firstDirectionTime + secondDirectionTime);

            if(curTime >= 1 && curTime <= firstDirectionTime) {
                intersection.direction = firstDirection;
            } else {
                intersection.direction = firstDirection == '|' ? '-' : '|';
            }
        }
    }

    static class Intersection {
        int number;
        char firstDirection;
        char direction;
        int horizontalTime;
        int verticalTime;

        public Intersection(int number, char direction, int horizontalTime, int verticalTime) {
            this.number = number;
            firstDirection = direction;
            this.direction = direction;
            this.horizontalTime = horizontalTime;
            this.verticalTime = verticalTime;
        }

        @Override
        public String toString() {
            return "Intersection{" +
                    "number=" + number +
                    ", firstDirection=" + firstDirection +
                    ", direction=" + direction +
                    ", horizontalTime=" + horizontalTime +
                    ", verticalTime=" + verticalTime +
                    '}';
        }
    }

    public static void main(String[] args) {
        scanner = new Reader();
        sb = new StringBuilder();

        while(true) {
            rowSize = scanner.nextInt();
            colSize = scanner.nextInt();
            if(rowSize == 0 && colSize == 0) {
                System.out.print(sb);
                System.exit(0);
            }

            input();
            solution();
        }
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
