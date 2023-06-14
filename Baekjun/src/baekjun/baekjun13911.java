package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun13911 {
	static int V, E, x, y;
    static int[] mcDonaldDistance, starbucksDistance, vertexType;
    static Map<Integer, List<Edge>> edges;
    static boolean[] visited;
    static List<Integer> mcDonald, starbucks;

    static void input() {
        Reader scanner = new Reader();

        V = scanner.nextInt();
        E = scanner.nextInt();

        edges = new HashMap<>();
        mcDonaldDistance = new int[V + 1];
        starbucksDistance = new int[V + 1];
        vertexType = new int[V + 1];
        visited = new boolean[V + 1];
        for(int vertex = 1; vertex <= V; vertex++) {
            edges.put(vertex, new ArrayList<>());
            mcDonaldDistance[vertex] = Integer.MAX_VALUE;
            starbucksDistance[vertex] = Integer.MAX_VALUE;
        }

        for(int edge = 0; edge < E; edge++) {
            int vertex1 = scanner.nextInt(), vertex2 = scanner.nextInt(), weight = scanner.nextInt();
            edges.get(vertex1).add(new Edge(vertex2, weight));
            edges.get(vertex2).add(new Edge(vertex1, weight));
        }

        int mcNum = scanner.nextInt();
        x = scanner.nextInt();
        mcDonald = new ArrayList<>();

        for(int idx = 0; idx < mcNum; idx++) {
            int vertex = scanner.nextInt();
            mcDonald.add(vertex);
            vertexType[vertex] = -1;
        }

        int starNum = scanner.nextInt();
        y = scanner.nextInt();
        starbucks = new ArrayList<>();
        for(int idx = 0; idx < starNum; idx++) {
            int vertex = scanner.nextInt();
            starbucks.add(vertex);
            vertexType[vertex] = 1;
        }
    }

    static void solution() {
        dijkstra();
    }

    static void dijkstra() {
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        // 맥도날드 거리 구하기
        for(int idx = 0; idx < mcDonald.size(); idx++) {
            int mcDonaldVertex = mcDonald.get(idx);
            if(visited[mcDonaldVertex]) continue;

            visited[mcDonaldVertex] = true;
            queue.offer(new Edge(mcDonaldVertex, 0));
            mcDonaldDistance[mcDonaldVertex] = 0;

            while(!queue.isEmpty()) {
                Edge cur = queue.poll();
                int curVertex = cur.vertex, curDistance = cur.distance;

                for(int idx2 = 0; idx2 < edges.get(curVertex).size(); idx2++) {
                    int nextVertex = edges.get(curVertex).get(idx2).vertex;
                    int nextDistance = edges.get(curVertex).get(idx2).distance;

                    // 1. 목적지가 맥도날드면 그 가중치랑 curDistance랑 비교해서 짧은 것 갱신 및 nextDistance = 0, 2.X보다 큰 거리는 안됨
                    if(vertexType[nextVertex] == -1 && !visited[nextVertex]) {
                        visited[nextVertex] = true;
                        mcDonaldDistance[nextVertex] = 0;
                        queue.offer(new Edge(nextVertex, 0));
                    } else {
                        if(nextDistance + curDistance > x) continue;
                        if(mcDonaldDistance[nextVertex] < nextDistance + curDistance) continue;

                        mcDonaldDistance[nextVertex] = nextDistance + curDistance;
                        queue.offer(new Edge(nextVertex, nextDistance + curDistance));
                    }
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int vertex, distance;

        public Edge(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return distance - o.distance;
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
