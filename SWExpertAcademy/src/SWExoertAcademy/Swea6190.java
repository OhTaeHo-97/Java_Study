package src.SWExoertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea6190 {
    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static int numCount;
    static int[] nums;

    static void input() {
        numCount = scanner.nextInt();
        nums = new int[numCount];
        for (int idx = 0; idx < numCount; idx++) {
            nums[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        int max = -1;
        for (int idx = 0; idx < numCount - 1; idx++) {
            for (int idx2 = idx + 1; idx2 < numCount; idx2++) {
                int multipliedNumber = nums[idx] * nums[idx2];
                if (isAscendingNumber(multipliedNumber)) {
                    max = Math.max(max, multipliedNumber);
                }
            }
        }

        answer.append(max).append('\n');
    }

    static boolean isAscendingNumber(int number) {
        String num = String.valueOf(number);
        for (int idx = 1; idx < num.length(); idx++) {
            if (num.charAt(idx - 1) > num.charAt(idx)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        for (int test = 1; test <= T; test++) {
            answer.append('#').append(test).append(' ');
            input();
            solution();
        }
        System.out.print(answer);
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
