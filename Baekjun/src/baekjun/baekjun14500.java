package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun14500 {
	static final int[] DX = {-1, 1, 0, 0}, DY = {0, 0, -1, 1};
    static int N, M, max;
    static int[][] map;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new int[N][M];
        max = Integer.MIN_VALUE;

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++)
                map[row][col] = scanner.nextInt();
        }
    }

    static void solution() {
        boolean[][] visited = new boolean[N][M];
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                visited[row][col] = true;
                dfs(row, col, 1, map[row][col], visited);
                visited[row][col] = false;
                checkOtherCase(row, col);
            }
        }

        System.out.println(max);
    }

    static void checkOtherCase(int x, int y) {
        if(x < N - 2 && y < M - 1)
            max = Math.max(max, map[x][y] + map[x + 1][y] + map[x + 2][y] + map[x + 1][y + 1]);
        if(x < N - 2 && y > 0)
            max = Math.max(max, map[x][y] + map[x + 1][y] + map[x + 2][y] + map[x + 1][y - 1]);
        if(x < N - 1 && y < M - 2)
            max = Math.max(max, map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x + 1][y + 1]);
        if(x > 0 && y < M - 2)
            max = Math.max(max, map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x - 1][y + 1]);
    }

    static void dfs(int x, int y, int count, int sum, boolean[][] visited) {
        if(count >= 4) {
            max = Math.max(max, sum);
            return;
        }

        for(int dir = 0; dir < DX.length; dir++) {
            int cx = x + DX[dir], cy = y + DY[dir];

            if(isInMap(cx, cy) && !visited[cx][cy]) {
                visited[cx][cy] = true;
                dfs(cx, cy, count + 1, sum + map[cx][cy], visited);
                visited[cx][cy] = false;
            }
        }
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
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
