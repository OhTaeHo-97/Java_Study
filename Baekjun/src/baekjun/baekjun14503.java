package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun14503 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] directionAmounts = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static int answer;
    static int rowSize;
    static int colSize;
    static int direction; // 0 : 북, 1 : 동, 2 : 남, 3 : 서
    static int[] robot;
    static int[][] map; // 0 : 청소되지 않은 빈 칸, 1 : 벽

    static void input() {
        Reader scanner = new Reader();

        rowSize = scanner.nextInt();
        colSize = scanner.nextInt();

        int x = scanner.nextInt();
        int y = scanner.nextInt();
        robot = new int[]{x, y};

        direction = scanner.nextInt();

        map = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                map[row][col] = scanner.nextInt();
            }
        }
    }

    static void solution() {
        while (true) {
            if (!turn()) {
                break;
            }
        }

        System.out.println(answer);
    }

    static boolean turn() {
        if (map[robot[0]][robot[1]] == 0) {
            map[robot[0]][robot[1]] = -1;
            answer++;
        }

        if (hasNonCleanedSpace(robot)) {
            goForward();
        } else {
            if (!goBack()) {
                return false;
            }
        }

        return true;
    }

    static void goForward() {
        int dir = (direction + 3) % 4;
        int cx = robot[0] + directionAmounts[dir][0];
        int cy = robot[1] + directionAmounts[dir][1];

        if (isInMap(cx, cy) && map[cx][cy] == 0) {
            robot = new int[]{cx, cy};
        }

        direction = dir;
    }

    static boolean goBack() {
        int dir = (direction + 2) % 4;
        int cx = robot[0] + directionAmounts[dir][0];
        int cy = robot[1] + directionAmounts[dir][1];

        if (isInMap(cx, cy) && map[cx][cy] != 1) {
            robot = new int[]{cx, cy};
            return true;
        } else {
            return false;
        }
    }

    static boolean hasNonCleanedSpace(int[] robot) {
        for (int dir = 0; dir < dx.length; dir++) {
            int cx = robot[0] + dx[dir];
            int cy = robot[1] + dy[dir];

            if (isInMap(cx, cy) && map[cx][cy] == 0) {
                return true;
            }
        }

        return false;
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < rowSize && y >= 0 && y < colSize;
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
