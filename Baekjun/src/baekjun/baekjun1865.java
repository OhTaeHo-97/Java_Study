package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun1865 {
	// N개의 지점, 지점들 사이에는 M개의 도로, W개의 웜홀
    // 무방향 도로, 웜홀은 방향이 있음
    // 웜홀 -> 시작 위치에서 도착 위치로 가는 하나의 경로, 도착을 하게 되면 시작을 하였을 때보다 시간이 뒤로 감
    // 한 지점에서 출발하여 시간여행을 하기 시작하여 다시 출발하였던 위치로 돌아왔을 때,
    // 출발하였을 때보다 시간이 되돌아가 있는 경우가 있는지 없는지

    // 1 <= TC <= 5
    // 지점의 수 N, 도로의 수 M, 웜홀 개수 W(1 <= N <= 500, 1 <= M <= 2500, 1 <= W, <= 200)
    // S, E, T -> S와 E는 연결된 지점 번호, T -> 도로를 통해 이동하는 시간
    // 웜홀 -> S, E, T -> S는 시작, E는 도착, T는 줄어드는 시간

    static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();
    static final int INF = 30000000;
    static int N, M, W;
    static Edge[] edges;
    static int[] weight;

    static void input() {
        N = scanner.nextInt();
        M = scanner.nextInt();
        W = scanner.nextInt();
        edges = new Edge[2 * M + W];
        for(int road = 0; road < M; road++) {
            int l1 = scanner.nextInt(), l2 = scanner.nextInt(), weight = scanner.nextInt();
            edges[2 * road] = new Edge(l1, l2, weight);
            edges[2 * road + 1] = new Edge(l2, l1, weight);
        }
        for(int worm = 2 * M; worm < 2 * M + W; worm++) {
            int start = scanner.nextInt(), end = scanner.nextInt(), weight = scanner.nextInt();
            edges[worm] = new Edge(start, end, weight * (-1));
        }
    }

    static void solution() {
        boolean isCycle = false;
        for(int loc = 1; loc <= N; loc++) {
            if(bellmanFord(loc)) {
                isCycle = true;
                sb.append("YES").append('\n');
                break;
            }
        }
        if(!isCycle) sb.append("NO").append('\n');
    }

    static boolean bellmanFord(int start) {
        weight = new int[N + 1];
        Arrays.fill(weight, INF);
        weight[start] = 0;
        boolean isChanged = false;

        loop:
        for(int loc = 1; loc <= N; loc++) {
            isChanged = false;
            for(Edge e : edges) {
                if(weight[e.start] == INF) continue;
                if(weight[e.end] > weight[e.start] + e.weight) {
                    weight[e.end] = weight[e.start] + e.weight;
                    isChanged = true;
                    if(loc == N) {
                        isChanged = true;
                        break loop;
                    }
                }
            }
            if(!isChanged) break;
        }
        return isChanged;
    }

    static class Edge {
        int start, end, weight;
        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        int TC = scanner.nextInt();
        while(TC-- > 0) {
            input();
            solution();
        }
        System.out.println(sb.toString());
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
                } catch(IOException e) {
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
