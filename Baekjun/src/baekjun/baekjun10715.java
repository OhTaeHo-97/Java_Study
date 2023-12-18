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

public class baekjun10715 {
    static int squareCount;
    static int roadCount;
    static int variableNum;
    static long totalDistance;
    static long answer;
    static Map<Integer, List<Road>> roads;
    static long[] distances;
    static boolean[] checked;

    static void input() {
        Reader scanner = new Reader();

        squareCount = scanner.nextInt();
        roadCount = scanner.nextInt();
        variableNum = scanner.nextInt();
        roads = new HashMap<>();
        distances = new long[squareCount + 1];
        checked = new boolean[squareCount + 1];
        for (int square = 1; square <= squareCount; square++) {
            roads.put(square, new ArrayList<>());
            distances[square] = Long.MAX_VALUE;
        }

        for (int count = 0; count < roadCount; count++) {
            int square1 = scanner.nextInt();
            int square2 = scanner.nextInt();
            int distance = scanner.nextInt();

            roads.get(square1).add(new Road(square2, distance));
            roads.get(square2).add(new Road(square1, distance));

            totalDistance += distance;
        }
    }

    static void solution() {
        dijkstra(1);
        System.out.println(answer);
    }

    static void dijkstra(int startSquare) {
        Queue<Road> queue = new PriorityQueue<>();
        long[] distances = new long[squareCount + 1];
        Arrays.fill(distances, Long.MAX_VALUE);

        queue.offer(new Road(startSquare, 0));
        distances[startSquare] = 0;
        answer = totalDistance;

        while (!queue.isEmpty()) {
            Road curRoad = queue.poll();

            if (checked[curRoad.squareNum]) {
                continue;
            }
            if (distances[curRoad.squareNum] < curRoad.distance) {
                continue;
            }

            checked[curRoad.squareNum] = true;
            for (Road next : roads.get(curRoad.squareNum)) {
                int nextSquare = next.squareNum;
                long nextDistance = next.distance;
                if (checked[nextSquare]) {
                    totalDistance -= nextDistance;
                }
            }

            answer = Math.min(answer, totalDistance + variableNum * curRoad.distance);

            for (Road next : roads.get(curRoad.squareNum)) {
                int nextSquare = next.squareNum;
                long nextDistance = next.distance + curRoad.distance;
                if (distances[nextSquare] > nextDistance) {
                    distances[nextSquare] = nextDistance;
                    queue.offer(new Road(nextSquare, nextDistance));
                }
            }
        }
    }

    static class Road implements Comparable<Road> {
        int squareNum;
        long distance;

        public Road(int squareNum, long distance) {
            this.squareNum = squareNum;
            this.distance = distance;
        }

        @Override
        public int compareTo(Road o) {
            if (distance < o.distance) {
                return -1;
            }
            if (distance > o.distance) {
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
