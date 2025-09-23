package SWExoertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Swea4303 {
    private static Reader scanner;
    private static StringBuilder sb;

    private static int testNumber;
    private static int rowSize;
    private static int colSize;
    private static int[][] price;
    private static int[][][] dp;

    private static void input() {
        rowSize = scanner.nextInt();
        colSize = scanner.nextInt();
        price = new int[rowSize + 2][colSize + 2];
        dp = new int[rowSize + 2][colSize + 2][16];

        for(int row = 0; row < dp.length; row++) {
            for(int col = 0; col < dp[row].length; col++) {
                Arrays.fill(dp[row][col], Integer.MAX_VALUE);
            }
        }

        for(int row = 1; row <= rowSize; row++) {
            String priceInfo = scanner.nextLine();
            for(int col = 1; col <= colSize; col++) {
                if(priceInfo.charAt(col - 1) == '.') {
                    price[row][col] = 0;
                } else {
                    price[row][col] = priceInfo.charAt(col - 1) - '0';
                }
            }
        }
    }

    private static void solution() {
        dp[1][1][0] = 0;

        for(int row = 1; row <= rowSize; row++) {
            for(int col = 1; col <= colSize; col++) {
                for(int dir = 0; dir < 16; dir++) {
                    if(dp[row][col][dir] == Integer.MAX_VALUE) continue;

                    int upPrice = price[row - 1][col];
                    int downPrice = price[row + 1][col];
                    int leftPrice = price[row][col - 1];
                    int rightPrice = price[row][col + 1];

                    int rightUp = 0;
                    int leftDown = 0;

                    if((dir & 1) != 0) upPrice = 0;
                    if((dir & 2) != 0) leftPrice = 0;
                    if((dir & 4) != 0) rightUp = 1;
                    if((dir & 8) != 0) leftDown = 2;

                    int temp;

                    // 오른쪽 이동, case 1
                    temp = dp[row][col][dir] + rightPrice + ((downPrice != 0) ? (upPrice + leftPrice) : Math.min(upPrice, leftPrice));
                    if(dp[row][col + 1][2 + rightUp] > temp) dp[row][col + 1][2 + rightUp] = temp;

                    // 오른쪽 이동, case 2
                    temp = dp[row][col][dir] + rightPrice + downPrice + Math.min(upPrice, leftPrice);
                    if(dp[row][col + 1][10 + rightUp] > temp) dp[row][col + 1][10 + rightUp] = temp;

                    // 아래 이동, case 1
                    temp = dp[row][col][dir] + downPrice + ((rightPrice != 0) ? (upPrice + leftPrice) : Math.min(upPrice, leftPrice));
                    if(dp[row + 1][col][1 + leftDown] > temp) dp[row + 1][col][1 + leftDown] = temp;

                    // 아래 이동, case 2
                    temp = dp[row][col][dir] + downPrice + rightPrice + Math.min(upPrice, leftPrice);
                    if(dp[row + 1][col][5 + leftDown] > temp) dp[row + 1][col][5 + leftDown] = temp;
                }
            }
        }

        int answer = dp[rowSize][colSize][0];
        for(int dir = 1; dir < 16; dir++) {
            answer = Math.min(answer, dp[rowSize][colSize][dir]);
        }
        sb.append('#').append(testNumber).append(' ').append(answer).append('\n');
    }

    public static void main(String[] args) {
        scanner = new Reader();
        sb = new StringBuilder();

        int testCount = scanner.nextInt();
        for(testNumber = 1; testNumber <= testCount; testNumber++) {
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
