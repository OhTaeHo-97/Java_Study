package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1256 {
	static final int SIZE = 100;
    static StringBuilder sb = new StringBuilder();
    static int N, M, K;
    static double[][] dp;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
    }

    static void solution() {
        dp = new double[SIZE + 1][SIZE + 1];

        if(checkNum(N, M) < K) {
            System.out.println(-1);
            return;
        }

        makeString(N, M, (double)K);
        System.out.println(sb);
    }

    static double checkNum(int aNum, int zNum) {
        if(aNum == 0 || zNum == 0)
            return 1;

        if(dp[aNum][zNum] != 0)
            return dp[aNum][zNum];

        return dp[aNum][zNum] = Double.min(checkNum(aNum - 1, zNum) + checkNum(aNum, zNum - 1), 1000000001);
    }

    static void makeString(int aNum, int zNum, double target) {
        if(aNum == 0) {
            for(int idx = 0; idx < zNum; idx++)
                sb.append('z');

            return;
        }

        if(zNum == 0) {
            for(int idx = 0; idx < aNum; idx++)
                sb.append('a');

            return;
        }

        double totalNum = checkNum(aNum - 1, zNum);

        if(target > totalNum) {
            sb.append('z');
            makeString(aNum, zNum - 1, target - totalNum);
        } else {
            sb.append('a');
            makeString(aNum - 1, zNum, target);
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
