package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun1162 {
    static int N, M, K;
    static long[][] times;
    static Map<Integer, List<Edge>> edges;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
        times = new long[N + 1][K + 1]; // 1번부터 n번 도시까지 k개의 도로를 포장하며 이동하였을 때의 최소 시간
        edges = new HashMap<>(); // 간선 정보

        for(int city = 1; city <= N; city++) {
            edges.put(city, new ArrayList<>());
            Arrays.fill(times[city], Long.MAX_VALUE);
        }

        for(int edge = 0; edge < M; edge++) {
            int city1 = scanner.nextInt(), city2 = scanner.nextInt(), time = scanner.nextInt();
            edges.get(city1).add(new Edge(city2, time, 0));
            edges.get(city2).add(new Edge(city1, time, 0));
        }
    }

    static void solution() {
        dijkstra();
        long min = Arrays.stream(times[N]).min().getAsLong();
        System.out.println(min);
    }

    static void dijkstra() {
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        queue.offer(new Edge(1, 0, 0));
        times[1][0] = 0;

        while(!queue.isEmpty()) {
            Edge cur = queue.poll();
            // 이전까지의 현재 도시에서의 최소 시간이 현재 이동한 시간보다 더 작다면
            // 현재 이동한 경로로 이동하였을 때에는 최소 시간이 될 수 없다는 뜻이므로 다음 경우를 확인한다
            if(times[cur.city][cur.pavedCnt] < cur.time) continue;

            // 현재 도시에 연결된 간선들을 순회하며 최소 시간이 되는 경우를 찾고 해당 경로로 이동한다
            for(Edge edge : edges.get(cur.city)) {
                long nextTime = cur.time + edge.time; // 연결된 간선의 도착 도시까지 이동하는 시간
                // 연결된 간선을 포장하지 않고 이동하였을 때의 시간이 최소 시간보다 작다면
                if(nextTime < times[edge.city][cur.pavedCnt]) {
                    // 시간을 갱신하고 queue에 해당 정보를 넣어 다음 경우를 탐색한다
                    times[edge.city][cur.pavedCnt] = nextTime;
                    queue.offer(new Edge(edge.city, nextTime, cur.pavedCnt));
                }
                // 연결된 간선을 포장하고 이동하였을 때의 시간이 최소 시간보다 작다면
                if(cur.pavedCnt < K && cur.time < times[edge.city][cur.pavedCnt + 1]) {
                    // 시간을 갱신하고 queue에 해당 정보를 넣어 다음 경우를 탐색한다
                    times[edge.city][cur.pavedCnt + 1] = cur.time;
                    queue.offer(new Edge(edge.city, cur.time, cur.pavedCnt + 1));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int city, pavedCnt;
        long time;

        public Edge(int city, long time, int pavedCnt) {
            this.city = city;
            this.time = time;
            this.pavedCnt = pavedCnt;
        }

        @Override
        public int compareTo(Edge o) {
            if(time < o.time) return -1;
            else if(time > o.time) return 1;
            return 0;
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
