package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class baekjun15559 {
    static int rowSize;
    static int colSize;
    static int groupCount;
    static int answer;
    static char[][] map;
    static int[][] visited;
    static Map<Character, int[]> directions = new HashMap<>() {{
        put('N', new int[]{-1, 0});
        put('S', new int[]{1, 0});
        put('W', new int[]{0, -1});
        put('E', new int[]{0, 1});
    }};

    static void input() {
        Reader scanner = new Reader();

        rowSize = scanner.nextInt();
        colSize = scanner.nextInt();
        map = new char[rowSize][colSize];
        visited = new int[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            String info = scanner.nextLine();
            for (int col = 0; col < colSize; col++) {
                map[row][col] = info.charAt(col);
            }
        }
    }

    static void solution() {
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (visited[row][col] == 0) {
                    groupCount++;
                    visited[row][col] = groupCount;
                    dfs(row, col);
                }
            }
        }

        System.out.println(answer);
    }

    static void dfs(int row, int col) {
        int[] direction = directions.get(map[row][col]);
        int nextX = row + direction[0];
        int nextY = col + direction[1];
        if (visited[nextX][nextY] == 0) {
            visited[nextX][nextY] = groupCount;
            dfs(nextX, nextY);
        } else if (visited[nextX][nextY] == groupCount) {
            answer++;
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
