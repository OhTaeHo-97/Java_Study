package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun16920 {
	static int N, M, P, wallNum;
    static int[] moveNums, playerCastleNum;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Loc>[] playersCastle;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        P = scanner.nextInt();
        map = new int[N][M];
        visited = new boolean[N][M];
        moveNums = new int[P + 1];
        playerCastleNum = new int[P + 1];
        playersCastle = new ArrayList[P + 1];
        for(int player = 1; player <= P; player++) {
            moveNums[player] = scanner.nextInt();
            playersCastle[player] = new ArrayList<>();
        }

        wallNum = 0;
        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < M; col++) {
                char cur = info.charAt(col);
                if(cur == '.') map[row][col] = 0;
                else if(cur == '#') {
                    map[row][col] = -1;
                    visited[row][col] = true;
                    wallNum++;
                }
                else {
                    map[row][col] = cur - '0';
                    visited[row][col] = true;
                    playersCastle[map[row][col]].add(new Loc(row, col, 0));
                    playerCastleNum[map[row][col]]++;
                }
            }
        }
    }

    static void solution() {
        while(true) {
            boolean isFinish = true;
            for(int player = 1; player <= P; player++)
                if(bfs(player)) isFinish = false;

            if(isFinish) break;
        }

        StringBuilder sb = new StringBuilder();
        for(int player = 1; player <= P; player++)
            sb.append(playerCastleNum[player]).append(' ');
        System.out.println(sb);
    }

    static boolean bfs(int player) {
        int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
        Queue<Loc> queue = new LinkedList<>();

        for(Loc loc : playersCastle[player]) queue.offer(loc);
        playersCastle[player].clear();

        while(!queue.isEmpty()) {
            Loc cur = queue.poll();
            if(cur.moveNum >= moveNums[player]) {
                cur.moveNum = 0;
                playersCastle[player].add(cur);
                continue;
            }

            for(int dir = 0; dir < 4; dir++) {
                int cx = cur.x + dx[dir], cy = cur.y + dy[dir];

                if(isInMap(cx, cy)) {
                    if(!visited[cx][cy] && cur.moveNum < moveNums[player]) {
                        queue.offer(new Loc(cx, cy, cur.moveNum + 1));
                        playerCastleNum[player]++;
                        visited[cx][cy] = true;
                    }
                }
            }
        }

        return !playersCastle[player].isEmpty();
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < N && y >= 0 && y < M) return true;
        return false;
    }

    static class Loc {
        int x, y, moveNum;

        public Loc(int x, int y, int moveNum) {
            this.x = x;
            this.y = y;
            this.moveNum = moveNum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Loc loc = (Loc) o;
            return x == loc.x && y == loc.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Loc{" +
                    "x=" + x +
                    ", y=" + y +
                    ", moveNum=" + moveNum +
                    '}';
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
