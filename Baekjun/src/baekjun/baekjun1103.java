package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1103 {
	static int N, M, answer;
    static boolean isInfinite;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static boolean[][] visited;
    static int[][] count;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new char[N][M];
        count = new int[N][M];
        visited = new boolean[N][M];
        isInfinite = false;
        answer = 0;

        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < M; col++) {
                map[row][col] = info.charAt(col);
            }
        }
    }

    static void solution() {
        visited[0][0] = true;

        dfs(0, 0, 1);

        System.out.println(isInfinite ? -1 : answer);
    }

    static void dfs(int x, int y, int cnt) {
        if(cnt > answer)
            answer = cnt;

        count[x][y] = cnt;

        for(int dir = 0; dir < 4; dir++) {
            int cx = x + (dx[dir] * (map[x][y] - '0')), cy = y + (dy[dir] * (map[x][y] - '0'));

            if(isInMap(cx, cy) && map[cx][cy] != 'H') {
                if(visited[cx][cy]) {
                    isInfinite = true;
                    return;
                }

                if(count[cx][cy] > cnt)
                    continue;

                visited[cx][cy] = true;
                dfs(cx, cy, cnt + 1);
                visited[cx][cy] = false;
            }
        }
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < N && y >= 0 && y < M) return true;
        return false;
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
