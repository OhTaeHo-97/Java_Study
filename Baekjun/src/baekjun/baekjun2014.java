package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun2014 {
    static int K, N;
    static long[] nums;
    static PriorityQueue<Long> multiplies;

    static void input() {
        Reader scanner = new Reader();

        K = scanner.nextInt();
        N = scanner.nextInt();
        nums = new long[K];
        multiplies = new PriorityQueue<>();

        for(int idx = 0; idx < K; idx++) {
            nums[idx] = scanner.nextLong();
            multiplies.offer(nums[idx]);
        }
    }

    static void solution() {
        long answer = 0;

        while(N-- > 0) {
            answer = multiplies.poll();

            for(int idx = 0; idx < K; idx++) {
                if((answer * nums[idx]) >= ((long) 2 << 30)) break;

                multiplies.offer(answer * nums[idx]);

                if(answer % nums[idx] == 0) break;
            }
        }

        System.out.println(answer);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}