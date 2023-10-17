package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun19581 {
    static int nodeNum;
    static Map<Integer, List<Edge>> tree;
    static boolean[] visited;

    static int longestNode;
    static int longestDist;

    static void input() {
        Reader scanner = new Reader();

        nodeNum = scanner.nextInt();
        tree = new HashMap<>();
        for(int node = 1; node <= nodeNum; node++) {
            tree.put(node, new ArrayList<>());
        }

        for(int edge = 0; edge < nodeNum - 1; edge++) {
            int node1 = scanner.nextInt();
            int node2 = scanner.nextInt();
            int weight = scanner.nextInt();

            tree.get(node1).add(new Edge(node2, weight));
            tree.get(node2).add(new Edge(node1, weight));
        }
    }

    static void solution() {
        int nodeOfDiameter1 = getNodeOfDiameter(1);
        int nodeOfDiameter2 = getNodeOfDiameter(nodeOfDiameter1);

        int longestDist1 = getLongestDistExceptOneNode(nodeOfDiameter1, nodeOfDiameter2);
        int longestDist2 = getLongestDistExceptOneNode(nodeOfDiameter2, nodeOfDiameter1);

        System.out.println(Math.max(longestDist1, longestDist2));
    }

    static int getLongestDistExceptOneNode(int node, int exceptNode) {
        longestNode = longestDist = 0;
        visited = new boolean[nodeNum + 1];
        dfs(node, 0, exceptNode);

        return longestDist;
    }

    static int getNodeOfDiameter(int node) {
        longestNode = longestDist = 0;
        visited = new boolean[nodeNum + 1];
        dfs(node, 0, 0);

        return longestNode;
    }

    static void dfs(int node, int weight, int exceptNode) {
        if(weight > longestDist) {
            longestNode = node;
            longestDist = weight;
        }

        visited[node] = true;

        for(Edge next : tree.get(node)) {
            if(next.node == exceptNode) {
                continue;
            }

            if(!visited[next.node]) {
                dfs(next.node, weight + next.weight, exceptNode);
            }
        }
    }

    static class Edge {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
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
