package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class baekjun17090 {
    static final Map<Character, int[]> DIRECTION = new HashMap<>() {{
        put('U', new int[]{-1, 0});
        put('R', new int[]{0, 1});
        put('D', new int[]{1, 0});
        put('L', new int[]{0, -1});
    }};

    static int rowLen;
    static int colLen;
    static char[][] map;
    static int[][] visited;
    static boolean[][] isVisited;

    static void input() {
        Reader scanner = new Reader();

        rowLen = scanner.nextInt();
        colLen = scanner.nextInt();
        map = new char[rowLen][colLen];
        visited = new int[rowLen][colLen];
        isVisited = new boolean[rowLen][colLen];

        for (int row = 0; row < rowLen; row++) {
            String info = scanner.nextLine();
            for (int col = 0; col < colLen; col++) {
                map[row][col] = info.charAt(col);
            }
        }
    }

    static void solution() {
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (visited[row][col] == 0) {
                    findPossibilityAboutEscape(row, col);
                }
            }
        }
        System.out.println(getPossibleSpace());
    }

    static int getPossibleSpace() {
        int answer = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (visited[row][col] == 1) {
                    answer++;
                }
            }
        }

        return answer;
    }

    static int findPossibilityAboutEscape(int row, int col) {
        int nextX = row + DIRECTION.get(map[row][col])[0];
        int nextY = col + DIRECTION.get(map[row][col])[1];
        if (!isInMap(nextX, nextY)) {
            return visited[row][col] = 1;
        }
        if (visited[row][col] != 0) {
            return visited[row][col];
        }
        if (isVisited[row][col]) {
            return visited[row][col] = -1;
        }

        isVisited[row][col] = true;
        return visited[row][col] = findPossibilityAboutEscape(nextX, nextY);
    }

    static boolean isInMap(int row, int col) {
        return row >= 0 && row < rowLen && col >= 0 && col < colLen;
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
