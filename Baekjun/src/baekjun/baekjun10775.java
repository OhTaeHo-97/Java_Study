package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun10775 {
	static int G, P;
    static int[] gates, parents;

    static void input() {
        Reader scanner = new Reader();
        G = scanner.nextInt();
        P = scanner.nextInt();
        gates = new int[P];

        for(int idx = 0; idx < P; idx++)
            gates[idx] = scanner.nextInt();
    }

    static void solution() {
        init(G + 1);

        int answer = 0;

        for(int idx = 0; idx < P; idx++) {
            int gate = findParent(gates[idx]);

            if(gate == 0) break;

            answer++;
            union(gate, gate - 1);
        }

        System.out.println(answer);
    }

    static void init(int size) {
        parents = new int[size];

        for(int gate = 0; gate < size; gate++)
            parents[gate] = gate;
    }

    static int findParent(int gate) {
        if(gate == parents[gate]) return gate;
        return parents[gate] = findParent(parents[gate]);
    }

    static void union(int gate1, int gate2) {
        int parent1 = findParent(gate1), parent2 = findParent(gate2);

        if(parent1 != parent2) {
            if(parent1 < parent2) parents[parent2] = parent1;
            else parents[parent1] = parent2;
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
