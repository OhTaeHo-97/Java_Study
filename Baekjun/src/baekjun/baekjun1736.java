package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun1736 {
    static int row;
    static int col;
    static int dustCount;
    static int[][] map;
    static PriorityQueue<Integer>[] dustLocs;

    static void input() {
        Reader scanner = new Reader();

        row = scanner.nextInt();
        col = scanner.nextInt();
        map = new int[row][col];
        dustLocs = new PriorityQueue[col];
        for (int c = 0; c < col; c++) {
            dustLocs[c] = new PriorityQueue<>();
        }

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                map[r][c] = scanner.nextInt();
                if (map[r][c] == 1) {
                    dustLocs[c].offer(r);
                    dustCount++;
                }
            }
        }
    }

    static void solution() {
        int answer = 0;
        for (int count = 0; count < col; count++) {
            int dust = 0;
            int height = row - 1;
            for (int c = col - 1; c >= 0; c--) {
                if (dustLocs[c].size() == 0) {
                    continue;
                }
                int dustLoc = dustLocs[c].peek();
                if (height >= dustLoc) {
                    while (dustLocs[c].size() > 0 && height >= dustLocs[c].peek()) {
                        dustLocs[c].poll();
                        dust++;
                    }
                    height = dustLoc;
                }
            }

            if (dust > 0) {
                answer++;
            }
            if (dust == 0) {
                break;
            }
        }

        System.out.println(answer);
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
