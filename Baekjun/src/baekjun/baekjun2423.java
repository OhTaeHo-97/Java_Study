package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun2423 {
    static int row;
    static int col;
    static int min = Integer.MAX_VALUE;
    static List<Loc>[][] edges;

    static void input() {
        Reader scanner = new Reader();

        row = scanner.nextInt() + 1;
        col = scanner.nextInt() + 1;
        edges = new ArrayList[row][col];
        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                edges[r][c] = new ArrayList<>();
            }
        }

        for(int r = 0; r < row - 1; r++) {
            String info = scanner.nextLine();
            for(int c = 0; c < col - 1; c++) {
                char edgeDir = info.charAt(c);
                if(edgeDir == '/') {
                    edges[r][c + 1].add(new Loc(r + 1, c));
                    edges[r + 1][c].add(new Loc(r, c + 1));
                    edges[r][c].add(new Loc(r + 1, c + 1, 1));
                    edges[r + 1][c + 1].add(new Loc(r, c, 1));
                } else {
                    edges[r][c].add(new Loc(r + 1, c + 1));
                    edges[r + 1][c + 1].add(new Loc(r, c));
                    edges[r][c + 1].add(new Loc(r + 1, c, 1));
                    edges[r + 1][c].add(new Loc(r, c + 1, 1));
                }
            }
        }
    }

    static void solution() {
        dijkstra();
        System.out.println(min == Integer.MAX_VALUE ? "NO SOLUTION" : min);
    }

    static void dijkstra() {
        PriorityQueue<Loc> queue = new PriorityQueue<>();
        int[][] distance = new int[row][col];
        for(int r = 0; r < distance.length; r++)
            Arrays.fill(distance[r], Integer.MAX_VALUE);

        queue.offer(new Loc(0, 0));
        distance[0][0] = 0;

        while(!queue.isEmpty()) {
            Loc cur = queue.poll();
            if(cur.x == row - 1 && cur.y == col - 1) {
                min = Math.min(min, cur.rotateNum);
                return;
            }
            if(distance[cur.x][cur.y] < cur.rotateNum) {
                continue;
            }

            for(Loc next : edges[cur.x][cur.y]) {
                if(distance[next.x][next.y] > cur.rotateNum + next.rotateNum) {
                    distance[next.x][next.y] = cur.rotateNum + next.rotateNum;
                    queue.offer(new Loc(next.x, next.y, cur.rotateNum + next.rotateNum));
                }
            }
        }
    }

    static class Loc implements Comparable<Loc> {
        int x;
        int y;
        int rotateNum;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
            this.rotateNum = 0;
        }

        public Loc(int x, int y, int rotateNum) {
            this.x = x;
            this.y = y;
            this.rotateNum = rotateNum;
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
        public int compareTo(Loc o) {
            return this.rotateNum - o.rotateNum;
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
