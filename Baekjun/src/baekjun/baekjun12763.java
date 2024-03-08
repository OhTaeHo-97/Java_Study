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

public class baekjun12763 {
    static int buildingCount;
    static int deadline;
    static int money;
    static int roadCount;
    static Map<Integer, List<Road>> roads;

    static void input() {
        Reader scanner = new Reader();

        buildingCount = scanner.nextInt();
        deadline = scanner.nextInt();
        money = scanner.nextInt();
        roadCount = scanner.nextInt();
        roads = new HashMap<>();
        for (int building = 1; building <= buildingCount; building++) {
            roads.put(building, new ArrayList<>());
        }

        for (int road = 1; road <= roadCount; road++) {
            int building1 = scanner.nextInt();
            int building2 = scanner.nextInt();
            int time = scanner.nextInt();
            int fee = scanner.nextInt();

            roads.get(building1).add(new Road(building2, time, fee));
            roads.get(building2).add(new Road(building1, time, fee));
        }
    }

    static void solution() {
        int minFee = dijkstra(1, buildingCount);
        System.out.println(minFee == Integer.MAX_VALUE ? -1 : minFee);
    }

    static int dijkstra(int startBuilding, int endBuilding) {
        Queue<Road> queue = new PriorityQueue<>();
        int[][] fee = new int[buildingCount + 1][deadline + 1];
        for (int building = 1; building <= buildingCount; building++) {
            if (building == startBuilding) {
                continue;
            }
            Arrays.fill(fee[building], Integer.MAX_VALUE);
        }

        queue.offer(new Road(startBuilding, 0, 0));
        fee[startBuilding][0] = 0;

        while (!queue.isEmpty()) {
            Road cur = queue.poll();
            if (fee[cur.building][cur.time] < cur.taxiFee) {
                continue;
            }

            for (Road next : roads.get(cur.building)) {
                int nextBuilding = next.building;
                int nextFee = cur.taxiFee + next.taxiFee;
                int nextTime = cur.time + next.time;

                if (nextTime > deadline || nextFee > money) {
                    continue;
                }
                if (fee[nextBuilding][nextTime] > nextFee) {
                    fee[nextBuilding][nextTime] = nextFee;
                    queue.offer(new Road(nextBuilding, nextTime, nextFee));
                }
            }
        }

        return Arrays.stream(fee[endBuilding]).filter(taxiFee -> taxiFee != 0).min().getAsInt();
    }

    static class Road implements Comparable<Road> {
        int building;
        int time;
        int taxiFee;

        public Road(int building, int time, int taxiFee) {
            this.building = building;
            this.time = time;
            this.taxiFee = taxiFee;
        }

        @Override
        public int compareTo(Road o) {
            if (taxiFee != o.taxiFee) {
                return taxiFee - o.taxiFee;
            }
            return time - o.time;
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
