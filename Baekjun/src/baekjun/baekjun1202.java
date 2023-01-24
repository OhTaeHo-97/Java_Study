package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun1202 {
	static int N, K;
    static Jewel[] jewels;
    static int[] bags;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        K = scanner.nextInt();

        jewels = new Jewel[N];
        bags = new int[K];

        for(int jewel = 0; jewel < N; jewel++) {
            int weight = scanner.nextInt(), value = scanner.nextInt();
            jewels[jewel] = new Jewel(weight, value);
        }

        for(int bag = 0; bag < K; bag++)
            bags[bag] = scanner.nextInt();
    }

    static void solution() {
        sort();
        System.out.println(getMaxSum());
    }

    static long getMaxSum() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        long answer = 0;
        for(int bag = 0, jewel = 0; bag < K; bag++) {
            while(jewel < N && jewels[jewel].weight <= bags[bag]) {
                queue.offer(jewels[jewel++].value);
            }

            if(!queue.isEmpty()) answer += queue.poll();
        }

        return answer;
    }

    static void sort() {
        Arrays.sort(jewels, new Comparator<Jewel>() {
            @Override
            public int compare(Jewel j1, Jewel j2) {
                if(j1.weight != j2.weight) return j1.weight - j2.weight;
                return j2.value - j1.value;
            }
        });

        Arrays.sort(bags);
    }

    static class Jewel {
        int weight, value;
        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
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
