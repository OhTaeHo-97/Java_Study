package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1939 {
	static int N, M, company1, company2;
    static HashMap<Integer, ArrayList<Edge>> bridges;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        M = scanner.nextInt();
        bridges = new HashMap<>();
        for(int island = 1; island <= N; island++) bridges.put(island, new ArrayList<>());
        for(int bridge = 0; bridge < M; bridge++) {
            int A = scanner.nextInt(), B = scanner.nextInt(), weight = scanner.nextInt();
            bridges.get(A).add(new Edge(B, weight));
            bridges.get(B).add(new Edge(A, weight));
        }
        company1 = scanner.nextInt();
        company2 = scanner.nextInt();
    }

    static void solution() {
        int max = findMax(company1, company2);
        System.out.println(max);
    }

    static int findMax(int start, int end) {
        // 이분 탐색
        int low = 1, high = 1000000000;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        int max = Integer.MIN_VALUE;
        while(low <= high) {
            int mid = (low + high) / 2;
            queue.offer(start);
            visited[start] = true;
            if(bfs(queue, mid, end, visited)) {
                max = Math.max(max, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            queue.clear();
            Arrays.fill(visited, false);
        }
        return max;
    }

    static boolean bfs(Queue<Integer> queue, int mid, int end, boolean[] visited) {
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(Edge e : bridges.get(cur)) {
                int city = e.city, weight = e.weight;
                if(weight >= mid) {
                    if(cur == end) return true;
                    if(!visited[city]) {
                        visited[city] = true;
                        queue.offer(city);
                    }
                }
            }
        }
        return false;
    }
    
    static class Edge {
        int city, weight;
        public Edge(int city, int weight) {
            this.city = city;
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
