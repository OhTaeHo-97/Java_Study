package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun16991 {
    static int N, visitBits;
    static int[][] locs;
    static double[][] distances, dp;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        locs = new int[N][2];
        distances = new double[N][N]; // 각 도시 사이의 거리
        // 각 자릿수에 해당하는 도시에 방문하였는지 비트마스킹을 통해 나타낼 것이므로 그때의 최대 비트를 계산한다
        // ex. 11001 -> 1, 4, 5번 도시는 방문하였고 2, 3번 도시는 방문하지 않았다
        visitBits = (1 << N) - 1;
        dp = new double[N][visitBits]; // 각 도시들을 방문하면서 최소 거리를 저장하기 위한 배열

        for(int idx = 0; idx < N; idx++) {
            locs[idx][0] = scanner.nextInt();
            locs[idx][1] = scanner.nextInt();
        }
    }

    static void solution() {
        calculateDistance(); // 각 도시 사이의 거리를 계산한다
        // tsp 메서드를 통해 각 도시들을 방문해보며 한 도시에서 N개의 도시를 돌아 시작 도시까지 방문하였을 때의 최소 거리를 구한다
        System.out.println(tsp(0, 1));
    }

    static double tsp(int city, int bit) { // city : 현재 방문해있는 도시, bit : 방문처리와 관련된 비트
        // 만약 N개의 도시를 모두 돌았다면, 마지막 도시에서 시작 도시까지의 거리를 반환하여 거리 계산에 사용한다
        if(bit == visitBits) return distances[city][0];
        // 만약 현재 도시의 현재 방문상태와 같은 상황을 이전에 방문했다면
        // 그때까지의 이동 거리를 반환하여 거리 계산에 사용한다
        if(dp[city][bit] != 0) return dp[city][bit];

        dp[city][bit] = Integer.MAX_VALUE;

        // 도시들을 순회하면서 재귀를 통해 각 도시들을 방문해보며 거리를 계산한다
        for(int nextCity = 0; nextCity < N; nextCity++) {
            // 현재 순회하고 있는 도시를 다음에 방문한다고 했을 때의 방문상태 비트를 계산한다
            int nextBit = bit | (1 << nextCity);
            // 만약 다음에 방문하려고 하는 도시로 이동할 수 있고 다음 방문하려고 하는 도시에 아직 방문하지 않았다면
            // 재귀를 통해 해당 도시를 방문해보고 다음 도시들까지 방문해보며 거리를 계산하고 더 짧은 거리로 갱신한다
            if(distances[city][nextCity] > 0 && (bit & (1 << nextCity)) == 0)
                dp[city][bit] = Math.min(dp[city][bit], tsp(nextCity, nextBit) + distances[city][nextCity]);
        }

        return dp[city][bit];
    }

    static void calculateDistance() {
        for(int start = 0; start < N; start++) {
            for(int end = start + 1; end < N; end++) {
                double distance = Math.sqrt(Math.pow(locs[start][0] - locs[end][0], 2) + Math.pow(locs[start][1] - locs[end][1], 2));
                distances[start][end] = distances[end][start] = distance;
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
