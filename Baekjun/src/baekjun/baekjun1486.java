package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun1486 {
	static int N, M, T, D;
    static int[][] map;
    static int[][][][] distance;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        T = scanner.nextInt();
        D = scanner.nextInt();
        map = new int[N][M];
        distance = new int[N][M][N][M];
        for(int idx = 0; idx < N; idx++) {
            for(int idx2 = 0; idx2 < M; idx2++) {
                for(int idx3 = 0; idx3 < N; idx3++)
                    Arrays.fill(distance[idx][idx2][idx3], Integer.MAX_VALUE);
            }
        }

        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < M; col++) {
                char curAlphabet = info.charAt(col);

                if(curAlphabet >= 'A' && curAlphabet <= 'Z')
                    map[row][col] = curAlphabet - 'A';
                else if(curAlphabet >= 'a' && curAlphabet <= 'z')
                    map[row][col] = (curAlphabet - 'a') + 26;
            }
        }
    }

    static void solution() {
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++)
                dijkstra(row, col);
        }

        System.out.println(findMax());
    }

    static int findMax() {
        int max = Integer.MIN_VALUE;

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if(distance[row][col][0][0] == Integer.MAX_VALUE ||
                        distance[0][0][row][col] == Integer.MAX_VALUE)
                    continue;

                if(distance[row][col][0][0] + distance[0][0][row][col] <= D)
                    max = Math.max(max, map[row][col]);
            }
        }

        return max;
    }

    static void dijkstra(int x, int y) {
        PriorityQueue<Loc> queue = new PriorityQueue<>();

        distance[x][y][x][y] = 0;
        queue.offer(new Loc(x, y, 0));

        while(!queue.isEmpty()) {
            Loc cur = queue.poll();
            if(distance[cur.x][cur.y][x][y] < cur.time) continue;

            for(int dir = 0; dir < 4; dir++) {
                int cx = cur.x + dx[dir], cy = cur.y + dy[dir], nextTime = 0;

                if(isInMap(cx, cy) && Math.abs(map[cur.x][cur.y] - map[cx][cy]) <= T) {
                    if(map[cx][cy] <= map[cur.x][cur.y]) nextTime = cur.time + 1;
                    else nextTime = (int)Math.pow((map[cx][cy] - map[cur.x][cur.y]), 2) + cur.time;

                    if(distance[cx][cy][x][y] > nextTime && nextTime <= D) {
                        distance[cx][cy][x][y] = nextTime;
                        queue.offer(new Loc(cx, cy, nextTime));
                    }
                }
            }
        }
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static class Loc implements Comparable<Loc> {
        int x, y, time;

        public Loc(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Loc o) {
            return time - o.time;
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
