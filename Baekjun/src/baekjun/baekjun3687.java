package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun3687 {
	static final int SIZE = 100;
    static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();
    static int n;
    static String[] max;
    static long[] min;

    static void input() {
        n = scanner.nextInt();
    }

    static void solution() {
        sb.append(min[n]).append(' ').append(max[n]).append('\n');
    }

    static void calcAllCase() {
        calcAllMin();
        calcAllMax();
    }

    static void calcAllMin() {
        min = new long[SIZE + 1];
        Arrays.fill(min, Long.MAX_VALUE);

        min[0] = min[1] = 0;
        min[2] = 1;
        min[3] = 7;
        min[4] = 4;
        min[5] = 2;
        min[6] = 6;
        min[7] = 8;
        min[8] = 10;

        String[] num = {"1", "7", "4", "2", "0", "8"};

        for(int idx = 9; idx <= SIZE; idx++) {
            for(int count = 2; count <= 7; count++) {
                String minNum = min[idx - count] + num[count - 2];
                if(Long.parseLong(minNum) == 0) continue;
                min[idx] = Math.min(Long.parseLong(minNum), min[idx]);
            }
        }
    }

    static void calcAllMax() {
        max = new String[SIZE + 1];

        max[2] = "1";
        for(int idx = 3; idx <= SIZE; idx++) {
            String maxNum = "";
            if(idx % 2 == 0) {
                for(int num = 0; num < idx / 2; num++) maxNum += "1";
            } else {
                int remain = idx - 3;
                maxNum += "7";
                for(int num = 0; num < remain / 2; num++) maxNum += "1";
            }

            max[idx] = maxNum;
        }
    }

    public static void main(String[] args) {
        calcAllCase();

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
