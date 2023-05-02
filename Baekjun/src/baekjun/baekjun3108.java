package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun3108 {
	static int N;
    static List<Rectangle> rectangles;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        rectangles = new ArrayList<>();
        rectangles.add(new Rectangle(new int[] {0, 0}, new int[] {0, 0}));

        for(int idx = 0; idx < N; idx++) {
            int leftX = scanner.nextInt(), leftY = scanner.nextInt(), rightX = scanner.nextInt(), rightY = scanner.nextInt();
            rectangles.add(new Rectangle(new int[] {leftX, leftY}, new int[] {rightX, rightY}));
        }
    }

    static void solution() {
        int answer = 0;
        boolean[] visited = new boolean[N + 1];

        for(int idx = 0; idx <= N; idx++) {
            if(!visited[idx]) {
                visited[idx] = true;
                bfs(idx, visited);
                answer++;
            }
        }

        System.out.println(answer - 1);
    }

    static void bfs(int index, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(index);

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int idx = 0; idx <= N; idx++) {
                if(idx == index || visited[idx] || !rectangles.get(cur).isOverlapped(rectangles.get(idx))) continue;
                visited[idx] = true;
                queue.offer(idx);
            }
        }
    }

    static class Rectangle {
        int[] leftBottom, rightTop;

        public Rectangle(int[] leftBottom, int[] rightTop) {
            this.leftBottom = leftBottom;
            this.rightTop = rightTop;
        }

        boolean isOverlapped(Rectangle rectangle) {
            if(leftBottom[0] < rectangle.leftBottom[0] && leftBottom[1] < rectangle.leftBottom[1]
            && rectangle.rightTop[0] < rightTop[0] && rectangle.rightTop[1] < rightTop[1]) return false;

            if(leftBottom[0] > rectangle.leftBottom[0] && leftBottom[1] > rectangle.leftBottom[1]
                    && rectangle.rightTop[0] > rightTop[0] && rectangle.rightTop[1] > rightTop[1]) return false;

            if(rightTop[1] < rectangle.leftBottom[1] || rightTop[0] < rectangle.leftBottom[0]
            || leftBottom[0] > rectangle.rightTop[0] || leftBottom[1] > rectangle.rightTop[1]) return false;

            return true;
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
