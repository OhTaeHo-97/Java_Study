package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun10021 {
    private static int fieldCount;
    private static int minPipeCost;
    private static long minCost;
    private static int[] parents;
    private static Field[] fields;
    private static PriorityQueue<Pipe> pipes;

    private static void input() {
        Reader scanner = new Reader();

        fieldCount = scanner.nextInt();
        minPipeCost = scanner.nextInt();
        fields = new Field[fieldCount];
        parents = new int[fieldCount];

        for(int field = 0; field < fieldCount; field++) {
            parents[field] = field;
            fields[field] = new Field(scanner.nextInt(), scanner.nextInt());
        }
    }

    private static void solution() {
        makeAllPipes();
        if(choosePipesForMinCost()) {
            System.out.println(minCost);
        } else {
            System.out.println(-1);
        }
    }

    private static void makeAllPipes() {
        pipes = new PriorityQueue<>();

        for(int field1Idx = 0; field1Idx < fieldCount - 1; field1Idx++) {
            for(int field2Idx = field1Idx + 1; field2Idx < fieldCount; field2Idx++) {
                Field field1 = fields[field1Idx];
                Field field2 = fields[field2Idx];
                long cost = (long)(Math.pow(field1.x - field2.x, 2) + Math.pow(field1.y - field2.y, 2));
                if(cost >= minPipeCost) {
                    pipes.offer(new Pipe(field1Idx, field2Idx, cost));
                }
            }
        }
    }

    private static boolean choosePipesForMinCost() {
        int chosenPipeCount = 0;
        while(!pipes.isEmpty() && chosenPipeCount < fieldCount - 1) {
            Pipe pipe = pipes.poll();
            if(pipe.cost < minPipeCost || isSameParent(pipe.field1, pipe.field2)) {
                continue;
            }
            union(pipe.field1, pipe.field2);
            minCost += pipe.cost;
            chosenPipeCount++;
        }

        return chosenPipeCount == fieldCount - 1;
    }

    private static int findParents(int fieldIdx) {
        if(parents[fieldIdx] == fieldIdx) {
            return fieldIdx;
        }
        return parents[fieldIdx] = findParents(parents[fieldIdx]);
    }

    private static void union(int field1Idx, int field2Idx) {
        int field1Parent = findParents(field1Idx);
        int field2Parent = findParents(field2Idx);

        if(field1Parent != field2Parent) {
            if(field1Parent > field2Parent) {
                parents[field1Parent] = field2Parent;
            } else {
                parents[field2Parent] = field1Parent;
            }
        }
    }

    private static boolean isSameParent(int field1Idx, int field2Idx) {
        int field1Parent = findParents(field1Idx);
        int field2Parent = findParents(field2Idx);
        return field1Parent == field2Parent;
    }

    static class Field {
        int x;
        int y;

        public Field(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Pipe implements Comparable<Pipe> {
        int field1;
        int field2;
        long cost;

        public Pipe(int field1, int field2, long cost) {
            this.field1 = field1;
            this.field2 = field2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pipe p) {
            if(this.cost < p.cost) {
                return -1;
            }
            if(this.cost > p.cost) {
                return 1;
            }
            if(this.field1 != p.field1) {
                return this.field1 - p.field1;
            }
            return this.field2 - p.field2;
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
