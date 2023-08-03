package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun1800 {
    static int N, P, K, max;
    static Map<Integer, List<Edge>> edges;
    static int[] costs;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        P = scanner.nextInt();
        K = scanner.nextInt();
        edges = new HashMap<>();
        costs = new int[N + 1];
        for(int computer = 1; computer <= N; computer++)
            edges.put(computer, new ArrayList<>());

        for(int edge = 0; edge < P; edge++) {
            int computer1 = scanner.nextInt(), computer2 = scanner.nextInt(), cost = scanner.nextInt();
            edges.get(computer1).add(new Edge(computer2, cost));
            edges.get(computer2).add(new Edge(computer1, cost));
            max = Math.max(max, cost);
        }
    }

    static void solution() {
        // (mid)원 이하의 비용으로 1번 컴퓨터에서 N번 컴퓨터를 연결할 수 있을지 확인하는 결정 문제로 변경해본다
        // (mid)원 이하의 비용 -> (mid)원보다 비싼 간선을 포함하지 않음, 포함하더라도 K개 이하로 포함해야 함(K개만큼 공짜로 연결할 수 있으므로)
        // 그래서 다익스트라를 통해 1번 컴퓨터에서 N번 컴퓨터로 연결할 때
        // (mid)원보다 비싼 간선의 개수를 구하고 그 개수가 K보다 작거나 같은 경우의 mid원 중 가장 적은 비용을 찾는다
        int left = 0, right = max, answer = Integer.MAX_VALUE;
        while(left <= right) {
            int mid = (left + right) / 2;

            if(dijkstra(1, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    static boolean dijkstra(int start, int cost) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        Arrays.fill(costs, Integer.MAX_VALUE);

        costs[start] = 0;
        queue.offer(new Edge(start, 0));

        while(!queue.isEmpty()) {
            Edge cur = queue.poll();
            if(costs[cur.vertex] < cur.cost) continue;

            for(Edge next : edges.get(cur.vertex)) {
                int nextVertex = next.vertex;
                int nextCost = cur.cost;

                // (mid)원보다 비싼 간선에 대해서만 비용을 1 증가시켜
                // (mid)원보다 비싼 간선의 개수를 구한다
                if(next.cost > cost) nextCost++;

                if(costs[nextVertex] > nextCost) {
                    costs[nextVertex] = nextCost;
                    queue.offer(new Edge(nextVertex, nextCost));
                }
            }
        }

        // (mid)원보다 비싼 간선의 개수가 K보다 작거나 같을 때에는 (mid)원으로 인터넷 설치가 가능하므로
        // true를 반환
        return costs[N] <= K;
    }

    static class Edge implements Comparable<Edge> {
        int vertex;
        int cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            if(cost < o.cost) return -1;
            else if(cost > o.cost) return 1;
            return 0;
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
