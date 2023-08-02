package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class baekjun4574 {
    static final int SIZE = 9;

    static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();
    static int testCaseNum, N;
    static int[][] sudoku;
    static Set<Pair> visitedPairs;
    static boolean isFinish;
    static int[] dx = {1, 0}, dy = {0, 1};

    static void input() {
        N = scanner.nextInt();
        if(N == 0) {
            System.out.print(sb);
            System.exit(0);
        }
        testCaseNum++;

        visitedPairs = new HashSet<>();
        sudoku = new int[SIZE][SIZE];
        isFinish = false;

        for(int count = 0; count < N; count++) {
            int firstNum = scanner.nextInt();
            String firstLoc = scanner.next();
            int secondNum = scanner.nextInt();
            String secondLoc = scanner.next();

            sudoku[firstLoc.charAt(0) - 'A'][firstLoc.charAt(1) - '1'] = firstNum;
            sudoku[secondLoc.charAt(0) - 'A'][secondLoc.charAt(1) - '1'] = secondNum;
            visitedPairs.add(new Pair(Math.min(firstNum, secondNum), Math.max(firstNum, secondNum)));
        }

        for(int num = 1; num <= SIZE; num++) {
            String loc = scanner.next();
            sudoku[loc.charAt(0) - 'A'][loc.charAt(1) - '1'] = num;
        }
    }

    static void solution() {
        backtracking(0);
    }

    static void backtracking(int index) {
        if(index >= SIZE * SIZE) {
            sb.append("Puzzle ").append(testCaseNum).append('\n');
            for(int row = 0; row < SIZE; row++) {
                for(int col = 0; col < SIZE; col++)
                    sb.append(sudoku[row][col]);
                sb.append('\n');
            }
            isFinish = true;
            return;
        }

        int rowIdx = index / 9, colIdx = index % 9;
        if(sudoku[rowIdx][colIdx] != 0) {
            backtracking(index + 1);
            return;
        }

        for(int num = 1; num <= SIZE; num++) {
            if(isPossibleNum(rowIdx, colIdx, num)) {
                for(int dir = 0; dir < dx.length; dir++) {
                    int cx = rowIdx + dx[dir], cy = colIdx + dy[dir];
                    if(isInMap(cx, cy) && sudoku[cx][cy] == 0) {
                        for(int neighborNum = 1; neighborNum <= SIZE; neighborNum++) {
                            if(num == neighborNum) continue;
                            Pair newPair = new Pair(Math.min(num, neighborNum), Math.max(num, neighborNum));
                            if(!visitedPairs.contains(newPair) && isPossibleNum(cx, cy, neighborNum)) {
                                sudoku[rowIdx][colIdx] = num;
                                sudoku[cx][cy] = neighborNum;
                                visitedPairs.add(newPair);

                                backtracking(index + 1);
                                if(isFinish) return;

                                sudoku[rowIdx][colIdx] = 0;
                                sudoku[cx][cy] = 0;
                                visitedPairs.remove(newPair);
                            }
                        }
                    }
                }
            }
        }
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    static boolean isPossibleNum(int rowIdx, int colIdx, int num) {
        for(int idx = 0; idx < SIZE; idx++) {
            if(rowIdx != idx)
                if(sudoku[idx][colIdx] == num) return false;

            if(colIdx != idx)
                if(sudoku[rowIdx][idx] == num) return false;
        }

        int startRowIdx = (rowIdx / 3) * 3, startColIdx = (colIdx / 3) * 3;
        for(int row = startRowIdx; row < startRowIdx + 3; row++) {
            for(int col = startColIdx; col < startColIdx + 3; col++) {
                if(row == rowIdx && col == colIdx) continue;
                if(sudoku[row][col] == num) return false;
            }
        }

        return true;
    }

    static class Pair {
        int num1, num2;

        public Pair(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return (num1 == pair.num1 && num2 == pair.num2) || (num1 == pair.num2 && num2 == pair.num1);
        }

        @Override
        public int hashCode() {
            return Objects.hash(Math.min(num1, num2), Math.max(num1, num2));
        }
    }

    public static void main(String[] args) {
        while(true) {
            input();
            solution();
        }
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
