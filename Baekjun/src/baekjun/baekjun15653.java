package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun15653 {
    static int N, M;
    static char[][] map;
    static Loc red, blue;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new char[N][M];

        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < M; col++) {
                map[row][col] = info.charAt(col);
                if(map[row][col] == 'B') {
                    blue = new Loc(row, col);
                    map[row][col] = '.';
                } else if(map[row][col] == 'R') {
                    red = new Loc(row, col);
                    map[row][col] = '.';
                }
            }
        }
    }

    static void solution() {
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Ball> queue = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][M][N][M];

        queue.offer(new Ball(red, blue, 0));
        visited[red.x][red.y][blue.x][blue.y] = true;

        while(!queue.isEmpty()) {
            Ball cur = queue.poll();

            if(map[cur.red.x][cur.red.y] == 'O' && map[cur.blue.x][cur.blue.y] != 'O')
                return cur.moveNum;
            else if(map[cur.blue.x][cur.blue.y] == 'O') continue;

            Loc red = cur.red, blue = cur.blue;
            for(int dir = 0; dir < 4; dir++) {
                Loc tempRed = null, tempBlue = null;
                if(dir == 0) {
                    tempRed = moveLeft(red);
                    tempBlue = moveLeft(blue);

                    if(map[tempRed.x][tempRed.y] == 'O' && map[tempBlue.x][tempBlue.y] == 'O')
                        continue;

                    if(tempRed.x == tempBlue.x && tempRed.y == tempBlue.y) {
                        if(red.y > blue.y) tempRed.y++;
                        else tempBlue.y++;
                    }
                } else if(dir == 1) {
                    tempRed = moveRight(red);
                    tempBlue = moveRight(blue);

                    if(map[tempRed.x][tempRed.y] == 'O' && map[tempBlue.x][tempBlue.y] == 'O')
                        continue;

                    if(tempRed.x == tempBlue.x && tempRed.y == tempBlue.y) {
                        if(tempRed.y > tempBlue.y) tempBlue.y--;
                        else tempRed.y--;
                    }
                } else if(dir == 2) {
                    tempRed = moveUp(red);
                    tempBlue = moveUp(blue);

                    if(map[tempRed.x][tempRed.y] == 'O' && map[tempBlue.x][tempBlue.y] == 'O')
                        continue;

                    if(tempRed.x == tempBlue.x && tempRed.y == tempBlue.y) {
                        if(tempRed.x > tempBlue.x) tempRed.x++;
                        else tempBlue.x++;
                    }
                } else if(dir == 3) {
                    tempRed = moveDown(red);
                    tempBlue = moveDown(blue);

                    if(map[tempRed.x][tempRed.y] == 'O' && map[tempBlue.x][tempBlue.y] == 'O')
                        continue;

                    if(tempRed.x == tempBlue.x && tempRed.y == tempBlue.y) {
                        if(tempRed.x > tempBlue.x) tempBlue.x--;
                        else tempRed.x--;
                    }
                }

                if(visited[tempRed.x][tempRed.y][tempBlue.x][tempBlue.y]) continue;

                visited[tempRed.x][tempRed.y][tempBlue.x][tempBlue.y] = true;
                queue.offer(new Ball(tempRed, tempBlue, cur.moveNum + 1));
            }
        }

        return -1;
    }

    static Loc moveLeft(Loc ball) {
        for(int col = ball.y; col >= 0; col--) {
            if(map[ball.x][col] == 'O') return new Loc(ball.x, col);
            if(map[ball.x][col] == '#') return new Loc(ball.x, col + 1);
        }

        return new Loc(ball.x, 0);
    }

    static Loc moveRight(Loc ball) {
        for(int col = ball.y; col < M; col++) {
            if(map[ball.x][col] == 'O') return new Loc(ball.x, col);
            if(map[ball.x][col] == '#') return new Loc(ball.x, col - 1);
        }

        return new Loc(ball.x, M - 1);
    }

    static Loc moveUp(Loc ball) {
        for(int row = ball.x; row >= 0; row--) {
            if(map[row][ball.y] == 'O') return new Loc(row, ball.y);
            if(map[row][ball.y] == '#') return new Loc(row + 1, ball.y);
        }

        return new Loc(0, ball.y);
    }

    static Loc moveDown(Loc ball) {
        for(int row = ball.x; row < N; row++) {
            if(map[row][ball.y] == 'O') return new Loc(row, ball.y);
            if(map[row][ball.y] == '#') return new Loc(row - 1, ball.y);
        }

        return new Loc(N - 1, ball.y);
    }

    static class Ball {
        Loc red, blue;
        int moveNum;

        public Ball(Loc red, Loc blue, int moveNum) {
            this.red = red;
            this.blue = blue;
            this.moveNum = moveNum;
        }
    }

    static class Loc {
        int x, y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Loc{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
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
