package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1884 {
    static int preparedCost;
    static int cityCount;
    static int roadCount;
    static Map<Integer, List<Road>> roads;

    static void input() {
        Reader scanner = new Reader();

        preparedCost = scanner.nextInt();
        cityCount = scanner.nextInt();
        roadCount = scanner.nextInt();
        roads = new HashMap<>();
        for (int city = 1; city <= cityCount; city++) {
            roads.put(city, new ArrayList<>());
        }

        for (int road = 0; road < roadCount; road++) {
            int startCity = scanner.nextInt();
            int endCity = scanner.nextInt();
            int distance = scanner.nextInt();
            int cost = scanner.nextInt();

            roads.get(startCity).add(new Road(endCity, distance, cost));
        }
    }

    static void solution() {
        int[][] distances = dijkstra(1);
        int minDistance = Integer.MAX_VALUE;
        for (int cost = 0; cost <= preparedCost; cost++) {
            minDistance = Math.min(minDistance, distances[cityCount][cost]);
        }

        if (minDistance == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(minDistance);
    }

    static int[][] dijkstra(int startCity) {
        Queue<Road> queue = new PriorityQueue<>();
        int[][] distances = new int[cityCount + 1][preparedCost + 1];
        for (int row = 1; row <= cityCount; row++) {
            Arrays.fill(distances[row], Integer.MAX_VALUE);
        }

        queue.offer(new Road(startCity, 0, 0));
        distances[startCity][0] = 0;

        while (!queue.isEmpty()) {
            Road cur = queue.poll();
            if (distances[cur.city][cur.cost] < cur.distance) {
                continue;
            }

            for (Road next : roads.get(cur.city)) {
                int nextCity = next.city;
                int nextDistance = next.distance + cur.distance;
                int nextCost = next.cost + cur.cost;

                if (nextCost > preparedCost) {
                    continue;
                }
                if (distances[nextCity][nextCost] > nextDistance) {
                    distances[nextCity][nextCost] = nextDistance;
                    queue.offer(new Road(nextCity, nextDistance, nextCost));
                }
            }
        }

        return distances;
    }

    static class Road implements Comparable<Road> {
        int city;
        int distance;
        int cost;

        public Road(int city, int distance, int cost) {
            this.city = city;
            this.distance = distance;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road o) {
            return cost - o.cost;
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
