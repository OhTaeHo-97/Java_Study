package src.SWExoertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Swea2814 {
    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static int nodeCount;
    static int edgeCount;
    static int maxDistance;
    static Map<Integer, List<Integer>> edges;
    static boolean[] visited;

    static void input() {
        nodeCount = scanner.nextInt();
        edgeCount = scanner.nextInt();
        visited = new boolean[nodeCount + 1];
        edges = new HashMap<>();
        for (int node = 1; node <= nodeCount; node++) {
            edges.put(node, new ArrayList<>());
        }

        for (int edge = 0; edge < edgeCount; edge++) {
            int node1 = scanner.nextInt();
            int node2 = scanner.nextInt();

            edges.get(node1).add(node2);
            edges.get(node2).add(node1);
        }

        maxDistance = 0;
    }

    static void solution() {
        for (int node = 1; node <= nodeCount; node++) {
            visited = new boolean[nodeCount + 1];
            visited[node] = true;
            backtracking(node, 1);
        }

        answer.append(maxDistance).append('\n');
    }

    static void backtracking(int node, int distance) {
        maxDistance = Math.max(maxDistance, distance);

        for (int nextNode : edges.get(node)) {
            if (!visited[nextNode]) {
                visited[nextNode] = true;
                backtracking(nextNode, distance + 1);
                visited[nextNode] = false;
            }
        }
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        for (int test = 1; test <= T; test++) {
            answer.append('#').append(test).append(' ');
            input();
            solution();
        }
        System.out.print(answer);
    }

    static class Reader {
        BufferedReader br;
        StringTokenizer st;

        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
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
