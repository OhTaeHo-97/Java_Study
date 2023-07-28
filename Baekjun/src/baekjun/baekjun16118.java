package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun16118 {
    static int N, M;
    static Map<Integer, List<Edge>> edges;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        edges = new HashMap<>();
        for(int vertex = 1; vertex <= N; vertex++)
            edges.put(vertex, new ArrayList<>());

        for(int edge = 0; edge < M; edge++) {
            int vertex1 = scanner.nextInt(), vertex2 = scanner.nextInt(), distance = scanner.nextInt() * 2;
            edges.get(vertex1).add(new Edge(vertex2, distance));
            edges.get(vertex2).add(new Edge(vertex1, distance));
        }
    }

    static void solution() {
        long[] rabbit = dijkstra(1);
        long[][] fox = dijkstra2(1);

        int answer = 0;
        for(int vertex = 2; vertex < rabbit.length; vertex++) {
            if(rabbit[vertex] < Math.min(fox[0][vertex], fox[1][vertex])) answer++;
        }

        System.out.println(answer);
    }

    static long[][] dijkstra2(int start) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        long[][] distance = new long[2][N + 1];
        for(int idx = 0; idx < distance.length; idx++)
            Arrays.fill(distance[idx], Long.MAX_VALUE);

        distance[0][start] = 0;
        queue.offer(new Edge(start, 0, 0));

        while(!queue.isEmpty()) {
            Edge cur = queue.poll();
            if(distance[cur.state][cur.vertex] < cur.distance) continue;

            for(Edge next : edges.get(cur.vertex)) {
                int nextVertex = next.vertex;
                int nextState = 1 - cur.state;
                long nextDist = cur.distance + (cur.state == 0 ? next.distance / 2 : next.distance * 2);

                if(distance[nextState][nextVertex] > nextDist) {
                    distance[nextState][nextVertex] = nextDist;
                    queue.offer(new Edge(nextVertex, nextDist, nextState));
                }
            }
        }

        return distance;
    }

    static long[] dijkstra(int start) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        long[] distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);

        distance[start] = 0;
        queue.offer(new Edge(start, 0));

        while(!queue.isEmpty()) {
            Edge cur = queue.poll();
            if(distance[cur.vertex] < cur.distance) continue;

            for(Edge next : edges.get(cur.vertex)) {
                int nextVertex = next.vertex;
                long nextDist = cur.distance + next.distance;

                if(distance[nextVertex] > nextDist) {
                    distance[nextVertex] = nextDist;
                    queue.offer(new Edge(nextVertex, nextDist));
                }
            }
        }

        return distance;
    }

    static class Edge implements Comparable<Edge> {
        int vertex, state;
        long distance;

        public Edge(int vertex, long distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public Edge(int vertex, long distance, int state) {
            this.vertex = vertex;
            this.distance = distance;
            this.state = state;
        }

        @Override
        public int compareTo(Edge o) {
            if(distance < o.distance) return -1;
            else if(distance > o.distance) return 1;
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
