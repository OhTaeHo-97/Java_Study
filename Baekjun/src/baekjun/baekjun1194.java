package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1194 {
	static int N, M;
    static char[][] map;
    static Loc startLoc;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new char[N][M];

        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < M; col++) {
                map[row][col] = info.charAt(col);
                if(map[row][col] == '0') startLoc = new Loc(row, col, 0, 0);
            }
        }
    }

    static void solution() {
        System.out.println(bfs());
    }

    static int bfs() {
        int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
        Queue<Loc> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][64];

        queue.offer(startLoc);
        visited[startLoc.x][startLoc.y][startLoc.key] = true;

        while(!queue.isEmpty()) {
            Loc cur = queue.poll();
            if(map[cur.x][cur.y] == '1') return cur.moveNum;

            for(int dir = 0; dir < dx.length; dir++) {
                int cx = cur.x + dx[dir], cy = cur.y + dy[dir];

                if(isInMap(cx, cy)) {
                    if(!visited[cx][cy][cur.key] && map[cx][cy] != '#') {
                        if(map[cx][cy] >= 'a' && map[cx][cy] <= 'f') {
                            int key = 1 << (map[cx][cy] - 'a');
                            int nextKey = cur.key | key;
                            visited[cx][cy][nextKey] = true;
                            queue.offer(new Loc(cx, cy, cur.moveNum + 1, nextKey));
                        } else if(map[cx][cy] >= 'A' && map[cx][cy] <= 'F') {
                            int hasKey = cur.key & 1 << (map[cx][cy] - 'A');
                            if(hasKey == (int)Math.pow(2, map[cx][cy] - 'A')) {
                                visited[cx][cy][cur.key] = true;
                                queue.offer(new Loc(cx, cy, cur.moveNum + 1, cur.key));
                            }
                        } else {
                            visited[cx][cy][cur.key] = true;
                            queue.offer(new Loc(cx, cy, cur.moveNum + 1, cur.key));
                        }
                    }
                }
            }
        }

        return -1;
    }

    static boolean isInMap(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }

    static class Loc {
        int x, y, moveNum, key;

        public Loc(int x, int y, int moveNum, int key) {
            this.x = x;
            this.y = y;
            this.moveNum = moveNum;
            this.key = key;
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
