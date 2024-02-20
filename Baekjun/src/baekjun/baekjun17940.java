package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun17940 {
    static int subwayStationCount;
    static int endStationNumber;
    static int minTime;
    static int minTransferCount;
    static int[] companies;
    static int[][] times;
    static Map<Integer, List<Road>> roads;

    static void input() {
        Reader scanner = new Reader();

        subwayStationCount = scanner.nextInt();
        endStationNumber = scanner.nextInt();
        companies = new int[subwayStationCount];
        times = new int[subwayStationCount][subwayStationCount];
        for (int idx = 0; idx < subwayStationCount; idx++) {
            companies[idx] = scanner.nextInt();
        }
        roads = new HashMap<>();
        for (int station = 0; station < subwayStationCount; station++) {
            roads.put(station, new ArrayList<>());
        }

        for (int basisStation = 0; basisStation < subwayStationCount; basisStation++) {
            for (int otherStation = 0; otherStation < subwayStationCount; otherStation++) {
                int time = scanner.nextInt();
                if (basisStation == otherStation || time == 0) {
                    continue;
                }
                times[basisStation][otherStation] = time;
                roads.get(basisStation).add(new Road(otherStation, time, 0));
            }
        }
    }

    static void solution() {
        dijkstra(0, endStationNumber);
        System.out.println(minTransferCount + " " + minTime);
    }

    static void dijkstra(int start, int end) {
        Queue<Road> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[subwayStationCount];

        queue.offer(new Road(start, 0, 0));

        while (!queue.isEmpty()) {
            Road cur = queue.poll();
            while (visited[cur.stationNumber]) {
                cur = queue.poll();
            }
            if (cur.stationNumber == end) {
                minTime = cur.time;
                minTransferCount = cur.transferCount;
                break;
            }
            visited[cur.stationNumber] = true;

            for (Road next : roads.get(cur.stationNumber)) {
                int nextStationNumber = next.stationNumber;
                int nextTime = cur.time + next.time;
                int nextTransferCount = cur.transferCount;
                if (companies[cur.stationNumber] != companies[next.stationNumber]) {
                    nextTransferCount++;
                }

                if (!visited[nextStationNumber]) {
                    queue.offer(new Road(nextStationNumber, nextTime, nextTransferCount));
                }
            }
        }
    }

    static class Road implements Comparable<Road> {
        int stationNumber;
        int time;
        int transferCount;

        public Road(int stationNumber, int time, int transferCount) {
            this.stationNumber = stationNumber;
            this.time = time;
            this.transferCount = transferCount;
        }

        @Override
        public int compareTo(Road o) {
            if (transferCount != o.transferCount) {
                return transferCount - o.transferCount;
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
