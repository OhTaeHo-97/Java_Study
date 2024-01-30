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

public class baekjun11562 {
    static int buildingCount;
    static int roadCount;
    static int questionCount;
    static boolean[] isCalculated;
    static int[][] questions;
    static int[][] minConversionCounts;
    static Map<Integer, List<Road>> roads;

    static void input() {
        Reader scanner = new Reader();

        buildingCount = scanner.nextInt();
        roadCount = scanner.nextInt();
        isCalculated = new boolean[buildingCount + 1];
        minConversionCounts = new int[buildingCount + 1][buildingCount + 1];
        roads = new HashMap<>();
        for (int buildingNum = 1; buildingNum <= buildingCount; buildingNum++) {
            Arrays.fill(minConversionCounts[buildingNum], Integer.MAX_VALUE);
            roads.put(buildingNum, new ArrayList<>());
        }

        for (int road = 0; road < roadCount; road++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int bidirectional = scanner.nextInt();

            roads.get(start).add(new Road(end, 0));
            roads.get(end).add(new Road(start, (bidirectional + 1) % 2));
        }

        questionCount = scanner.nextInt();
        questions = new int[questionCount][2];
        for (int idx = 0; idx < questionCount; idx++) {
            questions[idx][0] = scanner.nextInt();
            questions[idx][1] = scanner.nextInt();
        }
    }

    static void solution() {
        StringBuilder answer = new StringBuilder();
        for (int questionIdx = 0; questionIdx < questionCount; questionIdx++) {
            int startBuilding = questions[questionIdx][0];
            int endBuilding = questions[questionIdx][1];
            if (isCalculated[startBuilding]) {
                answer.append(minConversionCounts[startBuilding][endBuilding]).append('\n');
                continue;
            }

            dijkstra(startBuilding, minConversionCounts[startBuilding]);
            isCalculated[startBuilding] = true;
            answer.append(minConversionCounts[startBuilding][endBuilding]).append('\n');
        }

        System.out.print(answer);
    }

    static void dijkstra(int startBuilding, int[] weights) {
        Queue<Road> queue = new PriorityQueue<>();

        queue.offer(new Road(startBuilding, 0));
        weights[startBuilding] = 0;

        while (!queue.isEmpty()) {
            Road cur = queue.poll();
            if (weights[cur.buildingNum] < cur.conversionCount) {
                continue;
            }

            for (Road next : roads.get(cur.buildingNum)) {
                int nextBuilding = next.buildingNum;
                int nextConversionCount = cur.conversionCount + next.conversionCount;

                if (weights[nextBuilding] > nextConversionCount) {
                    weights[nextBuilding] = nextConversionCount;
                    queue.offer(new Road(nextBuilding, nextConversionCount));
                }
            }
        }
    }

    static class Road implements Comparable<Road> {
        int buildingNum;
        int conversionCount;

        public Road(int buildingNum, int conversionCount) {
            this.buildingNum = buildingNum;
            this.conversionCount = conversionCount;
        }

        @Override
        public int compareTo(Road o) {
            return conversionCount - o.conversionCount;
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
