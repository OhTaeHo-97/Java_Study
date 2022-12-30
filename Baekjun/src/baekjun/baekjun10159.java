package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun10159 {
	static int N, M;
    static HashMap<Integer, ArrayList<Integer>> map;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new HashMap<>();
        for(int stuff = 1; stuff <= N; stuff++) map.put(stuff, new ArrayList<>());
        for(int comparison = 0; comparison < M; comparison++) {
            int heavy = scanner.nextInt(), light = scanner.nextInt();
            map.get(light).add(heavy);
        }
    }

    static void solution() {
        int[][] distances = new int[N + 1][N + 1];
        for(int stuff = 1; stuff <= N; stuff++) {
            Arrays.fill(distances[stuff], Integer.MAX_VALUE);
            dijkstra(stuff, distances[stuff]);
        }
        int[] nonComparable = new int[N + 1];
        for(int stuff1 = 1; stuff1 <= N; stuff1++) {
            for(int stuff2 = 1; stuff2 <= N; stuff2++) {
                if(stuff1 == stuff2) continue;
                if(distances[stuff1][stuff2] == Integer.MAX_VALUE && distances[stuff2][stuff1] == Integer.MAX_VALUE)
                    nonComparable[stuff1]++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int stuff = 1; stuff <= N; stuff++) sb.append(nonComparable[stuff]).append('\n');
        System.out.println(sb);
    }

    static void dijkstra(int start, int[] distance) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        distance[start] = 0;
        queue.offer(new Edge(start, 0));
        while(!queue.isEmpty()) {
            Edge cur = queue.poll();
            if(distance[cur.stuff] < cur.distance) continue;
            for(int stuff : map.get(cur.stuff)) {
                if(distance[stuff] > distance[cur.stuff] + 1) {
                    distance[stuff] = distance[cur.stuff] + 1;
                    queue.offer(new Edge(stuff, distance[stuff]));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int stuff, distance;
        public Edge(int stuff, int distance) {
            this.stuff = stuff;
            this.distance = distance;
        }
        @Override
        public int compareTo(Edge e) {
            return this.distance - e.distance;
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
