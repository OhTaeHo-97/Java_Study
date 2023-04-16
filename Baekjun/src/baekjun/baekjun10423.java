package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class baekjun10423 {
	static int N, M, K;
    static int[] parents;
    static HashSet<Integer> powerPlants;
    static int[][] cables;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
        powerPlants = new HashSet<>();
        parents = new int[N + 1];
        cables = new int[M][3];
        for(int city = 1; city <= N; city++) parents[city] = city;

        for(int city = 0; city < K; city++)
            powerPlants.add(scanner.nextInt());

        for(int cable = 0; cable < M; cable++) {
            int city1 = scanner.nextInt(), city2 = scanner.nextInt(), weight = scanner.nextInt();
            cables[cable][0] = city1;
            cables[cable][1] = city2;
            cables[cable][2] = weight;
        }
    }

    static void solution() {
        Arrays.sort(cables, (c1, c2) -> c1[2] - c2[2]);

        System.out.println(kruskal());
    }

    static int kruskal() {
        int index = 0, answer = 0;
        for(int cableNum = 0; cableNum < N - 1 && index < M; cableNum++) {
            if(isConnected()) break;

            if(isSameParents(cables[index][0], cables[index][1]) ||
                    powerPlants.contains(findParent(cables[index][0])) && powerPlants.contains(findParent(cables[index][1]))) {
                index++;
                cableNum--;
                continue;
            }

            union(cables[index][0], cables[index][1]);
            answer += cables[index][2];
            index++;
        }

        return answer;
    }

    static boolean isConnected() {
        for(int city = 1; city <= N; city++)
            if(!powerPlants.contains(parents[city])) return false;

        return true;
    }

    static int findParent(int city) {
        if(city == parents[city]) return city;
        return parents[city] = findParent(parents[city]);
    }

    static void union(int city1, int city2) {
        int parent1 = findParent(city1), parent2 = findParent(city2);
        if(parent1 != parent2) {
            if(powerPlants.contains(parent1)) parents[parent2] = parent1;
            else if(powerPlants.contains(parent2)) parents[parent1] = parent2;
            else {
                if(parent1 < parent2) parents[parent2] = parent1;
                else parents[parent1] = parent2;
            }
        }
    }

    static boolean isSameParents(int city1, int city2) {
        int parent1 = findParent(city1), parent2 = findParent(city2);

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
