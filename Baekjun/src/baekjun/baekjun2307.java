package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun2307 {
    static int N, M;
    static Map<Integer, List<Edge>> edges;
    static int[] times, path;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        edges = new HashMap<>();
        times = new int[N + 1];
        path = new int[N + 1];
        for(int vertex = 1; vertex <= N; vertex++) {
            edges.put(vertex, new ArrayList<>());
        }

        for(int edge = 0; edge < M; edge++) {
            int vertex1 = scanner.nextInt(), vertex2 = scanner.nextInt(), time = scanner.nextInt();
            edges.get(vertex1).add(new Edge(vertex2, time));
            edges.get(vertex2).add(new Edge(vertex1, time));
        }
    }

    static void solution() {
        // 1번 도시에서 다른 모든 도시로의 최소 시간을 구하고 이때 최소 시간에 해당하는 경로 역시 저장한다
        initDijkstra(1);
        // N까지의 최소 시간을 저장해놓는다
        int min = times[N];

        // 최소 시간에 해당하는 경로들에 대해 하나씩 해당 도로들을 막아보면서 그때 1번부터 N번 도시까지의 최소 시간 중 가장 큰 시간을 구한다
        int end = N, max = 0;
        while(end != 1){
            int start = path[end];
            dijkstra(1, start, end);

            if(times[N] > max) max = times[N];
            end = start; // 막을 도로를 변경하기 위해 도착 도시를 갱신한다
        }

        // 만약 최대 거리가 무한대라면 지연 시간이 무한대라는 뜻이므로 -1을 출력하고 그렇지 않다면 최대 시간 - 최소 시간을 출력한다
        if(max == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(max - min);
    }

    static void initDijkstra(int start) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        Arrays.fill(times, Integer.MAX_VALUE);

        queue.offer(new Edge(start, 0));
        times[start] = 0;

        while(!queue.isEmpty()) {
            Edge cur = queue.poll();
            if(times[cur.vertex] < cur.time) continue;

            for(Edge next : edges.get(cur.vertex)) {
                int nextVertex = next.vertex, nextTime = cur.time + next.time;
                if(times[nextVertex] > nextTime) {
                    times[nextVertex] = nextTime;
                    path[nextVertex] = cur.vertex; // 해당 도시 바로 이전 도시를 경로에 저장한다
                    queue.offer(new Edge(nextVertex, nextTime));
                }
            }
        }
    }

    static void dijkstra(int start, int startBlock, int endBlock) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        Arrays.fill(times, Integer.MAX_VALUE);

        queue.offer(new Edge(start, 0));
        times[start] = 0;

        while(!queue.isEmpty()) {
            Edge cur = queue.poll();
            if(times[cur.vertex] < cur.time) continue;

            for(Edge next : edges.get(cur.vertex)) {
                // 만약 현재 탐색하고 있는 경로가 막은 도로라면 이는 확인하지 않고 다른 도로들을 확인한다
                if(cur.vertex == startBlock && next.vertex == endBlock) continue;
                int nextVertex = next.vertex, nextTime = cur.time + next.time;
                if(times[nextVertex] > nextTime) {
                    times[nextVertex] = nextTime;
                    queue.offer(new Edge(nextVertex, nextTime));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int vertex, time;

        public Edge(int vertex, int time) {
            this.vertex = vertex;
            this.time = time;
        }

        @Override
        public int compareTo(Edge o) {
            return time - o.time;
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
