import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class CodeTreeTour {
    private static final int MAX_ID = 30005;

    private static Reader scanner;
    private static StringBuilder sb;

    private static int islandCount;
    private static int pathCount;
    private static int startIsland;
    private static int commandNumber;

    private static boolean[] isMadeId;
    private static boolean[] isCanceledId;
    private static int[] distances;
    private static Map<Integer, List<Path>> paths;
    private static Queue<Package> packages;

    private static void input() {
        commandNumber = scanner.nextInt();
        if(commandNumber == 100) {
            startIsland = 0;
            islandCount = scanner.nextInt();
            pathCount = scanner.nextInt();
            paths = new HashMap<>();
            for(int island = 0; island < islandCount; island++) {
                paths.put(island, new ArrayList<>());
            }

            for(int count = 0; count < pathCount; count++) {
                buildLand(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            }
            dijkstra(startIsland);
        } else if(commandNumber == 200) {
            int id = scanner.nextInt();
            int revenue = scanner.nextInt();
            int destination = scanner.nextInt();
            addPackage(id, revenue, destination);
        } else if(commandNumber == 300) {
            cancelPackage(scanner.nextInt());
        } else if(commandNumber == 400) {
            sb.append(sellPackage()).append('\n');
        } else if(commandNumber == 500) {
            changeStart(scanner.nextInt());
        }
    }

    private static void dijkstra(int start) {
        Queue<Path> queue = new PriorityQueue<>();
        distances = new int[islandCount];
        Arrays.fill(distances, Integer.MAX_VALUE);

        queue.offer(new Path(start, 0));
        distances[start] = 0;

        while(!queue.isEmpty()) {
            Path cur = queue.poll();
            if(cur.weight > distances[cur.to]) {
                continue;
            }

            for(Path path : paths.get(cur.to)) {
                int next = path.to;
                int nextWeight = cur.weight + path.weight;

                if(distances[next] > nextWeight) {
                    distances[next] = nextWeight;
                    queue.offer(new Path(next, nextWeight));
                }
            }
        }
    }

    private static void buildLand(int island1, int island2, int weight) {
        paths.get(island1).add(new Path(island2, weight));
        if(island1 != island2) {
            paths.get(island2).add(new Path(island1, weight));
        }
    }

    private static void addPackage(int id, int revenue, int destination) {
        isMadeId[id] = true;

        long profit = (distances[destination] == Integer.MAX_VALUE) ? Long.MIN_VALUE : (long) revenue - distances[destination];
        packages.offer(new Package(id, revenue, destination, profit));
    }

    private static void cancelPackage(int id) {
        if(isMadeId[id]) isCanceledId[id] = true;
    }

    private static int sellPackage() {
        while(!packages.isEmpty()) {
            Package ctPackage = packages.peek();

            if(ctPackage.profit < 0) {
                break;
            }

            packages.poll();
            if(!isCanceledId[ctPackage.id]) {
                isCanceledId[ctPackage.id] = true;
                return ctPackage.id;
            }
        }

        return -1;
    }

    private static void changeStart(int start) {
        startIsland = start;
        dijkstra(start);

        List<Package> newPackages = new ArrayList<>();
        while(!packages.isEmpty()) {
            newPackages.add(packages.poll());
        }

        for(Package ctPackage : newPackages) {
            ctPackage.profit = (distances[ctPackage.destination] == Integer.MAX_VALUE) ? Long.MIN_VALUE : (long) ctPackage.revenue - distances[ctPackage.destination];
            packages.offer(ctPackage);
        }
    }

    static class Path implements Comparable<Path> {
        int to;
        int weight;

        public Path(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Path other) {
            return Integer.compare(weight, other.weight);
        }
    }

    static class Package implements Comparable<Package> {
        int id;
        int revenue;
        int destination;
        long profit;

        public Package(int id, int revenue, int destination, long profit) {
            this.id = id;
            this.revenue = revenue;
            this.destination = destination;
            this.profit = profit;
        }

        @Override
        public int compareTo(Package other) {
            if(this.profit != other.profit) {
                return  Long.compare(other.profit, this.profit);
            }
            return Integer.compare(this.id, other.id);
        }
    }

    public static void main(String[] args) {
        scanner = new Reader();
        sb = new StringBuilder();
        packages = new PriorityQueue<>();
        isMadeId = new boolean[MAX_ID];
        isCanceledId = new boolean[MAX_ID];

        int commandCount = scanner.nextInt();
        for(int count = 0; count < commandCount; count++) {
            input();
        }
        System.out.println(sb);
    }

    static class Reader {
        BufferedReader br;
        StringTokenizer st;

        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
