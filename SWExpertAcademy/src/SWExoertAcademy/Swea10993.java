package src.SWExoertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Swea10993 {
    static final Map<Integer, Character> CONSTANT = new HashMap<Integer, Character>() {{
        put(-1, 'K');
        put(-2, 'D');
    }};

    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static int cityCount;
    static double[][] influence;
    static int[] parents;
    static List<City> cities;
    static List<Integer>[] dominantCities;

    static void input() {
        cityCount = scanner.nextInt();
        influence = new double[cityCount][cityCount];
        parents = new int[cityCount];
        cities = new ArrayList<>();
        dominantCities = new ArrayList[cityCount];
        Arrays.fill(parents, Integer.MIN_VALUE);

        for (int idx = 0; idx < cityCount; idx++) {
            cities.add(new City(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
            dominantCities[idx] = new ArrayList<>();
        }
    }

    static void solution() {
        calculateInfluence();
        findDominantCities();
        print();
    }

    static void print() {
        IntStream.range(0, cityCount).forEach(parent -> {
            int followingCity = findFollowingCity(parent);
            if (CONSTANT.containsKey(followingCity)) {
                answer.append(CONSTANT.get(followingCity)).append(' ');
                return;
            }

            answer.append(followingCity + 1).append(' ');
        });
        answer.append('\n');
    }

    static int findFollowingCity(int idx) {
        if (parents[idx] == -1 || parents[idx] == -2) {
            return parents[idx];
        }
        return findParent(idx);
    }

    static int findParent(int city) {
        if (parents[city] == -1 || parents[city] == -2) {
            return city;
        }
        return parents[city] = findParent(parents[city]);
    }

    static void calculateInfluence() {
        for (int basis = 0; basis < cityCount; basis++) {
            for (int other = 0; other < cityCount; other++) {
                if (basis == other) {
                    continue;
                }

                City basisCity = cities.get(basis);
                City otherCity = cities.get(other);
                double distanceSquare = calculateDistanceSquare(basisCity, otherCity);
                influence[basis][other] = basisCity.militaryPower / distanceSquare;
            }
        }
    }

    static double calculateDistanceSquare(City city1, City city2) {
        return Math.pow(city2.x - city1.x, 2) + Math.pow(city2.y - city1.y, 2);
    }

    static void findDominantCities() {
        for (int basis = 0; basis < cityCount; basis++) {
            for (int other = 0; other < cityCount; other++) {
                if (basis == other) {
                    continue;
                }

                if (influence[other][basis] > cities.get(basis).militaryPower) {
                    dominantCities[basis].add(other);
                }
            }

            sort(basis, dominantCities[basis]);
            findCityInfo(basis);
        }
    }

    static void sort(int basisCity, List<Integer> dominantCities) {
        Collections.sort(dominantCities, (cityIdx1, cityIdx2) -> {
            if (influence[cityIdx1][basisCity] < influence[cityIdx2][basisCity]) {
                return 1;
            }
            if (influence[cityIdx1][basisCity] > influence[cityIdx2][basisCity]) {
                return -1;
            }
            return 0;
        });
    }

    static void findCityInfo(int basisIdx) {
        if (dominantCities[basisIdx].size() == 0) {
            parents[basisIdx] = -1;
            return;
        }

        if (dominantCities[basisIdx].size() == 1) {
            parents[basisIdx] = dominantCities[basisIdx].get(0);
            return;
        }

        List<Integer> dominantCity = dominantCities[basisIdx];
        double max = influence[dominantCity.get(0)][basisIdx];
        if (max == influence[dominantCity.get(1)][basisIdx]) {
            parents[basisIdx] = -2;
            return;
        }
        parents[basisIdx] = dominantCities[basisIdx].get(0);
    }

    static class City {
        int x;
        int y;
        int militaryPower;

        public City(int x, int y, int militaryPower) {
            this.x = x;
            this.y = y;
            this.militaryPower = militaryPower;
        }
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        for (int test = 1; test <= T; test++) {
            answer.append('#').append(test).append(' ');
            input();
            solution();
        }
        System.out.print(answer);
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
