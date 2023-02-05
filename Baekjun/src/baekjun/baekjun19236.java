package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun19236 {
	static final int SIZE = 4;
    static Fish[][] map;
    static int[][] direction = {{0, 0}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    static PriorityQueue<Fish> fishes;

    static void input() {
        Reader scanner = new Reader();
        map = new Fish[SIZE][SIZE];
        fishes = new PriorityQueue<>();

        for(int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++) {
                int num = scanner.nextInt(), dir = scanner.nextInt();
                map[row][col] = new Fish(row, col, num, dir);
                fishes.offer(new Fish(row, col, num, dir));
            }
        }
    }

    static void solution() {

    }

    static void simulate(Fish shark, int count, Fish[][] map, PriorityQueue<Fish> queue) {
        moveFish(map, queue);
        moveShark(shark, map);
    }

    static void moveShark(Fish shark, Fish[][] map) {
        
    }

    static void moveFish(Fish[][] map, PriorityQueue<Fish> queue) {
        PriorityQueue<Fish> newQueue = new PriorityQueue<>();
        while(!queue.isEmpty()) {
            Fish cur = queue.poll();

            int count = 0;
            while(count < 8) {
                int cx = cur.x + direction[cur.dir][0], cy = cur.y + direction[cur.dir][1];
                if(isInMap(cx, cy)) {
                    Fish temp = map[cur.x][cur.y];
                    map[cur.x][cur.y] = map[cx][cy];
                    map[cx][cy] = temp;
                    break;
                }

                int dir = cur.dir + 1;
                if(dir > 8) {
                    dir %= 8;
                }

                count++;
            }
        }
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < SIZE && y >= 0 && y < SIZE) return true;
        return false;
    }

    static class Fish implements Comparable<Fish> {
        int x, y, num, dir;
        // dir -> 1 : 위, 2 : 왼쪽 위, 3 : 왼, 4 : 왼쪽 아래, 5 : 아래, 6 : 오른쪽 아래, 7 : 오른, 8 : 오른쪽 위

        public Fish(int x, int y, int num, int dir) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
        }

        @Override
        public int compareTo(Fish f) {
            return this.num - f.num;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Fish fish = (Fish) o;
            return num == fish.num;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num);
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
