package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun10800 {
	static int N;
    static Ball[] balls;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        balls = new Ball[N];

        for(int idx = 0; idx < N; idx++) {
            int color = scanner.nextInt(), size = scanner.nextInt();
            balls[idx] = new Ball(idx, color, size);
        }
    }

    static void solution() {
        Arrays.sort(balls);

        StringBuilder sb = new StringBuilder();
        int sum = 0, index = 0;
        int[] colorPoints = new int[N + 1], gatheredPoint = new int[N];
        for(int idx = 0; idx < balls.length; idx++) {
            Ball ball = balls[idx];

            while(ball.size > balls[index].size) {
                sum += balls[index].size;
                colorPoints[balls[index].color] += balls[index].size;
                index++;
            }

            gatheredPoint[ball.index] = sum - colorPoints[ball.color];
        }

        for(int point : gatheredPoint) sb.append(point).append('\n');

        System.out.print(sb);
    }

    static class Ball implements Comparable<Ball> {
        int index, color, size;

        public Ball(int index, int color, int size) {
            this.index = index;
            this.color = color;
            this.size = size;
        }

        @Override
        public int compareTo(Ball b) {
            return this.size - b.size;
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
