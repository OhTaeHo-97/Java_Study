package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun23288 {
	final static int[] pairs = new int[] {0, 6, 5, 4, 3, 2, 1};
    // 0 : 동, 1 : 서, 2 : 남, 3 : 북
    final static int[] rotateCW = {2, 3, 1, 0}, rotateCCW = {3, 2, 0, 1}, reverseDirection = {1, 0, 3, 2};
    final static int[][] moveAmount = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    static int N, M, K;
    static int[][] map;
    static int[][] dice = {
            {0, 2, 0},
            {4, 1, 3},
            {0, 5, 0},
            {0, 6, 0}
    };
    static Dice diceLoc = new Dice(0, 0, 0);
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
        map = new int[N][M];

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++)
                map[row][col] = scanner.nextInt();
        }
    }

    static void solution() {
        int sum = 0;

        for(int count = 0; count < K; count++)
            sum += oneTurn();

        System.out.println(sum);
    }

    static int oneTurn() {
        move();
        int point = bfs(diceLoc.x, diceLoc.y);
        setDirection();

        return point;
    }

    static void setDirection() {
        if(dice[3][1] > map[diceLoc.x][diceLoc.y]) {
            diceLoc.direction = rotateCW[diceLoc.direction];
        } else if(dice[3][1] < map[diceLoc.x][diceLoc.y]) {
            diceLoc.direction = rotateCCW[diceLoc.direction];
        }
    }

    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int num = map[x][y], count = 1;

        queue.offer(new int[] {x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int dir = 0; dir < 4; dir++) {
                int cx = cur[0] + dx[dir], cy = cur[1] + dy[dir];

                if(isInMap(cx, cy) && map[cx][cy] == num && !visited[cx][cy]) {
                    visited[cx][cy] = true;
                    count++;
                    queue.offer(new int[] {cx, cy});
                }
            }
        }

        return num * count;
    }

    static void move() {
        int x = diceLoc.x + moveAmount[diceLoc.direction][0], y = diceLoc.y + moveAmount[diceLoc.direction][1];

        if(!isInMap(x, y)) diceLoc.direction = reverseDirection[diceLoc.direction];
        diceLoc.x += moveAmount[diceLoc.direction][0]; diceLoc.y += moveAmount[diceLoc.direction][1];

        if(diceLoc.direction == 0) moveEast();
        else if(diceLoc.direction == 1) moveWest();
        else if(diceLoc.direction == 2) moveSouth();
        else if(diceLoc.direction == 3) moveNorth();
    }

    static void moveEast() {
        dice[1][2] = dice[1][1];
        dice[1][1] = dice[1][0];

        dice[3][1] = pairs[dice[1][1]];
        dice[1][0] = pairs[dice[1][2]];
    }

    static void moveWest() {
        dice[1][0] = dice[1][1];
        dice[1][1] = dice[1][2];

        dice[3][1] = pairs[dice[1][1]];
        dice[1][2] = pairs[dice[1][0]];
    }

    static void moveSouth() {
        int temp = dice[3][1];

        for(int idx = 2; idx >= 0; idx--)
            dice[idx + 1][1] = dice[idx][1];

        dice[0][1] = temp;
    }

    static void moveNorth() {
        int temp = dice[0][1];

        for(int idx = 1; idx <= 3; idx++)
            dice[idx - 1][1] = dice[idx][1];

        dice[3][1] = temp;
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < N && y >= 0 && y < M) return true;
        return false;
    }

    static class Dice {
        int x, y, direction;

        public Dice(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
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
