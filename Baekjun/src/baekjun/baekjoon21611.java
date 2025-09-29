package baekjun;

import java.io.*;
import java.util.*;

public class baekjoon21611 {
    private static int[][] directions = new int[][] {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    private static int size;
    private static int blizzardCount;
    private static int maxMarvelNumber;
    private static int[] start;
    private static int[] explosionCount;
    private static int[][] map;
    private static Blizzard[] blizzards;

    private static void input() {
        Reader scanner = new Reader();

        size = scanner.nextInt();
        blizzardCount = scanner.nextInt();
        start = new int[] {size / 2, size / 2};
        map = new int[size][size];
        blizzards = new Blizzard[blizzardCount];

        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                map[row][col] = scanner.nextInt();
                maxMarvelNumber = Math.max(maxMarvelNumber, map[row][col]);
            }
        }

        explosionCount = new int[maxMarvelNumber + 1];

        for(int idx = 0; idx < blizzardCount; idx++) {
            int direction = scanner.nextInt();
            int distance = scanner.nextInt();
            blizzards[idx] = new Blizzard(direction, distance);
        }
    }

    private static void solution() {
        for(Blizzard blizzard : blizzards) {
            doBlizzard(blizzard);
            moveMarvel();
            explodeMarvel();
            moveMarvel();
        }
        explodeMarvel();

        long answer = 0L;
        for(int idx = 1; idx <= 3; idx++) {
            answer += idx * explosionCount[idx];
        }
        System.out.println(answer);
    }

    private static void explodeMarvel() {
        Stack<int[]> explodePositions = new Stack<>();
        Stack<Integer> explodeNumberCounts = new Stack<>();
        int[] pos = new int[] {start[0], start[1]};
        int dir = 0;
        int distance = 1;
        int marvelNumber = 0;
        int explodeNumberCount = 0;

        outer:
        while(pos[0] > 0 && pos[1] > 0) {
            for(int count = 1; count <= 2; count++) {
                for(int dist = 1; dist <= distance; dist++) {
                    int x = pos[0] + directions[dir][0];
                    int y = pos[1] + directions[dir][1];

                    if(map[x][y] == 0) {
                        marvelNumber = 0;
                        explodeNumberCount = 0;
                        continue;
                    }

                    if(marvelNumber == 0) {
                        marvelNumber = map[x][y];
                        explodeNumberCount = 1;
                    } else {
                        if(marvelNumber == map[x][y]) {
                            explodeNumberCount++;
                        } else {
                            if(explodeNumberCount >= 4) {
                                for(int i = 0; i < explodeNumberCount; i++) {
                                    int[] explodePosition = explodePositions.pop();
                                    map[explodePosition[0]][explodePosition[1]] = 0;
                                }
                                explosionCount[marvelNumber] += explodeNumberCount;

                                int[] prevPosition = explodePositions.peek();
                                if(map[x][y] == map[prevPosition[0]][prevPosition[1]]) {
                                    marvelNumber = map[x][y];
                                    explodeNumberCount = explodeNumberCounts.pop();
                                    explodeNumberCount++;
                                } else {
//                                    explodeNumberCounts.push(explodeNumberCount);
                                    explodeNumberCount = 1;
                                    marvelNumber = map[x][y];
                                }
//                                pos[0] = x;
//                                pos[1] = y;
//                                if(pos[0] <= 0 && pos[1] <= 0) break outer;
//                                continue;
                            } else {
                                explodeNumberCounts.push(explodeNumberCount);
                                explodeNumberCount = 1;
                                marvelNumber = map[x][y];
                            }
                        }
                    }
                    explodePositions.add(new int[] {x, y});
                    pos[0] = x;
                    pos[1] = y;
                    if(pos[0] <= 0 && pos[1] <= 0) break outer;
                }
                dir = (dir + 1) % 4;
            }
            distance++;
        }
        explodeNumberCounts.push(explodeNumberCount);
    }

    private static void moveMarvel() {
        Queue<Integer> marvels = new LinkedList<>();
        int dir = 0;
        int distance = 1;
        int[] pos = new int[] {start[0], start[1]};
        outer:
        while(pos[0] > 0 || pos[1] > 0) {
            for(int count = 1; count <= 2; count++) {
                for(int dist = 1; dist <= distance; dist++) {
                    int x = pos[0] + directions[dir][0];
                    int y = pos[1] + directions[dir][1];
                    if(map[x][y] != 0) marvels.offer(map[x][y]);
                    pos[0] = x;
                    pos[1] = y;
                    if(pos[0] <= 0 && pos[1] <= 0) break outer;
                }
                dir = (dir + 1) % 4;
            }
            distance++;
        }

        pos = new int[] {start[0], start[1]};
        dir = 0;
        distance = 1;
        outer:
        while(pos[0] > 0 || pos[1] > 0) {
            for(int count = 1; count <= 2; count++) {
                for(int dist = 1; dist <= distance; dist++) {
                    int x = pos[0] + directions[dir][0];
                    int y = pos[1] + directions[dir][1];
                    if(!marvels.isEmpty()) {
                        map[x][y] = marvels.poll();
                    } else {
                        map[x][y] = 0;
                    }
                    pos[0] = x;
                    pos[1] = y;
                    if((pos[0] <= 0 && pos[1] <= 0)) break outer;
                }
                dir = (dir + 1) % 4;
            }
            distance++;
        }
    }

    private static void doBlizzard(Blizzard blizzard) {
        int[][] directions = new int[][] {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for(int distance = 1; distance <= blizzard.distance; distance++) {
            int x = start[0] + (directions[blizzard.direction][0] * distance);
            int y = start[1] + (directions[blizzard.direction][1] * distance);
            map[x][y] = 0;
        }
    }

    static class Blizzard {
        int direction;
        int distance;

        public Blizzard(int direction, int distance) {
            this.direction = direction;
            this.distance = distance;
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
