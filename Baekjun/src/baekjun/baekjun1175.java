package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun1175 {
    static int N, M;
    static char[][] map;
    static int[] start;
    static int[][] receivers;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new char[N][M];
        receivers = new int[2][2];
        int receiverIdx = 0;

        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < M; col++) {
                map[row][col] = info.charAt(col);

                if(map[row][col] == 'S') {
                    start = new int[] {row, col};
                    map[row][col] = '.';
                } else if (map[row][col] == 'C') {
                    receivers[receiverIdx][0] = row;
                    receivers[receiverIdx++][1] = col;
                    map[row][col] = '.';
                }
            }
        }
    }

    static void solution() {
        List<Loc> firstReceiver = bfs(new Loc(start[0], start[1], -1), receivers[0]), secondReceiver = bfs(new Loc(start[0], start[1], -1), receivers[1]);

        int answer = Integer.MAX_VALUE;
        if(firstReceiver != null) {
            for(Loc path : firstReceiver) {
                List<Loc> firstToSecond = bfs(path, receivers[0]);
                if(firstToSecond == null) continue;
                answer = Math.min(answer, path.moveNum + firstToSecond.get(0).moveNum);
            }
        }

        if(secondReceiver != null) {
            for(Loc path : secondReceiver) {
                List<Loc> secondToFirst = bfs(path, receivers[0]);
                if(secondToFirst == null) continue;
                answer = Math.min(answer, path.moveNum + secondToFirst.get(0).moveNum);
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static List<Loc> bfs(Loc start, int[] end) {
        List<Loc> paths = new ArrayList<>();
        Queue<Loc> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][4];
        int min = Integer.MAX_VALUE;

        queue.offer(new Loc(start.x, start.y, start.direction));

        while(!queue.isEmpty()) {
            Loc cur = queue.poll();
            if(cur.x == end[0] && cur.y == end[1]) {
                if(min > cur.moveNum) {
                    paths = new ArrayList<>();
                    paths.add(cur);
                } else if(min == cur.moveNum)
                    paths.add(cur);
                continue;
            }

            for(int dir = 0; dir < dx.length; dir++) {
                if(cur.direction == dir) continue;
                int cx = cur.x + dx[dir], cy = cur.y + dy[dir];
                if(isInMap(cx, cy)) {
                    if(!visited[cx][cy][dir] && map[cx][cy] == '.') {
                        visited[cx][cy][dir] = true;
                        queue.offer(new Loc(cx, cy, cur.moveNum + 1, dir));
                    }
                }
            }
        }

        if(paths.size() == 0) return null;
        else return paths;
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static class Loc {
        int x, y, moveNum, direction;

        public Loc(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            moveNum = 0;
        }

        public Loc(int x, int y, int moveNum, int direction) {
            this.x = x;
            this.y = y;
            this.moveNum = moveNum;
            this.direction = direction;
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
