package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1613 {
	static StringBuilder sb = new StringBuilder();
    static final int MAX = Integer.MAX_VALUE;
    static int n, k, s;
    static int[][] distances, question;
    static void input() {
        Reader scanner = new Reader();
        n = scanner.nextInt();
        k = scanner.nextInt();
        distances = new int[n + 1][n + 1];
        for(int event = 1; event <= n; event++) {
            for(int event2 = 1; event2 <= n; event2++) {
                if(event != event2) distances[event][event2] = MAX;
            }
        }
        for(int idx = 0; idx < k; idx++) {
            int before = scanner.nextInt(), next = scanner.nextInt();
            distances[before][next] = 1;
        }
        s = scanner.nextInt();
        question = new int[s][2];
        for(int idx = 0; idx < s; idx++) {
            question[idx][0] = scanner.nextInt();
            question[idx][1] = scanner.nextInt();
        }
    }

    static void solution() {
        floydWarshall(distances);
        for(int idx = 0; idx < s; idx++) {
            int first = question[idx][0], second = question[idx][1];
            if(distances[first][second] != MAX && distances[second][first] == MAX) {
                sb.append(-1).append('\n');
            } else if(distances[first][second] == MAX && distances[second][first] != MAX) {
                sb.append(1).append('\n');
            } else if(distances[first][second] == MAX && distances[second][first] == MAX) {
                sb.append(0).append('\n');
            }
        }
        System.out.println(sb);
    }

    static void floydWarshall(int[][] distances) {
        for(int middle = 1; middle <= n; middle++) {
            for(int start = 1; start <= n; start++) {
                for(int end = 1; end <= n; end++) {
                    if(start == end) continue;
                    if(distances[start][middle] == MAX || distances[middle][end] == MAX)
                        continue;
                    if(distances[start][end] > distances[start][middle] + distances[middle][end])
                        distances[start][end] = distances[start][middle] + distances[middle][end];
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
