package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun14442 {
	static int N, M, K;
    static int[][] map;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
        map = new int[N][M];
        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < M; col++) {
                map[row][col] = info.charAt(col) - '0';
            }
        }
    }

    static void solution() {
        int answer = bfs(new int[] {0, 0}, new int[] {N - 1, M - 1});
        System.out.println(answer == 0 ? -1 : answer);
    }

    static int bfs(int[] start, int[] end) {
        int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
        Queue<Loc> queue = new LinkedList<>();
        queue.offer(new Loc(start[0], start[1], 1, 0));
        boolean[][][] visited = new boolean[N][M][K + 1];
        visited[start[0]][start[1]][0] = true;
        int answer = 0;
        while(!queue.isEmpty()) {
            Loc cur = queue.poll();
            if(cur.x == end[0] && cur.y == end[1]) {
                answer = cur.space;
                break;
            }
            for(int dir = 0; dir < 4; dir++) {
                int cx = cur.x + dx[dir], cy = cur.y + dy[dir];
                if(isInMap(cx, cy)) {
                    if(map[cx][cy] == 0) {
                        if(!visited[cx][cy][cur.wall]) {
                            visited[cx][cy][cur.wall] = true;
                            queue.offer(new Loc(cx, cy, cur.space + 1, cur.wall));
                        }
                    } else if(map[cx][cy] == 1 && cur.wall < K) {
                        if(!visited[cx][cy][cur.wall + 1]) {
                            visited[cx][cy][cur.wall + 1] = true;
                            queue.offer(new Loc(cx, cy, cur.space + 1, cur.wall + 1));
                        }
                    }
                }
            }
        }
        return answer;
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < N && y >= 0 && y < M) return true;
        return false;
    }

    static class Loc {
        int x, y, space, wall;
        public Loc(int x, int y, int space, int wall) {
            this.x = x;
            this.y = y;
            this.space = space;
            this.wall = wall;
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
