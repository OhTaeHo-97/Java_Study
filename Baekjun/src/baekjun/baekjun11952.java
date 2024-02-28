package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class baekjun11952 {
    static int cityCount;
    static int roadCount;
    static int zombieCityCount;
    static int dangerousCityRange;
    static int safeCityPrice;
    static int dangerousCityPrice;
    static Set<Integer> zombieCities;
    static Set<Integer> dangerousCities;
    static Map<Integer, List<Integer>> roads;

    static void input() {
        Reader scanner = new Reader();

        cityCount = scanner.nextInt();
        roadCount = scanner.nextInt();
        zombieCityCount = scanner.nextInt();
        dangerousCityRange = scanner.nextInt();
        safeCityPrice = scanner.nextInt();
        dangerousCityPrice = scanner.nextInt();

        zombieCities = new HashSet<>();
        for (int zombieCity = 0; zombieCity < zombieCityCount; zombieCity++) {
            zombieCities.add(scanner.nextInt());
        }

        roads = new HashMap<>();
        for (int city = 1; city <= cityCount; city++) {
            roads.put(city, new ArrayList<>());
        }
        for (int road = 0; road < roadCount; road++) {
            int city1 = scanner.nextInt();
            int city2 = scanner.nextInt();

            roads.get(city1).add(city2);
            roads.get(city2).add(city1);
        }
    }

    static void solution() {
        findDangerousCities();
        System.out.println(dijkstra(1));
    }

    static long dijkstra(int startCity) {
        Queue<Road> queue = new PriorityQueue<>();
        long[] prices = new long[cityCount + 1];
        Arrays.fill(prices, Long.MAX_VALUE);

        queue.offer(new Road(startCity, 0, 0));
        prices[startCity] = 0;

        while (!queue.isEmpty()) {
            Road cur = queue.poll();

            if (prices[cur.cityNumber] < cur.price) {
                continue;
            }

            for (int next : roads.get(cur.cityNumber)) {
                if (zombieCities.contains(next)) {
                    continue;
                }

                int nextCity = next;
                long nextPrice = cur.price;
                if (nextCity != cityCount) {
                    if (dangerousCities.contains(next)) {
                        nextPrice += dangerousCityPrice;
                    } else {
                        nextPrice += safeCityPrice;
                    }
                }

                if (prices[nextCity] > nextPrice) {
                    prices[nextCity] = nextPrice;
                    if (nextCity != cityCount) {
                        queue.offer(new Road(nextCity, cur.moveCount + 1, nextPrice));
                    }
                }
            }
        }

        return prices[cityCount];
    }

    static void findDangerousCities() {
        Queue<Road> queue = new LinkedList<>();
        boolean[] visited = new boolean[cityCount + 1];
        dangerousCities = new HashSet<>();

        for (int zombieCity : zombieCities) {
            queue.offer(new Road(zombieCity, 0, 0));
            visited[zombieCity] = true;
        }

        while (!queue.isEmpty()) {
            Road cur = queue.poll();

            for (int nextCity : roads.get(cur.cityNumber)) {
                if (!visited[nextCity] && cur.moveCount + 1 <= dangerousCityRange) {
                    visited[nextCity] = true;
                    dangerousCities.add(nextCity);
                    queue.offer(new Road(nextCity, cur.moveCount + 1, 0));
                }
            }
        }
    }

    static class Road implements Comparable<Road> {
        int cityNumber;
        int moveCount;
        long price;

        public Road(int cityNumber, int moveCount, long price) {
            this.cityNumber = cityNumber;
            this.moveCount = moveCount;
            this.price = price;
        }

        @Override
        public int compareTo(Road o) {
            if (price < o.price) {
                return -1;
            } else if (price > o.price) {
                return 1;
            }
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
