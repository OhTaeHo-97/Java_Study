package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun16933 {
    static int N, M, K;
    static boolean[][] map;
    static int[][][] visited;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
        map = new boolean[N][M]; // (n, m)이 벽이 없는 위치인지 여부
        visited = new int[N][M][K + 1]; // (n, m) 위치까지 벽을 k개 깨면서 방문하였을 때의 최단 거리

        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < M; col++) {
                map[row][col] = info.charAt(col) == '0';
                Arrays.fill(visited[row][col], Integer.MAX_VALUE);
            }
        }
    }

    static void solution() {
        System.out.println(bfs());
    }

    static int bfs() {
        // 상하좌우 및 현재 위치에 머물러있는 경우
        int[] dx = {-1, 0, 1, 0, 0}, dy = {0, -1, 0, 1, 0};
        Queue<Loc> queue = new LinkedList<>();

        queue.offer(new Loc(0, 0, 1, 0, false));
        visited[0][0][0] = 1;

        boolean isNight = false; // 현재 상황이 밤인지 여부
        while(!queue.isEmpty()) {
            int size = queue.size();

            // 이동한 횟수별로 탐색을 진행하기 위해 Queue에 있는 원소 개수만큼 반복
            for(int element = 0; element < size; element++) {
                Loc cur = queue.poll();
                // 아직 현재 위치에서 머무르지 않았고 현재 위치까지 방문하는 데에 이동한 거리가 이전에 현재 위치까지 방문하는 데에 걸린 최단 거리보다 크다면
                // 이후 이동은 최단 거리가 될 수 없으니 다음 경우를 탐색
                if(!cur.stay && visited[cur.x][cur.y][cur.wall] < cur.moveNum) continue;
                // (N, M) 위치까지 왔다면 최단 경로 반환
                if(cur.x == N - 1 && cur.y == M - 1) return cur.moveNum;

                // 낮에는 벽을 부술 수 있기 때문에 현재 자리에 머물러있을 필요가 없다
                // 그러므로 밤이라면 현재 자리에 머무는 경우까지 다음 자리에 대해 탐색하고
                // 낮이라면 머물 필요가 없으니 상하좌우 인접한 곳에 대해서만 탐색한다
                int dirLen = isNight ? 5 : 4;
                for(int dir = 0; dir < dirLen; dir++) {
                    int cx = cur.x + dx[dir], cy = cur.y + dy[dir];

                    if(isInMap(cx, cy)) {
                        // 만약 밤이라면
                        if(isNight) {
                            // 상하좌우 인접한 곳에 대해서는 다음과 같은 방식으로 이동할 수 있는지 확인하고 탐색을 진행한다
                            if(dir != 4) {
                                // 인접한 곳이 벽이 없는 곳이고 이전에 방문한 최단 거리가 현재 상황에서 방문하는 거리보다 더 크다면
                                // 최단 거리를 갱신하고 다음 탐색을 위해 Queue에 해당 위치에 대한 정보를 넣는다
                                // 밤이라면 벽을 부술 수 없으므로 벽이 있는 곳에 대해서는 생각하지 않는다
                                if(map[cx][cy] && visited[cx][cy][cur.wall] > cur.moveNum + 1) {
                                    visited[cx][cy][cur.wall] = cur.moveNum + 1;
                                    queue.offer(new Loc(cx, cy, cur.moveNum + 1, cur.wall, false));
                                }
                            } else { // 현재 자리에 머무는 경우에 대해서는 다음 탐색을 위해 Queue에 해당 위치에 대한 정보를 넣는다
                                queue.offer(new Loc(cx, cy, cur.moveNum + 1, cur.wall, true));
                            }
                        } else { // 낮이라면
                            // 인접한 자리를 확인하고 만약 벽이라면 현재까지 부신 벽의 개수에 1을 더해준다
                            int wall = cur.wall + (!map[cx][cy] ? 1 : 0);
                            // 만약 부순 후의 부순 벽의 개수가 K보다 크다면 벽을 꺨 수 없다는 뜻이므로 이러한 경우는 이동이 불가능하다
                            // 그러므로 부신 후의 부순 벽의 개수가 K보다 작거나 같고 이전에 방문한 최단 거리가 해당 위치까지 이동하는 데에 이동한 거리보다 크다면
                            // 최단 거리를 갱신하고 다음 탐색을 위해 Queue에 해당 위치에 대한 정보를 넣는다
                            if(wall <= K && visited[cx][cy][wall] > cur.moveNum + 1) {
                                visited[cx][cy][wall] = cur.moveNum + 1;
                                queue.offer(new Loc(cx, cy, cur.moveNum + 1, wall, false));
                            }
                        }
                    }
                }
            }

            isNight = !isNight;
        }

        return -1;
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static class Loc {
        int x, y, moveNum, wall;
        boolean stay;

        public Loc(int x, int y, int moveNum, int wall, boolean stay) {
            this.x = x;
            this.y = y;
            this.moveNum = moveNum;
            this.wall = wall;
            this.stay = stay;
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
