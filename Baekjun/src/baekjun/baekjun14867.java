package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun14867 {
	static final int MAXSIZE = 100000;
    static int a, b, c, d;

    static void input() {
        Reader scanner = new Reader();

        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();
        d = scanner.nextInt();
    }

    static void solution() {
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Bottles> queue = new LinkedList<>();
        HashSet<Bottles> visited = new HashSet<>();

        queue.offer(new Bottles(0, 0, 0));
        visited.add(new Bottles(0, 0, 0));

        while(!queue.isEmpty()) {
            Bottles cur = queue.poll();

            if(cur.A == c && cur.B == d) return cur.moveNum;

            if(visited.add(new Bottles(a, cur.B, cur.moveNum + 1))) queue.offer(new Bottles(a, cur.B, cur.moveNum + 1));
            if(visited.add(new Bottles(cur.A, b, cur.moveNum + 1))) queue.offer(new Bottles(cur.A, b, cur.moveNum + 1));
            if(visited.add(new Bottles(0, cur.B, cur.moveNum + 1))) queue.offer(new Bottles(0, cur.B, cur.moveNum + 1));
            if(visited.add(new Bottles(cur.A, 0, cur.moveNum + 1))) queue.offer(new Bottles(cur.A, 0, cur.moveNum + 1));

            int extraB = b - cur.B, remainA, remainB;
            if(cur.A > extraB) {
                remainA = cur.A - extraB;
                remainB = b;
            } else {
                remainA = 0;
                remainB = cur.B + cur.A;
            }
            if(visited.add(new Bottles(remainA, remainB, cur.moveNum + 1))) queue.offer(new Bottles(remainA, remainB, cur.moveNum + 1));

            int extraA = a - cur.A;
            if(cur.B > extraA) {
                remainB = cur.B - extraA;
                remainA = a;
            } else {
                remainB = 0;
                remainA = cur.B + cur.A;
            }
            if(visited.add(new Bottles(remainA, remainB, cur.moveNum + 1))) queue.offer(new Bottles(remainA, remainB, cur.moveNum + 1));
        }

        return -1;
    }

    static class Bottles {
        int A, B, moveNum;

        public Bottles(int a, int b, int moveNum) {
            A = a;
            B = b;
            this.moveNum = moveNum;
        }

        @Override
        public boolean equals(Object o) {
            Bottles bottles = (Bottles)o;

            if(A == bottles.A && B == bottles.B) return true;
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(A, B);
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
