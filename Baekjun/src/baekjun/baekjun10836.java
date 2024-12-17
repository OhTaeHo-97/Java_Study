package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun10836 {
    private static int size;
    private static int day;
    private static int[][] growAmounts;
    private static int[][] larvae;

    private static void input() {
        Reader scanner = new Reader();

        size = scanner.nextInt();
        day = scanner.nextInt();
        growAmounts = new int[day][3];

        for(int date = 0; date < day; date++) {
            for(int count = 0; count < growAmounts[date].length; count++) {
                growAmounts[date][count] = scanner.nextInt();
            }
        }
    }

    private static void solution() {
        initializeLarvae();
        for(int date = 0; date < day; date++) {
            growLarvae(growAmounts[date]);
        }
        printLarvae();
    }

    private static void printLarvae() {
        for (int row = 0; row < larvae.length; row++) {
            for (int col = 0; col < larvae[row].length; col++) {
                if(larvae[row][col] != 0) {
                    System.out.print(larvae[row][col] + " ");
                } else {
                    larvae[row][col] = larvae[row - 1][col];
                    System.out.print(larvae[row][col] + " ");
                }
            }
            System.out.println();
        }
    }

    private static void initializeLarvae() {
        larvae = new int[size][size];
        Arrays.fill(larvae[0], 1);
        for(int row = 1; row < size; row++) {
            larvae[row][0] = 1;
        }
    }

    private static void growLarvae(int[] growAmount) {
        int row = size - 1;
        int col = 0;

        int zeroCount = growAmount[0];
        while(zeroCount-- > 0) {
            if(row > 0) {
                row--;
            } else {
                col++;
            }
        }

        int oneCount = growAmount[1];
        while(oneCount-- > 0) {
            larvae[row][col]++;
            if(row > 0) {
                row--;
            } else {
                col++;
            }
        }

        int twoCount = growAmount[2];
        while(twoCount-- > 0) {
            larvae[row][col] += 2;
            if(row > 0) {
                row--;
            } else {
                col++;
            }
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
