package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun28297 {
    static int gearCount;
    static int[] parents;
    static List<Gear> gears;
    static Queue<Belt> belts;

    static void input() {
        Reader scanner = new Reader();

        gearCount = scanner.nextInt();
        gears = new ArrayList<>();
        parents = new int[gearCount];
        for (int gear = 0; gear < gearCount; gear++) {
            parents[gear] = gear;
            gears.add(new Gear(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }
    }

    static void solution() {
        makeAllBelts();
        System.out.println(calculateMinTotalBeltLength());
    }

    static double calculateMinTotalBeltLength() {
        double totalBeltLength = 0;
        int count = 0;

        while (!belts.isEmpty()) {
            Belt belt = belts.poll();
            if (!isSameParents(belt.gear1, belt.gear2)) {
                union(belt.gear1, belt.gear2);
                totalBeltLength += belt.beltLength;
                count++;
            }

            if (count >= gearCount - 1) {
                break;
            }
        }

        return totalBeltLength;
    }

    static int findParent(int gear) {
        if (gear == parents[gear]) {
            return gear;
        }
        return parents[gear] = findParent(parents[gear]);
    }

    static void union(int gear1, int gear2) {
        int parent1 = findParent(gear1);
        int parent2 = findParent(gear2);

        if (parent1 != parent2) {
            if (parent1 < parent2) {
                parents[parent2] = parent1;
            } else {
                parents[parent1] = parent2;
            }
        }
    }

    static boolean isSameParents(int gear1, int gear2) {
        return findParent(gear1) == findParent(gear2);
    }

    static void makeAllBelts() {
        belts = new PriorityQueue<>();
        for (int firstGearIdx = 0; firstGearIdx < gearCount; firstGearIdx++) {
            for (int secondGearIdx = firstGearIdx + 1; secondGearIdx < gearCount; secondGearIdx++) {
                double beltLength = calculateBeltLength(gears.get(firstGearIdx), gears.get(secondGearIdx));
                belts.offer(new Belt(firstGearIdx, secondGearIdx, beltLength));
            }
        }
    }

    static double calculateBeltLength(Gear gear1, Gear gear2) {
        double totalBeltLength = 0;
        int maxRadius = Math.max(gear1.radius, gear2.radius);
        int minRadius = Math.min(gear1.radius, gear2.radius);
        int bottomLineLength = maxRadius - minRadius;
        double hypotenuseLength = Math.pow(gear1.x - gear2.x, 2) + Math.pow(gear1.y - gear2.y, 2);

        if (minRadius + maxRadius < Math.sqrt(hypotenuseLength)) {
            double length = Math.sqrt(hypotenuseLength - Math.pow(bottomLineLength, 2));
            totalBeltLength += length * 2;

            double theta = Math.acos(bottomLineLength / Math.sqrt(hypotenuseLength));
            totalBeltLength += maxRadius * (Math.PI * 2 - 2 * theta);
            totalBeltLength += minRadius * (2 * theta);

            return totalBeltLength;
        }

        return 0;
    }

    static class Gear {
        int x;
        int y;
        int radius;

        public Gear(int x, int y, int radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }
    }

    static class Belt implements Comparable<Belt> {
        int gear1;
        int gear2;
        double beltLength;

        public Belt(int gear1, int gear2, double beltLength) {
            this.gear1 = gear1;
            this.gear2 = gear2;
            this.beltLength = beltLength;
        }

        @Override
        public int compareTo(Belt o) {
            if (beltLength < o.beltLength) {
                return -1;
            }
            if (beltLength > o.beltLength) {
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
