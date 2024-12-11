package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun18430 {
    // N x M 크기의 직사각형 형태이며 나무 재료의 부위마다 강도가 조금씩 다름
    // 나무 재료를 잘라 여러 개의 부메랑을 만들고자 함
    //  - 부메랑은 항상 3칸을 차지하는 ㄱ 모양으로 만들어야 함
    //  - 부메랑의 가능한 모양은 총 4가지
    //  - 부메랑의 중심이 되는 칸은 강도의 영향을 2배로 받는다
    //  - 각 칸의 강도가 주어졌을 때, 길동이가 만들 수 있는 부메랑들의 강도 합의 최댓값

    // 1 <= N, M <= 5
    // 1 <= K <= 100(강도)

    private static int[][][] directions = new int[][][]{
            {{0, -1}, {1, 0}},
            {{0, -1}, {-1, 0}},
            {{0, 1}, {-1, 0}},
            {{0, 1}, {1, 0}}
    };
    private static int answer;
    private static int rowLength;
    private static int colLength;
    private static int[][] strength;
    private static boolean[][] visited;

    private static void input() {
        Reader scanner = new Reader();

        answer = Integer.MIN_VALUE;
        rowLength = scanner.nextInt();
        colLength = scanner.nextInt();
        strength = new int[rowLength][colLength];
        visited = new boolean[rowLength][colLength];

        for(int row = 0; row < rowLength; row++) {
            for(int col = 0; col < colLength; col++) {
                strength[row][col] = scanner.nextInt();
            }
        }
    }

    private static void solution() {
        backtracking(0, 0);
        System.out.println(answer);
    }

    private static void backtracking(int count, int sum) {
        if(count == rowLength * colLength) {
            answer = Math.max(answer, sum);
            return;
        }

        int row = count / colLength;
        int col = count % colLength;

        if(!visited[row][col]) {
            for (int dir = 0; dir < directions.length; dir++) {
                int row1 = row + directions[dir][0][0];
                int col1 = col + directions[dir][0][1];
                int row2 = row + directions[dir][1][0];
                int col2 = col + directions[dir][1][1];

                if(isPossible(row1, col1, row2, col2)) {
                    visited[row1][col1] = true;
                    visited[row][col] = true;
                    visited[row2][col2] = true;
                    backtracking(count + 1, sum + strength[row1][col1] + strength[row2][col2] + (strength[row][col] * 2));
                    visited[row1][col1] = false;
                    visited[row][col] = false;
                    visited[row2][col2] = false;
                }
            }
        }

        backtracking(count + 1, sum);
    }

    private static boolean isPossible(int row1, int col1, int row2, int col2) {
        if(!isInMap(row1, col1)) {
            return false;
        }
        if(!isInMap(row2, col2)) {
            return false;
        }
        if(visited[row1][col1]) {
            return false;
        }
        if(visited[row2][col2]) {
            return false;
        }
        return true;
    }

    private static boolean isInMap(int x, int y) {
        return x >= 0 && x < rowLength && y >= 0 && y < colLength;
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
