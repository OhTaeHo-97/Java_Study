package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class baekjun17834 {
    static int bushCount;
    static int pathCount;
    static int count;
    static int count2;
    static int mang;
    static int[] visited;
    static Map<Integer, List<Integer>> map;

    static void input() {
        Reader scanner = new Reader();

        bushCount = scanner.nextInt();
        pathCount = scanner.nextInt();
        visited = new int[bushCount + 1];
        map = new HashMap<>();
        for (int bush = 1; bush <= bushCount; bush++) {
            map.put(bush, new ArrayList<>());
        }

        for (int count = 0; count < pathCount; count++) {
            int bush1 = scanner.nextInt();
            int bush2 = scanner.nextInt();

            map.get(bush1).add(bush2);
            map.get(bush2).add(bush1);
        }
    }

    static void solution() {
        for (int bush = 1; bush <= bushCount; bush++) {
            if (visited[bush] != 0) {
                continue;
            }
            count++;
            visited[bush] = 1;
            dfs(bush);
        }

        if (mang != 0) {
            System.out.println("0");
        } else {
            System.out.println(2 * count * count2);
        }
    }

    static void dfs(int bush) {
        for (int next : map.get(bush)) {
            if (visited[next] == 0) {
                visited[next] = 3 - visited[bush];
                if (visited[next] == 1) {
                    count++;
                } else {
                    count2++;
                }
                dfs(next);
            } else if (visited[next] == visited[bush]) {
                mang = 1;
                return;
            }
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
            while (st == null || !st.hasMoreElements()) {
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
