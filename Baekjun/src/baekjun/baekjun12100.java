package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun12100 {
	static int N, max;
    static int[][] board;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        board = new int[N][N];

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++)
                board[row][col] = scanner.nextInt();
        }
    }

    static void solution() {
        max = 2;
        move(0, board);

        System.out.println(max);
    }

    static void move(int moveNum, int[][] board) {
        if(moveNum == 5) {
            findMax(board);
            return;
        }

        move(moveNum + 1, moveUp(board));
        move(moveNum + 1, moveDown(board));
        move(moveNum + 1, moveLeft(board));
        move(moveNum + 1, moveRight(board));
    }

    static void findMax(int[][] board) {
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++)
                max = Math.max(max, board[row][col]);
        }
    }

    static int[][] moveUp(int[][] board) {
        int[][] boardCopy = new int[N][N];
        Queue<Integer> queue = new LinkedList<>();

        for(int col = 0; col < N; col++) {
            for(int row = 0; row < N; row++)
                if(board[row][col] != 0) queue.offer(board[row][col]);

            int index = 0;
            while(!queue.isEmpty()) {
                int cur = queue.poll();

                if(boardCopy[index][col] == 0) boardCopy[index][col] = cur;
                else if(boardCopy[index][col] == cur) boardCopy[index++][col] = cur * 2;
                else boardCopy[++index][col] = cur;
            }
        }

        return boardCopy;
    }

    static int[][] moveDown(int[][] board) {
        int[][] boardCopy = new int[N][N];
        Queue<Integer> queue = new LinkedList<>();

        for(int col = 0; col < N; col++) {
            for(int row = N - 1; row >= 0; row--)
                if(board[row][col] != 0) queue.offer(board[row][col]);

            int index = N - 1;
            while(!queue.isEmpty()) {
                int cur = queue.poll();

                if(boardCopy[index][col] == 0) boardCopy[index][col] = cur;
                else if(boardCopy[index][col] == cur) boardCopy[index--][col] = cur * 2;
                else boardCopy[--index][col] = cur;
            }
        }

        return boardCopy;
    }

    static int[][] moveLeft(int[][] board) {
        int[][] boardCopy = new int[N][N];
        Queue<Integer> queue = new LinkedList<>();

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++)
                if(board[row][col] != 0) queue.offer(board[row][col]);

            int index = 0;
            while(!queue.isEmpty()) {
                int cur = queue.poll();

                if(boardCopy[row][index] == 0) boardCopy[row][index] = cur;
                else if(boardCopy[row][index] == cur) boardCopy[row][index++] = cur * 2;
                else boardCopy[row][++index] = cur;
            }
        }

        return boardCopy;
    }

    static int[][] moveRight(int[][] board) {
        int[][] boardCopy = new int[N][N];
        Queue<Integer> queue = new LinkedList<>();

        for(int row = 0; row < N; row++) {
            for(int col = N - 1; col >= 0; col--)
                if(board[row][col] != 0) queue.offer(board[row][col]);

            int index = N - 1;
            while(!queue.isEmpty()) {
                int cur = queue.poll();

                if(boardCopy[row][index] == 0) boardCopy[row][index] = cur;
                else if(boardCopy[row][index] == cur) boardCopy[row][index--] = cur * 2;
                else boardCopy[row][--index] = cur;
            }
        }

        return boardCopy;
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
