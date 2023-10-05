package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun1184 {
    static int size;
    static int[][] profits;
    static int[][] direction = {{1, -1}, {1, 1}, {-1, -1}, {-1, 1}};
    static int[][] startDirection = {{0, 0}, {0, 1}, {-1, 0}, {-1, 1}};

    static void input() {
        Reader scanner = new Reader();

        size = scanner.nextInt();
        profits = new int[size + 1][size + 1];

        for(int row = 1; row <= size; row++) {
            for(int col = 1; col <= size; col++) {
                profits[row][col] = scanner.nextInt();
            }
        }
    }

    static void solution() {
        int answer = 0;

        for(int row = 2; row <= size; row++) {
            for(int col = 1; col < size; col++) {
                answer += findDividedLandNum(row, col);
            }
        }

        System.out.println(answer);
    }

    static int findDividedLandNum(int x, int y) {
        int sameSumNum = 0;

        for(int dir = 0; dir < direction.length / 2; dir++) {
            List<Integer> leftLand = getProfitSum(x + startDirection[dir][0], y + startDirection[dir][1], dir);
            List<Integer> rightLand = getProfitSum(x + startDirection[(direction.length - 1) - dir][0], y + startDirection[(direction.length - 1) - dir][1], (direction.length - 1) - dir);

            for(int leftSum : leftLand) {
                for(int rightSum : rightLand) {
                    if(leftSum == rightSum) {
                        sameSumNum++;
                    }
                }
            }
        }

        return sameSumNum;
    }

    static List<Integer> getProfitSum(int x, int y, int dir) {
        int[][] prefixSum = new int[size + 1][size + 1];
        List<Integer> profitSum = new ArrayList<>();

        for(int row = x; direction[dir][0] > 0 ? row <= size : row > 0; row += direction[dir][0]) {
            int eachRowSum = 0;

            for(int col = y; direction[dir][1] > 0 ? col <= size : col > 0; col += direction[dir][1]) {
                eachRowSum += profits[row][col];

                prefixSum[row][col] = prefixSum[row - direction[dir][0]][col] + eachRowSum;
                profitSum.add(prefixSum[row][col]);
            }
        }

        return profitSum;
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
