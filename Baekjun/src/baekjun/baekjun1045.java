package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class baekjun1045 {
    static int cityCount;
    static int roadCount;
    static int[] parents;
    static boolean[][] connectivity;
    static List<Road> roads;
    static Set<Road> mstEdges;

    static void input() {
        Reader scanner = new Reader();

        cityCount = scanner.nextInt();
        roadCount = scanner.nextInt();
        parents = new int[cityCount];
        connectivity = new boolean[cityCount][cityCount];
        roads = new ArrayList<>();

        for (int startCity = 0; startCity < cityCount; startCity++) {
            String info = scanner.nextLine();
            parents[startCity] = startCity;
            for (int endCity = 0; endCity < cityCount; endCity++) {
                if (info.charAt(endCity) == 'Y') {
                    roads.add(new Road(startCity, endCity));
                }
            }
        }
    }

    static void solution() {
        Collections.sort(roads);
        if (!kruskal()) {
            System.out.println(-1);
            return;
        }

        if (!findRemainRoads()) {
            System.out.println(-1);
            return;
        }

        int[] answers = calculateEndPointCount();
        print(answers);
    }

    static void print(int[] answers) {
        StringBuilder answer = new StringBuilder();
        Arrays.stream(answers).forEach(count -> answer.append(count).append(' '));
        System.out.println(answer);
    }

    static int[] calculateEndPointCount() {
        int[] answers = new int[cityCount];
        for (Road edge : mstEdges) {
            answers[edge.startCity]++;
            answers[edge.endCity]++;
        }
        return answers;
    }

    static boolean findRemainRoads() {
        int remainCount = roadCount - (cityCount - 1);

        for (int idx = 0; idx < roads.size() && remainCount > 0; idx++) {
            Road road = roads.get(idx);
            if (!mstEdges.contains(road) && !connectivity[road.endCity][road.startCity]) {
                mstEdges.add(road);
                connectivity[road.startCity][road.endCity] = true;
                remainCount--;
            }
        }

        return remainCount == 0;
    }

    static boolean kruskal() {
        mstEdges = new HashSet<>();

        int count = cityCount - 1;
        for (int idx = 0; idx < roads.size() && count > 0; idx++) {
            Road curRoad = roads.get(idx);

            if (!isSameParent(curRoad.startCity, curRoad.endCity)) {
                union(curRoad.startCity, curRoad.endCity);
                connectivity[curRoad.startCity][curRoad.endCity] = true;
                count--;
                mstEdges.add(curRoad);
            }
        }

        return count == 0;
    }

    static int findParent(int city) {
        if (city == parents[city]) {
            return city;
        }
        return parents[city] = findParent(parents[city]);
    }

    static void union(int city1, int city2) {
        int parent1 = findParent(city1);
        int parent2 = findParent(city2);
        if (parent1 != parent2) {
            if (parent1 < parent2) {
                parents[parent2] = parent1;
                return;
            }
            parents[parent1] = parent2;
        }
    }

    static boolean isSameParent(int city1, int city2) {
        int parent1 = findParent(city1);
        int parent2 = findParent(city2);
        return parent1 == parent2;
    }

    static class Road implements Comparable<Road> {
        int startCity;
        int endCity;

        public Road(int startCity, int endCity) {
            this.startCity = startCity;
            this.endCity = endCity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Road road = (Road) o;
            return startCity == road.startCity && endCity == road.endCity;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startCity, endCity);
        }

        @Override
        public int compareTo(Road o) {
            if (startCity != o.startCity) {
                return startCity - o.startCity;
            }
            return endCity - o.endCity;
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
