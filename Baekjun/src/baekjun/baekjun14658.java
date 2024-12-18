package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun14658 {
    private static int answer;
    private static int colLength;
    private static int rowLength;
    private static int trampolineLength;
    private static int meteorCount;
    private static List<int[]> meteors;

    private static void input() {
        Reader scanner = new Reader();

        colLength = scanner.nextInt();
        rowLength = scanner.nextInt();
        trampolineLength = scanner.nextInt();
        meteorCount = scanner.nextInt();
        meteors = new ArrayList<>();
        answer = Integer.MIN_VALUE;

        for(int meteor = 0; meteor < meteorCount; meteor++) {
            meteors.add(new int[] {scanner.nextInt(), scanner.nextInt()});
        }
    }

    private static void solution() {
        for (int[] meteor1 : meteors) {
            for (int[] meteor2 : meteors) {
                answer = Math.max(answer, boundMeteor(meteor1[0], meteor2[1]));
            }
        }
        System.out.println(meteorCount - answer);
    }

    private static int boundMeteor(int x, int y) {
        int count = 0;
        for(int[] meteor : meteors) {
            if(isInBound(x, y, meteor)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isInBound(int x, int y, int[] meteor) {
        return x <= meteor[0] && meteor[0] <= x + trampolineLength && y <= meteor[1] && meteor[1] <= y + trampolineLength;
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
