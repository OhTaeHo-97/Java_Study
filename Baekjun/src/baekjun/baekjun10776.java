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

public class baekjun10776 {
    static int depth;
    static int islandCount;
    static int roadCount;
    static int startIsland;
    static int endIsland;
    static Map<Integer, List<Road>> roads;

    static void input() {
        Reader scanner = new Reader();

        depth = scanner.nextInt();
        islandCount = scanner.nextInt();
        roadCount = scanner.nextInt();
        roads = new HashMap<>();
        for (int island = 1; island <= islandCount; island++) {
            roads.put(island, new ArrayList<>());
        }

        for (int road = 0; road < roadCount; road++) {
            int island1 = scanner.nextInt();
            int island2 = scanner.nextInt();
            int time = scanner.nextInt();
            int depth = scanner.nextInt();

            roads.get(island1).add(new Road(island2, time, depth));
            roads.get(island2).add(new Road(island1, time, depth));
        }

        startIsland = scanner.nextInt();
        endIsland = scanner.nextInt();
    }

    static void solution() {
        System.out.println(dijkstra(startIsland, endIsland));
    }

    static int dijkstra(int startIsland, int endIsland) {
        Queue<Road> queue = new PriorityQueue<>();
        int[][] times = new int[islandCount + 1][depth + 1];
        for (int island = 1; island <= islandCount; island++) {
            Arrays.fill(times[island], Integer.MAX_VALUE);
        }

        queue.offer(new Road(startIsland, 0, depth));
        times[startIsland][depth] = 0;

        while (!queue.isEmpty()) {
            Road cur = queue.poll();
            if (times[cur.islandNumber][cur.depth] < cur.time) {
                continue;
            }

            for (Road next : roads.get(cur.islandNumber)) {
                int nextIsland = next.islandNumber;
                int nextTime = cur.time + next.time;
                int depth = cur.depth - next.depth;

                if (depth <= 0) {
                    continue;
                }
                if (times[nextIsland][depth] > nextTime) {
                    times[nextIsland][depth] = nextTime;
                    queue.offer(new Road(nextIsland, nextTime, depth));
                }
            }
        }

        int minTime = Arrays.stream(times[endIsland]).min().getAsInt();
        return minTime == Integer.MAX_VALUE ? -1 : minTime;
    }

    static class Road implements Comparable<Road> {
        int islandNumber;
        int time;
        int depth;

        public Road(int islandNumber, int time, int depth) {
            this.islandNumber = islandNumber;
            this.time = time;
            this.depth = depth;
        }

        @Override
        public int compareTo(Road r) {
            if (time != r.time) {
                return time - r.time;
            }
            return r.depth - depth;
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
