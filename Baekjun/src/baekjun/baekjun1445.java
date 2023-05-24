package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun1445 {
	static final int[] DX = {-1, 0, 1, 0}, DY = {0, -1, 0, 1};
    static int N, M;
    static char[][] map;
    static Loc start;
    static List<Loc> garbageList;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new char[N][M];
        garbageList = new ArrayList<>();

        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < M; col++) {
                map[row][col] = info.charAt(col);

                if(map[row][col] == 'S') start = new Loc(row, col, 0, 0);
                else if(map[row][col] == 'g') garbageList.add(new Loc(row, col));
            }
        }
    }

    static void solution() {
        setNearOfGarbageList();
        Loc answer = bfs();

        StringBuilder sb = new StringBuilder();
        sb.append(answer.garbageCnt).append(' ').append(answer.nearCnt);
        System.out.println(sb);
    }

    static Loc bfs() {
        PriorityQueue<Loc> queue = new PriorityQueue<>((l1, l2) -> {
            if(l1.garbageCnt != l2.garbageCnt) return l1.garbageCnt - l2.garbageCnt;
            return l1.nearCnt - l2.nearCnt;
        });
        boolean[][] visited = new boolean[N][M];

        queue.offer(start);
        visited[start.x][start.y] = true;

        while(!queue.isEmpty()) {
            Loc cur = queue.poll();
            if(map[cur.x][cur.y] == 'F')
                return cur;

            for(int dir = 0; dir < DX.length; dir++) {
                int cx = cur.x + DX[dir], cy = cur.y + DY[dir];

                if(isInMap(cx, cy) && !visited[cx][cy]) {
                    switch(map[cx][cy]) {
                        case 'g':
                            queue.offer(new Loc(cx, cy, cur.garbageCnt + 1, cur.nearCnt));
                            break;
                        case 'N':
                            queue.offer(new Loc(cx, cy, cur.garbageCnt, cur.nearCnt + 1));
                            break;
                        default:
                            queue.offer(new Loc(cx, cy, cur.garbageCnt, cur.nearCnt));
                            break;
                    }

                    visited[cx][cy] = true;
                }
            }
        }

        return null;
    }

    static void setNearOfGarbageList() {
        for(Loc garbage : garbageList) {
            for(int dir = 0; dir < DX.length; dir++) {
                int cx = garbage.x + DX[dir], cy = garbage.y + DY[dir];
                if(isInMap(cx, cy) && map[cx][cy] == '.')
                    map[cx][cy] = 'N';
            }
        }
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < N && y >= 0 && y < M) return true;
        return false;
    }

    static class Loc {
        int x, y, garbageCnt, nearCnt;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Loc(int x, int y, int garbageCnt, int nearCnt) {
            this.x = x;
            this.y = y;
            this.garbageCnt = garbageCnt;
            this.nearCnt = nearCnt;
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
