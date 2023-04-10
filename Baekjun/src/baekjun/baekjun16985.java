package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun16985 {
	static final int SIZE = 5;
    static boolean[][][] maze, copyMaze;
    static boolean[] visited;
    static int[] boardOrder;
    static int answer;

    static void input() {
        Reader scanner = new Reader();

        maze = new boolean[SIZE][SIZE][SIZE];
        copyMaze = new boolean[SIZE][SIZE][SIZE];
        visited = new boolean[SIZE];
        boardOrder = new int[SIZE];

        for(int height = 0; height < SIZE; height++) {
            for(int row = 0; row < SIZE; row++) {
                for(int col = 0; col < SIZE; col++) {
                    int info = scanner.nextInt();
                    if(info == 1) {
                        maze[height][row][col] = true;
                        copyMaze[height][row][col] = true;
                    }
                }
            }
        }
    }

    static void solution() {
        answer = Integer.MAX_VALUE;
        recFunc(0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void recFunc(int boardIdx) {
        if(boardIdx == SIZE) {
            makeMaze(boardOrder);
            rotateMaze(0);
            return;
        }

        for(int idx = 0; idx < SIZE; idx++) {
            if(!visited[idx]) {
                visited[idx] = true;
                boardOrder[boardIdx] = idx;
                recFunc(boardIdx + 1);
                visited[idx] = false;
            }
        }
    }

    static void rotateBoard(int height) {
        boolean[][] temp = new boolean[SIZE][SIZE];
        for(int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++)
                temp[row][col] = copyMaze[height][(SIZE - 1) - col][row];
        }

        System.arraycopy(temp, 0, copyMaze[height], 0, SIZE);
    }

    static void rotateMaze(int height) {
        if(height == SIZE) {
            bfs();
            return;
        }

        rotateMaze(height + 1);

        rotateBoard(height);
        rotateMaze(height + 1);

        rotateBoard(height);
        rotateMaze(height + 1);

        rotateBoard(height);
        rotateMaze(height + 1);

        rotateBoard(height);
    }

    static void bfs() {
        if(!copyMaze[0][0][0]  || !copyMaze[SIZE - 1][SIZE - 1][SIZE - 1]) return;

        int[][] direction = {{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {-1, 0, 0}, {1, 0, 0}};
        Queue<Position> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[copyMaze.length][copyMaze[0].length][copyMaze[0][0].length];

        queue.offer(new Position(0, 0, 0, 0));
        visited[0][0][0] = true;

        while(!queue.isEmpty()) {
            Position cur = queue.poll();
            if(cur.h == SIZE - 1 && cur.x == SIZE - 1 && cur.y == SIZE - 1) {
                answer = Math.min(answer, cur.moveNum);
                return;
            }

            for(int dir = 0; dir < direction.length; dir++) {
                int ch = cur.h + direction[dir][0], cx = cur.x + direction[dir][1], cy = cur.y + direction[dir][2];

                if(isInMap(ch, cx, cy)) {
                    if(!visited[ch][cx][cy] && copyMaze[ch][cx][cy]) {
                        visited[ch][cx][cy] = true;
                        queue.offer(new Position(ch, cx, cy, cur.moveNum + 1));
                    }
                }
            }
        }
    }

    static boolean isInMap(int h, int x, int y) {
        if(h >= 0 && h < SIZE && x >= 0 && x < SIZE && y >= 0 && y < SIZE) return true;
        return false;
    }

    static void makeMaze(int[] boardOrder) {
        for(int h = 0; h < SIZE; h++) {
            for(int x = 0; x < SIZE; x++) {
                for(int y = 0; y < SIZE; y++)
                    copyMaze[h][x][y] = maze[boardOrder[h]][x][y];
            }
        }
    }

    static class Position {
        int x, y, h, moveNum;

        public Position(int h, int x, int y, int moveNum) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.moveNum = moveNum;
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
