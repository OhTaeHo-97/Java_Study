package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun1774 {
	static int N, M;
    static double answer;
    static int[][] locations, connected;
    static int[] parents;
    static PriorityQueue<Edge> edges;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        M = scanner.nextInt();
        locations = new int[N + 1][2];
        connected = new int[M + 1][2];
        for(int idx = 1; idx <= N; idx++) {
            int x = scanner.nextInt(), y = scanner.nextInt();
            locations[idx][0] = x;
            locations[idx][1] = y;
        }
        for(int idx = 0; idx < M; idx++) {
            int first = scanner.nextInt(), second = scanner.nextInt();
            connected[idx][0] = first;
            connected[idx][1] = second;
        }
    }

    static void solution() {
        answer = 0;
        parents = new int[N + 1];
        for(int loc = 1; loc <= N; loc++) parents[loc] = loc;

        int count = 0;
        for(int idx = 0; idx < M; idx++) {
            if(!isSameParents(connected[idx][0], connected[idx][1])) {
                union(connected[idx][0], connected[idx][1]);
                count++;
            }
        }

        makeEdge();
        while(count < N - 1) {
            Edge edge = edges.poll();
            int loc1 = edge.start, loc2 = edge.end;
            if(!isSameParents(loc1, loc2)) {
                union(loc1, loc2);
                count++;
                answer += edge.distance;
            }
        }

        System.out.println(String.format("%.2f", answer));
    }

    static void makeEdge() {
        edges = new PriorityQueue<>();
        for(int loc1 = 1; loc1 <= N; loc1++) {
            for(int loc2 = loc1 + 1; loc2 <= N; loc2++) {
                if(loc1 == loc2) continue;
                edges.offer(new Edge(loc1, loc2, getDistance(locations[loc1], locations[loc2])));
            }
        }
    }

    static double getDistance(int[] loc1, int[] loc2) {
        double distance = Math.sqrt(Math.pow(loc1[0] - loc2[0], 2) + Math.pow(loc1[1] - loc2[1], 2));
        return distance;
    }

    static int findParents(int loc) {
        if(loc == parents[loc]) return loc;
        return parents[loc] = findParents(parents[loc]);
    }

    static void union(int loc1, int loc2) {
        int parent1 = findParents(loc1), parent2 = findParents(loc2);
        if(parent1 != parent2) {
            if(parent1 < parent2) parents[parent2] = parent1;
            else parents[parent1] = parent2;
        }
    }

    static boolean isSameParents(int loc1, int loc2) {
        int parent1 = findParents(loc1), parent2 = findParents(loc2);
        if(parent1 == parent2) return true;
        return false;
    }

    static class Edge implements Comparable<Edge> {
        int start, end;
        double distance;
        public Edge(int start, int end, double distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge e) {
            if(this.distance < e.distance) return -1;
            else if(this.distance > e.distance) return 1;
            else return 0;
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
