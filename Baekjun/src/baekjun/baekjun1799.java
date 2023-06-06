package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1799 {
	static int[] dx = {-1, -1, 1, 1}, dy = {-1, 1, -1, 1};
    static int size, blackNum, whiteNum;
    static boolean[][] map, blackVisited, whiteVisited;

    static void input() {
        Reader scanner = new Reader();

        size = scanner.nextInt();
        map = new boolean[size][size];
        blackVisited = new boolean[size][size];
        whiteVisited = new boolean[size][size];

        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                int num = scanner.nextInt();

                if(num == 1) map[row][col] = true;
                else map[row][col] = false;
            }
        }
    }

    static void solution() {
        blackSearch(0, 0, 0, blackVisited);
        whiteSearch(0, 1, 0, whiteVisited);

        System.out.println(blackNum + whiteNum);
    }

    static void whiteSearch(int x, int y, int count, boolean[][] visited) {
        whiteNum = Math.max(whiteNum, count);

        if(y >= size) {
            x++;
            y = (x % 2 == 0) ? 1 : 0;
        }

        if(x >= size) return;

        if(isPossible(x, y, visited)) {
            visited[x][y] = true;
            whiteSearch(x, y + 2, count + 1, visited);
            visited[x][y] = false;
        }

        whiteSearch(x, y + 2, count, visited);
    }

    static void blackSearch(int x, int y, int count, boolean[][] visited) {
        blackNum = Math.max(blackNum, count);

        if(y >= size) {
            x++;
            y = (x % 2 == 0) ? 0 : 1;
        }

        if(x >= size) return;

        if(isPossible(x, y, visited)) {
            visited[x][y] = true;
            blackSearch(x, y + 2, count + 1, visited);
            visited[x][y] = false;
        }

        blackSearch(x, y + 2, count, visited);
    }

    static boolean isPossible(int x, int y, boolean[][] visited) {
        if(!map[x][y]) return false;

        for(int dir = 0; dir < 4; dir++) {
            int cx = x + dx[dir], cy = y + dy[dir];

            for(int count = 0; count < size; count++) {
                if(isInMap(cx, cy)) {
                    if(visited[cx][cy]) return false;

                    cx += dx[dir];
                    cy += dy[dir];
                }
            }
        }

        return true;
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
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
    }
}
