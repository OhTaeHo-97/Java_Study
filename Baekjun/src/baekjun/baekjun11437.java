package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjun11437 {
	static StringBuilder sb = new StringBuilder();
    static int N, M;
    static HashMap<Integer, LinkedList<Integer>> tree;
    static int[][] nodes;
    static int[] parents, depth;

    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        tree = new HashMap<>();
        for (int node = 1; node <= N; node++) tree.put(node, new LinkedList<>());
        for (int edge = 0; edge < N - 1; edge++) {
            int node1 = scanner.nextInt(), node2 = scanner.nextInt();
            if (node1 == 1) {
                tree.get(node1).add(node2);
            } else if (node2 == 1) {
                tree.get(node2).add(node1);
            } else {
                tree.get(node1).add(node2);
                tree.get(node2).add(node1);
            }
        }
        M = scanner.nextInt();
        nodes = new int[M][2];
        for (int idx = 0; idx < M; idx++) {
            nodes[idx][0] = scanner.nextInt();
            nodes[idx][1] = scanner.nextInt();
        }
    }

    static void solution() {
        parents = new int[N + 1];
        for (int node = 1; node <= N; node++) parents[node] = node;
        depth = new int[N + 1];
        makeTree(1, -1);
        for (int idx = 0; idx < M; idx++)
            sb.append(findLCA(nodes[idx][0], nodes[idx][1])).append('\n');
        System.out.println(sb);
    }

    static void makeTree(int node, int prev) {
        if (prev != -1) {
            parents[node] = prev;
            depth[node] = depth[prev] + 1;
        }
        if (tree.get(node).size() == 1 && tree.get(node).get(0) == prev) {
            tree.get(node).remove(0);
            return;
        }
        for (int idx = 0; idx < tree.get(node).size(); idx++) {
            if (prev == tree.get(node).get(idx)) {
                tree.get(node).remove(idx);
                idx--;
                continue;
            }
            makeTree(tree.get(node).get(idx), node);
        }
    }

    static int findLCA(int node1, int node2) {
        int deeperNode = depth[node1] > depth[node2] ? node1 : node2;
        int shallowerNode = depth[node1] > depth[node2] ? node2 : node1;
        int deepDepth = depth[deeperNode], shallowDepth = depth[shallowerNode];
        if (deepDepth != shallowDepth) {
            while (deepDepth != shallowDepth) {
                deepDepth--;
                deeperNode = parents[deeperNode];
            }
        }
        while (deeperNode != shallowerNode) {
            deeperNode = parents[deeperNode];
            shallowerNode = parents[shallowerNode];
        }
        return deeperNode;
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
