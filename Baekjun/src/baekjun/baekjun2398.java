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

public class baekjun2398 {
    static int switchCount;
    static int linkCount;
    static int minCost;
    static int linkCountInMinCostSituation;
    static int[] targets;
    static int[][] costs;
    static int[][] paths;
    static Map<Integer, List<Switch>> links;

    static void input() {
        Reader scanner = new Reader();

        switchCount = scanner.nextInt();
        linkCount = scanner.nextInt();
        links = new HashMap<>();
        for (int switchNumber = 1; switchNumber <= switchCount; switchNumber++) {
            links.put(switchNumber, new ArrayList<>());
        }

        for (int link = 1; link <= linkCount; link++) {
            int switch1 = scanner.nextInt();
            int switch2 = scanner.nextInt();
            int cost = scanner.nextInt();

            links.get(switch1).add(new Switch(switch2, cost));
            links.get(switch2).add(new Switch(switch1, cost));
        }

        targets = new int[3];
        costs = new int[switchCount + 1][switchCount + 1];
        paths = new int[switchCount + 1][switchCount + 1];
        for (int idx = 0; idx < targets.length; idx++) {
            targets[idx] = scanner.nextInt();
            Arrays.fill(costs[targets[idx]], Integer.MAX_VALUE);
            for (int switchNumber = 1; switchNumber <= switchCount; switchNumber++) {
                paths[targets[idx]][switchNumber] = switchNumber;
            }
        }
    }

    static void solution() {
        for (int idx = 0; idx < targets.length; idx++) {
            dijkstra(targets[idx], costs[targets[idx]], paths[targets[idx]]);
        }

        int minBasisSwitch = findBasisSwitchAndMinTotalCost();
        String path = findPath(minBasisSwitch);

        StringBuilder answer = new StringBuilder();
        answer.append(minCost).append(' ').append(linkCountInMinCostSituation).append('\n');
        answer.append(path);
        System.out.print(answer);
    }

    static int findBasisSwitchAndMinTotalCost() {
        minCost = Integer.MAX_VALUE;
        int minBasisSwitch = 0;
        for (int basisSwitch = 1; basisSwitch <= switchCount; basisSwitch++) {
            int cost = 0;
            for (int idx = 0; idx < targets.length; idx++) {
                cost += costs[targets[idx]][basisSwitch];
            }
            if (minCost > cost) {
                minBasisSwitch = basisSwitch;
                minCost = cost;
            }
        }

        return minBasisSwitch;
    }

    static String findPath(int basisSwitch) {
        StringBuilder path = new StringBuilder();
        for (int idx = 0; idx < targets.length; idx++) {
            int ah = basisSwitch;
            while (ah != targets[idx]) {
                path.append(ah).append(' ').append(paths[targets[idx]][ah]).append('\n');
                ah = paths[targets[idx]][ah];
                linkCountInMinCostSituation++;
            }
        }

        return path.toString();
    }

    static void dijkstra(int startSwitch, int[] cost, int[] path) {
        Queue<Switch> linkQueue = new PriorityQueue<>();

        linkQueue.offer(new Switch(startSwitch, 0));
        cost[startSwitch] = 0;

        while (!linkQueue.isEmpty()) {
            Switch cur = linkQueue.poll();
            if (cost[cur.switchNumber] < cur.cost) {
                continue;
            }

            for (Switch link : links.get(cur.switchNumber)) {
                int nextSwitch = link.switchNumber;
                int nextCost = cur.cost + link.cost;

                if (cost[nextSwitch] > nextCost) {
                    cost[nextSwitch] = nextCost;
                    path[nextSwitch] = cur.switchNumber;
                    linkQueue.offer(new Switch(nextSwitch, nextCost));
                }
            }
        }
    }

    static class Switch implements Comparable<Switch> {
        int switchNumber;
        int cost;

        public Switch(int switchNumber, int cost) {
            this.switchNumber = switchNumber;
            this.cost = cost;
        }

        @Override
        public int compareTo(Switch o) {
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
