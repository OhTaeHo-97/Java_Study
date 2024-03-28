package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class baekjun12896 {
    static int cityCount;
    static int longestCity;
    static int longestDistance;
    static Map<Integer, List<Integer>> roads;

    static void input() {
        Reader scanner = new Reader();

        cityCount = scanner.nextInt();
        roads = new HashMap<>();
        for (int city = 1; city <= cityCount; city++) {
            roads.put(city, new ArrayList<>());
        }

        for (int road = 0; road < cityCount - 1; road++) {
            int city1 = scanner.nextInt();
            int city2 = scanner.nextInt();

            roads.get(city1).add(city2);
            roads.get(city2).add(city1);
        }
    }

    static void solution() {
        int firstLongestCity = getLongestCity(1);
        int secondLongestCity = getLongestCity(firstLongestCity);

        System.out.println((1 + longestDistance) / 2);
    }

    static int getLongestCity(int city) {
        longestCity = longestDistance = 0;
        dfs(city, 0, new boolean[cityCount + 1]);
        return longestCity;
    }

    static void dfs(int city, int distance, boolean[] visited) {
        if (distance > longestDistance) {
            longestDistance = distance;
            longestCity = city;
        }

        visited[city] = true;

        for (int nextCity : roads.get(city)) {
            if (!visited[nextCity]) {
                dfs(nextCity, distance + 1, visited);
            }
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
