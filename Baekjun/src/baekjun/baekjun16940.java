package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun16940 {
	static int N;
    static HashMap<Integer, ArrayList<Integer>> map;
    static int[] bfsOrder;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        map = new HashMap<>();
        bfsOrder = new int[N];
        for(int node = 1; node <= N; node++) map.put(node, new ArrayList<>());

        for(int edge = 1; edge < N; edge++) {
            int node1 = scanner.nextInt(), node2 = scanner.nextInt();

            map.get(node1).add(node2);
            map.get(node2).add(node1);
        }

        for(int node = 0; node < N; node++) bfsOrder[node] = scanner.nextInt();
    }

    static void solution() {
        System.out.println(bfs(bfsOrder[0]) ? 1 : 0);
    }

    static boolean bfs(int startNode) {
        if(startNode != 1) return false;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        HashSet<Integer> remain = new HashSet<>();

        queue.offer(startNode);
        visited[startNode] = true;

        int index = 1;
        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int next : map.get(cur)) {
                if(!visited[next]) {
                    visited[next] = true;
                    remain.add(next);
                }
            }

            int size = remain.size();
            for(int idx = 0; idx < size; idx++) {
                if(remain.remove(bfsOrder[index])) queue.offer(bfsOrder[index++]);
                else return false;
            }
        }

        return true;
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
