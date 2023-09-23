package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class baekjun6091 {
    static int vertexNum;
    static PriorityQueue<Edge> edges;
    static int[] parents;
    static Map<Integer, PriorityQueue<Integer>> adjacentVertex;

    static void input() {
        Reader scanner = new Reader();

        vertexNum = scanner.nextInt();
        edges = new PriorityQueue<>();
        adjacentVertex = new TreeMap<>();
        parents = new int[vertexNum + 1];
        for(int vertex = 1; vertex <= vertexNum; vertex++) {
            parents[vertex] = vertex;
            adjacentVertex.put(vertex, new PriorityQueue<>());
        }

        for(int startVertex = 1; startVertex < vertexNum; startVertex++) {
            for(int endVertex = startVertex + 1; endVertex <= vertexNum; endVertex++) {
                int distance = scanner.nextInt();
                edges.offer(new Edge(startVertex, endVertex, distance));
            }
        }
    }

    static void solution() {
        // 트리는 한 정점에서 다른 하나의 정점으로 가는 경로가 유일해야 하기 때문에
        // MST와 union-find를 이용하여 문제를 해결한다
        kruskal();
        print();
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for(int vertex : adjacentVertex.keySet()) {
            sb.append(adjacentVertex.get(vertex).size()).append(' ');
            while(!adjacentVertex.get(vertex).isEmpty()) {
                sb.append(adjacentVertex.get(vertex).poll()).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static void kruskal() {
        for(int count = 0; count < vertexNum - 1; count++) {
            Edge edge = edges.poll();
            if(isSameParents(edge.startVertex, edge.endVertex)) {
                count--;
                continue;
            }

            union(edge.startVertex, edge.endVertex);
            adjacentVertex.get(edge.startVertex).offer(edge.endVertex);
            adjacentVertex.get(edge.endVertex).offer(edge.startVertex);
        }
    }

    static int findParent(int vertex) {
        if(parents[vertex] == vertex)
            return vertex;
        return parents[vertex] = findParent(parents[vertex]);
    }

    static void union(int vertex1, int vertex2) {
        int parent1 = findParent(vertex1);
        int parent2 = findParent(vertex2);

        if(parent1 != parent2) {
            if(parent1 < parent2) parents[parent2] = parent1;
            else parents[parent1] = parent2;
        }
    }

    static boolean isSameParents(int vertex1, int vertex2) {
        int parent1 = findParent(vertex1);
        int parent2 = findParent(vertex2);

        return parent1 == parent2;
    }

    static class Edge implements Comparable<Edge> {
        int startVertex;
        int endVertex;
        int distance;

        public Edge(int startVertex, int endVertex, int distance) {
            this.startVertex = startVertex;
            this.endVertex = endVertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.distance != o.distance) {
                return this.distance - o.distance;
            } else {
                if(this.startVertex != o.startVertex) {
                    return this.startVertex - o.startVertex;
                } else {
                    return this.endVertex - o.endVertex;
                }
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
