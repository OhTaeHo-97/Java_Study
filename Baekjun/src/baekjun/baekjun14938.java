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

public class baekjun14938 {
    // floyd warshall 알고리즘
//    static int pointCount;
//    static int range;
//    static int roadCount;
//    static int[] items;
//    static int[][] distances;
//
//    static void input() {
//        Reader scanner = new Reader();
//
//        pointCount = scanner.nextInt();
//        range = scanner.nextInt();
//        roadCount = scanner.nextInt();
//        items = new int[pointCount + 1];
//        distances = new int[pointCount + 1][pointCount + 1];
//
//        for (int point = 1; point <= pointCount; point++) {
//            items[point] = scanner.nextInt();
//            Arrays.fill(distances[point], Integer.MAX_VALUE);
//            distances[point][point] = 0;
//        }
//
//        for (int road = 0; road < roadCount; road++) {
//            int point1 = scanner.nextInt();
//            int point2 = scanner.nextInt();
//            int distance = scanner.nextInt();
//
//            distances[point1][point2] = Math.min(distances[point1][point2], distance);
//            distances[point2][point1] = Math.min(distances[point2][point1], distance);
//        }
//    }
//
//    static void solution() {
//        floydWarshall();
//        int answer = findMaxItemCount();
//        System.out.println(answer);
//    }
//
//    static int findMaxItemCount() {
//        int answer = Integer.MIN_VALUE;
//
//        for (int point = 1; point <= pointCount; point++) {
//            int itemCount = calculateItemCountByPoint(point);
//            answer = Math.max(answer, itemCount);
//        }
//
//        return answer;
//    }
//
//    static int calculateItemCountByPoint(int basisPoint) {
//        int itemCount = items[basisPoint];
//        for (int point = 1; point <= pointCount; point++) {
//            if (basisPoint == point) {
//                continue;
//            }
//            if (distances[basisPoint][point] <= range) {
//                itemCount += items[point];
//            }
//        }
//
//        return itemCount;
//    }
//
//    static void floydWarshall() {
//        for (int middle = 1; middle <= pointCount; middle++) {
//            for (int start = 1; start <= pointCount; start++) {
//                for (int end = 1; end <= pointCount; end++) {
//                    if (start == middle || end == middle || start == end) {
//                        continue;
//                    }
//                    if (distances[start][middle] == Integer.MAX_VALUE || distances[middle][end] == Integer.MAX_VALUE) {
//                        continue;
//                    }
//
//                    if (distances[start][middle] + distances[middle][end] < distances[start][end]) {
//                        distances[start][end] = distances[start][middle] + distances[middle][end];
//                    }
//                }
//            }
//        }
//    }


    // 다익스트라 알고리즘
    static int pointCount;
    static int range;
    static int roadCount;
    static int[] items;
    static Map<Integer, List<Edge>> edges;

    static void input() {
        Reader scanner = new Reader();

        pointCount = scanner.nextInt();
        range = scanner.nextInt();
        roadCount = scanner.nextInt();
        items = new int[pointCount + 1];
        edges = new HashMap<>();

        for (int point = 1; point <= pointCount; point++) {
            items[point] = scanner.nextInt();
            edges.put(point, new ArrayList<>());
        }

        for (int road = 0; road < roadCount; road++) {
            int point1 = scanner.nextInt();
            int point2 = scanner.nextInt();
            int distance = scanner.nextInt();

            edges.get(point1).add(new Edge(point2, distance));
            edges.get(point2).add(new Edge(point1, distance));
        }
    }

    static void solution() {
        int answer = 0;
        for (int point = 1; point <= pointCount; point++) {
            answer = Math.max(answer, findItemCountByPoint(point));
        }
        System.out.println(answer);
    }

    static int findItemCountByPoint(int point) {
        int[] distances = dijkstra(point);
        return calculateItemCountByPoint(point, distances);
    }

    static int calculateItemCountByPoint(int basisPoint, int[] distances) {
        int itemCount = items[basisPoint];
        for (int point = 1; point <= pointCount; point++) {
            if (basisPoint == point) {
                continue;
            }
            if (distances[point] <= range) {
                itemCount += items[point];
            }
        }

        return itemCount;
    }

    static int[] dijkstra(int point) {
        Queue<Edge> queue = new PriorityQueue<>();
        int[] distances = new int[pointCount + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        queue.offer(new Edge(point, 0));
        distances[point] = 0;

        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            if (distances[cur.point] < cur.distance) {
                continue;
            }

            for (Edge next : edges.get(cur.point)) {
                int nextPoint = next.point;
                int nextDistance = cur.distance + next.distance;

                if (distances[nextPoint] > nextDistance) {
                    distances[nextPoint] = nextDistance;
                    queue.offer(new Edge(nextPoint, nextDistance));
                }
            }
        }

        return distances;
    }

    static class Edge implements Comparable<Edge> {
        int point;
        int distance;

        public Edge(int point, int distance) {
            this.point = point;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge e) {
            return distance - e.distance;
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
