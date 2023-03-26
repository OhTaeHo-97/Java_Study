package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class baekjun16724 {
	static int N, M;
    static char[][] map;
    static int[] parents;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new char[N][M];
        parents = new int[N * 1000];

        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < M; col++) {
                map[row][col] = info.charAt(col);
                parents[row * 1000 + col] = row * 1000 + col;
            }
        }
    }

    static void solution() {
        boolean[][] visited = new boolean[N][M];
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if(!visited[row][col]) {
                    visited[row][col] = true;
                    dfs(row * 1000 + col, visited);
                }
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++)
                set.add(findParent(row * 1000 + col));
        }

        System.out.println(set.size());
    }

    static void dfs(int loc, boolean[][] visited) {
        int row = loc / 1000, col = loc % 1000;

        if(map[row][col] == 'U') row--;
        else if(map[row][col] == 'D') row++;
        else if(map[row][col] == 'L') col--;
        else if(map[row][col] == 'R') col++;

        if(isInMap(row, col)) {
            union(loc, row * 1000 + col);
            if(!visited[row][col]) {
                visited[row][col] = true;
                dfs(row * 1000 + col, visited);
            }
        }
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < N && y >= 0 && y < M) return true;
        return false;
    }

    static int findParent(int loc) {
        if(parents[loc] == loc) return loc;
        return parents[loc] = findParent(parents[loc]);
    }

    static void union(int loc1, int loc2) {
        int parent1 = findParent(loc1), parent2 = findParent(loc2);

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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
