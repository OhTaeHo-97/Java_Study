package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun4991 {
    static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();
    static int w, h, min, massedNum;
    static char[][] map;
    static int[][] distance;
    static Loc[] massedLoc;

    static void input() {
        w = scanner.nextInt();
        h = scanner.nextInt();

        if(w == 0 && h == 0) {
            System.out.print(sb);
            System.exit(0);
        }

        min = Integer.MAX_VALUE;
        map = new char[h][w];
        massedLoc = new Loc[11];
        massedNum = 1;

        for(int row = 0; row < h; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < w; col++) {
                map[row][col] = info.charAt(col);

                if(map[row][col] == 'o') massedLoc[0] = new Loc(row, col);
                else if(map[row][col] == '*') massedLoc[massedNum++] = new Loc(row, col);
            }
        }
    }

    static void solution() {
        distance = new int[massedNum + 1][massedNum + 1];
        for(int start = 0; start < massedNum; start++) {
            for(int end = start + 1; end < massedNum; end++) {
                int dist = bfs(massedLoc[start], massedLoc[end]);
                if(dist == -1) {
                    sb.append(-1).append('\n');
                    return;
                } else {
                    distance[start][end] = distance[end][start] = dist;
                }
            }
        }

        boolean[] selected = new boolean[massedNum];
        dfs(0, 0, 0, selected);

        sb.append(min).append('\n');
    }

    static void dfs(int index, int count, int totalDist, boolean[] selected) {
        if(count == massedNum - 1) {
            min = Math.min(min, totalDist);
            return;
        }

        for(int idx = 1; idx < massedNum; idx++) {
            if(!selected[idx]) {
                selected[idx] = true;
                dfs(idx, count + 1, totalDist + distance[index][idx], selected);
                selected[idx] = false;
            }
        }
    }

    static int bfs(Loc start, Loc end) {
        int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
        boolean[][] visited = new boolean[h][w];
        Queue<Loc> queue = new LinkedList<>();

        queue.offer(start);
        visited[start.x][start.y] = true;
        map[start.x][start.y] = '.';

        int moveNum = 0;
        while(!queue.isEmpty()) {
            int curSize = queue.size();
            for(int idx = 0; idx < curSize; idx++) { // 거리별 탐색
                Loc cur = queue.poll();
                if(cur.x == end.x && cur.y == end.y)
                    return moveNum;

                for(int dir = 0; dir < 4; dir++) {
                    int cx = cur.x + dx[dir], cy = cur.y + dy[dir];
                    if(isInMap(cx, cy)) {
                        if(!visited[cx][cy] && map[cx][cy] != 'x') {
                            visited[cx][cy] = true;
                            queue.offer(new Loc(cx, cy));
                        }
                    }
                }
            }

            moveNum++;
        }

        return -1;
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < h && y >= 0 && y < w;
    }

    static class Loc {
        int x, y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        while(true) {
            input();
            solution();
        }
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
