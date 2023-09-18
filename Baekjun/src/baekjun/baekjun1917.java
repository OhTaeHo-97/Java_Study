package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1917 {
    static final int TESTCASE_NUM = 3;
    static final int SIZE = 6;

    static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();

    static int[][] cube;
    static boolean[][] visited;
    static boolean[] hasBeenBottom;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int startX;
    static int startY;

    static void input() {
        cube = new int[SIZE][SIZE];
        hasBeenBottom = new boolean[SIZE + 1];
        visited = new boolean[SIZE][SIZE];

        for(int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++) {
                cube[row][col] = scanner.nextInt();
            }
        }
    }

    static void solution() {
        pickStartPoint();
        makeCube(startX, startY);
        if(canBeCube()) {
            sb.append("yes").append('\n');
        } else {
            sb.append("no").append('\n');
        }
    }

    static boolean canBeCube() {
        for(int idx = 1; idx <= SIZE; idx++) {
            if(!hasBeenBottom[idx]) {
                return false;
            }
        }
        return true;
    }

    static void makeCube(int x, int y) {
        boolean[] temp = new boolean[SIZE + 1];
        hasBeenBottom[2] = true;
        visited[x][y] = true;

        for(int dir = 0; dir < dx.length; dir++) {
            int cx = x + dx[dir];
            int cy = y + dy[dir];

            if(isInMap(cx, cy)) {
                if(!visited[cx][cy] && cube[cx][cy] == 1) {
                    rotateCube(dir, temp);
                    makeCube(cx, cy);
                    rotateCube(3 - dir, temp);
                }
            }
        }
    }

    static void rotateCube(int direction, boolean[] temp) {
        if(direction == 0) { // 북
            rotateNorth(temp);
        } else if(direction == 1) { // 서
            rotateWest(temp);
        } else if(direction == 2) { // 동
            rotateEast(temp);
        } else if(direction == 3) { // 남
            rotateSouth(temp);
        }

        for(int idx = 1; idx <= SIZE; idx++)
            hasBeenBottom[idx] = temp[idx];
    }

    static void rotateNorth(boolean[] temp) {
        temp[1] = hasBeenBottom[2];
        temp[2] = hasBeenBottom[3];
        temp[3] = hasBeenBottom[4];
        temp[4] = hasBeenBottom[1];
        temp[5] = hasBeenBottom[5];
        temp[6] = hasBeenBottom[6];
    }

    static void rotateWest(boolean[] temp) {
        temp[1] = hasBeenBottom[1];
        temp[2] = hasBeenBottom[5];
        temp[3] = hasBeenBottom[3];
        temp[4] = hasBeenBottom[6];
        temp[5] = hasBeenBottom[4];
        temp[6] = hasBeenBottom[2];
    }

    static void rotateEast(boolean[] temp) {
        temp[1] = hasBeenBottom[1];
        temp[2] = hasBeenBottom[6];
        temp[3] = hasBeenBottom[3];
        temp[4] = hasBeenBottom[5];
        temp[5] = hasBeenBottom[2];
        temp[6] = hasBeenBottom[4];
    }

    static void rotateSouth(boolean[] temp) {
        temp[1] = hasBeenBottom[4];
        temp[2] = hasBeenBottom[1];
        temp[3] = hasBeenBottom[2];
        temp[4] = hasBeenBottom[3];
        temp[5] = hasBeenBottom[5];
        temp[6] = hasBeenBottom[6];
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    static void pickStartPoint() {
        for(int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++) {
                if(cube[row][col] == 1) {
                    startX = row;
                    startY = col;
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        for(int count = 0; count < TESTCASE_NUM; count++) {
            input();
            solution();
        }

        System.out.print(sb);
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
