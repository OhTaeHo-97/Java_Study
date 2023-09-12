package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun14908 {
    static int N;
    static PriorityQueue<Task> tasks;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        tasks = new PriorityQueue<>();

        for(int idx = 1; idx <= N; idx++) {
            int workingPeriod = scanner.nextInt();
            int delayCost = scanner.nextInt();

            tasks.offer(new Task(idx, workingPeriod, delayCost));
        }
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();
        for(int idx = 0; idx < N; idx++) {
            sb.append(tasks.poll().index).append(' ');
        }

        System.out.println(sb);
    }

    static class Task implements Comparable<Task> {
        int index;
        int workingPeriod;
        int delayCost;

        public Task(int index, int workingPeriod, int delayCost) {
            this.index = index;
            this.workingPeriod = workingPeriod;
            this.delayCost = delayCost;
        }

        @Override
        public int compareTo(Task t) {
            double firstTaskCost = (double) workingPeriod / delayCost;
            double secondTaskCost = (double) t.workingPeriod / t.delayCost;

            if(firstTaskCost == secondTaskCost) {
                return index - t.index;
            }

            return Double.compare(firstTaskCost, secondTaskCost);
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
