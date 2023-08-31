package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun17835 {
    static int N;
    static int M;
    static int K;
    static int maxCity = 0;
    static long maxDist = Long.MIN_VALUE;
    static Map<Integer, List<Edge>> edges;
    static int[] interviewRooms;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
        edges = new HashMap<>();
        interviewRooms = new int[K];

        for(int city = 1; city <= N; city++)
            edges.put(city, new ArrayList<>());

        for(int edge = 0; edge < M; edge++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int distance = scanner.nextInt();

            edges.get(end).add(new Edge(start, distance));
        }

        for(int idx = 0; idx < K; idx++)
            interviewRooms[idx] = scanner.nextInt();
    }

    static void solution() {
        long[] distances = dijkstra(interviewRooms);
        findMaxDistanceCity(distances);

        StringBuilder sb = new StringBuilder();
        sb.append(maxCity).append('\n').append(maxDist);
        System.out.println(sb);
    }

    static void findMaxDistanceCity(long[] distances) {
        for(int city = 1; city <= N; city++) {
            if(distances[city] > maxDist) {
                maxDist = distances[city];
                maxCity = city;
            }
        }
    }

    static long[] dijkstra(int[] start) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        long[] distances = new long[N + 1];
        Arrays.fill(distances, Long.MAX_VALUE);

        for(int startVertex : start) {
            distances[startVertex] = 0L;
            queue.offer(new Edge(startVertex, 0L));
        }

        while(!queue.isEmpty()) {
            Edge cur = queue.poll();
            if(distances[cur.vertex] < cur.distance)
                continue;

            for(Edge next : edges.get(cur.vertex)) {
                int nextVertex = next.vertex;
                long nextDist = cur.distance + next.distance;
                if(nextDist < distances[nextVertex]) {
                    distances[nextVertex] = nextDist;
                    queue.offer(new Edge(nextVertex, nextDist));
                }
            }
        }

        return distances;
    }

    static class Edge implements Comparable<Edge> {
        int vertex;
        long distance;

        public Edge(int vertex, long distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge e) {
            if(this.distance < e.distance) return -1;
            else if(this.distance > e.distance) return 1;
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
