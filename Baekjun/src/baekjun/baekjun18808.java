package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun18808 {
	static int N, M, K;
    static boolean[][] isAttached;
    static Sticker[] stickers;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt(); // 세로 길이
        M = scanner.nextInt(); // 가로 길이
        K = scanner.nextInt(); // 스티커 개수
        isAttached = new boolean[N][M];
        stickers = new Sticker[K];

        for(int idx = 0; idx < K; idx++) {
            int row = scanner.nextInt(), col = scanner.nextInt();
            stickers[idx] = new Sticker(row, col);

            for(int x = 0; x < row; x++) {
                for(int y = 0; y < col; y++) {
                    int num = scanner.nextInt();
                    if(num == 1) stickers[idx].attached[x][y] = true;
                }
            }
        }
    }

    static void solution() {
        for(int idx = 0; idx < K; idx++)
            attachSticker(stickers[idx]);

        System.out.println(getAttachedNum());
    }

    static void print() {
        for(int row = 0; row < isAttached.length; row++) {
            for(int col = 0; col < isAttached[row].length; col++) {
                System.out.print(isAttached[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int getAttachedNum() {
        int count = 0;

        for(int row = 0; row < isAttached.length; row++) {
            for(int col = 0; col < isAttached[row].length; col++) {
                if(isAttached[row][col]) count++;
            }
        }

        return count;
    }

    static void attachSticker(Sticker sticker) {
        if(isPossible(sticker.attached)) return;
        if(isPossible(sticker.rotate90())) return;
        if(isPossible(sticker.rotate180())) return;
        isPossible(sticker.rotate270());
    }

    static boolean isPossible(boolean[][] attached) {
        int rowNum = attached.length, colNum = attached[0].length;

        if(rowNum > isAttached.length || colNum > isAttached[0].length) return false;

        for(int x = 0; x <= isAttached.length - rowNum; x++) {
            for(int y = 0; y <= isAttached[0].length - colNum; y++) {
                boolean flag = true;
                Loop :
                for(int row = x; row < x + rowNum; row++) {
                   for(int col = y; col < y + colNum; col++) {
                       if(isAttached[row][col] && attached[row - x][col - y]) {
                           flag = false;
                           break Loop;
                       }
                   }
               }

                if(flag) {
                    for(int row = x; row < x + rowNum; row++) {
                        for(int col = y; col < y + colNum; col++)
                            if(attached[row - x][col - y]) isAttached[row][col] = true;
                    }
                    return true;
                }
            }
        }

        return false;
    }

    static class Sticker {
        boolean[][] attached;

        public Sticker(int row, int col) {
           attached = new boolean[row][col];
        }

        public boolean[][] rotate90() {
            boolean[][] rotate = new boolean[attached[0].length][attached.length];

            for(int row = 0; row < attached.length; row++) {
                for(int col = 0; col < attached[row].length; col++)
                    rotate[col][(attached.length - 1) - row] = attached[row][col];
            }

            return rotate;
        }

        public boolean[][] rotate180() {
            boolean[][] rotate = new boolean[attached.length][attached[0].length];

            for(int row = 0; row < attached.length; row++) {
                for(int col = 0; col < attached[row].length; col++)
                    rotate[(attached.length - 1) - row][(attached[row].length - 1) - col] = attached[row][col];
            }

            return rotate;
        }

        public boolean[][] rotate270() {
            boolean[][] rotate = new boolean[attached[0].length][attached.length];

            for(int row = 0; row < attached.length; row++) {
                for(int col = 0; col < attached[row].length; col++)
                    rotate[(attached[row].length - 1) - col][row] = attached[row][col];
            }

            return rotate;
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
