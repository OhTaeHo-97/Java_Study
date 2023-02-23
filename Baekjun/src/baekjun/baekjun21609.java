package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun21609 {
	static int N, M, maxBlockNum, blockNum;
    static long score;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new int[N][N];

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++)
                map[row][col] = scanner.nextInt();
        }
    }

    static void solution() {
        score = 0L;

        boolean[][] visited = new boolean[N][N];
        maxBlockNum = 0;
        blockNum = 0;
        ArrayList<int[]> maxBlock = new ArrayList<>();
        ArrayList<int[]> temp = new ArrayList<>();

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                if(!visited[row][col] && (map[row][col] != -1 && map[row][col] != 0 && map[row][col] != -2)) {
                    blockNum = 0;
                    temp = new ArrayList<>();
                    dfs(row, col, map[row][col], visited, temp);

                    if(blockNum >= maxBlockNum) {
                        maxBlock = new ArrayList<>();
                        maxBlock.addAll(temp);
                        maxBlockNum = blockNum;
                    }
                }
            }
        }

        for(int[] block : maxBlock)
            map[block[0]][block[1]] = -2;

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++)
                System.out.print(map[row][col] + " ");
            System.out.println();
        }
        System.out.println();

        score += (long)Math.pow(maxBlockNum, 2);

        int[][] tempMap = new int[N][N];
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++)
                tempMap[row][col] = map[col][(N - 1) - row];
        }

        map = tempMap;

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++)
                System.out.print(map[row][col] + " ");
            System.out.println();
        }
    }

    static void dfs(int x, int y, int color, boolean[][] visited, ArrayList<int[]> temp) {
        if(visited[x][y]) return;

        visited[x][y] = true;
        temp.add(new int[] {x, y});
        blockNum++;

        for(int dir = 0; dir < 4; dir++) {
            int cx = x + dx[dir], cy = y + dy[dir];

            if(isInMap(cx, cy)) {
                if(!visited[cx][cy] && (map[cx][cy] == color || map[cx][cy] == 0)) {
                    dfs(cx, cy, color, visited, temp);
                }
            }
        }
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < N && y >= 0 && y < N) return true;
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
    }
}
