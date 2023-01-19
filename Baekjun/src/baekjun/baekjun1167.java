package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class baekjun1167 {
	static int V, answer, longestNode;
    static HashMap<Integer, ArrayList<Edge>> map;
    static void input() {
        Reader scanner = new Reader();
        V = scanner.nextInt();
        map = new HashMap<>();
        for(int node = 1; node <= V; node++) {
            int cur = scanner.nextInt();
            map.put(cur, new ArrayList<>());
            while(true) {
                int neighbor = scanner.nextInt();
                if(neighbor == -1) break;
                int weight = scanner.nextInt();
                map.get(cur).add(new Edge(neighbor, weight));
            }
        }
    }

    static void solution() {
        boolean[] visited = new boolean[V + 1];
        answer = 0;
        dfs(1, 0, visited);

        visited = new boolean[V + 1];
        dfs(longestNode, 0, visited);

        System.out.println(answer);
    }

    static void dfs(int node, int weight, boolean[] visited) {
        if(weight > answer) {
            answer = weight;
            longestNode = node;
        }

        visited[node] = true;

        for(Edge e : map.get(node)) {
            if(!visited[e.node])
                dfs(e.node, weight + e.weight, visited);
        }
    }

    static class Edge {
        int node, weight;
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
