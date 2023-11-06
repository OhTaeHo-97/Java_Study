package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun5213 {
    static int size;
    static int[][] tiles;
    static int[][] tileNumbers;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void input() {
        Reader scanner = new Reader();

        size = scanner.nextInt();
        tiles = new int[size][size * 2];
        tileNumbers = new int[size][size * 2];

        int tileNum = 1;
        for(int row = 0; row < size; row++) {
            for(int col = row % 2 == 0 ? 0 : 1; col < (row % 2 == 0 ? tiles[row].length : tiles[row].length - 1); col += 2) {
                int left = scanner.nextInt();
                int right = scanner.nextInt();

                tiles[row][col] = left;
                tiles[row][col + 1] = right;
                tileNumbers[row][col] = tileNumbers[row][col + 1] = tileNum++;
            }
        }
    }

    static void solution() {
        List<Integer> answer = bfs();

        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append('\n');
        answer.stream().forEach(tileNum -> sb.append(tileNum).append(' '));
        System.out.println(sb);
    }

    static List<Integer> bfs() {
        Queue<Loc> queue = new LinkedList<>();
        List<Integer> paths = new ArrayList<>(List.of(1));
        boolean[][] visited = new boolean[size][size * 2];

        queue.offer(new Loc(0, 0, paths));
        queue.offer(new Loc(0, 1, paths));
        visited[0][0] = visited[0][1] = true;

        int maxTileNumber = Integer.MIN_VALUE;
        Loc answer = null;
        while(!queue.isEmpty()) {
            Loc cur = queue.poll();

            if(tileNumbers[cur.x][cur.y] > maxTileNumber) {
                maxTileNumber = tileNumbers[cur.x][cur.y];
                answer = cur;
            }

            for(int dir = 0; dir < dx.length; dir++) {
                int cx = cur.x + dx[dir];
                int cy = cur.y + dy[dir];

                if(isInMap(cx, cy) && !visited[cx][cy]) {
                    if(tiles[cur.x][cur.y] == tiles[cx][cy]) {
                        visited[cx][cy] = true;

                        List<Integer> nextPaths = new ArrayList<>(cur.paths);
                        nextPaths.add(tileNumbers[cx][cy]);

                        queue.offer(new Loc(cx, cy, nextPaths));

                        if(tileNumbers[cx][cy] == tileNumbers[cx][cy + 1]) {
                            visited[cx][cy + 1] = true;
                            queue.offer(new Loc(cx, cy + 1, nextPaths));
                            continue;
                        }
                        if(tileNumbers[cx][cy] == tileNumbers[cx][cy - 1]) {
                            visited[cx][cy - 1] = true;
                            queue.offer(new Loc(cx, cy - 1, nextPaths));
                        }
                    }
                }
            }
        }

        return answer.paths;
    }

    static boolean isInMap(int x, int y) {
        return 0 <= x && x < size && 0 <= y && y < size * 2;
    }

    static class Loc {
        int x;
        int y;
        List<Integer> paths;

        public Loc(int x, int y, List<Integer> paths) {
            this.x = x;
            this.y = y;
            this.paths = paths;
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
