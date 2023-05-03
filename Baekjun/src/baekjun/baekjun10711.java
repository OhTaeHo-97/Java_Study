package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun10711 {
	static int H, W;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Loc> queue;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};

    static void input() {
        Reader scanner = new Reader();

        H = scanner.nextInt();
        W = scanner.nextInt();
        map = new int[H][W];
        visited = new boolean[H][W];
        queue = new LinkedList<>();

        for(int row = 0; row < H; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < W; col++) {
                if(info.charAt(col) == '.') map[row][col] = 0;
                else map[row][col] = info.charAt(col) - '0';
            }
        }
    }

    static void solution() {
        init();
        System.out.println(bfs());
    }

    static int bfs() {
        int time = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int count = 0; count < size; count++) {
                Loc cur = queue.poll();

                map[cur.x][cur.y] = 0;
                for(int dir = 0; dir < 8; dir++) {
                    int cx = cur.x + dx[dir], cy = cur.y + dy[dir];

                    if(isInMap(cx, cy)) {
                        if(!visited[cx][cy] && map[cx][cy] != 0) {
                            int emptyCount = getEmptyLocNum(cx, cy);
                            if(emptyCount >= map[cx][cy]) {
                                visited[cx][cy] = true;
                                queue.offer(new Loc(cx, cy));
                            }
                        }
                    }

                }
            }

            time++;
        }

        return time;
    }

    static void init() {
        for(int row = 0; row < H; row++) {
            for(int col = 0; col < W; col++) {
                if(map[row][col] == 0 || map[row][col] == 9) continue;

                int count = getEmptyLocNum(row, col);

                if(count >= map[row][col]) {
                    visited[row][col] = true;
                    queue.offer(new Loc(row, col));
                }
            }
        }
    }

    static int getEmptyLocNum(int x, int y) {
        int count = 0;

        for(int dir = 0; dir < 8; dir++) {
            int cx = x + dx[dir], cy = y + dy[dir];
            if(isInMap(cx, cy) && map[cx][cy] == 0) count++;
        }

        return count;
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < H && y >= 0 && y < W) return true;
        return false;
    }

    static class Loc {
        int x, y;

        public Loc(int x, int y) {
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
