package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class baekjun15971 {
    private static int answer = Integer.MAX_VALUE;
    private static int roomCount;
    private static int firstRobot;
    private static int secondRobot;
    private static Map<Integer, Set<Path>> paths;

    private static void input() {
        Reader scanner = new Reader();

        roomCount = scanner.nextInt();
        firstRobot = scanner.nextInt();
        secondRobot = scanner.nextInt();
        paths = new HashMap<>();
        for (int roomNumber = 1; roomNumber <= roomCount; roomNumber++) {
            paths.put(roomNumber, new HashSet<>());
        }

        for (int pathCount = 0; pathCount < roomCount - 1; pathCount++) {
            int room1 = scanner.nextInt();
            int room2 = scanner.nextInt();
            int distance = scanner.nextInt();

            paths.get(room1).add(new Path(room2, distance));
            paths.get(room2).add(new Path(room1, distance));
        }
    }

    /*
     * 첫 번째 로봇이 두 번째 로봇까지 가도록 했을 때의 경로가 첫 번째 로봇, 두 번째 로봇이 만나기 위해 같이 이동하면서 이동한 경로와 같다
     * 그러므로 첫 번째 로봇이 두 번째 로봇까지 가도록 했을 때의 총 거리를 구한다
     *  - 이때 같은 통로에 위치하면 되는데, 통로의 양쪽 끝 방에 두 로봇이 존재해도 같은 통로에 위치하는 것이므로
     *    이동한 경로 중 가장 긴 통로의 길이를 빼주면 된다
     */
    private static void solution() {
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Position> queue = new LinkedList<>();
        boolean[] visited = new boolean[roomCount + 1];

        queue.offer(new Position(firstRobot, 0, 0));
        visited[firstRobot] = true;

        while (!queue.isEmpty()) {
            Position curPosition = queue.poll();
            if (curPosition.position == secondRobot) {
                return curPosition.distanceMinusMax();
            }

            for (Path path : paths.get(curPosition.position)) {
                if (!visited[path.room]) {
                    visited[path.room] = true;
                    queue.offer(new Position(path.room, curPosition.distance + path.distance,
                            Math.max(curPosition.max, path.distance)));
                }
            }
        }

        return 0;
    }

    static class Path {
        int room;
        int distance;

        public Path(int room, int distance) {
            this.room = room;
            this.distance = distance;
        }
    }

    static class Position {
        int position;
        int distance;
        int max;

        public Position(int position, int distance, int max) {
            this.position = position;
            this.distance = distance;
            this.max = max;
        }

        public int distanceMinusMax() {
            return distance - max;
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
            while (st == null || !st.hasMoreElements()) {
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
