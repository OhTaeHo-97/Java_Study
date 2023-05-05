package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun1368 {
	static int N;
    static int[] wellCosts, parents;
    static PriorityQueue<Edge> edges;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        edges = new PriorityQueue<>();
        wellCosts = new int[N + 1];
        parents = new int[N + 1];
        for(int idx = 1; idx <= N; idx++) {
            int makeCost = scanner.nextInt();

            wellCosts[idx] = makeCost;
            parents[idx] = idx;
        }

        for(int start = 1; start <= N; start++) {
            for(int end = 1; end <= N; end++) {
                int cost = scanner.nextInt();

                if(start == end) edges.add(new Edge(0, start, wellCosts[start]));
                else edges.add(new Edge(start, end, cost));
            }
        }
    }

    static void solution() {
        System.out.println(kruskal());
    }

    static int kruskal() {
        int count = 0, totalCost = 0;
        while(count < N) {
            Edge cur = edges.poll();
            int start = cur.start, end = cur.end, cost = cur.cost;

            if(!isSameParent(start, end)) {
                union(start, end);
                count++;
                totalCost += cost;
            }
        }

        return totalCost;
    }

    static int findParent(int ricePaddy) {
        if(ricePaddy == parents[ricePaddy]) return ricePaddy;
        return parents[ricePaddy] = findParent(parents[ricePaddy]);
    }

    static void union(int ricePaddy1, int ricePaddy2) {
        int parent1 = findParent(ricePaddy1), parent2 = findParent(ricePaddy2);

        if(parent1 != parent2) {
            if(parent1 < parent2) parents[parent2] = parent1;
            else parents[parent1] = parent2;
        }
    }

    static boolean isSameParent(int ricePaddy1, int ricePaddy2) {
        int parent1 = findParent(ricePaddy1), parent2 = findParent(ricePaddy2);
        return parent1 == parent2;
    }

    static class Edge implements Comparable<Edge> {
        int start, end, cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }


        @Override
        public int compareTo(Edge e) {
            return cost - e.cost;
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
