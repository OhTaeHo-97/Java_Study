package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun4386 {
	static int n;
    static int[] parents;
    static double[][] stars;
    static PriorityQueue<Edge> list;
    static void input() {
        Reader scanner = new Reader();
        n = scanner.nextInt();
        stars = new double[n][2];
        for(int star = 0; star < n; star++) {
            double x = scanner.nextDouble(), y = scanner.nextDouble();
            stars[star][0] = x;
            stars[star][1] = y;
        }
    }

    static void solution() {
        makeEdge();
        double totalDist = kruskal();
        System.out.println(String.format("%.2f", totalDist));
    }

    static void makeEdge() {
        list = new PriorityQueue<>();
        for(int star = 0; star < n - 1; star++) {
            double startX = stars[star][0], startY = stars[star][1];
            for(int other = star + 1; other < n; other++) {
                double endX = stars[other][0], endY = stars[other][1];
                double dist = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
                list.offer(new Edge(star, other, dist));
            }
        }
    }

    static double kruskal() {
        parents = new int[n];
        for(int star = 0; star < n; star++) parents[star] = star;
        double totalDist = 0;
        for(int idx = 0; idx < n - 1; idx++) {
            Edge e = list.poll();
            int start = e.startIdx, end = e.endIdx;
            double dist = e.dist;
            if(isSameParents(start, end)) {
                idx--;
                continue;
            }
            union(start, end);
            totalDist += dist;
        }
        return totalDist;
    }

    static int findParent(int star) {
        if(parents[star] == star) return star;
        return parents[star] = findParent(parents[star]);
    }

    static void union(int star1, int star2) {
        int parent1 = findParent(star1), parent2 = findParent(star2);
        if(parent1 != parent2) {
            if(parent1 < parent2) parents[parent2] = parent1;
            else parents[parent1] = parent2;
        }
    }

    static boolean isSameParents(int star1, int star2) {
        int parent1 = findParent(star1), parent2 = findParent(star2);
        if(parent1 == parent2) return true;
        return false;
    }

    static class Edge implements Comparable<Edge> {
        int startIdx, endIdx;
        double dist;
        public Edge(int startIdx, int endIdx, double dist) {
            this.startIdx = startIdx;
            this.endIdx = endIdx;
            this.dist = dist;
        }
        public int compareTo(Edge e) {
            if(this.dist < e.dist) return -1;
            else if(this.dist > e.dist) return 1;
            else return 0;
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
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
