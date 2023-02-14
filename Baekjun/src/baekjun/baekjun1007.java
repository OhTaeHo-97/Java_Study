package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1007 {
	static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();

    static int N;
    static double answer;
    static int[][] points;
    static boolean[] visited;

    static void input() {
        N = scanner.nextInt();
        points = new int[N][2];
        visited = new boolean[N];

        for(int idx = 0; idx < N; idx++) {
            points[idx][0] = scanner.nextInt();
            points[idx][1] = scanner.nextInt();
        }
    }

    static void solution() {
        answer = Double.MAX_VALUE;

        choosePoints(0, 0);

        sb.append(answer).append('\n');
    }

    static void choosePoints(int index, int count) {
        if(count == N / 2) {
            answer = Math.min(answer, getVectorAmount());
            return;
        }

        for(int idx = index; idx < N; idx++) {
            if(!visited[idx]) {
                visited[idx] = true;
                choosePoints(idx + 1, count + 1);
                visited[idx] = false;
            }
        }
    }

    static double getVectorAmount() {
        int x = 0, y = 0;

        for(int idx = 0; idx < N; idx++) {
            if(visited[idx]) {
                x += points[idx][0];
                y += points[idx][1];
            } else {
                x -= points[idx][0];
                y -= points[idx][1];
            }
        }

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();

        while(T-- > 0) {
            input();
            solution();
        }

        System.out.print(sb);
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
