package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun13460 {
	static int N, M;
    static char[][] map;
    static Turn init;
    static int[] hole;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new char[N][M];
        int[] red = null, blue = null;

        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < M; col++) {
                map[row][col] = info.charAt(col);

                if(map[row][col] == 'O') hole = new int[] {row, col};
                else if(map[row][col] == 'R') {
                    map[row][col] = '.';
                    red = new int[] {row, col};
                } else if(map[row][col] == 'B') {
                    map[row][col] = '.';
                    blue = new int[] {row, col};
                }
            }
        }

        init = new Turn(red, blue);
    }

    static void solution() {
        System.out.println(bfs(init));
    }

    static int bfs(Turn init) {
        boolean[][][][] visited = new boolean[N][M][N][M];
        Queue<Turn> queue = new LinkedList<>();

        queue.offer(init);

        while(!queue.isEmpty()) {
            Turn cur = queue.poll();

            if(map[cur.red[0]][cur.red[1]] == 'O' && map[cur.blue[0]][cur.blue[1]] != 'O') return cur.moveNum;
            else if(map[cur.blue[0]][cur.blue[1]] == 'O') continue;

            int[] red = cur.red, blue = cur.blue;
            for(int dir = 0; dir < 4; dir++) {
                int[] tempRed = null, tempBlue = null;
                if(dir == 0) {
                    tempRed = moveLeft(red);
                    tempBlue = moveLeft(blue);

                    if(map[tempRed[0]][tempRed[1]] == 'O' && map[tempBlue[0]][tempBlue[1]] == 'O') continue;

                    if(tempRed[0] == tempBlue[0] && tempRed[1] == tempBlue[1]) {
                        if(red[1] > blue[1]) tempRed[1]++;
                        else tempBlue[1]++;
                    }
                } else if(dir == 1) {
                    tempRed = moveRight(red);
                    tempBlue = moveRight(blue);

                    if(map[tempRed[0]][tempRed[1]] == 'O' && map[tempBlue[0]][tempBlue[1]] == 'O') continue;

                    if(tempRed[0] == tempBlue[0] && tempRed[1] == tempBlue[1]) {
                        if(red[1] > blue[1]) tempBlue[1]--;
                        else tempRed[1]--;
                    }
                } else if(dir == 2) {
                    tempRed = moveUp(red);
                    tempBlue = moveUp(blue);

                    if(map[tempRed[0]][tempRed[1]] == 'O' && map[tempBlue[0]][tempBlue[1]] == 'O') continue;

                    if(tempRed[0] == tempBlue[0] && tempRed[1] == tempBlue[1]) {
                        if(red[0] > blue[0]) tempRed[0]++;
                        else tempBlue[0]++;
                    }
                } else if(dir == 3) {
                    tempRed = moveDown(red);
                    tempBlue = moveDown(blue);

                    if(map[tempRed[0]][tempRed[1]] == 'O' && map[tempBlue[0]][tempBlue[1]] == 'O') continue;

                    if(tempRed[0] == tempBlue[0] && tempRed[1] == tempBlue[1]) {
                        if(red[0] > blue[0]) tempBlue[0]--;
                        else tempRed[0]--;
                    }
                }

                if(visited[tempRed[0]][tempRed[1]][tempBlue[0]][tempBlue[1]]) continue;
                if(cur.moveNum + 1 > 10) continue;

                visited[tempRed[0]][tempRed[1]][tempBlue[0]][tempBlue[1]] = true;
                queue.offer(new Turn(tempRed, tempBlue, cur.moveNum + 1));
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
        for(int col = loc[1]; col < M; col++) {
            if(map[loc[0]][col] == 'O') return new int[] {loc[0], col};
            if(map[loc[0]][col] == '#') return new int[] {loc[0], col - 1};
        }

        return new int[] {loc[0], M - 1};
    }

    static int[] moveUp(int[] loc) {
        for(int row = loc[0]; row >= 0; row--) {
            if(map[row][loc[1]] == 'O') return new int[] {row, loc[1]};

            if(map[row][loc[1]] == '#')  return new int[] {row + 1, loc[1]};
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

    static class Turn {
        int[] red, blue;
        int moveNum;

        public Turn(int[] red, int[] blue) {
            this.red = red;
            this.blue = blue;
            moveNum = 0;
        }

        public Turn(int[] red, int[] blue, int moveNum) {
            this.red = red;
            this.blue = blue;
            this.moveNum = moveNum;
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
