import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class MagicForestSearch {
    private static Reader scanner;
    private static int[][] directions = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int point;
    private static int rSize;
    private static int cSize;
    private static int spiritCount;

    private static Gollum[] gollums;
    private static int[] gollumCenterRows;
    private static int[][] forest;

    private static void input() {
        scanner = new Reader();
        point = 0;

        rSize = scanner.nextInt();
        cSize = scanner.nextInt();
        spiritCount = scanner.nextInt();
        gollums = new Gollum[spiritCount + 1];
        gollumCenterRows = new int[spiritCount + 1];
        forest = new int[rSize + 3][cSize];

        for(int count = 1; count <= spiritCount; count++) {
            gollums[count] = new Gollum(1, scanner.nextInt() - 1, scanner.nextInt());
            gollumCenterRows[count] = 1;
        }
    }

    private static void solution() {
        for(int gollumIdx = 1; gollumIdx < gollums.length; gollumIdx++) {
            if(moveGollum(gollumIdx, gollums[gollumIdx])) {
                point += findPoint(gollumCenterRows[gollumIdx], gollums[gollumIdx].column);
            }
        }
        System.out.println(point);
    }

    private static boolean moveGollum(int idx, Gollum gollum) {
        int row = 1;
        boolean canMove = true;

        CenterFor:
        for(row = 2; row < rSize + 2; row++) {
            // 중심 좌표
            int curRow = row;
            int curCol = gollum.column;

            DirFor:
            for(int dir = 0; dir < directions.length; dir++) {
                int neighborRow = curRow + directions[dir][0];
                int neighborCol = curCol + directions[dir][1];

                if(isInMap(neighborRow, neighborCol)) {
                    if(forest[neighborRow][neighborCol] != 0) {
                        if(canMoveOtherWay(idx, curRow - 1, curCol - 1)) {
                            gollum.setColumn(curCol - 1);
                            gollum.rotateExit(false);
//                            row++;
                            break;
                        } else if(canMoveOtherWay(idx, curRow - 1, curCol + 1)) {
                            gollum.setColumn(curCol + 1);
                            gollum.rotateExit(true);
//                            row++;
                            break;
                        } else {
                            if(row - 1 < 4) {
                                canMove = false;
                            }
                            row--;
                            break CenterFor;
                        }
                    }
                }
            }
        }

        if(canMove) {
            row = row >= rSize + 2 ? rSize + 1 : row;
            gollumCenterRows[idx] = row;
            forest[row][gollum.column] = idx;
            for(int dir = 0; dir < directions.length; dir++) {
                forest[row + directions[dir][0]][gollum.column + directions[dir][1]] = idx;
            }
        } else {
            directions = new int[rSize + 3][cSize];
        }

        return canMove;
    }

    private static int findPoint(int x, int y) {
        Queue<Spirit> spirits = new PriorityQueue<>();
        boolean[] visited = new boolean[gollums.length];
        int idx = forest[x][y];

        spirits.offer(new Spirit(x, y, idx));
        visited[idx] = true;
        int point = 0;

        while(!spirits.isEmpty()) {
            Spirit cur = spirits.poll();
            Gollum gollum = gollums[cur.idx];
            int exitX = gollumCenterRows[cur.idx] + directions[gollum.exit][0];
            int exitY = gollum.column + directions[gollum.exit][1];
            point = Math.max(point, (gollumCenterRows[cur.idx] - 2) + 1);

            for(int dir = 0; dir < directions.length; dir++) {
                int dx = exitX + directions[dir][0];
                int dy = exitY + directions[dir][1];
                if(isInMap(dx, dy) &&  forest[dx][dy] != 0 && forest[dx][dy] != cur.idx && !visited[forest[dx][dy]]) {
                    visited[forest[dx][dy]] = true;
                    spirits.offer(new Spirit(dx, dy, forest[dx][dy]));
                    point = Math.max(point, dx);
                }
            }
        }

        return point;
    }

    private static boolean isInMap(int x, int y) {
        return x >= 0 && x < rSize + 3 && y >= 0 && y < cSize;
    }

    private static boolean canMoveOtherWay(int idx, int x, int y) {
        if(forest[x][y] != 0) return false;
        int nextRow = x + 1;
        int nextCol = y;
        for(int dir = 0; dir < directions.length; dir++) {
            int neighborRow = x + directions[dir][0];
            int neighborCol = y + directions[dir][1];
            int nextNeighborRow = nextRow + directions[dir][0];
            int nextNeighborCol = nextCol + directions[dir][1];

            if(!isInMap(neighborRow, neighborCol) || forest[neighborRow][neighborCol] != 0) {
                return false;
            }
            if(!isInMap(nextNeighborRow, nextNeighborCol) || forest[nextNeighborRow][nextNeighborCol] != 0) {
                return false;
            }
        }

        return true;
    }

    static class Gollum {
        int row;
        int column;
        int exit; // 0 : 북, 1 : 동, 2 : 남, 3 : 서

        public Gollum(int row, int column, int exit) {
            this.column = column;
            this.exit = exit;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public void rotateExit(boolean isCW) {
            if(isCW) { // 시계 방향이면
                exit = (exit + 1) % 4;
            } else { // 반시계 방향이면
                int tempExit = exit - 1;
                if(tempExit < 0) tempExit = 4 + tempExit;
                exit = tempExit;
            }
        }
    }

    static class Spirit implements Comparable<Spirit> {
        int x;
        int y;
        int idx;

        public Spirit(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }

        @Override
        public int compareTo(Spirit s) {
            return Integer.compare(s.y, y);
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
            while(st == null || !st.hasMoreTokens()) {
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
