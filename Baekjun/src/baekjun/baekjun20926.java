package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun20926 {
    static int width;
    static int height;
    static int[] tera;
    static int[] exit;
    static int[][] maze;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void input() {
        Reader scanner = new Reader();

        width = scanner.nextInt();
        height = scanner.nextInt();
        maze = new int[height][width];

        for (int row = 0; row < height; row++) {
            String info = scanner.nextLine();
            for (int col = 0; col < width; col++) {
                if (Character.isDigit(info.charAt(col))) {
                    maze[row][col] = info.charAt(col) - '0';
                } else {
                    if (info.charAt(col) == 'T') {
                        tera = new int[]{row, col};
                    } else {
                        if (info.charAt(col) == 'R') {
                            maze[row][col] = -1;
                        }
                        if (info.charAt(col) == 'H') {
                            maze[row][col] = -2;
                        }
                        if (info.charAt(col) == 'E') {
                            exit = new int[]{row, col};
                            maze[row][col] = -3;
                        }
                    }
                }
            }
        }
    }

    static void solution() {
        int time = dijkstra();
        if (time == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(time);
    }

    static int dijkstra() {
        Queue<Tera> teras = new PriorityQueue<>();
        int[][] times = new int[height][width];
        for (int row = 0; row < height; row++) {
            Arrays.fill(times[row], Integer.MAX_VALUE);
        }

        teras.offer(new Tera(tera[0], tera[1], 0));
        times[tera[0]][tera[1]] = 0;

        while (!teras.isEmpty()) {
            Tera cur = teras.poll();
            if (times[cur.x][cur.y] < cur.time) {
                continue;
            }

            for (int dir = 0; dir < dx.length; dir++) {
                int[] tera = new int[]{cur.x, cur.y};
                int moveTime = move(dir, tera);
                if (moveTime == -1) {
                    continue;
                }

                int nextTime = cur.time + moveTime;
                if (times[tera[0]][tera[1]] > nextTime) {
                    times[tera[0]][tera[1]] = nextTime;
                    teras.offer(new Tera(tera[0], tera[1], nextTime));
                }
            }
        }

        return times[exit[0]][exit[1]];
    }

    static int move(int dir, int[] tera) {
        int totalTime = 0;
        while (true) {
            tera[0] += dx[dir];
            tera[1] += dy[dir];
            if (!isInMap(tera[0], tera[1]) || maze[tera[0]][tera[1]] == -2) {
                tera[0] -= dx[dir];
                tera[1] -= dy[dir];
                return -1;
            }
            if (maze[tera[0]][tera[1]] == -1) {
                tera[0] -= dx[dir];
                tera[1] -= dy[dir];
                break;
            }
            if (maze[tera[0]][tera[1]] == -3) {
                break;
            }

            totalTime += maze[tera[0]][tera[1]];
        }

        return totalTime;
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < height && y >= 0 && y < width;
    }

    static class Tera implements Comparable<Tera> {
        int x;
        int y;
        int time;

        public Tera(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Tera{" +
                    "x=" + x +
                    ", y=" + y +
                    ", time=" + time +
                    '}';
        }

        @Override
        public int compareTo(Tera o) {
            return time - o.time;
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
