package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun3109 {
	static int R, C;
    static char[][] map;
    static int[] dx = {-1, 0, 1}, dy = {1, 1, 1};
    static void input() {
        Reader scanner = new Reader();
        R = scanner.nextInt();
        C = scanner.nextInt();
        map = new char[R][C];

        for(int row = 0; row < R; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < C; col++)
                map[row][col] = info.charAt(col);
        }
    }

    static void solution() {
        int answer = 0;
        boolean[][] visited = new boolean[R][C];

        for(int row = 0; row < R; row++) {
            if(dfs(row, 0, visited))
                answer++;
        }

        System.out.println(answer);
    }

    static boolean dfs(int x, int y, boolean[][] visited) {
        if(visited[x][y]) return false;

        visited[x][y] = true;

        if(y == C - 1) return true;

        for(int dir = 0; dir < 3; dir++) {
            int cx = x + dx[dir], cy = y + dy[dir];

            if(isInMap(cx, cy) && map[cx][cy] == '.' && !visited[cx][cy]) {
                if(dfs(cx, cy, visited)) return true;
            }
        }

        return false;
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < R && y >= 0 && y < C) return true;
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
