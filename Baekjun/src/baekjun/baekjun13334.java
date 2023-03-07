package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun13334 {
	static int n, d;
    static PriorityQueue<Route> routes;

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextInt();
        routes = new PriorityQueue<>();

        for(int route = 1; route <= n; route++) {
            int loc1 = scanner.nextInt(), loc2 = scanner.nextInt();
            routes.offer(new Route(Math.min(loc1, loc2), Math.max(loc1, loc2)));
        }

        d = scanner.nextInt();
    }

    static void solution() {
        PriorityQueue<Integer> locations = new PriorityQueue<>();

        int count = 0, answer = 0;
        while(!routes.isEmpty()) {
            Route route = routes.poll();

            while(!locations.isEmpty() && locations.peek() < (route.end - d)) {
                locations.poll();
                count--;
            }

            if(route.start >= (route.end - d)) {
                locations.offer(route.start);
                count++;
            }

            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }

    static class Route implements Comparable<Route> {
        int start, end;

        public Route(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Route r) {
            if(this.end != r.end) return this.end - r.end;
            return this.start - r.start;
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
