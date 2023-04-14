package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun13418 {
	static int N, M;
    static int[][] roads;
    static int[] parents;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        roads = new int[M + 1][3];
        parents = new int[N + 1];

        roads[0][0] = scanner.nextInt();
        roads[0][1] = scanner.nextInt();
        roads[0][2] = scanner.nextInt();

        for(int road = 1; road <= M; road++) {
            int start = scanner.nextInt(), end = scanner.nextInt(), isUphill = scanner.nextInt();
            roads[road][0] = start;
            roads[road][1] = end;
            roads[road][2] = isUphill;
        }
    }

    static void solution() {
        for(int building = 0; building <= N; building++)
            parents[building] = building;
        Arrays.sort(roads, (road1, road2) -> road1[2] - road2[2]);
        int max = kruskal();

        for(int building = 0; building <= N; building++)
            parents[building] = building;
        Arrays.sort(roads, (road1, road2) -> road2[2] - road1[2]);
        int min = kruskal();

        System.out.println(max - min);
    }

    static int kruskal() {
        int upHillNum = 0;

        int index = 0;
        for(int roadNum = 0; roadNum < N; roadNum++) {
            int[] road = roads[index];
            if(isSameParents(road[0], road[1])) {
                roadNum--;
            } else {
                union(road[0], road[1]);
                if(road[2] == 0) upHillNum++;
            }
            index++;
        }

        return (int)Math.pow(upHillNum, 2);
    }

    static int findParent(int building) {
        if(building == parents[building]) return building;
        return parents[building] = findParent(parents[building]);
    }

    static void union(int building1, int building2) {
        int parent1 = findParent(building1), parent2 = findParent(building2);

        if(parent1 != parent2) {
            if(parent1 < parent2) parents[parent2] = parent1;
            else parents[parent1] = parent2;
        }
    }

    static boolean isSameParents(int building1, int building2) {
        int parent1 = findParent(building1), parent2 = findParent(building2);

        if(parent1 == parent2) return true;
        return false;
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
