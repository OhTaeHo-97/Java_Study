package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun6087 {
	static int W, H;
    static char[][] map;
    static int[][] lasers;
    static void input() {
        Reader scanner = new Reader();
        W = scanner.nextInt();
        H = scanner.nextInt();
        map = new char[H][W];
        lasers = new int[2][2];
        int idx = 0;
        for(int row = 0; row < H; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < W; col++) {
                map[row][col] = info.charAt(col);
                if(map[row][col] == 'C') {
                    lasers[idx][0] = row;
                    lasers[idx][1] = col;
                    idx++;
                }
            }
        }
    }

    static void solution() {
        int answer = bfs(lasers[0], lasers[1]);
        System.out.println(answer);
    }

    static int bfs(int[] start, int[] end) {
        PriorityQueue<Loc> queue = new PriorityQueue<>();
        int[][] visited = new int[H][W];
        for(int row = 0; row < H; row++)
            Arrays.fill(visited[row], Integer.MAX_VALUE);
        int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
        queue.offer(new Loc(start[0], start[1], 0, 4));
        int answer = -1;
        while(!queue.isEmpty()) {
            Loc cur = queue.poll();
            if(cur.x == end[0] && cur.y == end[1]) {
                answer = cur.mirrorNum;
                break;
            }
            for(int dir = 0; dir < 4; dir++) {
                int cx = cur.x + dx[dir], cy = cur.y + dy[dir];
                if(isInMap(cx, cy)) {
                    if(map[cx][cy] != '*') {
                        if(cur.direction == dir || cur.direction == 4) {
                            if(visited[cx][cy] >= cur.mirrorNum) { // 방향이 같은 경우
                                visited[cx][cy] = cur.mirrorNum;
                                queue.offer(new Loc(cx, cy, cur.mirrorNum, dir));
                            }
                        } else {
                            if(visited[cx][cy] >= cur.mirrorNum + 1) { // 방향이 다른 경우
                                visited[cx][cy] = cur.mirrorNum + 1;
                                queue.offer(new Loc(cx, cy, cur.mirrorNum + 1, dir));
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < H && y >= 0 && y < W) return true;
        return false;
    }

    static void offerLoc(int x, int y, int mirror, int direction, int[][][] mirrorNum, boolean[][][] visited, Queue<Loc> queue) {
        if(isInMap(x, y) && map[x][y] != '*') {
            if(!visited[x][y][direction] && mirrorNum[x][y][direction] > mirror) {
                mirrorNum[x][y][direction] = mirror;
                queue.offer(new Loc(x, y, mirror, direction));
            }
        }
    }

    static class Loc implements Comparable<Loc> {
        int x, y, mirrorNum, direction;
        // direction =>
        // 0 : 우, 1 : 하, 2 : 좌, 3 : 상
        public Loc(int x, int y, int mirrorNum, int direction) {
            this.x = x;
            this.y = y;
            this.mirrorNum = mirrorNum;
            this.direction = direction;
        }

        @Override
        public int compareTo(Loc l) {
            return this.mirrorNum - l.mirrorNum;
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
