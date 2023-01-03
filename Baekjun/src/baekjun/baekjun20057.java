package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun20057 {
	static int N, answer;
    static int[] tornado;
    static int[] direction = {2, 1, 0, 3}; // 0: 우, 1: 하, 2: 좌, 3: 상
    static int[][] movedAmount = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int[][][] neighbor = {{{-1, 1}, {1, 1}, {-1, 0}, {1, 0}, {-2, 0}, {2, 0}, {-1, -1}, {1, -1}, {0, 2}},
                                    {{1, -1}, {1, 1}, {0, -1}, {0, 1}, {0, -2}, {0, 2}, {-1, -1}, {-1, 1}, {2, 0}},
                                    {{-1, -1}, {1, -1}, {-1, 0}, {1, 0}, {-2, 0}, {2, 0}, {-1, 1}, {1, 1}, {0, -2}},
                                    {{-1, -1}, {-1, 1}, {0, -1}, {0, 1}, {0, -2}, {0, 2}, {1, -1}, {1, 1}, {-2, 0}}};
    static double[] percent = {0.1, 0.1, 0.07, 0.07, 0.02, 0.02, 0.01, 0.01, 0.05};
    static int[][] map;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        map = new int[N][N];
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) map[row][col] = scanner.nextInt();
        }
    }

    static void solution() {
        answer = 0;
        tornado = new int[] {N / 2, N / 2};
        int count = 0;
        for(int moveNum = 1; moveNum < N; moveNum++) {
            for(int repeat = 0; repeat < (moveNum == N - 1 ? 3 : 2); repeat++) {
                for(int cont = 0; cont < moveNum; cont++) {
                    int dir = direction[count % 4];
                    int[] movedLoc = new int[] {tornado[0] + movedAmount[dir][0], tornado[1] + movedAmount[dir][1]};
                    move(movedLoc, dir);
                    tornado = movedLoc;
                }
                count++;
            }
        }
        System.out.println(answer);
    }

    static void move(int[] loc, int dir) {
        int sand = map[loc[0]][loc[1]], original = map[loc[0]][loc[1]];
        for(int idx = 0; idx < percent.length; idx++) {
            int[] spreadLoc = new int[] {loc[0] + neighbor[dir][idx][0], loc[1] + neighbor[dir][idx][1]};
            int spreadAmount = (int)Math.floor(original * percent[idx]);
            sand -= spreadAmount;
            if(!isInMap(spreadLoc[0], spreadLoc[1])) {
                answer += spreadAmount;
                continue;
            }
            map[spreadLoc[0]][spreadLoc[1]] += spreadAmount;
        }
        if(isInMap(loc[0] + movedAmount[dir][0], loc[1] + movedAmount[dir][1])) {
            map[loc[0] + movedAmount[dir][0]][loc[1] + movedAmount[dir][1]] += sand;
        } else {
            answer += sand;
        }
        map[loc[0]][loc[1]] = 0;
    }

    static boolean isInMap(int x, int y) {
        if(x < 0 || x >= N || y < 0 || y >= N) return false;
        return true;
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
