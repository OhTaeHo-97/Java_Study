package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun2300 {
    static int buildingNum;
    static List<Building> buildings;

    static void input() {
        Reader scanner = new Reader();

        buildingNum = scanner.nextInt();
        buildings = new ArrayList<>();

        for (int cnt = 0; cnt < buildingNum; cnt++) {
            buildings.add(new Building(scanner.nextInt(), scanner.nextInt()));
        }
    }

    static void solution() {
        Collections.sort(buildings);

        int[] dp = new int[buildingNum + 1];

        for (int basisIdx = 1; basisIdx <= buildingNum; basisIdx++) {
            int dist = 0;
            dp[basisIdx] = Integer.MAX_VALUE;

            for (int idx = basisIdx; idx >= 1; idx--) {
                dist = Math.max(dist, Math.abs(buildings.get(idx - 1).y));
                dp[basisIdx] = Math.min(dp[basisIdx],
                        dp[idx - 1] + Math.max(2 * dist, buildings.get(basisIdx - 1).x - buildings.get(idx - 1).x));
            }
        }

        System.out.println(dp[buildingNum]);
    }

    static class Building implements Comparable<Building> {
        int x;
        int y;

        public Building(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Building o) {
            if (x != o.x) {
                return x - o.x;
            }
            return y - o.y;
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
