package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1960 {
    static int size;
    static int[] rows;
    static int[] cols;
    static int[][] matrix;

    static void input() {
        Reader scanner = new Reader();

        size = scanner.nextInt();
        rows = new int[size];
        cols = new int[size];
        matrix = new int[size][size];

        for (int row = 0; row < size; row++) {
            rows[row] = scanner.nextInt();
        }
        for (int col = 0; col < size; col++) {
            cols[col] = scanner.nextInt();
        }
    }

    static void solution() {
        Queue<Cell> columnCells = new PriorityQueue<>();
        for (int col = 0; col < size; col++) {
            columnCells.offer(new Cell(col, cols[col]));
        }

        for (int row = 0; row < size; row++) {
            Queue<Cell> poppedQueue = new LinkedList<>();

            for (int col = 0; col < rows[row]; col++) {
                Cell cur = columnCells.poll();
                matrix[row][cur.index] = 1;
                poppedQueue.offer(new Cell(cur.index, cur.oneCount - 1));
            }

            while (!poppedQueue.isEmpty()) {
                columnCells.offer(poppedQueue.poll());
            }
        }

        StringBuilder answer = new StringBuilder();
        if (!isMatrixValid()) {
            answer.append(-1).append('\n');
        } else {
            answer.append(1).append('\n');
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    answer.append(matrix[row][col]);
                }
                answer.append('\n');
            }
        }

        System.out.print(answer);
    }

    static boolean isMatrixValid() {
        for (int col = 0; col < size; col++) {
            int columnOneCount = 0;
            for (int row = 0; row < size; row++) {
                columnOneCount += matrix[row][col];
            }
            if (columnOneCount != cols[col]) {
                return false;
            }
        }

        return true;
    }

    static class Cell implements Comparable<Cell> {
        int index;
        int oneCount;

        public Cell(int index, int oneCount) {
            this.index = index;
            this.oneCount = oneCount;
        }

        @Override
        public int compareTo(Cell o) {
            if (oneCount != o.oneCount) {
                return o.oneCount - oneCount;
            }
            return o.index - index;
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
