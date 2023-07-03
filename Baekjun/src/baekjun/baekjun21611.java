package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjun21611 {
    static int N, M;
    static int[][] map;
    static int[][] commands;
    static int[] dx = {0, -1, 1, 0, 0}, dy = {0, 0, 0, -1, 1};
    static int[] sharkLoc;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new int[N][N];
        commands = new int[M][2];
        sharkLoc = new int[2];
        sharkLoc[0] = sharkLoc[1] = N / 2;

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++)
                map[row][col] = scanner.nextInt();
        }

        for(int idx = 0; idx < M; idx++) {
            int dir = scanner.nextInt(), dist = scanner.nextInt();
            commands[idx][0] = dir;
            commands[idx][1] = dist;
        }
    }

    static void solution() {
        for(int idx = 0; idx < M; idx++) {
            while(true) {
                destroyMarble(commands[idx][0], commands[idx][1]);
                moveMarble();

            }
        }
    }

    static void moveMarble() {
        int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
        Stack<Integer> marbles = new Stack<>();

        int row = 0, col = 0, amountNum = N;
        for(int count = 0; count < N / 2 * 4; count++) {
            int dir = count % 4;
            int repeatNum = (amountNum == N || amountNum == 1) ? 1 : 2;
            count += (repeatNum - 1);
            for(int repeat = 0; repeat < repeatNum; repeat++) {
                for(int amount = 0; amount < amountNum; amount++) {
                    if(map[row][col] != 0)
                        marbles.push(map[row][col]);
                    row += dx[dir];
                    col += dy[dir];
                }
                dir = count % 4;
            }
            amountNum--;
        }

        dx = new int[] {1, 0, -1, 0};
        dy = new int[] {0, 1, 0, -1};
        row = sharkLoc[0];
        col = sharkLoc[1] - 1;
        amountNum = 1;
        for(int count = 0; count < N / 2 * 4; count++) {
            int dir = count % 4;
            int repeatNum = (amountNum == 1 || amountNum == N) ? 1 : 2;
            count += (repeatNum - 1);
            for(int repeat = 0; repeat < repeatNum; repeat++) {
                for(int amount = 0; amount < amountNum; amount++) {
                    if(marbles.isEmpty()) return;
                    map[row][col] = marbles.pop();
                    row += dx[dir];
                    col += dy[dir];
                }
                dir = count % 4;
            }
            amountNum++;
        }
    }

    static void destroyMarble(int dir, int dist) {
        int row = sharkLoc[0], col = sharkLoc[1];
        for(int amount = 0; amount < dist; amount++) {
            row += dx[dir];
            col += dy[dir];

            map[row][col] = 0;
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
