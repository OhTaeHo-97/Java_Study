package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun17070 {
    static int houseSize;
    static int answer;
    static boolean[][] map;

    static void input() {
        Reader scanner = new Reader();

        houseSize = scanner.nextInt();
        answer = 0;
        map = new boolean[houseSize][houseSize];

        for (int row = 0; row < houseSize; row++) {
            for (int col = 0; col < houseSize; col++) {
                int info = scanner.nextInt();
                map[row][col] = (info == 0);
            }
        }
    }

    static void solution() {
        dfs(0, 1, 0);
        System.out.println(answer);
    }

    static void dfs(int x, int y, int direction) {
        if (x == houseSize - 1 && y == houseSize - 1) {
            answer++;
            return;
        }

        if (direction == 0 || direction == 1) {
            if (y + 1 < houseSize && map[x][y + 1]) {
                dfs(x, y + 1, 0);
            }
        }
        if (direction == 1 || direction == 2) {
            if (x + 1 < houseSize && map[x + 1][y]) {
                dfs(x + 1, y, 2);
            }
        }
        if (x + 1 < houseSize && y + 1 < houseSize && isBlank(x, y)) {
            dfs(x + 1, y + 1, 1);
        }
    }

    static boolean isBlank(int x, int y) {
        return map[x][y + 1] && map[x + 1][y] && map[x + 1][y + 1];
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
            while (st == null || !st.hasMoreElements()) {
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
