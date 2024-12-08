package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun17182 {
    private static int planetCount;
    private static int startPlanet;
    private static int answer = Integer.MAX_VALUE;
    private static int[][] times;
    private static int[][] minTimes;

    private static void input() {
        Reader scanner = new Reader();

        planetCount = scanner.nextInt();
        startPlanet = scanner.nextInt();
        times = new int[planetCount][planetCount];
        minTimes = new int[planetCount][planetCount];

        for(int row = 0; row < planetCount; row++) {
            for(int col = 0; col < planetCount; col++) {
                times[row][col] = scanner.nextInt();
                minTimes[row][col] = times[row][col];
            }
        }
    }

    private static void solution() {
        floydWarshall();
        boolean[] visited = new boolean[planetCount];
        visited[startPlanet] = true;
        bruteForce(startPlanet, 0, 1, visited);
        System.out.println(answer);
    }

    private static void bruteForce(int planet, int time, int visitCount, boolean[] visited) {
        if(visitCount == planetCount) {
            answer = Math.min(answer, time);
            return;
        }

        for(int nextPlanet = 0; nextPlanet < planetCount; nextPlanet++) {
            if(visited[nextPlanet]) {
                continue;
            }

            visited[nextPlanet] = true;
            bruteForce(nextPlanet, time + minTimes[planet][nextPlanet], visitCount + 1, visited);
            visited[nextPlanet] = false;
        }
    }

    private static void floydWarshall() {
        for(int mid = 0; mid < planetCount; mid++) {
            for(int start = 0; start < planetCount; start++) {
                for(int end = 0; end < planetCount; end++) {
                    if (start == end || start == mid || mid == end) {
                        continue;
                    }
                    if(minTimes[start][end] > minTimes[start][mid] + minTimes[mid][end]) {
                        minTimes[start][end] = minTimes[start][mid] + minTimes[mid][end];
                    }
                }
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
