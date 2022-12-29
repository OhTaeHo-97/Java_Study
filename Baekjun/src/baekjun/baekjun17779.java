package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class baekjun17779 {
	static int N;
    static int[][] A;
    static ArrayList<int[]> boundary;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        A = new int[N][N];
        boundary = new ArrayList<>();
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) A[row][col] = scanner.nextInt();
        }
    }

    static void solution() {
        int x = 0, y = 0, answer = Integer.MAX_VALUE;
        for(x = 0; x < N; x++) {
            for(y = 0; y < N; y++) {
                int d1 = 0, d2 = 0;
                for(d1 = 1; d1 < N; d1++) {
                    if(y - d1 < 0) continue;
                    for(d2 = 1; d2 < N; d2++) {
                        if(y + d2 >= N) continue;
                        if(x + d1 + d2 >= N) continue;
                        boundary = new ArrayList<>();
                        answer = Math.min(answer, simulation(x, y, d1, d2));
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static int simulation(int x, int y, int d1, int d2) {
        getBoundary(x, y, d1, d2);
        return getMaxDif(x, y, d1, d2);
    }

    static int getMaxDif(int x, int y, int d1, int d2) {
        int[] population = new int[5];
        boolean[][] visited = new boolean[N][N];
        population[4] = getFifthArea(visited);
        population[0] = getFirstArea(x, y, d1, visited);
        population[1] = getSecondArea(x, y, d2, visited);
        population[2] = getThirdArea(x, y, d1, d2, visited);
        population[3] = getFourthArea(x, y, d1, d2, visited);
        Arrays.sort(population);
        return population[4] - population[0];
    }

    static int getFirstArea(int x, int y, int d1, boolean[][] visited) {
        int total = 0;
        for(int row = 0; row < x + d1; row++) {
            for(int col = 0; col <= y; col++) {
                total = calcPopulation(visited, total, row, col);
            }
        }
        return total;
    }

    static int getSecondArea(int x, int y, int d2, boolean[][] visited) {
        int total = 0;
        for(int row = 0; row <= x + d2; row++) {
            for(int col = y + 1; col < N; col++) {
                total = calcPopulation(visited, total, row, col);
            }
        }
        return total;
    }

    static int getThirdArea(int x, int y, int d1, int d2, boolean[][] visited) {
        int total = 0;
        for(int row = x + d1; row < N; row++) {
            for(int col = 0; col < y - d1 + d2; col++) {
                total = calcPopulation(visited, total, row, col);
            }
        }
        return total;
    }

    static int getFourthArea(int x, int y, int d1, int d2, boolean[][] visited) {
        int total = 0;
        for(int row = x + d2 + 1; row < N; row++) {
            for(int col = y - d1 + d2; col < N; col++) {
                total = calcPopulation(visited, total, row, col);
            }
        }
        return total;
    }

    static int getFifthArea(boolean[][] visited) {
        Collections.sort(boundary, new Comparator<int[]>() {
            @Override
            public int compare(int[] b1, int[] b2) {
                if(b1[0] != b2[0]) return b1[0] - b2[0];
                return b1[1] - b2[1];
            }
        });
        int total = 0;
        for(int idx = 0; idx < boundary.size(); idx++) {
            ArrayList<int[]> vertex = new ArrayList<>();
            int[] cur = boundary.get(idx);
            vertex.add(cur);
            for(int next = idx + 1; next < boundary.size(); next++) {
                int[] nextBoundary = boundary.get(next);
                if(nextBoundary[0] != vertex.get(vertex.size() - 1)[0]) {
                    idx = next - 1;
                    break;
                } else if(nextBoundary[1] != vertex.get(vertex.size() - 1)[1]) {
                    vertex.add(nextBoundary);
                }
            }
            if(vertex.size() == 1) {
                int row = vertex.get(0)[0], col = vertex.get(0)[1];
                total = calcPopulation(visited, total, row, col);
                continue;
            }
            int row = vertex.get(0)[0];
            for(int col = vertex.get(0)[1]; col <= vertex.get(1)[1]; col++) {
                total = calcPopulation(visited, total, row, col);
            }
        }
        return total;
    }

    private static int calcPopulation(boolean[][] visited, int total, int row, int col) {
        if(!visited[row][col]) {
            visited[row][col] = true;
            total += A[row][col];
        }
        return total;
    }

    static void getBoundary(int x, int y, int d1, int d2) {
        for(int num = 0; num <= d1; num++) {
            boundary.add(new int[] {x + num, y - num});
            boundary.add(new int[] {x + d2 + num, y + d2 - num});
        }
        for(int num = 0; num <= d2; num++) {
            boundary.add(new int[] {x + num, y + num});
            boundary.add(new int[] {x + d1 + num, y - d1 + num});
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
