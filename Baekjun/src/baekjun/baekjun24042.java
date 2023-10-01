package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun24042 {
    static int areaNum;
    static int period;
    static Map<Integer, List<Crosswalk>> crosswalks;

    static void input() {
        Reader scanner = new Reader();

        areaNum = scanner.nextInt();
        period = scanner.nextInt();
        crosswalks = new HashMap<>();
        for(int area = 1; area <= areaNum; area++) {
            crosswalks.put(area, new ArrayList<>());
        }

        for(int count = 0; count < period; count++) {
            int area1 = scanner.nextInt();
            int area2 = scanner.nextInt();

            crosswalks.get(area1).add(new Crosswalk(area2, count));
            crosswalks.get(area2).add(new Crosswalk(area1, count));
        }
    }

    static void solution() {
        System.out.println(dijkstra(1));
    }

    static long dijkstra(int start) {
        PriorityQueue<Crosswalk> queue = new PriorityQueue<>();
        long[] weights = new long[areaNum + 1];
        Arrays.fill(weights, Long.MAX_VALUE);

        queue.offer(new Crosswalk(start, 0));
        weights[start] = 0;

        while (!queue.isEmpty()) {
            Crosswalk cur = queue.poll();

            if (cur.area == areaNum) {
                return cur.weight;
            }
            if (weights[cur.area] < cur.weight) {
                continue;
            }

            for(Crosswalk next : crosswalks.get(cur.area)) {
                int nextArea = next.area;
                long nextWeight = getNextWeight(next.weight, cur.weight) + 1;

                if(weights[nextArea] > nextWeight) {
                    weights[nextArea] = nextWeight;
                    queue.offer(new Crosswalk(nextArea, nextWeight));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    static long getNextWeight(long nextWeight, long curWeight) {
        // 양수일 때
        if(nextWeight >= curWeight) {
            return (nextWeight - curWeight) + curWeight;
        }

        // 음수일 때
        long curPeriod = curWeight % period;
        if(nextWeight >= curPeriod) {
            return (nextWeight - curPeriod) + curWeight;
        } else {
            return (period + (nextWeight - curPeriod)) + curWeight;
        }
    }

    static class Crosswalk implements Comparable<Crosswalk> {
        int area;
        long weight;

        public Crosswalk(int area, long weight) {
            this.area = area;
            this.weight = weight;
        }

        @Override
        public int compareTo(Crosswalk o) {
            if(weight < o.weight) return -1;
            else if(weight > o.weight) return 1;
            return 0;
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
