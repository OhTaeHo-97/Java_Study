package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun5022 {
    static int r;
    static int c;

    static int[] a1;
    static int[] a2;
    static int[] b1;
    static int[] b2;
    static Path[][] map; // 경로상에서 현재 지점 바로 이전 지점을 나타내는 2차원 배열
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void input() {
        Reader scanner = new Reader();

        c = scanner.nextInt();
        r = scanner.nextInt();

        int y = scanner.nextInt();
        int x = scanner.nextInt();
        a1 = new int[] {x, y};
        y = scanner.nextInt();
        x = scanner.nextInt();
        a2 = new int[] {x, y};
        y = scanner.nextInt();
        x = scanner.nextInt();
        b1 = new int[] {x, y};
        y = scanner.nextInt();
        x = scanner.nextInt();
        b2 = new int[] {x, y};
    }

    static void solution() {
        // 필요한 전선의 최솟값을 구하기 위해서는 a1 ~ a2, b1 ~ b2 경로 중 하나의 경로에 대해 먼저 최단 경로를 찾고
        // 그 경로를 지나지 않는 다른 하나의 최단 경로를 찾아 그 경로의 길이를 더해주면 된다
        // a1 ~ a2에 대한 최단경로를 먼저 찾는 경우와 b1 ~ b2에 대한 최단경로를 먼저 찾는 경우 2가지의 경우가 존재하므로
        // 각 경우를 진행해보고 더 짧은 경로의 길이를 출력한다
        int distA = getMinDistance(a1, a2, b1, b2);
        int distB = getMinDistance(b1, b2, a1, a2);

        if(distA == Integer.MAX_VALUE && distB == Integer.MAX_VALUE)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(Math.min(distA, distB));
    }

    static int getMinDistance(int[] start, int[] end, int[] nextStart, int[] nextEnd) {
        // 먼저 구하는 최단경로에 대해 그 경로의 각 지점에서 바로 이전 지점이 어딘지를 표시하는 배열이다
        // a1 ~ a2을 먼저 구할 때와 b1 ~ b2을 먼저 구할 때, 두 경우가 있으니 각 경우를 구할 때 초기화를 진행한다
        map = new Path[r + 1][c + 1];
        // 먼저 구하는 최단경로의 길이를 BFS를 통해 구한다
        int firstDistance = bfs(start, end, nextStart, nextEnd, new boolean[r + 1][c + 1]);

        // 먼저 구한 경로를 2차원 boolean 배열에 표시한다
        // 남은 경로를 구할 때 해당 지점들은 지나갈 수 없기 때문에 체크해놓는다
        boolean[][] isPath = new boolean[r + 1][c + 1];
        checkPath(start, end, isPath);

        // 남은 경로의 최단 길이를 BFS를 통해 구한다
        int secondDistance = bfs(nextStart, nextEnd, isPath);
        // 먼저 구하는 경로는 도달하지 못하는 경우가 없기 때문에 체크해주지 않고
        // 남은 경로를 구할 떄에는 도달할 수 없는 경우 존재하기 때문에 만약 도달하지 못한다면 max값을 반환하여 도달하지 못했음을 표시한다
        if(secondDistance == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
            // 도달했다면 먼저 구한 경로의 최단 길이와 두 번째 구한 경로의 최단 길이를 더하여 반환한다
        else
            return firstDistance + secondDistance;
    }

    // 두 번째 경로(남은 경로)의 최단 거리를 구하는 메서드
    static int bfs(int[] start, int[] end, boolean[][] visited) {
        Queue<Path> queue = new LinkedList<>();

        queue.offer(new Path(start[0], start[1], 0));
        // visited에는 처음 구한 경로에 해당하는 지점들이 true로 표시되어 있다(해당 위치는 갈 수 없는 위치이므로)
        visited[start[0]][start[1]] = true;

        while(!queue.isEmpty()) {
            Path cur = queue.poll();
            if(cur.x == end[0] && cur.y == end[1]) {
                return cur.distance;
            }

            for(int dir = 0; dir < dx.length; dir++) {
                int cx = cur.x + dx[dir];
                int cy = cur.y + dy[dir];

                if(isInMap(cx, cy) && !visited[cx][cy]) {
                    visited[cx][cy] = true;
                    queue.offer(new Path(cx, cy, cur.distance + 1));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    // 먼저 구하는 경로의 최단 길이와 경로를 구하는 메서드
    static int bfs(int[] start, int[] end, int[] exceptPoint1, int[] exceptPoint2, boolean[][] visited) {
        Queue<Path> queue = new LinkedList<>();

        queue.offer(new Path(start[0], start[1], 0));
        visited[start[0]][start[1]] = visited[exceptPoint1[0]][exceptPoint1[1]] = visited[exceptPoint2[0]][exceptPoint2[1]] = true;

        while(!queue.isEmpty()) {
            Path cur = queue.poll();
            if(cur.x == end[0] && cur.y == end[1])
                return cur.distance;

            for(int dir = 0; dir < dx.length; dir++) {
                int cx = cur.x + dx[dir];
                int cy = cur.y + dy[dir];

                if(isInMap(cx, cy) && !visited[cx][cy]) {
                    visited[cx][cy] = true;
                    // 이동할 수 있는 곳은 경로가 될 수 있는 후보이기 때문에 이전 위치, 즉 Queue에서 뽑은 위치를 2차원 배열에 설정한다
                    map[cx][cy] = cur;
                    queue.offer(new Path(cx, cy, cur.distance + 1));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x <= r && y >= 0 && y <= c;
    }

    // 먼저 구한 경로를 2차원 boolean 배열에 체크하는 메서드
    static void checkPath(int[] start, int[] end, boolean[][] isPath) {
        Path curPoint = new Path(end[0], end[1], 0);
        isPath[curPoint.x][curPoint.y] = true; // isPath에는 첫 번째로 구한 경로에 해당하는 위치들을 표시한다

        while(true) {
            // map이라는 2차원 배열에 경로 상에서 현재 위치 바로 이전 위치가 저장되어있기 때문에 이를 통해 경로를 타고 isPath에 해당 위치들을 표시한다
            isPath[curPoint.x][curPoint.y] = true;
            if(curPoint.x == start[0] && curPoint.y == start[1])
                break;
            curPoint = map[curPoint.x][curPoint.y];
        }
    }

    static class Path {
        int x;
        int y;
        int distance;

        public Path(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
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
