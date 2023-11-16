package src.SWExoertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea4615 {
    static final int BLACK = 1;
    static final int WHITE = 2;
    static final int[] DX = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] DY = {0, 1, 1, 1, 0, -1, -1, -1};

    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static int size;
    static int turnCount;
    static int[][] board;
    static int[][] turns;

    static void input() {
        size = scanner.nextInt();
        turnCount = scanner.nextInt();
        board = new int[size][size];
        turns = new int[turnCount][3];

        for (int idx = 0; idx < turnCount; idx++) {
            turns[idx][1] = scanner.nextInt() - 1;
            turns[idx][0] = scanner.nextInt() - 1;
            turns[idx][2] = scanner.nextInt();
        }
    }

    static void solution() {
        init();
        for (int[] turn : turns) {
            layStoneAndChangeBoard(turn[0], turn[1], turn[2]);
        }
        int[] stoneCounts = new int[]{getStoneCount(BLACK), getStoneCount(WHITE)};
        print(stoneCounts);
    }

    static void print(int[] stoneCounts) {
        answer.append(stoneCounts[0]).append(' ').append(stoneCounts[1]).append('\n');
    }

    static void init() {
        int initIdx = size / 2 - 1;

        board[initIdx][initIdx] = board[initIdx + 1][initIdx + 1] = WHITE;
        board[initIdx][initIdx + 1] = board[initIdx + 1][initIdx] = BLACK;
    }

    static void layStoneAndChangeBoard(int x, int y, int color) {
        int[][] copyBoard = copyBoard();

        for (int dir = 0; dir < DX.length; dir++) {
            changeBoard(x, y, DX[dir], DY[dir], color, copyBoard);
        }
        board = copyBoard;
    }

    static void changeBoard(int x, int y, int dx, int dy, int color, int[][] copyBoard) {
        int nextDistance = findNextSameColorPositionDistance(x + dx, y + dy, dx, dy, color);
        if (nextDistance != 0) {
            for (int count = 0; count <= nextDistance; count++) {
                copyBoard[x][y] = color;
                x += dx;
                y += dy;
            }
        }
    }

    static int findNextSameColorPositionDistance(int x, int y, int dx, int dy, int color) {
        int distance = 0;
        for (; isInMap(x, y); x += dx, y += dy) {
            if (board[x][y] == 0) {
                return 0;
            }
            if (board[x][y] == color) {
                return distance;
            }
            distance++;
        }
        return 0;
    }

    static int getStoneCount(int color) {
        int stoneCount = 0;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == color) {
                    stoneCount++;
                }
            }
        }

        return stoneCount;
    }

    static int[][] copyBoard() {
        int[][] copy = new int[size][size];
        for (int row = 0; row < size; row++) {
            copy[row] = board[row].clone();
        }
        return copy;
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        for (int test = 1; test <= T; test++) {
            answer.append('#').append(test).append(' ');
            input();
            solution();
        }
        System.out.print(answer);
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
