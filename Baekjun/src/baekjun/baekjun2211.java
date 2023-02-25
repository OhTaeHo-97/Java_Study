package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun2211 {
	static int N, M;
    static HashMap<Integer, ArrayList<Circuit>> map;
    static int[] times, path;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();

        map = new HashMap<>();
        for(int computer = 1; computer <= N; computer++)
            map.put(computer, new ArrayList<>());

        times = new int[N + 1];
        Arrays.fill(times, Integer.MAX_VALUE);

        path = new int[N + 1];
        for(int computer = 1; computer <= N; computer++)
            path[computer] = computer;

        for(int idx = 0; idx < M; idx++) {
            int computer1 = scanner.nextInt(), computer2 = scanner.nextInt();
            int time = scanner.nextInt();

            map.get(computer1).add(new Circuit(computer2, time));
            map.get(computer2).add(new Circuit(computer1, time));
        }
    }

    static void solution() {
        dijkstra(1);

        StringBuilder sb = new StringBuilder();

        int circuitNum = 0;
        for(int computer = 2; computer <= N; computer++) {
            circuitNum++;
            if(path[computer] == 1) {
                sb.append(1).append(' ').append(computer).append('\n');
            } else {
                sb.append(computer).append(' ').append(path[computer]).append('\n');
            }
        }

        sb.insert(0, circuitNum + "\n");

        System.out.println(sb);
    }

    static void dijkstra(int start) {
        PriorityQueue<Circuit> queue = new PriorityQueue<>();

        queue.offer(new Circuit(start, 0));
        times[start] = 0;

        while(!queue.isEmpty()) {
            Circuit cur = queue.poll();

            if(times[cur.computer] < cur.time) continue;

            for(Circuit circuit : map.get(cur.computer)) {
                int computer = circuit.computer, time = circuit.time;
                if(times[computer] > times[cur.computer] + time) {
                    times[computer] = times[cur.computer] + time;
                    path[computer] = cur.computer;
                    queue.offer(new Circuit(computer, times[computer]));
                }
            }
        }
    }

    static class Circuit implements Comparable<Circuit> {
        int computer, time;

        public Circuit(int computer, int time) {
            this.computer = computer;
            this.time = time;
        }

        @Override
        public int compareTo(Circuit c) {
            return this.time - c.time;
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
