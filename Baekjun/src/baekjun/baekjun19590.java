package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun19590 {
    static int marbleTypeNum;
    static int[] marbles;
    static PriorityQueue<Integer> maxHeap;

    static void input() {
        Reader scanner = new Reader();

        marbleTypeNum = scanner.nextInt();
        marbles = new int[marbleTypeNum];
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int idx = 0; idx < marbleTypeNum; idx++) {
            marbles[idx] = scanner.nextInt();
            maxHeap.offer(marbles[idx]);
        }
    }

    static void solution() {
        long max = maxHeap.poll();
        long remainedSum = maxHeap.stream().mapToLong(Long :: valueOf).sum();

        if(max >= remainedSum) {
            System.out.println(max - remainedSum);
        } else {
            long totalNum = Arrays.stream(marbles).mapToLong(Long :: valueOf).sum();
            if(totalNum % 2 == 0) {
                System.out.println(0);
            } else {
                System.out.println(1);
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