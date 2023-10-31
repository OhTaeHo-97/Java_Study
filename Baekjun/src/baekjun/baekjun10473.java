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

public class baekjun10473 {
    static Point start;
    static Point end;
    static int artilleryNum;
    static List<Point> points;
    static Map<Integer, List<Edge>> edges;

    static void input() {
        Reader scanner = new Reader();

        points = new ArrayList<>();
        edges = new HashMap<>();
        start = new Point(scanner.nextDouble(), scanner.nextDouble(), false);
        points.add(start);
        end = new Point(scanner.nextDouble(), scanner.nextDouble(), false);

        artilleryNum = scanner.nextInt();
        for(int cnt = 0; cnt < artilleryNum; cnt++) {
            Point artillery = new Point(scanner.nextDouble(), scanner.nextDouble(), true);
            points.add(artillery);
        }

        points.add(end);
    }

    static void solution() {
        makeMap();
        double[] times = dijkstra(0);
        System.out.println(times[points.size() - 1]);
    }

    static void makeMap() {
        for(int idx = 0; idx < points.size(); idx++) {
            edges.put(idx, new ArrayList<>());
        }

        for(int basis = 0; basis < points.size() - 1; basis++) {
            for(int pointIdx = basis + 1; pointIdx < points.size(); pointIdx++) {
                Point basisPoint = points.get(basis);
                Point point = points.get(pointIdx);

                edges.get(basis).add(new Edge(pointIdx, getTime(basisPoint, point, basisPoint.isArtillery)));
                edges.get(pointIdx).add(new Edge(basis, getTime(point, basisPoint, point.isArtillery)));
            }
        }
    }

    static double getTime(Point start, Point end, boolean isArtillery) {
        double dist = Math.sqrt(Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2));
        double walkingTime = dist / 5;
        if(!isArtillery) {
            return walkingTime;
        }

        double artilleryTime = calculateArtilleryTime(dist);
        return Math.min(walkingTime, artilleryTime);
    }

    static double calculateArtilleryTime(double distance) {
        if(distance == 50) {
            return 2;
        }

        double artilleryTime = 2;
        distance = Math.abs(distance - 50);
        artilleryTime += (distance / 5);
        return artilleryTime;
    }

    static double[] dijkstra(int start) {
        Queue<Edge> queue = new PriorityQueue<>();
        double[] times = new double[points.size()];
        Arrays.fill(times, Double.MAX_VALUE);

        times[start] = 0;
        queue.offer(new Edge(start, 0));

        while(!queue.isEmpty()) {
            Edge cur = queue.poll();
            if(times[cur.pointIdx] < cur.time) {
                continue;
            }

            for(Edge next : edges.get(cur.pointIdx)) {
                int nextPoint = next.pointIdx;
                double nextTime = cur.time + next.time;

                if(times[nextPoint] > nextTime) {
                    times[nextPoint] = nextTime;
                    queue.offer(new Edge(nextPoint, nextTime));
                }
            }
        }

        return times;
    }

    static class Point {
        double x;
        double y;
        boolean isArtillery;

        public Point(double x, double y, boolean isArtillery) {
            this.x = x;
            this.y = y;
            this.isArtillery = isArtillery;
        }
    }

    static class Edge implements Comparable<Edge> {
        int pointIdx;
        double time;

        public Edge(int pointIdx, double time) {
            this.pointIdx = pointIdx;
            this.time = time;
        }

        @Override
        public int compareTo(Edge o) {
            if(time < o.time) return -1;
            if(time > o.time) return 1;
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
