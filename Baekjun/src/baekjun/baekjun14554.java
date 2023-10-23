package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun14554 {
    static final int DIVISOR = 1_000_000_009;
    static int villageNum;
    static int roadNum;
    static int startVillage;
    static int endVillage;
    static Map<Integer, List<Edge>> roads;

    static void input() {
        Reader scanner = new Reader();

        villageNum = scanner.nextInt();
        roadNum = scanner.nextInt();
        startVillage = scanner.nextInt();
        endVillage = scanner.nextInt();
        roads = new HashMap<>();
        for(int village = 1; village <= villageNum; village++) {
            roads.put(village, new ArrayList<>());
        }

        for(int road = 0; road < roadNum; road++) {
            int village1 = scanner.nextInt();
            int village2 = scanner.nextInt();
            int distance = scanner.nextInt();

            roads.get(village1).add(new Edge(village2, distance));
            roads.get(village2).add(new Edge(village1, distance));
        }
    }

    static void solution() {
        System.out.println(dijkstra(startVillage, endVillage));
    }

    static long dijkstra(int startVillage, int endVillage) {
        Queue<Edge> queue = new PriorityQueue<>();
        long[] distances = new long[villageNum + 1];
        int[] dp = new int[villageNum + 1]; // 해당 마을로 최단 경로로 올 수 있는 경로의 개수
        Arrays.fill(distances, Long.MAX_VALUE);

        queue.offer(new Edge(startVillage, 0));
        distances[startVillage] = 0;
        dp[startVillage] = 1;

        while(!queue.isEmpty()) {
            Edge cur = queue.poll();
            // 이전까지 구한 최단거리보다 현재 거리가 더 크면 최단거리가 될 수 없기 때문에 다음 도로를 탐색한다
            if(distances[cur.village] < cur.distance) {
                continue;
            }
            // 우리가 구하고자 하는 것은 startVillage부터 endVillage까지 최단거리 경로 개수이기 때문에
            // endVillage 이후의 경로는 구할 필요가 없기 때문에 현재 위치가 endVillage라면 다음 도로를 탐색한다
            if(cur.village == endVillage) {
                continue;
            }

            // 현재 마을에 이어진 도로를 순회하며 최단 거리 갱신 및 경로 개수를 구한다
            for(Edge nextRoad : roads.get(cur.village)) {
                int nextVillage = nextRoad.village;
                long nextDist = distances[cur.village] + nextRoad.distance;

                // 만약 지금까지 구한 최단 거리보다 현재 경로에서의 최단 거리가 더 작다면
                // 최단 거리를 갱신할 수 있기 때문에 최단 거리를 갱신하고
                // 현재 위치로 최단 거리로 이동해온 경로들을 이용하여 다음 도로를 이용하면 다음 마을까지 최단 거리로 갈 수 있으니
                // 현재 위치로 최단 거리로 이동해온 경로의 수만큼 다음 마을까지 최단 거리로 갈 수 있다는 뜻이 된다
                // 그러므로 다음 마을까지 최단 거리로 갈 수 있는 최단 경로의 수를 현재 위치의 최단 경로의 수로 변경한다
                if (distances[nextVillage] > nextDist) {
                    distances[nextVillage] = nextDist;
                    dp[nextVillage] = dp[cur.village];

                    queue.offer(new Edge(nextVillage, nextDist));
                }
                // 만약 이전까지 구한 최단 거리와 현재 경로로 이동한 최단 거리가 같다면
                // 다음 마을까지 이전에 구해놓은 최단 경로로 이동해도 되고, 현재 마을까지 이동해온 경로들을 이용하여 다음 마을까지 최단 경로로 이동해도 된다
                // 그러므로 현재 마을의 최단 경로 수와 다음 마을의 최단 경로 수의 합으로 다음 마을의 최단 경로 수를 갱신한다
                else if(distances[nextVillage] == nextDist) {
                    dp[nextVillage] = (dp[nextVillage] + dp[cur.village]) % DIVISOR;
                }
            }
        }

        return dp[endVillage];
    }

    static class Edge implements Comparable<Edge> {
        int village;
        long distance;

        public Edge(int village, long distance) {
            this.village = village;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            if(distance < o.distance) return -1;
            else if(distance > o.distance) return 1;
            else return 0;
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
