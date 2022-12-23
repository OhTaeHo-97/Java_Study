package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjun2533 {
	static int N;
    static HashMap<Integer, LinkedList<Integer>> tree;
    static boolean[] visited;
    static int[][] dp;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        tree = new HashMap<>();
        for(int node = 1; node <= N; node++) tree.put(node, new LinkedList<>());
        for(int edge = 0; edge < N - 1; edge++) {
            int node1 = scanner.nextInt(), node2 = scanner.nextInt();
            tree.get(node1).add(node2);
            tree.get(node2).add(node1);
        }
    }

    static void solution() {
        visited = new boolean[N + 1];
        dp = new int[N + 1][2];
        findEarlyAdaptor(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void findEarlyAdaptor(int node) {
        visited[node] = true;
        dp[node][0] = 0;
        dp[node][1] = 1;

        for(int n : tree.get(node)) {
            if(!visited[n]) {
                findEarlyAdaptor(n);
                dp[node][0] += dp[n][1];
                dp[node][1] += Math.min(dp[n][0], dp[n][1]);
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
                } catch(IOException e) {
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
