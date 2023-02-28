package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjun11780 {
	static StringBuilder sb = new StringBuilder();
    static int n, m;
    static long[][] costs;
    static int[][] path;

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextInt();
        m = scanner.nextInt();
        costs = new long[n + 1][n + 1];
        path = new int[n + 1][n + 1];
        for(int row = 0; row <= n; row++) {
            for(int col = 0; col <= n; col++) {
                path[row][col] = -1;
                if(row == col) costs[row][col] = 0;
                else costs[row][col] = Long.MAX_VALUE;
            }
        }

        for(int edge = 0; edge < m; edge++) {
            int start = scanner.nextInt(), end = scanner.nextInt(), cost = scanner.nextInt();

            if(costs[start][end] > cost)
                costs[start][end] = cost;
            path[start][end] = start;
        }
    }

    static void solution() {
        floydWarshall(costs);

        print(costs, path);
        System.out.print(sb);
    }

    static void print(long[][] costs, int[][] path) {
        printCosts(costs);
        printPaths(path);
    }

    static void printCosts(long[][] costs) {
        for(int row = 1; row <= n; row++) {
            for(int col = 1; col <= n; col++) {
                if(costs[row][col] == Long.MAX_VALUE) sb.append(0).append(' ');
                else sb.append(costs[row][col]).append(' ');
            }
            sb.append('\n');
        }
    }

    static void printPaths(int[][] path) {
        for(int row = 1; row <= n; row++) {
            for(int col = 1; col <= n; col++) {
                if(path[row][col] == -1)
                    sb.append(0).append('\n');
                else {
                    Stack<Integer> pathStack = new Stack<>();
                    int pre = col;
                    pathStack.push(col);

                    while(row != path[row][pre]) {
                        pre = path[row][pre];
                        pathStack.push(pre);
                    }

                    sb.append((pathStack.size() + 1)).append(' ');

                    sb.append(row).append(' ');
                    while(!pathStack.isEmpty()) {
                        sb.append(pathStack.pop()).append(' ');
                    }
                    sb.append('\n');
                }
            }
        }
    }

    static void floydWarshall(long[][] costs) {
        for(int middle = 1; middle <= n; middle++) {
            for(int start = 1; start <= n; start++) {
                for(int end = 1; end <= n; end++) {
                    if(middle == start) continue;
                    if(start == end) continue;
                    if(middle == end) continue;

                    if(costs[start][middle] == Long.MAX_VALUE || costs[middle][end] == Long.MAX_VALUE) continue;

                    if(costs[start][end] > costs[start][middle] + costs[middle][end]) {
                        costs[start][end] = costs[start][middle] + costs[middle][end];
                        path[start][end] = path[middle][end];
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
