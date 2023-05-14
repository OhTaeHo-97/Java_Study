package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun17244 {
	static int M, N, stuffNum;
    static char[][] map;
    static int[][][] minMoveNums;
    static int[] startLoc, endLoc;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new char[M][N];
        minMoveNums = new int[M][N][(int)Math.pow(2, 5)];
        for(int row = 0; row < M; row++) {
            for(int col = 0; col < N; col++)
                Arrays.fill(minMoveNums[row][col], Integer.MAX_VALUE);
        }

        for(int row = 0; row < M; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < N; col++) {
                map[row][col] = info.charAt(col);

                if(map[row][col] == 'S') {
                    startLoc = new int[] {row, col};
                    map[row][col] = '.';
                } else if(map[row][col] == 'E') {
                    endLoc = new int[]{row, col};
                    map[row][col] = '.';
                } else if(map[row][col] == 'X') {
                    map[row][col] = (char)(stuffNum + '0');
                    stuffNum++;
                }
            }
        }
    }

    static void solution() {
        bfs();
        System.out.println(minMoveNums[endLoc[0]][endLoc[1]][(1 << stuffNum) - 1]);
    }

    static void bfs() {
        Queue<Loc> queue = new LinkedList<>();

        queue.offer(new Loc(startLoc[0], startLoc[1], 0, 0));
        minMoveNums[startLoc[0]][startLoc[1]][0] = 0;

        while (!queue.isEmpty()) {
            Loc cur = queue.poll();
            if(cur.x == endLoc[0] && cur.y == endLoc[1] && cur.stuffNum == ((1 << stuffNum) - 1)) break;

            for(int dir = 0; dir < dx.length; dir++) {
                int cx = cur.x + dx[dir], cy = cur.y + dy[dir];

                if(isInMap(cx, cy) && map[cx][cy] != '#') {
                    int nextStuffNum = cur.stuffNum;
                    if('0' <= map[cx][cy] && map[cx][cy] < stuffNum + '0') nextStuffNum |= (1 << (map[cx][cy] - '0'));
                    if(minMoveNums[cx][cy][nextStuffNum] > cur.moveNum + 1) {
                        minMoveNums[cx][cy][nextStuffNum] = cur.moveNum + 1;
                        queue.offer(new Loc(cx, cy, nextStuffNum, cur.moveNum + 1));
                    }
                }
            }
        }
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < M && y >= 0 && y < N) return true;
        return false;
    }

    static class Loc {
        int x, y, stuffNum, moveNum;

        public Loc(int x, int y, int stuffNum, int moveNum) {
            this.x = x;
            this.y = y;
            this.stuffNum = stuffNum;
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
