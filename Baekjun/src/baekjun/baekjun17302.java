package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun17302 {
    static int rowSize;
    static int colSize;
    static char[][] board;
    static int[][] answers;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void input() {
        Reader scanner = new Reader();

        rowSize = scanner.nextInt();
        colSize = scanner.nextInt();
        board = new char[rowSize][colSize];
        answers = new int[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            String info = scanner.nextLine();
            for (int col = 0; col < colSize; col++) {
                board[row][col] = info.charAt(col);
            }
        }
    }

    static void solution() {
        for (int row = 0; row < rowSize; row++) {
            Arrays.fill(answers[row], 3);
            for (int col = 0; col < colSize; col++) {
                reverse(row, col);
            }
        }

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (board[row][col] == 'B') {
                    board[row][col] = 'W';
                    answers[row][col] = 2;
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        answer.append(1).append('\n');
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                answer.append(answers[row][col]);
            }
            answer.append('\n');
        }
        System.out.print(answer);
    }

    static void reverse(int row, int col) {
        board[row][col] = flip(board[row][col]);
        for (int dir = 0; dir < dx.length; dir++) {
            int cx = row + dx[dir];
            int cy = col + dy[dir];
            if (!isInBoard(cx, cy)) {
                continue;
            }
            board[cx][cy] = flip(board[cx][cy]);
        }
    }

    static char flip(char color) {
        if (color == 'B') {
            return 'W';
        }
        return 'B';
    }

    static boolean isInBoard(int x, int y) {
        return 0 <= x && x < rowSize && 0 <= y && y < colSize;
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
