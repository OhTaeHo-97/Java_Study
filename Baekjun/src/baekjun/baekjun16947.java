package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun16947 {
	// DFS로 순환되는 지점들을 저장
    // BFS로 각 지점을 돌면서 순환되는 지점과 가장 가까운 거리를 구함

    static int N;
    static HashMap<Integer, ArrayList<Integer>> map;
    static HashSet<Integer> loopLine = null;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        map = new HashMap<>();
        for(int station = 1; station <= N; station++)
            map.put(station, new ArrayList<>());

        for(int path = 0; path < N; path++) {
            int station1 = scanner.nextInt(), station2 = scanner.nextInt();

            map.get(station1).add(station2);
            map.get(station2).add(station1);
        }
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();

        for(int station = 1; station <= N; station++) {
            boolean[] visited = new boolean[N + 1];
            getLoopLineStations(station, -1, station, visited, new ArrayList<>());
        }

        for(int station = 1; station <= N; station++) {
            if(loopLine.contains(station)) {
                sb.append(0).append(' ');
                continue;
            }

            sb.append(getShortestDistance(station)).append(' ');
        }

        System.out.println(sb);
    }

    static int getShortestDistance(int start) {
        Queue<Station> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        queue.offer(new Station(start, 0));
        visited[start] = true;

        while(!queue.isEmpty()) {
            Station curStation = queue.poll();

            if(loopLine.contains(curStation.station)) return curStation.distance;

            for(int next : map.get(curStation.station)) {
                if(!visited[next]) {
                    visited[next] = true;
                    queue.offer(new Station(next, curStation.distance + 1));
                }
            }
        }

        return 0;
    }

    static void getLoopLineStations(int station, int prev, int start, boolean[] visited, ArrayList<Integer> stations) {
        if(visited[start] && start == station) {
            loopLine = new HashSet<>(stations);
            return;
        }

        for(int next : map.get(station)) {
            if(next == prev) continue;

            if(!visited[next]) {
                visited[next] = true;
                stations.add(next);

                getLoopLineStations(next, station, start, visited, stations);
                if(loopLine != null) return;

                visited[next] = false;
                stations.remove(stations.size() - 1);
            }
        }
    }

    static class Station {
        int station, distance;

        public Station(int station, int distance) {
            this.station = station;
            this.distance = distance;
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
