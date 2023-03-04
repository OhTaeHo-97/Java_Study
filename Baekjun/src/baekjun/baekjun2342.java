package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun2342 {
	static ArrayList<Integer> moveLocs;
    static int[][][] dp;

    static void input() {
        Reader scanner = new Reader();

        moveLocs = new ArrayList<>();
        while(true) {
            int loc = scanner.nextInt();
            if(loc == 0) break;
            moveLocs.add(loc);
        }
    }

    static void solution() {
        dp = new int[5][5][moveLocs.size()];
        for(int row = 0; row < 5; row++) {
            for(int col = 0; col < 5; col++)
                Arrays.fill(dp[row][col], -1);
        }

        System.out.println(findMinStrength(0, 0, 0));
    }

    static int findMinStrength(int left, int right, int count) {
        if(count == moveLocs.size()) return 0;

        if(dp[left][right][count] != -1) return dp[left][right][count];

        dp[left][right][count] = Math.min(
                findMinStrength(moveLocs.get(count), right, count + 1) + getNecessaryStrength(left, moveLocs.get(count)),
                findMinStrength(left, moveLocs.get(count), count + 1) + getNecessaryStrength(right, moveLocs.get(count))
        );

        return dp[left][right][count];
    }

    static int getNecessaryStrength(int curLoc, int destLoc) {
        if(curLoc == 0) return 2;

        int num = Math.abs(curLoc - destLoc);

        if(num == 0) return 1;
        else if(num == 1 || num == 3) return 3;
        else return 4;
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
