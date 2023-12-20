package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1176 {
    static int studentCount;
    static int minDiff;
    static int[] heights;
    static long[][] dp;

    static void input() {
        Reader scanner = new Reader();

        studentCount = scanner.nextInt();
        minDiff = scanner.nextInt();
        heights = new int[studentCount];
        dp = new long[1 << studentCount][studentCount];

        for (int idx = 0; idx < studentCount; idx++) {
            heights[idx] = scanner.nextInt();
            dp[1 << idx][idx] = 1;
        }
    }

    static void solution() {
        findNumberOfCases();
        System.out.println(calculateTotalNumberOfCases());
    }

    static long calculateTotalNumberOfCases() {
        long answer = 0;
        int state = (1 << studentCount) - 1;
        for (int idx = 0; idx < studentCount; idx++) {
            answer += dp[state][idx];
        }

        return answer;
    }

    static void findNumberOfCases() {
        for (int state = 1; state < (1 << studentCount); state++) {
            for (int student = 0; student < studentCount; student++) {
                if (dp[state][student] == 0) {
                    continue;
                }
                for (int nextStudent = 0; nextStudent < studentCount; nextStudent++) {
                    int newState = state | (1 << nextStudent);
                    if ((state & (1 << (nextStudent))) == 0
                            && Math.abs(heights[student] - heights[nextStudent]) > minDiff) {
                        dp[newState][nextStudent] += dp[state][student];
                    }
                }
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
