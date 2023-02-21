package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun2696 {
	static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();

    static int M;
    static int[] nums;
    static PriorityQueue<Integer> min, max;

    static void input() {
        M = scanner.nextInt();
        nums = new int[M];
        min = new PriorityQueue<>();
        max = new PriorityQueue<>(Collections.reverseOrder());

        for(int idx = 0; idx < M; idx++)
            nums[idx] = scanner.nextInt();
    }

    static void solution() {
        sb.append(M / 2 + 1).append('\n');
        int size = 0;

        for(int idx = 1; idx <= M; idx++) {
            if(max.isEmpty()) {
                max.offer(nums[idx - 1]);
                sb.append(max.peek()).append(' ');
                size++;
                continue;
            } else if(min.size() == max.size()) {
                max.offer(nums[idx - 1]);
            } else {
                min.offer(nums[idx - 1]);
            }

            if(max.size() != 0 && min.size() != 0) {
                makeHeap(min, max);
            }

            if(idx % 2 != 0) {
                sb.append(max.peek()).append(' ');
                size++;
            }

            if(size / 10 > 0) {
                sb.append('\n');
                size -= 10;
            }
        }

        sb.append('\n');
    }

    static void makeHeap(PriorityQueue<Integer> min, PriorityQueue<Integer> max) {
        if(min.peek() < max.peek()) {
            int maxCentral = max.poll(), minCentral = min.poll();
            max.offer(minCentral);
            min.offer(maxCentral);
        }
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();

        while(T-- > 0) {
            input();
            solution();
        }

        System.out.println(sb);
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
