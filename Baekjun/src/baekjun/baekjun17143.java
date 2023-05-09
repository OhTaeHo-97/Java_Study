package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun17143 {
	static final int[][] DIRECTION = {{}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static final int[] REVERSE_DIR = {0, 2, 1, 4, 3};
    static int R, C, M;
    static Shark[][] sharks;

    static void input() {
        Reader scanner = new Reader();

        R = scanner.nextInt();
        C = scanner.nextInt();
        M = scanner.nextInt();
        sharks = new Shark[R][C];

        for(int idx = 0; idx < M; idx++) {
            int x = scanner.nextInt() - 1, y = scanner.nextInt() - 1;
            int speed = scanner.nextInt(), direction = scanner.nextInt(), size = scanner.nextInt();

            if(sharks[x][y] != null && sharks[x][y].size > size) continue;
            sharks[x][y] = new Shark(x, y, speed, direction, size);
        }
    }

    static void solution() {
        // direction -> 1 : 위, 2 : 아래, 3 : 오른쪽, 4 : 왼쪽
        int totalSize = 0;
        for (int col = 0; col < C; col++) {
            totalSize += catchShark(col);
            moveShark();
        }

        System.out.println(totalSize);
    }

    static void moveShark() {
        Shark[][] newSharks = new Shark[R][C];

        for(int row = 0; row < R; row++) {
            for(int col = 0; col < C; col++) {
                if(sharks[row][col] == null) continue;

                Shark shark = sharks[row][col];
                for(int moveNum = 0; moveNum < shark.speed; moveNum++) {
                    int cx = shark.x + DIRECTION[shark.direction][0], cy = shark.y + DIRECTION[shark.direction][1];
                    if(!isInMap(cx, cy)) {
                        shark.direction = REVERSE_DIR[shark.direction];
                        cx = shark.x + DIRECTION[shark.direction][0]; cy = shark.y + DIRECTION[shark.direction][1];
                    }

                    shark.x = cx; shark.y = cy;
                }

                if(newSharks[shark.x][shark.y] != null && newSharks[shark.x][shark.y].size > shark.size) continue;
                newSharks[shark.x][shark.y] = shark;
            }
        }

        sharks = newSharks;
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < R && y >= 0 && y < C) return true;
        return false;
    }

    static int catchShark(int col) {
        int size = 0;
        for(int row = 0; row < R; row++) {
            if(sharks[row][col] != null) {
                size = sharks[row][col].size;
                sharks[row][col] = null;
                break;
            }
        }

        return size;
    }

    static class Shark {
        int x, y, speed, direction, size;

        public Shark(int x, int y, int speed, int direction, int size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
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
