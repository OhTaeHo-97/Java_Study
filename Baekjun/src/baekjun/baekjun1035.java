package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class baekjun1035 {
    static final int SIZE = 5;

    static char[][] board;
    static int[][] conn;
    static List<Integer> fragments;
    static int fragmentBit;
    static boolean[] visited;
    static int fragmentNum, answer = Integer.MAX_VALUE, count;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    static void input() {
        Reader scanner = new Reader();

        board = new char[SIZE][SIZE];
        conn = new int[SIZE][SIZE];
        fragments = new ArrayList<>();
        fragmentBit = 1 << 25;
        visited = new boolean[fragmentBit];

        for(int row = 0; row < SIZE; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < SIZE; col++) {
                board[row][col] = info.charAt(col);

                if(board[row][col] == '*')
                    fragments.add(SIZE * row + col);
            }
        }

        fragmentNum = fragments.size();
    }

    static void solution() {

    }

    static boolean isInBoard(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    public static void main(String[] args) {
        input();
        solution();
    }

    static class Reader {
        BufferedReader br;

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
