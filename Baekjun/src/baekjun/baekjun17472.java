package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class baekjun17472 {
	static final int[] DX = {-1, 0, 1, 0}, DY = {0, -1, 0, 1};

    static int N, M, islandNum;
    static boolean[][] map;
    static int[][] islandMap;
    static ArrayList<ArrayList<int[]>> islands;
    static int[] parents;
    static ArrayList<Edge> bridgeCandidates;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new boolean[N][M];
        islandMap = new int[N][M];
        islands = new ArrayList<>();
        islands.add(new ArrayList<>());

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                int num = scanner.nextInt();
                map[row][col] = (num == 1);
            }
        }
    }

    static void solution() {
        findIsland();
        calcDistances();
        System.out.println(dijkstra());
    }

    static int dijkstra() {
        parents = new int[islandNum + 1];
        for(int island = 1; island <= islandNum; island++) parents[island] = island;

        Collections.sort(bridgeCandidates, (e1, e2) -> e1.distance - e2.distance);

        int selectedNum = 0, totalDistance = 0;
        for(int idx = 0; idx < bridgeCandidates.size(); idx++) {
            Edge candidate = bridgeCandidates.get(idx);

            if(isSameParents(candidate.start, candidate.end)) continue;

            union(candidate.start, candidate.end);
            totalDistance += candidate.distance;

            selectedNum++;
            if(selectedNum >= islandNum - 1) break;
        }

        if(selectedNum == islandNum - 1) return totalDistance;
        else return -1;
    }

    static int findParent(int island) {
        if(parents[island] == island) return island;
        return parents[island] = findParent(parents[island]);
    }

    static void union(int island1, int island2) {
        int parent1 = findParent(island1), parent2 = findParent(island2);

        if(parent1 != parent2) {
            if(parent1 < parent2) parents[parent2] = parent1;
            else parents[parent1] = parent2;
        }
    }

    static boolean isSameParents(int island1, int island2) {
        int parent1 = findParent(island1), parent2 = findParent(island2);

        return parent1 == parent2;
    }

    static void calcDistances() {
        bridgeCandidates = new ArrayList<>();

        for(int landIdx1 = 1; landIdx1 < islandNum; landIdx1++) {
            for(int landIdx2 = landIdx1 + 1; landIdx2 <= islandNum; landIdx2++) {
                int minDistance = calcMinDistance(islands.get(landIdx1), islands.get(landIdx2));
                if(minDistance != Integer.MAX_VALUE)
                    bridgeCandidates.add(new Edge(landIdx1, landIdx2, minDistance));
            }
        }
    }

    static int calcMinDistance(ArrayList<int[]> island1, ArrayList<int[]> island2) {
        int min = Integer.MAX_VALUE;

        for(int idx1 = 0; idx1 < island1.size(); idx1++) {
            for(int idx2 = 0; idx2 < island2.size(); idx2++) {
                int[] loc1 = island1.get(idx1), loc2 = island2.get(idx2);
                boolean isConnect = true;
                if(loc1[0] == loc2[0]) {
                    int increment = loc1[1] < loc2[1] ? 1 : -1;
                    for(int y = loc1[1] + increment; loc1[1] < loc2[1] ? y < loc2[1] : y > loc2[1]; y += increment) {
                        if(map[loc1[0]][y]) {
                            isConnect = false;
                            break;
                        }
                    }

                    if(!isConnect) continue;
                } else if(loc1[1] == loc2[1]) {
                    int increment = loc1[0] < loc2[0] ? 1 : -1;
                    for(int x = loc1[0] + increment; loc1[0] < loc2[0] ? x < loc2[0] : x > loc2[0]; x += increment) {
                        if(map[x][loc1[1]]) {
                            isConnect = false;
                            break;
                        }
                    }

                    if(!isConnect) continue;
                } else continue;

                int distance = Math.abs(loc1[0] - loc2[0]) + Math.abs(loc1[1] - loc2[1]) - 1;
                if(distance <= 1) continue;
                min = Math.min(min, distance);
            }
        }

        return min;
    }

    static void findIsland() {
        boolean[][] visited = new boolean[N][M];
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if(!visited[row][col] && map[row][col]) {
                    visited[row][col] = true;
                    islands.add(new ArrayList<>());
                    islandNum++;
                    islandMap[row][col] = islandNum;
                    islands.get(islandNum).add(new int[] {row, col});
                    dfs(row, col, islandNum, visited);
                }
            }
        }
    }

    static void dfs(int x, int y, int island, boolean[][] visited) {
        for(int dir = 0; dir < DX.length; dir++) {
            int cx = x + DX[dir], cy = y + DY[dir];

            if(isInMap(cx, cy)) {
                if(!visited[cx][cy] && map[cx][cy]) {
                    visited[cx][cy] = true;
                    islandMap[cx][cy] = island;
                    islands.get(island).add(new int[] {cx, cy});
                    dfs(cx, cy, island, visited);
                }
            }
        }
    }

    static boolean isInMap(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }

    static class Edge {
        int start, end, distance;

        public Edge(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
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
