package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun9370 {
	static Reader scanner = new Reader();
    static StringBuilder sb = new StringBuilder();

    static int n, m, t, s, g, h;
    static int[] destinations;
    static HashMap<Integer, ArrayList<Edge>> map;
    static int pastEdgeWeight;

    static void input() {
        n = scanner.nextInt(); // 교차로 개수
        m = scanner.nextInt(); // 도로 개수
        t = scanner.nextInt(); // 목적지 후보 개수
        destinations = new int[t];

        s = scanner.nextInt(); // 출발지
        g = scanner.nextInt(); // 예술가들이 지난 교차로 사이 도로의 교차로1
        h = scanner.nextInt(); // 예술가들이 지난 교차로 사이 도로의 교차로2

        map = new HashMap<>();

        for(int edge = 0; edge < m; edge++) {
            int a = scanner.nextInt(), b = scanner.nextInt(), d = scanner.nextInt();

            if(!map.containsKey(a))
                map.put(a, new ArrayList<>());
            if(!map.containsKey(b))
                map.put(b, new ArrayList<>());

            map.get(a).add(new Edge(b, d));
            map.get(b).add(new Edge(a, d));

            if((a == g && b == h) || (a == h && b == g))
                pastEdgeWeight = d;
        }

        for(int destination = 0; destination < t; destination++)
            destinations[destination] = scanner.nextInt();
    }

    static void solution() {
        int[] startDist = dijkstra(s);
        int[] inter1Dist = dijkstra(g);
        int[] inter2Dist = dijkstra(h);

        ArrayList<Integer> answer = new ArrayList<>();

        for(int idx = 0; idx < t; idx++) {
            int destination = destinations[idx];

            int dist1 = startDist[g] + pastEdgeWeight + inter2Dist[destination];
            int dist2 = startDist[h] + pastEdgeWeight + inter1Dist[destination];
            int shortest = Math.min(dist1, dist2);

            if(startDist[destination] == shortest)
                answer.add(destination);
        }

        Collections.sort(answer);

        for(int destination : answer)
            sb.append(destination).append(' ');
        sb.append('\n');
    }

    static int[] dijkstra(int start) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        queue.offer(new Edge(start, 0));
        dist[start] = 0;

        while(!queue.isEmpty()) {
            Edge cur = queue.poll();

            if(dist[cur.cityNum] < cur.weight)
                continue;

            for(Edge e : map.get(cur.cityNum)) {
                int weight = e.weight + dist[cur.cityNum];

                if(dist[e.cityNum] > weight) {
                    dist[e.cityNum] = weight;
                    queue.offer(new Edge(e.cityNum, dist[e.cityNum]));
                }
            }
        }

        return dist;
    }

    static class Edge implements Comparable<Edge> {
        int cityNum, weight;

        public Edge(int cityNum, int weight) {
            this.cityNum = cityNum;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        while(T-- > 0) {
            input();
            solution();
        }

        System.out.print(sb);
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
