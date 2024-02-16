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

public class baekjun2982 {
    static int intersectionCount;
    static int roadCount;
    static int startIntersection;
    static int endIntersection;
    static int startDiffTime;
    static int godoolaIntersectionCount;
    static int[] godoolaIntersections;
    static int[][] times;
    static Map<Integer, List<Road>> roads;
    static Loc[][] startAndEndTimes;

    static void input() {
        Reader scanner = new Reader();

        intersectionCount = scanner.nextInt();
        roadCount = scanner.nextInt();
        times = new int[intersectionCount + 1][intersectionCount + 1];
        startAndEndTimes = new Loc[intersectionCount + 1][intersectionCount + 1];
        roads = new HashMap<>();
        for (int intersection = 1; intersection <= intersectionCount; intersection++) {
            roads.put(intersection, new ArrayList<>());
            Arrays.fill(startAndEndTimes[intersection], new Loc(0, 0));
        }

        startIntersection = scanner.nextInt();
        endIntersection = scanner.nextInt();
        startDiffTime = scanner.nextInt();
        godoolaIntersectionCount = scanner.nextInt();
        godoolaIntersections = new int[godoolaIntersectionCount];

        for (int idx = 0; idx < godoolaIntersectionCount; idx++) {
            godoolaIntersections[idx] = scanner.nextInt();
        }

        for (int road = 0; road < roadCount; road++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int time = scanner.nextInt();

            roads.get(start).add(new Road(end, time));
            roads.get(end).add(new Road(start, time));
            times[start][end] = time;
            times[end][start] = time;
        }
    }

    static void solution() {
        calculateGodoolaLoc();
        System.out.println(dijkstra(startIntersection));
    }

    static int dijkstra(int start) {
        Queue<Road> queue = new PriorityQueue<>();
        int[] times = new int[intersectionCount + 1];
        Arrays.fill(times, Integer.MAX_VALUE);

        queue.offer(new Road(start, startDiffTime));
        times[startIntersection] = startDiffTime;

        while (!queue.isEmpty()) {
            Road cur = queue.poll();

            for (Road road : roads.get(cur.intersection)) {
                int nextIntersection = road.intersection;

                if (times[cur.intersection] >= startAndEndTimes[cur.intersection][road.intersection].start
                        && times[cur.intersection] <= startAndEndTimes[cur.intersection][road.intersection].end) {
                    times[nextIntersection] = startAndEndTimes[cur.intersection][road.intersection].end + road.time;
                    queue.offer(new Road(nextIntersection, times[nextIntersection]));
                } else {
                    int nextTime = times[cur.intersection] + road.time;
                    if (times[nextIntersection] > nextTime) {
                        times[nextIntersection] = nextTime;
                        queue.offer(new Road(nextIntersection, times[nextIntersection]));
                    }
                }
            }
        }

        return times[endIntersection] - startDiffTime;
    }

    static void calculateGodoolaLoc() {
        int time = 0;

        for (int idx = 0; idx < godoolaIntersections.length - 1; idx++) {
            int start = godoolaIntersections[idx];
            int end = godoolaIntersections[idx + 1];

            startAndEndTimes[start][end].start = time;
            startAndEndTimes[end][start].start = time;

            startAndEndTimes[start][end].end = time + times[start][end];
            startAndEndTimes[end][start].end = time + times[end][start];
            time += times[start][end];
        }
    }

    static class Loc {
        int start;
        int end;

        public Loc(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static class Road implements Comparable<Road> {
        int intersection;
        int time;

        public Road(int intersection, int time) {
            this.intersection = intersection;
            this.time = time;
        }

        @Override
        public int compareTo(Road o) {
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
