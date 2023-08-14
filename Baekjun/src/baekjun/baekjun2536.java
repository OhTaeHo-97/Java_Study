package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun2536 {
    static int m, n, k;
    static int[] departure, destination;
    static Bus[] buses;

    static void input() {
        Reader scanner = new Reader();

        m = scanner.nextInt();
        n = scanner.nextInt();
        k = scanner.nextInt();
        buses = new Bus[k + 1];

        for(int bus = 1; bus <= k; bus++) {
            int busNum = scanner.nextInt();
            int startX = scanner.nextInt(), startY = scanner.nextInt();
            int endX = scanner.nextInt(), endY = scanner.nextInt();

            if(startX == endX)
                buses[busNum] = new Bus(new int[] {startX, Math.min(startY, endY)}, new int[] {endX, Math.max(startY, endY)}, true);
            else if(startY == endY)
                buses[busNum] = new Bus(new int[] {Math.min(startX, endX), startY}, new int[] {Math.max(startX, endX), endY}, false);
        }

        int startX = scanner.nextInt(), startY = scanner.nextInt();
        departure = new int[] {startX, startY};

        int endX = scanner.nextInt(), endY = scanner.nextInt();
        destination = new int[] {endX, endY};
    }

    static void solution() {
        System.out.println(bfs());
    }

    static int bfs() {
        List<Loc> startBuses = findStartBuses(departure);

        PriorityQueue<Loc> queue = new PriorityQueue<>();
        boolean[] visitedBus = new boolean[k + 1];
        for(Loc startBus : startBuses) {
            queue.offer(startBus);
            visitedBus[startBus.busNum] = true;
        }

        while(!queue.isEmpty()) {
            Loc cur = queue.poll();
            Bus bus = buses[cur.busNum];
            if(bus.isVertical && destination[0] == bus.start[0] &&
                    (bus.start[1] <= destination[1] && destination[1] <= bus.end[1]))
                return cur.usedBusNum;
            if(!bus.isVertical && destination[1] == bus.start[1] &&
                    (bus.start[0] <= destination[0] && destination[0] <= bus.end[0]))
                return cur.usedBusNum;

            for(int busIdx = 1; busIdx <= k; busIdx++) {
                if(!visitedBus[busIdx] && isCross(buses[cur.busNum], buses[busIdx])) {
                    visitedBus[busIdx] = true;
                    queue.offer(new Loc(busIdx, cur.usedBusNum + 1));
                }
            }
        }

        return -1;
    }

    static List<Loc> findStartBuses(int[] departure) {
        List<Loc> startBuses = new ArrayList<>();
        for(int busIdx = 1; busIdx <= k; busIdx++) {
            Bus bus = buses[busIdx];

            if(bus.isVertical && departure[0] == bus.start[0] &&
                    (bus.start[1] <= departure[1] && departure[1] <= bus.end[1])) {
                startBuses.add(new Loc(busIdx, 1));
            } else if(!bus.isVertical && departure[1] == bus.start[1] &&
                    (bus.start[0] <= departure[0] && departure[0] <= bus.end[0])) {
                startBuses.add(new Loc(busIdx, 1));
            }
        }

        return startBuses;
    }

    static boolean isCross(Bus bus1, Bus bus2) {
        if(bus1.isVertical && bus2.isVertical) {
            if(bus1.start[0] != bus2.start[0] || bus1.start[1] > bus2.end[1] || bus1.end[1] < bus2.start[1])
                return false;
            else
                return true;
        } else if(bus1.isVertical && !bus2.isVertical) {
            if(bus1.start[0] >= bus2.start[0] && bus1.start[0] <= bus2.end[0] && bus2.start[1] >= bus1.start[1] && bus2.start[1] <= bus1.end[1])
                return true;
            else
                return false;
        } else if(!bus1.isVertical && bus2.isVertical) {
            if(bus2.start[0] >= bus1.start[0] && bus2.start[0] <= bus1.end[0] && bus1.start[1] >= bus2.start[1] && bus1.start[1] <= bus2.end[1])
                return true;
            else
                return false;
        } else {
            if(bus1.start[1] != bus2.start[1] || bus1.start[0] > bus2.end[0] || bus1.end[0] < bus2.start[0])
                return false;
            else
                return true;
        }
    }

    static class Loc implements Comparable<Loc> {
        int busNum, usedBusNum;

        public Loc(int busNum, int usedBusNum) {
            this.busNum = busNum;
            this.usedBusNum = usedBusNum;
        }

        @Override
        public int compareTo(Loc o) {
            return usedBusNum - o.usedBusNum;
        }
    }

    static class Bus {
        int[] start, end;
        boolean isVertical;

        public Bus(int[] start, int[] end, boolean isVertical) {
            this.start = start;
            this.end = end;
            this.isVertical = isVertical;
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
    }
}
