package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun18809 {
    static int N, M, G, R, answer;
    static int[][] map;
    static List<int[]> possibleLoc;
    static int[] colors;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        G = scanner.nextInt();
        R = scanner.nextInt();
        map = new int[N][M];
        possibleLoc = new ArrayList<>();

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                map[row][col] = scanner.nextInt();
                if(map[row][col] == 2) possibleLoc.add(new int[] {row, col});
            }
        }

        colors = new int[possibleLoc.size()];
    }

    static void solution() {
        sprayCultureMedium(0, G, R, G + R);
        System.out.println(answer);
    }

    static void sprayCultureMedium(int index, int green, int red, int count) {
        if(count <= 0 || index >= possibleLoc.size()) {
            if(green == 0 && red == 0)
                answer = Math.max(answer, bfs());
            return;
        }

        for(int idx = index; idx < possibleLoc.size(); idx++) {
            if(green > 0) {
                colors[idx] = 1;
                sprayCultureMedium(idx + 1, green - 1, red, count - 1);
                colors[idx] = 0;
            }
            if(red > 0) {
                colors[idx] = 2;
                sprayCultureMedium(idx + 1, green, red - 1, count - 1);
                colors[idx] = 0;
            }
            sprayCultureMedium(idx + 1, green, red, count);
        }
    }

    static int bfs() {
        Queue<Loc> queue = new LinkedList<>();
        Color[][] visited = new Color[N][M];
        for(int row = 0; row < N; row++)
            Arrays.fill(visited[row], new Color(0, 0));

        for(int idx = 0; idx < colors.length; idx++) {
            if(colors[idx] != 0) {
                int[] loc = possibleLoc.get(idx);
                visited[loc[0]][loc[1]] = new Color(colors[idx], 0);
                queue.offer(new Loc(loc[0], loc[1], colors[idx]));
            }
        }

        int moveNum = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            moveNum++;
            for(int count = 0; count < size; count++) {
                Loc cur = queue.poll();

                for(int dir = 0; dir < dx.length; dir++) {
                    int cx = cur.x + dx[dir], cy = cur.y + dy[dir];

                    if(isInMap(cx, cy)) {
                        if(map[cx][cy] != 0 && visited[cx][cy].color != 3) {
                            if(visited[cx][cy].color == 0) {
                                visited[cx][cy] = new Color(cur.color, moveNum);
                                queue.offer(new Loc(cx, cy, cur.color));
                            } else {
                                if(visited[cx][cy].time == moveNum && visited[cx][cy].color != cur.color)
                                    visited[cx][cy] = new Color(3, moveNum);
                            }
                        }
                    }
                }
            }
        }

        return getFlowerNum(visited);
    }

    static int getFlowerNum(Color[][] map) {
        int count = 0;
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++)
                if(map[row][col].color == 3) count++;
        }

        return count;
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static class Color {
        int color, time;

        public Color(int color, int time) {
            this.color = color;
            this.time = time;
        }
    }

    static class Loc {
        int x, y, color;

        public Loc(int x, int y, int color) {
            this.x = x;
            this.y = y;
            this.color = color;
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
