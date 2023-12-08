package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun16939 {
    static final int SIZE = 8;
    static int[][] colors;
    static int[][] copy;

    static void input() {
        Reader scanner = new Reader();

        colors = new int[SIZE][SIZE];

        for (int idx = 0; idx < 24; idx++) {
            if (idx < 12) {
                colors[idx / 2][idx % 2 + 2] = scanner.nextInt();
            }
            if (idx < 16) {
                colors[(idx - 12) / 2 + 2][(idx - 12) / 2] = scanner.nextInt();
            }
            if (idx < 20) {
                colors[(idx - 16) / 2 + 2][(idx - 17) / 2 + 4] = scanner.nextInt();
            }
            colors[(idx - 20) / 2 + 2][(idx - 20) / 2 + 6] = scanner.nextInt();
        }
    }

    static void solution() {

    }

    static void rotateSquare(int row, int col, int direction) {
        if (direction == 0) { // 시계
            copy[row][col] = colors[row + 1][col];
            copy[row][col + 1] = colors[row][col];
            copy[row + 1][col] = colors[row + 1][col + 1];
            copy[row + 1][col + 1] = colors[row][col + 1];
            return;
        }
    }

    static void copyColors() {
        copy = new int[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            copy[row] = colors[row].clone();
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
