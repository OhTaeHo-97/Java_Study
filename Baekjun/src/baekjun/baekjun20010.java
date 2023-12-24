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

public class baekjun20010 {
    static int villageCount;
    static int tradeRouteCount;
    static int[] parents;
    static Queue<TradeRoute> tradeRoutes;
    static Map<Integer, List<Route>> mst;

    static void input() {
        Reader scanner = new Reader();

        villageCount = scanner.nextInt();
        tradeRouteCount = scanner.nextInt();
        parents = new int[villageCount];
        tradeRoutes = new PriorityQueue<>();
        mst = new HashMap<>();
        for (int village = 0; village < villageCount; village++) {
            parents[village] = village;
            mst.put(village, new ArrayList<>());
        }

        for (int count = 0; count < tradeRouteCount; count++) {
            int village1 = scanner.nextInt();
            int village2 = scanner.nextInt();
            int cost = scanner.nextInt();

            tradeRoutes.offer(new TradeRoute(village1, village2, cost));
        }
    }

    static void solution() {
        int totalCost = kruskal();
        int[] costs = new int[villageCount];
        int maxVillage = findMostFarVillage(0, costs);
        maxVillage = findMostFarVillage(maxVillage, costs);

        String answer = totalCost + "\n" + costs[maxVillage];
        System.out.println(answer);
    }

    static int findMostFarVillage(int village, int[] costs) {
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[village] = 0;

        dfs(village, 0, costs);

        int maxVillage = 0;
        int maxCost = Integer.MIN_VALUE;
        for (int idx = 0; idx < villageCount; idx++) {
            if (costs[idx] > maxCost) {
                maxCost = costs[idx];
                maxVillage = idx;
            }
        }

        return maxVillage;
    }

    static void dfs(int village, int cost, int[] costs) {
        for (Route route : mst.get(village)) {
            if (costs[route.village] > cost + route.cost) {
                costs[route.village] = cost + route.cost;
                dfs(route.village, cost + route.cost, costs);
            }
        }
    }

    static int kruskal() {
        int totalCost = 0;
        List<TradeRoute> selected = new ArrayList<>();

        while (selected.size() < villageCount - 1) {
            TradeRoute cur = tradeRoutes.poll();

            if (!isSameParents(cur.startVillage, cur.endVillage)) {
                union(cur.startVillage, cur.endVillage);
                totalCost += cur.cost;
                selected.add(cur);
                mst.get(cur.startVillage).add(new Route(cur.endVillage, cur.cost));
                mst.get(cur.endVillage).add(new Route(cur.startVillage, cur.cost));
            }
        }

        return totalCost;
    }

    static int findParent(int village) {
        if (village == parents[village]) {
            return village;
        }
        return parents[village] = findParent(parents[village]);
    }

    static void union(int village1, int village2) {
        int parent1 = findParent(village1);
        int parent2 = findParent(village2);

        if (parent1 != parent2) {
            if (parent1 < parent2) {
                parents[parent2] = parent1;
                return;
            }
            parents[parent1] = parent2;
        }
    }

    static boolean isSameParents(int village1, int village2) {
        int parent1 = findParent(village1);
        int parent2 = findParent(village2);

        return parent1 == parent2;
    }

    static class Route {
        int village;
        int cost;

        public Route(int village, int cost) {
            this.village = village;
            this.cost = cost;
        }
    }

    static class TradeRoute implements Comparable<TradeRoute> {
        int startVillage;
        int endVillage;
        int cost;

        public TradeRoute(int startVillage, int endVillage, int cost) {
            this.startVillage = startVillage;
            this.endVillage = endVillage;
            this.cost = cost;
        }

        @Override
        public int compareTo(TradeRoute o) {
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
