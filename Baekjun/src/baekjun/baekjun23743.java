package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun23743 {
    static int roomCount;
    static int warpCount;
    static int[] parents;
    static int[] exitDoorTimes;
    static int[] times;
    static Queue<Warp> warps;
    static List<Warp> selectedWarps;

    static void input() {
        Reader scanner = new Reader();

        roomCount = scanner.nextInt();
        warpCount = scanner.nextInt();
        parents = new int[roomCount + 1];
        exitDoorTimes = new int[roomCount + 1];
        times = new int[roomCount + 1];
        warps = new PriorityQueue<>();

        for (int count = 0; count < warpCount; count++) {
            int startRoom = scanner.nextInt();
            int endRoom = scanner.nextInt();
            int time = scanner.nextInt();

            warps.offer(new Warp(startRoom, endRoom, time));
        }

        for (int roomIdx = 1; roomIdx <= roomCount; roomIdx++) {
            times[roomIdx] = Integer.MAX_VALUE;
            parents[roomIdx] = roomIdx;
            exitDoorTimes[roomIdx] = scanner.nextInt();
        }
    }

    static void solution() {
        int time = kruskal();
        for (int roomIdx = 1; roomIdx <= roomCount; roomIdx++) {
            int parent = findParent(roomIdx);
            times[parent] = Math.min(times[parent], exitDoorTimes[roomIdx]);
        }
        for (int roomIdx = 1; roomIdx <= roomCount; roomIdx++) {
            if (times[roomIdx] != Integer.MAX_VALUE) {
                time += times[roomIdx];
            }
        }

        System.out.println(time);
    }

    static int kruskal() {
        selectedWarps = new ArrayList<>();
        int count = 0;
        int time = 0;

        while (!warps.isEmpty() && count < warpCount) {
            Warp cur = warps.poll();
            if (!isSameParents(cur.startRoom, cur.endRoom)) {
                int usingWarp = cur.time + Math.min(exitDoorTimes[cur.startRoom], exitDoorTimes[cur.endRoom]);
                int noUsingWarp = exitDoorTimes[cur.startRoom] + exitDoorTimes[cur.endRoom];
                if (usingWarp < noUsingWarp) {
                    union(cur.startRoom, cur.endRoom);
                    time += cur.time;
                    selectedWarps.add(cur);
                    count++;
                }
            }
        }

        return time;
    }

    static int findParent(int roomNumber) {
        if (roomNumber == parents[roomNumber]) {
            return roomNumber;
        }
        return parents[roomNumber] = findParent(parents[roomNumber]);
    }

    static void union(int roomNumber1, int roomNumber2) {
        int parent1 = findParent(roomNumber1);
        int parent2 = findParent(roomNumber2);

        if (parent1 != parent2) {
            if (parent1 < parent2) {
                parents[parent2] = parent1;
            } else {
                parents[parent1] = parent2;
            }
        }
    }

    static boolean isSameParents(int roomNumber1, int roomNumber2) {
        int parent1 = findParent(roomNumber1);
        int parent2 = findParent(roomNumber2);
        return parent1 == parent2;
    }

    static class Warp implements Comparable<Warp> {
        int startRoom;
        int endRoom;
        int time;

        public Warp(int startRoom, int endRoom, int time) {
            this.startRoom = startRoom;
            this.endRoom = endRoom;
            this.time = time;
        }

        @Override
        public int compareTo(Warp o) {
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
