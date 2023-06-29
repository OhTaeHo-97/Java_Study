package hyundai_mobis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class ParkingSystem {
    static int N, M;
    static int[][] parkingLot;
    static List<List<int[]>> parkingLotZones;
    static List<Integer> scores;
    static int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        parkingLot = new int[N][M];
        parkingLotZones = new ArrayList<>();
        scores = new ArrayList<>();

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++)
                parkingLot[row][col] = scanner.nextInt();
        }
    }

    static void solution() {
        boolean[][] visited = new boolean[N][M];

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if(!visited[row][col] && parkingLot[row][col] != 1) {
                    visited[row][col] = true;

                    List<int[]> zone = new ArrayList<>();
                    zone.add(new int[] {row, col});
                    parkingLotZones.add(zone);

                    dfs(row, col, visited, zone);
                }
            }
        }

        calcScore();

        Collections.sort(scores);

        System.out.println(scores.get(scores.size() - 1));
    }

    static void calcScore() {
        for(int idx = 0; idx < parkingLotZones.size(); idx++) {
            int score = 0;
            for(int[] loc : parkingLotZones.get(idx)) {
                if(parkingLot[loc[0]][loc[1]] == 0) score++;
                else if(parkingLot[loc[0]][loc[1]] == 2) score -= 2;
            }

            scores.add(score);
        }
    }

    static void dfs(int x, int y, boolean[][] visited, List<int[]> zone) {
        for(int dir = 0; dir < dx.length; dir++) {
            int cx = x + dx[dir], cy = y + dy[dir];

            if(isInMap(cx, cy)) {
                if(!visited[cx][cy] && parkingLot[cx][cy] != 1) {
                    visited[cx][cy] = true;
                    zone.add(new int[] {cx, cy});
                    dfs(cx, cy, visited, zone);
                }
            }
        }
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < N & y >= 0 && y < M;
    }

    public static void main(String[] args) throws Exception {
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
                } catch(IOException e) {
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
