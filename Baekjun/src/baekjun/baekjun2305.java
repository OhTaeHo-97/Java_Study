package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2305 {
    static int seatCnt;
    static int nonReservedSeat;

    static int[] reservedSeatCnt;
    static int[] reservedSeatCntSum;

    static void input() {
        Reader scanner = new Reader();

        seatCnt = scanner.nextInt();
        nonReservedSeat = scanner.nextInt();
    }

    static void solution() {
        getReservedSeatCnt();

        int[] dp = new int[seatCnt + 1];
        dp[0] = dp[1] = 1;
        dp[2] = 2;
        for(int idx = 3; idx <= seatCnt; idx++) {
            dp[idx] = dp[idx - 1] + dp[idx - 2] + reservedSeatCntSum[idx - 2] + reservedSeatCntSum[idx - 3];
        }

        System.out.println(dp[seatCnt - nonReservedSeat + 1] * reservedSeatCnt[nonReservedSeat - 1] + dp[nonReservedSeat] * reservedSeatCnt[seatCnt - nonReservedSeat] - reservedSeatCnt[nonReservedSeat - 1] * reservedSeatCnt[seatCnt - nonReservedSeat]);
    }

    static void getReservedSeatCnt() {
        reservedSeatCnt = new int[seatCnt + 1];
        reservedSeatCntSum = new int[seatCnt + 1];
        reservedSeatCnt[0] = reservedSeatCnt[1] = reservedSeatCntSum[0] = 1;
        reservedSeatCntSum[1] = 2;

        for(int idx = 2; idx < reservedSeatCnt.length; idx++) {
            reservedSeatCnt[idx] = reservedSeatCnt[idx - 1] + reservedSeatCnt[idx - 2];
            reservedSeatCntSum[idx] = reservedSeatCntSum[idx - 1] + reservedSeatCnt[idx];
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
