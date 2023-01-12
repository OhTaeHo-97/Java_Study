package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun20058 {
	static StringBuilder sb = new StringBuilder();
    static int N, Q, size, eachMassSize;
    static int[][] A;
    static int[] levels, dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        Q = scanner.nextInt();
        size = (int)Math.pow(2, N);
        A = new int[size][size];
        levels = new int[Q];
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) A[row][col] = scanner.nextInt();
        }
        for(int idx = 0; idx < Q; idx++) levels[idx] = scanner.nextInt();
    }

    static void solution() {
        for(int idx = 0; idx < Q; idx++) fireStorm(levels[idx]);
        int[] answer = findAnswers();
        for(int idx = 0; idx < answer.length; idx++)
            sb.append(answer[idx]).append('\n');
        System.out.print(sb);
    }

    static int[] findAnswers() {
        boolean[][] visited = new boolean[size][size];
        int total = 0;
        int maxSize = 0;
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                total = sum(row, col, total);
                if(!visited[row][col] && A[row][col] != 0) {
                    eachMassSize = 0;
                    dfs(row, col, visited);
                    maxSize = Math.max(maxSize, eachMassSize);
                }
            }
        }
        int[] answer = {total, maxSize};
        return answer;
    }

    static void dfs(int x, int y, boolean[][] visited) {
        if(visited[x][y]) return;
        visited[x][y] = true;
        eachMassSize++;
        for(int dir = 0; dir < 4; dir++) {
            int cx = x + dx[dir], cy = y + dy[dir];
            if(isInMap(cx, cy)) {
                if(!visited[cx][cy] && A[cx][cy] != 0)
                    dfs(cx, cy, visited);
            }
        }
    }

    static int sum(int row, int col, int total) {
        return total + A[row][col];
    }

    static void fireStorm(int level) {
        rotate(level);
        melt();
    }

    static void melt() {
        boolean[][] isMelt = findMeltPoint();
        meltPoints(isMelt);
    }

    static void meltPoints(boolean[][] isMelt) {
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                if(isMelt[row][col]) A[row][col]--;
            }
        }
    }

    static boolean[][] findMeltPoint() {
        boolean[][] isMelt = new boolean[size][size];
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                if(A[row][col] == 0) continue;
                int count = 0;
                for(int dir = 0; dir < 4; dir++) {
                    int cx = row + dx[dir], cy = col + dy[dir];
                    if(isInMap(cx, cy)) {
                        if(A[cx][cy] != 0) count++;
                    }
                }
                if(count < 3) isMelt[row][col] = true;
            }
        }
        return isMelt;
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < size && y >= 0 && y < size) return true;
        return false;
    }

    static void rotate(int level) {
        int numbers = (int)Math.pow(2, N * 2 - level * 2);
        int levelSize = (int)Math.pow(2, level);
        int row = 0, col = 0;
        for(int count = 0; count < numbers; count++) {
            rotateEachPart(row, col, row + levelSize - 1, col + levelSize - 1);
            row += levelSize;
            if(row == size) {
                row = 0;
                col += levelSize;
            }
        }
    }

    static void rotateEachPart(int startX, int startY, int endX, int endY) {
        int[][] rotateArr = new int[endX - startX + 1][endY - startY + 1];
        for(int col = startY; col <= endY; col++) {
            for(int row = endX; row >= startX; row--)
                rotateArr[col - startY][endX - row] = A[row][col];
        }

        for(int row = startX; row <= endX; row++) {
            for(int col = startY; col <= endY; col++)
                A[row][col] = rotateArr[row - startX][col - startY];
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
