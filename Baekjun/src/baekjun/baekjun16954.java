package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class baekjun16954 {
	static final int SIZE = 8;
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 0, 1, 1, 1, 0, -1, -1, -1};
    static char[][] map;
    static Queue<Character> locs;

    static void input() {
        Reader scanner = new Reader();
        map = new char[8][8];
        locs = new LinkedList<>();

        for(int row = 0; row < SIZE; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < SIZE; col++)
                map[row][col] = info.charAt(col);
        }
    }

    static void solution() {
        locs.offer(new Character(SIZE - 1, 0));
        System.out.println(bfs() ? 1 : 0);
    }

    static boolean bfs() {
        while(!locs.isEmpty()) {
            int size = locs.size();

            for(int count = 0; count < size; count++) {
                Character cur = locs.poll();

                if(map[cur.x][cur.y] == '#') continue;

                for(int dir = 0; dir < 9; dir++) {
                    int cx = cur.x + dx[dir], cy = cur.y + dy[dir];

                    if(isInMap(cx, cy)) {
                        if(cx == 0 && cy == 7) return true;
                        if(map[cx][cy] == '.')
                            locs.offer(new Character(cx, cy));
                    }
                }
            }

            moveWalls();
        }

        return false;
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < SIZE && y >= 0 && y < SIZE) return true;
        return false;
    }

    static void moveWalls() {
        for(int row = SIZE - 1; row >= 0; row--) {
            for(int col = SIZE - 1; col >= 0; col--) {
                if(row - 1 < 0) map[row][col] = '.';
                else map[row][col] = map[row - 1][col];
            }
        }
    }

    static class Character {
        int x, y;

        public Character(int x, int y) {
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

        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
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
