package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun15644 {
    static int N, M;
    static String path;
    static char[][] map;
    static int[] red, blue, hole;
    static Marble init;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static char[] directions = new char[] {'L', 'R', 'U', 'D'};

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new char[N][M];

        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < M; col++) {
                map[row][col] = info.charAt(col);
                if(map[row][col] == 'R') {
                    red = new int[] {row, col};
                    map[row][col] = '.';
                } else if(map[row][col] == 'B') {
                    blue = new int[] {row, col};
                    map[row][col] = '.';
                } else if(map[row][col] == 'O')
                    hole = new int[] {row, col};
            }
        }

        init = new Marble(red, blue);
    }

    static void solution() {
        int totalMoveNum = bfs(init);

        StringBuilder sb = new StringBuilder();
        if(path == null)
            sb.append(totalMoveNum);
        else
            sb.append(totalMoveNum).append('\n').append(path);
        System.out.println(sb);
    }

    static int bfs(Marble init) {
        Queue<Marble> marbleQueue = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][M][N][M];

        marbleQueue.offer(init);
        visited[init.red[0]][init.red[1]][init.blue[0]][init.blue[1]] = true;

        while(!marbleQueue.isEmpty()) {
            Marble cur = marbleQueue.poll();

            if(map[cur.red[0]][cur.red[1]] == 'O' && map[cur.blue[0]][cur.blue[1]] != 'O') {
                path = cur.path;
                return cur.moveNum;
            }
            if(map[cur.blue[0]][cur.blue[1]] == 'O') continue;

            int[] red = cur.red, blue = cur.blue;
            for(int dir = 0; dir < dx.length; dir++) {
                int[] tempRed = null, tempBlue = null;

                if(dir == 0) {
                    tempRed = moveLeft(red);
                    tempBlue = moveLeft(blue);

                    if(map[tempRed[0]][tempRed[1]] == 'O' && map[tempBlue[0]][tempBlue[1]] == 'O')
                        continue;

                    if(tempRed[0] == tempBlue[0] && tempRed[1] == tempBlue[1]) {
                        if(red[1] > blue[1]) tempRed[1]++;
                        else tempBlue[1]++;
                    }
                } else if(dir == 1) {
                    tempRed = moveRight(red);
                    tempBlue = moveRight(blue);

                    if(map[tempRed[0]][tempRed[1]] == 'O' && map[tempBlue[0]][tempBlue[1]] == 'O')
                        continue;

                    if(tempRed[0] == tempBlue[0] && tempRed[1] == tempBlue[1]) {
                        if(red[1] > blue[1]) tempBlue[1]--;
                        else tempRed[1]--;
                    }
                } else if(dir == 2) {
                    tempRed = moveUp(red);
                    tempBlue = moveUp(blue);

                    if(map[tempRed[0]][tempRed[1]] == 'O' && map[tempBlue[0]][tempBlue[1]] == 'O')
                        continue;

                    if(tempRed[0] == tempBlue[0] && tempRed[1] == tempBlue[1]) {
                        if(red[0] > blue[0]) tempRed[0]++;
                        else tempBlue[0]++;
                    }
                } else {
                    tempRed = moveDown(red);
                    tempBlue = moveDown(blue);

                    if(map[tempRed[0]][tempRed[1]] == 'O' && map[tempBlue[0]][tempBlue[1]] == 'O')
                        continue;

                    if(tempRed[0] == tempBlue[0] && tempRed[1] == tempBlue[1]) {
                        if(red[0] > blue[0]) tempBlue[0]--;
                        else tempRed[0]--;
                    }
                }

                if(visited[tempRed[0]][tempRed[1]][tempBlue[0]][tempBlue[1]]) continue;

                visited[tempRed[0]][tempRed[1]][tempBlue[0]][tempBlue[1]] = true;
                marbleQueue.offer(new Marble(tempRed, tempBlue, cur.moveNum + 1, cur.path + directions[dir]));
            }
        }

        return -1;
    }

    static int[] moveLeft(int[] loc) {
        for(int col = loc[1]; col >= 0; col--) {
            if(map[loc[0]][col] == 'O') return new int[] {loc[0], col};
            if(map[loc[0]][col] == '#') return new int[] {loc[0], col + 1};
        }

        return new int[] {loc[0], 0};
    }

    static int[] moveRight(int[] loc) {
        for (int col = loc[1]; col < M; col++) {
            if(map[loc[0]][col] == 'O') return new int[] {loc[0], col};
            if(map[loc[0]][col] == '#') return new int[] {loc[0], col - 1};
        }

        return new int[] {loc[0], M - 1};
    }

    static int[] moveUp(int[] loc) {
        for(int row = loc[0]; row >= 0; row--) {
            if(map[row][loc[1]] == 'O') return new int[] {row, loc[1]};
            if(map[row][loc[1]] == '#') return new int[] {row + 1, loc[1]};
        }

        return new int[] {0, loc[1]};
    }

    static int[] moveDown(int[] loc) {
        for(int row = loc[0]; row < N; row++) {
            if(map[row][loc[1]] == 'O') return new int[] {row, loc[1]};
            if(map[row][loc[1]] == '#') return new int[] {row - 1, loc[1]};
        }

        return new int[] {N - 1, loc[1]};
    }

    static class Marble {
        int[] red, blue;
        int moveNum;
        String path;

        public Marble(int[] red, int[] blue) {
            this.red = red;
            this.blue = blue;
            moveNum = 0;
            path = "";
        }

        public Marble(int[] red, int[] blue, int moveNum) {
            this.red = red;
            this.blue = blue;
            this.moveNum = moveNum;
            path = "";
        }

        public Marble(int[] red, int[] blue, int moveNum, String path) {
            this.red = red;
            this.blue = blue;
            this.moveNum = moveNum;
            this.path = path;
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
