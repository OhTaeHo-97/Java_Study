package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1719 {
	static int n, m;
    static int[][] times, parents;

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextInt(); // 집하장 개수
        m = scanner.nextInt(); // 집하장간 경로의 개수
        parents = new int[n + 1][n + 1]; // 해당 경로까지 갈 때 직전 노드를 나타냄
        times = new int[n + 1][n + 1];
        for(int row = 0; row <= n; row++) {
            for(int col = 0; col <= n; col++) {
                if(row == col) continue;
                times[row][col] = Integer.MAX_VALUE;
                times[col][row] = Integer.MAX_VALUE;
            }
        }

        for(int path = 0; path < m; path++) {
            int pickUpBook1 = scanner.nextInt(), pickUpBook2 = scanner.nextInt();
            int time = scanner.nextInt();

            times[pickUpBook1][pickUpBook2] = Math.min(times[pickUpBook1][pickUpBook2], time);
            times[pickUpBook2][pickUpBook1] = Math.min(times[pickUpBook2][pickUpBook1], time);

            parents[pickUpBook1][pickUpBook2] = pickUpBook1;
            parents[pickUpBook2][pickUpBook1] = pickUpBook2;
        }
    }

    static void solution() {
        floydWarshall();

        StringBuilder sb = new StringBuilder();

        for(int row = 1; row <= n; row++) {
            for(int col = 1; col <= n; col++) {
                if(row == col) sb.append("- ");
                else sb.append(getPrev(row, col)).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }

    static int getPrev(int start, int end) {
        int prev = end, next = parents[start][end];

        while(next != start) {
            prev = next;
            next = parents[start][next];
        }

        return prev;
    }

    static void print() {
        for(int row = 1; row <= n; row++) {
            for(int col = 1; col <= n; col++) {
                System.out.print(parents[row][col] + " ");
            }
            System.out.println();
        }
    }

    static void floydWarshall() {
        for(int middle = 1; middle <= n; middle++) {
            for(int start = 1; start <= n; start++) {
                for(int end = 1; end <= n; end++) {
                    if(start == end || start == middle || middle == end) continue;

                    if(times[start][middle] == Integer.MAX_VALUE || times[middle][end] == Integer.MAX_VALUE) continue;

                    if(times[start][end] > times[start][middle] + times[middle][end]) {
                        times[start][end] = times[start][middle] + times[middle][end];
                        parents[start][end] = parents[middle][end];
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
