package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1938 {
	static int N;
    static char[][] map;
    static int[][] treeLoc, endLoc;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        map = new char[N][N];
        treeLoc = new int[3][2];
        endLoc = new int[3][2];

        int treeIdx = 0, endIdx = 0;

        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();

            for(int col = 0; col < N; col++) {
                map[row][col] = info.charAt(col);

                if(map[row][col] == 'B') {
                    treeLoc[treeIdx][0] = row;
                    treeLoc[treeIdx][1] = col;
                    treeIdx++;
                }

                if (map[row][col] == 'E') {
                    endLoc[endIdx][0] = row;
                    endLoc[endIdx][1] = col;
                    endIdx++;
                }
            }
        }
    }

    static void solution() {
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Tree> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[2][N][N];
        int answer = 0;

        int direction = -1;
        if(treeLoc[0][1] + 1 == treeLoc[1][1]) direction = 0;
        else direction = 1;

        queue.offer(new Tree(treeLoc[1][0], treeLoc[1][1], direction, 0));
        visited[direction][treeLoc[1][0]][treeLoc[1][1]] = true;

        while(!queue.isEmpty()) {
            Tree cur = queue.poll();

            if(cur.x == endLoc[1][0] && cur.y == endLoc[1][1]) {
                if(cur.direction == 0) {
                    if(map[cur.x][cur.y - 1] == 'E' && map[cur.x][cur.y + 1] == 'E') {
                        answer = cur.moveNum;
                        break;
                    }
                } else if(cur.direction == 1) {
                    if(map[cur.x - 1][cur.y] == 'E' && map[cur.x + 1][cur.y] == 'E') {
                        answer = cur.moveNum;
                        break;
                    }
                }
            }

            for(int dir = 0; dir < 4; dir++) {
                int cx = cur.x + dx[dir], cy = cur.y + dy[dir];

                if(isInMap(cx, cy, cur.direction, dir)) {
                    if(!visited[cur.direction][cx][cy]) {
                        visited[cur.direction][cx][cy] = true;
                        queue.offer(new Tree(cx, cy, cur.direction, cur.moveNum + 1));
                    }
                }
            }

            if(canRotate(cur.x, cur.y)) {
                if(cur.direction == 0) {
                    if(!visited[1][cur.x][cur.y]) {
                        visited[1][cur.x][cur.y] = true;
                        queue.offer(new Tree(cur.x, cur.y, 1, cur.moveNum + 1));
                    }
                } else if(cur.direction == 1) {
                    if(!visited[0][cur.x][cur.y]) {
                        visited[0][cur.x][cur.y] = true;
                        queue.offer(new Tree(cur.x, cur.y, 0, cur.moveNum + 1));
                    }
                }
            }
        }

        return answer;
    }

    static boolean canRotate(int x, int y) {
        for(int row = x - 1; row <= x + 1; row++) {
            for(int col = y - 1; col <= y + 1; col++) {
                if(row < 0 || row >= N || col < 0 || col >= N) return false;
                if(map[row][col] == '1') return false;
            }
        }

        return true;
    }

    static boolean isInMap(int x, int y, int direction, int dir) {
        if(x < 0 || x >= N || y < 0 || y >= N) return false;

        if(direction == 0) {
            if(dir >= 2)
                if(y - 1 < 0 || y + 1 >= N) return false;

            if(map[x][y] == '1' || map[x][y - 1] == '1' || map[x][y + 1] == '1')
                return false;
        } else if(direction == 1) {
            if(dir < 2)
                if(x - 1 < 0 || x + 1 >= N) return false;

            if(map[x][y] == '1' || map[x - 1][y] == '1' || map[x + 1][y] == '1')
                return false;
        }

        return true;
    }

    static class Tree {
        int x, y, direction, moveNum;

        public Tree(int x, int y, int direction, int moveNum) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.moveNum = moveNum;
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
