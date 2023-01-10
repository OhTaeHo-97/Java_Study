package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun1726 {
	static int N, M;
    static int[][] map;
    static Robot start, end;
    static void input() {
        Reader scanner = new Reader();
        M = scanner.nextInt();
        N = scanner.nextInt();
        map = new int[M][N];
        for(int row = 0; row < M; row++) {
            for(int col = 0; col < N; col++)
                map[row][col] = scanner.nextInt();
        }
        start = new Robot(scanner.nextInt() - 1, scanner.nextInt() - 1, 0, scanner.nextInt());
        end = new Robot(scanner.nextInt() - 1, scanner.nextInt() - 1, 0, scanner.nextInt());
    }

    static void solution() {
        System.out.println(bfs(start, end));
    }

    static int bfs(Robot start, Robot end) {
        int[][] turnDir = {{0, 0}, {3, 4}, {3, 4}, {1, 2}, {1, 2}};
        int[] dx = {0, 0, 0, 1, -1}, dy = {0, 1, -1, 0, 0};
        PriorityQueue<Robot> queue = new PriorityQueue<>();
        int[][][] visited = new int[M][N][5];
        for(int row = 0; row < M; row++) {
            for(int col = 0; col < N; col++)
                Arrays.fill(visited[row][col], Integer.MAX_VALUE);
        }
        visited[start.x][start.y][start.direction] = 0;
        queue.offer(new Robot(start.x, start.y, 0, start.direction));
        int answer = 0;
        while(!queue.isEmpty()) {
            Robot cur = queue.poll();
            if(cur.x == end.x && cur.y == end.y && cur.direction == end.direction) {
                answer = cur.moveNum;
                break;
            }
            for(int move = 1; move <= 3; move++) {
                int cx = cur.x + dx[cur.direction] * move, cy = cur.y + dy[cur.direction] * move;
                if(isInMap(cx, cy)) {
                    if(map[cx][cy] == 1) break;
                    if(visited[cx][cy][cur.direction] > visited[cur.x][cur.y][cur.direction] + 1) {
                        visited[cx][cy][cur.direction] = visited[cur.x][cur.y][cur.direction] + 1;
                        queue.offer(new Robot(cx, cy, visited[cx][cy][cur.direction], cur.direction));
                    }
                }
            }
            for(int dir = 0; dir < turnDir[cur.direction].length; dir++) {
                int direction = turnDir[cur.direction][dir];
                if(visited[cur.x][cur.y][direction] > visited[cur.x][cur.y][cur.direction] + 1) {
                    visited[cur.x][cur.y][direction] = visited[cur.x][cur.y][cur.direction] + 1;
                    queue.offer(new Robot(cur.x, cur.y, visited[cur.x][cur.y][direction], direction));
                }
            }
        }
        return answer;
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < M && y >= 0 && y < N) return true;
        return false;
    }

    static class Robot implements Comparable<Robot> {
        int x, y, moveNum, direction;
        public Robot(int x, int y, int moveNum, int direction) {
            this.x = x;
            this.y = y;
            this.moveNum = moveNum;
            this.direction = direction;
        }

        @Override
        public int compareTo(Robot r) {
            return this.moveNum - r.moveNum;
        }

        @Override
        public String toString() {
            return "Robot{" +
                    "x=" + x +
                    ", y=" + y +
                    ", moveNum=" + moveNum +
                    ", direction=" + direction +
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
    }
}
