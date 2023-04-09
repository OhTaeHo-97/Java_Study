package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class baekjun16964 {
	static int N, index;
    static boolean flag;
    static HashMap<Integer, ArrayList<Integer>> map;
    static int[] dfsOrder;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        map = new HashMap<>();
        dfsOrder = new int[N + 1];
        for(int node = 1; node <= N; node++) map.put(node, new ArrayList<>());

        for(int edge = 1; edge < N; edge++) {
            int node1 = scanner.nextInt(), node2 = scanner.nextInt();

            map.get(node1).add(node2);
            map.get(node2).add(node1);
        }

        for(int order = 0; order < N; order++)
            dfsOrder[order] = scanner.nextInt();
    }

    static void solution() {
        if(dfsOrder[0] != 1) {
            System.out.println(0);
            return;
        }

        index = 1;
        flag = true;

        dfs(1, new boolean[N + 1]);

        if(flag) System.out.println(1);
        else System.out.println(0);
    }

    static void dfs(int node, boolean[] visited) {
        if(visited[node]) return;
        visited[node] = true;

        HashSet<Integer> set = new HashSet<>();
        for(int next : map.get(node)) {
            if(!visited[next]) set.add(next);
        }

        if(set.size() == 0) return;

        if(set.contains(dfsOrder[index])) dfs(dfsOrder[index++], visited);
        else flag = false;
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
