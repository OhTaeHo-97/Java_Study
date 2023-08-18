package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun14464 {
    static int C, N;
    static int[] chickens;
    static Cow[] cows;

    static void input() {
        Reader scanner = new Reader();

        C = scanner.nextInt();
        N = scanner.nextInt();

        chickens = new int[C];
        cows = new Cow[N];

        for(int idx = 0; idx < C; idx++)
            chickens[idx] = scanner.nextInt();

        for(int idx = 0; idx < N; idx++)
            cows[idx] = new Cow(scanner.nextInt(), scanner.nextInt());
    }

    static void solution() {
        Arrays.sort(chickens);
        Arrays.sort(cows); // 소의 시작 시간 기준 오름차순으로, 시작 시간이 같다면 마지막 시간 기준 오름차순으로 정렬

        // 각 닭이 도와줄 수 있는 소의 마지막 시간들을 저장하는 PriorityQueue
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int answer = 0, cowIdx = 0;

        // 각 닭의 시간을 순회하면서 각 닭에 소를 배치한다
        for(int idx = 0; idx < C; idx++) {
            int chicken = chickens[idx]; // 현재 닭의 시간

            // 소들을 순회하면서 소의 시작 시간이 현재 닭의 시간보다 작거나 같은 소라면
            // 현재 닭에 배치될 수 있는 소이기 때문에 queue에 소의 마지막 시간을 저장한다
            // 소의 시작 시간이 현재 닭의 시간보다 커지게 되면 해당 소부터는 현재 닭에 배치될 수 없는 소이므로 더이상 순회하지 않는다
            while(cowIdx < N && cows[cowIdx].start <= chicken)
                queue.offer(cows[cowIdx++].end);

            // 소의 마지막 시간이 현재 닭의 시간보다 작다면 해당 소에는 현재 닭을 배치할 수 없기 때문에
            // queue에서 제거한다
            while(!queue.isEmpty() && queue.peek() < chicken)
                queue.poll();

            // queue에 값이 존재한다면 마지막 시간이 가장 빠른 소에 현재 닭을 배치하는 것이
            // 가장 많은 소에 닭을 배치할 수 있는 방법이기 때문에 마지막 시간이 가장 빠른 소에 현재 닭을 배치한다
            if(!queue.isEmpty()) {
                int endTime = queue.poll();
                answer++;
            }
        }

        System.out.println(answer);
    }

    static class Cow implements Comparable<Cow> {
        int start, end;

        public Cow(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Cow o) {
            if(start != o.start) return start - o.start;
            return end - o.end;
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
