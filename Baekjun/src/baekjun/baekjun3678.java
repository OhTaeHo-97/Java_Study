package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun3678 {
    static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();
    static final int SIZE = 10_000;

    static int[] dx = {-2, -1, 1, 2, 1, -1};
    static int[] dy = {0, -1, -1, 0, 1, 1};
    static int nthTile;
    static int[][] map = new int[240][120];
    static int[] answer = new int[SIZE + 1];
    static int[] numberCnt = new int[6];

    static void input() {
        nthTile = scanner.nextInt();
    }

    static void solution() {
        sb.append(answer[nthTile]).append('\n');
    }

    static void makeMap() {
        int x = 120;
        int y = 60;
        int index = 1;

        map[x][y] = 1;
        numberCnt[1]++;
        answer[index++] = 1;

        int size = 1;

        while(index <= SIZE) {
            x += dx[5];
            y += dy[5];
            int nextNum = getNextNum(x, y);

            map[x][y] = nextNum;
            numberCnt[nextNum]++;
            answer[index++] = nextNum;

            for(int idx = 0; idx < 6; idx++) {
                for(int cnt = 0; cnt < size; cnt++) {
                    if(idx == 0 && cnt == size - 1) {
                        continue;
                    }
                    if(index > SIZE) break;

                    x += dx[idx];
                    y += dy[idx];
                    nextNum = getNextNum(x, y);
                    map[x][y] = nextNum;
                    numberCnt[nextNum]++;
                    answer[index++] = nextNum;
                }
            }

            size++;
        }
    }

    static int getNextNum(int x, int y) {
        int[] available = new int[6];
        for(int idx = 0; idx < 6; idx++) {
            available[map[x + dx[idx]][y + dy[idx]]] = 1;
        }

        int minCnt = Integer.MAX_VALUE;
        int minNum = 0;
        for(int idx = 1; idx < 6; idx++) {
            if(available[idx] == 0 && numberCnt[idx] < minCnt) {
                minCnt = numberCnt[idx];
                minNum = idx;
            }
        }

        return minNum;
    }

    public static void main(String[] args) {
        makeMap();

        int T = scanner.nextInt();

        while(T-- > 0) {
            input();
            solution();
        }

        System.out.print(sb);
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
