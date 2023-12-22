package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun30221 {
    static int cityCount;
    static int trainLineCount;
    static List<Edge> lines;

    static void input() {
        Reader scanner = new Reader();

        cityCount = scanner.nextInt();
        trainLineCount = scanner.nextInt();
        lines = new ArrayList<>();

        for(int count = 0; count < trainLineCount; count++) {
            int startCity = scanner.nextInt();
            int endCity = scanner.nextInt();
            String type = scanner.next();
            int time = scanner.nextInt();

            if(type.equals("b")) {
                lines.add(new Edge(startCity, endCity, time));
                continue;
            }
            lines.add(new Edge(startCity, endCity, -time));
        }
    }

    static void solution() {
        long[] times = bellmanFord(1);
        StringBuilder sb = new StringBuilder();
        for(int idx = 1; idx <= cityCount; idx++) {
            if(times[idx] < 0) {
                sb.append(idx).append('\n');
            }
        }
        System.out.print(sb);
    }

    static long[] bellmanFord(int startCity) {
        long[] times = new long[cityCount + 1];
        Arrays.fill(times, Long.MAX_VALUE);
        times[startCity] = 0;

        boolean isChanged = false;

        Loop:
        for (int count = 0; count < cityCount - 1; count++) {
            isChanged = false;
            for(Edge next : lines) {
                if(times[next.startCity] == Long.MAX_VALUE) {
                    continue;
                }

                int nextCity = next.endCity;
                long nextTime = times[next.startCity] + next.time;
                if(times[nextCity] > nextTime) {
                    isChanged = true;
                    times[nextCity] = nextTime;
                }
            }
            if(!isChanged) {
                break;
            }
        }

        return times;
    }

    static class Edge {
        int startCity;
        int endCity;
        long time;

        public Edge(int startCity, int endCity, long time) {
            this.startCity = startCity;
            this.endCity = endCity;
            this.time = time;
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
