package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun11967 {
	static int N, M;
    static HashMap<Room, ArrayList<Room>> switches;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static boolean[][] lighted, visited;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        switches = new HashMap<>();

        for(int idx = 0; idx < M; idx++) {
            int x = scanner.nextInt(), y = scanner.nextInt();
            int a = scanner.nextInt(), b = scanner.nextInt();

            Room lightingRoom = new Room(x - 1, y - 1), lightedRoom = new Room(a - 1, b - 1);

            if(!switches.containsKey(lightingRoom)) switches.put(lightingRoom, new ArrayList<>());
            switches.get(lightingRoom).add(lightedRoom);
        }
    }

    static void solution() {
        lighted = new boolean[N][N];
        visited = new boolean[N][N];

        System.out.println(bfs(0, 0) + 1);
    }

    static int bfs(int x, int y) {
        int count = 0;
        Queue<Room> queue = new LinkedList<>();
        visited = new boolean[N][N];

        queue.offer(new Room(x, y));
        lighted[x][y] = true;
        visited[x][y] = true;

        boolean flag = false;
        while(!queue.isEmpty()) {
            Room cur = queue.poll();

            for(Room lightedRoom : switches.getOrDefault(cur, new ArrayList<>())) {
                if(!lighted[lightedRoom.x][lightedRoom.y]) {
                    lighted[lightedRoom.x][lightedRoom.y] = true;
                    count++;
                    flag = true;
                }
            }

            for(int dir = 0; dir < dx.length; dir++) {
                int cx = cur.x + dx[dir], cy = cur.y + dy[dir];

                if(isInMap(cx, cy)) {
                    if(lighted[cx][cy] && !visited[cx][cy]) {
                        visited[cx][cy] = true;
                        queue.offer(new Room(cx, cy));
                    }
                }
            }
        }

        if(flag) count += bfs(0, 0);

        return count;
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < N && y >= 0 && y < N) return true;
        return false;
    }

    static class Room {
        int x, y;

        public Room(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Room room = (Room) o;
            return x == room.x && y == room.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Room{" +
                    "x=" + x +
                    ", y=" + y +
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
