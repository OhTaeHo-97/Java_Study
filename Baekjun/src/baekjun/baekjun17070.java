package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun17070 {
    static int houseSize;
    static int[][] map;

    static void input() {
        Reader scanner = new Reader();

        houseSize = scanner.nextInt();
        map = new int[houseSize][houseSize];

        for(int row = 0; row < houseSize; row++) {
            for(int col = 0; col < houseSize; col++) {
                map[row][col] = scanner.nextInt();
            }
        }
    }

    static void solution() {

    }

    static class Pipe {
        int[] beforePoint;
        int[] nextPoint;
        int direction;

        public Pipe(int[] beforePoint, int[] nextPoint, int direction) {
            this.beforePoint = beforePoint;
            this.nextPoint = nextPoint;
            this.direction = direction;
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
