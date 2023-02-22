package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1507 {
	static int N;
    static int[][] edges;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        edges = new int[N][N];

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++)
                edges[row][col] = scanner.nextInt();
        }
    }

    static void solution() {
        if(!floydWarshall(edges)) {
            System.out.println(-1);
            return;
        }

        System.out.println(getTotalDist(edges));
    }

    static int getTotalDist(int[][] edges) {
        int totalDist = 0;

        for(int row = 0; row < N; row++) {
            for(int col = row + 1; col < N; col++) {
                if(edges[row][col] != Integer.MAX_VALUE)
                    totalDist += edges[row][col];
            }
        }

        return totalDist;
    }

    static boolean floydWarshall(int[][] edges) {
        for(int middle = 0; middle < N; middle++) {
            for(int start = 0; start < N; start++) {
                for(int end = 0; end < N; end++) {
                    if(start == end) continue;
                    if(start == middle) continue;
                    if(middle == end) continue;

                    if(edges[start][middle] == Integer.MAX_VALUE) continue;
                    if(edges[middle][end] == Integer.MAX_VALUE) continue;
                    if(edges[start][end] == Integer.MAX_VALUE) continue;

                    if(edges[start][end] > edges[start][middle] + edges[middle][end]) {
                        return false;
                    }

                    if(edges[start][end] == edges[start][middle] + edges[middle][end]) {
                        edges[start][end] = Integer.MAX_VALUE;
                        edges[end][start] = Integer.MAX_VALUE;
                    }
                }
            }
        }

        return true;
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
