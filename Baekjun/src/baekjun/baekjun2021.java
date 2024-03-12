package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class baekjun2021 {
    static int stationCount;
    static int lineCount;
    static int startStationNumber;
    static int endStationNumber;
    static Map<Integer, Set<Integer>> lines;
    static Map<Integer, Set<Integer>> stations;

    static void input() {
        Reader scanner = new Reader();

        stationCount = scanner.nextInt();
        lineCount = scanner.nextInt();
        lines = new HashMap<>();
        stations = new HashMap<>();

        for (int line = 1; line <= lineCount; line++) {
            lines.put(line, new HashSet<>());
            inputEachLine(line, scanner);
        }

        startStationNumber = scanner.nextInt();
        endStationNumber = scanner.nextInt();
    }

    static void inputEachLine(int lineNumber, Reader scanner) {
        while (true) {
            int stationNumber = scanner.nextInt();
            if (stationNumber == -1) {
                return;
            }
            lines.get(lineNumber).add(stationNumber);
            stations.computeIfAbsent(stationNumber, key -> new HashSet<>());
            stations.get(stationNumber).add(lineNumber);
        }
    }

    static void solution() {
        System.out.println(bfs(startStationNumber, endStationNumber));
    }

    static int bfs(int startStation, int endStation) {
        Queue<Station> stationQueue = new PriorityQueue<>();
        boolean[] lineVisited = new boolean[lineCount + 1];
        boolean[] stationVisited = new boolean[stationCount + 1];

        stationQueue.offer(new Station(startStation, 0, 0));
        stationVisited[startStation] = true;

        int minChangeCount = -1;
        while (!stationQueue.isEmpty()) {
            Station cur = stationQueue.poll();
            if (cur.stationNumber == endStation) {
                minChangeCount = cur.changeCount;
                break;
            }

            for (int lineNumber : stations.get(cur.stationNumber)) {
                if (lineVisited[lineNumber]) {
                    continue;
                }

                lineVisited[lineNumber] = true;
                int nextChangeCount = cur.changeCount;
                nextChangeCount += cur.lineNumber == lineNumber ? 0 : 1;
                if (cur.lineNumber == 0) {
                    nextChangeCount = 0;
                }

                for (int stationNumber : lines.get(lineNumber)) {
                    if (stationVisited[stationNumber]) {
                        continue;
                    }
                    stationVisited[stationNumber] = true;
                    stationQueue.offer(new Station(stationNumber, lineNumber, nextChangeCount));
                }
            }
        }

        return minChangeCount;
    }

    static class Station implements Comparable<Station> {
        int stationNumber;
        int lineNumber;
        int changeCount;

        public Station(int stationNumber, int lineNumber, int changeCount) {
            this.stationNumber = stationNumber;
            this.lineNumber = lineNumber;
            this.changeCount = changeCount;
        }

        @Override
        public int compareTo(Station o) {
            return changeCount - o.changeCount;
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
