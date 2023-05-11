package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun2931 {
	static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int R, C;
    static boolean[][][] map;
    static int[] start, end;
    static ArrayList<Loc> empties;

    static void input() {
        Reader scanner = new Reader();

        R = scanner.nextInt();
        C = scanner.nextInt();
        // 0 : 위, 1 : 오른, 2 : 아래, 3 : 왼
        map = new boolean[R][C][4];
        empties = new ArrayList<>();

        for(int row = 0; row < R; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < C; col++) {
                char curChar = info.charAt(col);

                switch (curChar) {
                    case 'M':
                        start = new int[] {row, col};
                        break;
                    case 'Z':
                        end = new int[] {row, col};
                        break;
                    case '|':
                        map[row][col][0] = true;
                        map[row][col][2] = true;
                        break;
                    case '-':
                        map[row][col][1] = true;
                        map[row][col][3] = true;
                        break;
                    case '+':
                        Arrays.fill(map[row][col], true);
                        break;
                    case '1':
                        map[row][col][1] = true;
                        map[row][col][2] = true;
                        break;
                    case '2':
                        map[row][col][0] = true;
                        map[row][col][1] = true;
                        break;
                    case '3':
                        map[row][col][0] = true;
                        map[row][col][3] = true;
                        break;
                    case '4':
                        map[row][col][2] = true;
                        map[row][col][3] = true;
                        break;
                }
            }
        }
    }

    static void solution() {
        HashSet<Loc> fromStart = bfs(start);
        HashSet<Loc> fromEnd = bfs(end);
        System.out.println(fromStart);
        System.out.println();
        System.out.println(fromEnd);
        fromStart.stream().forEach(loc -> {
            if(fromEnd.contains(loc)) empties.add(loc);
        });

        simulation();
    }

    static void simulation() {
        for(int idx = 0; idx < empties.size(); idx++) {
            Loc cur = empties.get(idx);
            System.out.println(cur);
        }
    }

    static HashSet<Loc> bfs(int[] start) {
        HashSet<Loc> set = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[R][C][4];

        for(int dir = 0; dir < 4; dir++) {
            int cx = start[0] + dx[dir], cy = start[1] + dy[dir];
            if(isInMap(cx, cy) && map[cx][cy][dir]) {
                visited[cx][cy][dir] = true;
                queue.offer(new int[] {cx, cy, dir});
            }
        }
        Arrays.fill(visited[start[0]][start[1]], true);

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int dir = 0; dir < 4; dir++) {
                int cx = cur[0] + dx[dir], cy = cur[1] + dy[dir];

                if(isInMap(cx, cy) && !visited[cx][cy][dir]) {
                    if(map[cur[0]][cur[1]][dir] && !map[cx][cy][(dir + 2) % 2]) {
                        visited[cx][cy][dir] = true;
                        set.add(new Loc(cx, cy, (dir + 2) % 2));
                    } else if(map[cur[0]][cur[1]][dir] && map[cx][cy][(dir + 2) % 2]) {
                        visited[cx][cy][dir] = true;
                        queue.offer(new int[] {cx, cy, dir});
                    }
                }
            }
        }

        return set;
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < R && y >= 0 && y < C) return true;
        return false;
    }

    static class Loc {
        int x, y, dir;

        public Loc(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Loc loc = (Loc) o;
            return x == loc.x && y == loc.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Loc{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
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
