package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun14476 {
	static int N;
    static int[] nums;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        nums = new int[N + 2];

        for(int idx = 1; idx <= N; idx++)
            nums[idx] = scanner.nextInt();
    }

    static void solution() {
        int[] leftGcd = new int[N + 2], rightGcd = new int[N + 2];
        for(int idx = 1; idx <= N; idx++) leftGcd[idx] = gcd(nums[idx], leftGcd[idx - 1]);
        for(int idx = N; idx > 0; idx--) rightGcd[idx] = gcd(nums[idx], rightGcd[idx + 1]);

        int answer = Integer.MIN_VALUE, max = Integer.MIN_VALUE;
        for(int idx = 1; idx <= N; idx++) {
            int tempGcd = gcd(leftGcd[idx - 1], rightGcd[idx + 1]);
            if(tempGcd > max) {
                if(nums[idx] % tempGcd != 0) {
                    max = tempGcd;
                    answer = nums[idx];
                }
            }
        }

        if(answer == Integer.MIN_VALUE) System.out.println(-1);
        else System.out.println(max + " " + answer);
    }

    static int gcd(int num1, int num2) {
        if(num2 == 0) return num1;
        return gcd(num2, num1 % num2);
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
