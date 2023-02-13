package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class baekjun16946 {
	static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] map, mass;
    static boolean[][] visited;
    static ArrayList<int[]> walls;
    static HashMap<Integer, Integer> massNums;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new int[N][M];
        walls = new ArrayList<>();

        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();

            for(int col = 0; col < M; col++) {
                map[row][col] = info.charAt(col) - '0';

                if(map[row][col] == 1)
                    walls.add(new int[] {row, col});
            }
        }
    }

    static void solution() {
        getMassLoc();

        findReachableLocNum();

        System.out.print(sb);
    }

    static void findReachableLocNum() {
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if(map[row][col] == 1) {
                    HashSet<Integer> neighborMass = new HashSet<>();
                    for(int dir = 0; dir < 4; dir++) {
                        int cx = row + dx[dir], cy = col + dy[dir];

                        if(isInMap(cx, cy)) {
                            if (map[cx][cy] == 0 && mass[cx][cy] != 0)
                                neighborMass.add(mass[cx][cy]);
                        }
                    }

                    Iterator<Integer> iter = neighborMass.iterator();
                    int sum = 1;

                    while(iter.hasNext()) {
                        int neighbor = iter.next();
                        sum += massNums.get(neighbor);
                    }

                    sb.append(sum % 10);
                }

                else if(map[row][col] == 0) {
                    sb.append('0');
                }
            }
            sb.append('\n');
        }
    }

    static void getMassLoc() {
        visited = new boolean[N][M];
        mass = new int[N][M];
        massNums = new HashMap<>();

        int massNum = 0;

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if(!visited[row][col] && map[row][col] == 0) {
                    massNum++;
                    massNums.put(massNum, 0);
                    dfs(row, col, massNum);
                }
            }
        }
    }

    static void dfs(int x, int y, int massNum) {
        if(visited[x][y]) return;

        visited[x][y] = true;
        mass[x][y] = massNum;
        massNums.put(massNum, massNums.get(massNum) + 1);

        for(int dir = 0; dir < 4; dir++) {
            int cx = x + dx[dir], cy = y + dy[dir];

            if(isInMap(cx, cy)) {
                if(!visited[cx][cy] && map[cx][cy] == 0) {
                    dfs(cx ,cy, massNum);
                }
            }
        }
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < N && y >= 0 && y < M) return true;
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
