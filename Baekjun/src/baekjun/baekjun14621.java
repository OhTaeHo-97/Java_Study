package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class baekjun14621 {
	static int N, M;
    static int[][] edges;
    static char[] genders;
    static int[] parents;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();

        edges = new int[M][3];
        genders = new char[N + 1];
        parents = new int[N + 1];
        for(int school = 1; school <= N; school++) parents[school] = school;

        for(int idx = 1; idx <= N; idx++) genders[idx] = scanner.next().charAt(0);

        for(int idx = 0; idx < M; idx++) {
            int school1 = scanner.nextInt(), school2 = scanner.nextInt(), distance = scanner.nextInt();

            edges[idx][0] = school1;
            edges[idx][1] = school2;
            edges[idx][2] = distance;
        }
    }

    static void solution() {
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] e1, int[] e2) {
                return e1[2] - e2[2];
            }
        });

        ArrayList<int[]> minEdges = kruskal();
        if(minEdges.size() < N - 1) System.out.println(-1);
        else {
            int distance = 0;
            for(int[] edge : minEdges) distance += edge[2];
            System.out.println(distance);
        }
    }

    static ArrayList<int[]> kruskal() {
        ArrayList<int[]> minEdges = new ArrayList<>();

        int index = 0;
        for(int count = 0; count < N - 1; count++) {
            if(index == edges.length) break;

            int[] edge = edges[index];

            if(genders[edge[0]] == genders[edge[1]] || isSameParent(edge[0], edge[1])) {
                count--;
            } else {
                union(edge[0], edge[1]);
                minEdges.add(edge);
            }
            index++;
        }

        return minEdges;
    }

    static int findParent(int school) {
        if(school == parents[school]) return school;
        return parents[school] = findParent(parents[school]);
    }

    static void union(int school1, int school2) {
        int parent1 = findParent(school1), parent2 = findParent(school2);

        if(parent1 != parent2) {
            if(parent1 < parent2) parents[parent2] = parent1;
            else parents[parent1] = parent2;
        }
    }

    static boolean isSameParent(int school1, int school2) {
        int parent1 = findParent(school1), parent2 = findParent(school2);

        return parent1 == parent2;
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
