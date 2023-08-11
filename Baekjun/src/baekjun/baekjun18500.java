package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun18500 {
    static int R, C, N;
    static char[][] map;
    static int[] heights;

    static void input() {
        Reader scanner = new Reader();

        R = scanner.nextInt();
        C = scanner.nextInt();
        map = new char[R][C];
        for(int row = 0; row < R; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < C; col++)
                map[row][col] = info.charAt(col);
        }

        N = scanner.nextInt();
        heights = new int[N];
        // 높이가 바닥에서부터의 높이이기 때문에 이를 인덱스로 변경하여 저장한다
        for(int idx = 0; idx < N; idx++)
            heights[idx] = R - scanner.nextInt();
    }

    static void solution() {
        boolean isLeft = true; // 왼쪽에서 던지는 상황이라면 true, 오른쪽에서 던지는 상황이라면 false
        for(int height : heights) {
            oneTurn(height, isLeft);
            isLeft = !isLeft;
        }

        printMap();
    }

    static void printMap() {
        StringBuilder sb = new StringBuilder();
        for(int row = 0; row < R; row++) {
            for(int col = 0; col < C; col++)
                sb.append(map[row][col]);
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static void oneTurn(int height, boolean isLeft) {
        throwStick(height, isLeft); // 막대기를 던지는 함수

        // 동굴의 위치들을 모두 순회하면서 클러스터를 찾고 해당 클러스터가 아래로 떨어져야 한다면 떨어뜨리며
        // 동굴의 상태를 갱신한다
        boolean[][] visited = new boolean[R][C];
        for(int row = 0; row < R; row++) {
            for(int col = 0; col < C; col++) {
                if(!visited[row][col] && map[row][col] == 'x') {
                    // 만약 찾은 클러스터가 아래로 떨어졌다면
                    // 문제에서 두 개 이상의 클러스터가 동시에 떨어지는 경우가 없다고 하였기 때문에
                    // 더이상 떨어지는 클러스터가 없을 것이니 다음 턴으로 넘어간다
                    if(findClusterAndFallDownCluster(row, col, visited)) return;
                }
            }
        }
    }

    static boolean findClusterAndFallDownCluster(int x, int y, boolean[][] visited) {
        int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> cluster = new ArrayList<>(); // 클러스터에 해당하는 위치들을 저장하는 List

        visited[x][y] = true;
        queue.offer(new int[] {x, y});

        // 현재 만들어진 클러스터에서 가장 아래에 해당하는 행 좌표
        int lowestLoc = Integer.MIN_VALUE;
        // BFS를 통해 클러스터를 찾는다
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            cluster.add(new int[] {cur[0], cur[1]});
            lowestLoc = Math.max(lowestLoc, cur[0]);

            for(int dir = 0; dir < dx.length; dir++) {
                int cx = cur[0] + dx[dir], cy = cur[1] + dy[dir];

                if(isInMap(cx, cy)) {
                    if(!visited[cx][cy] && map[cx][cy] == 'x') {
                        visited[cx][cy] = true;
                        queue.offer(new int[] {cx, cy});
                    }
                }
            }
        }

        // 만약 가장 아래 행 좌표가 바닥에 해당하는 좌표가 아니라면
        // 해당 클러스터는 아래로 떨어져야 할 수 있기 때문에 해당 클러스터를 아래로 떨어뜨려본다
        if(lowestLoc != R - 1) {
            fallDownCluster(cluster);
            return true;
        }

        return false;
    }

    static void fallDownCluster(List<int[]> cluster) {
        // 클러스터를 아래로 떨어뜨릴 양을 의미한다
        int fallAmount = 1;

        // 클러스터를 아래로 떨어뜨려야 하니 우선 현재 클러스터의 위치를 빈칸으로 변경해준댜
        for(int[] loc : cluster)
            map[loc[0]][loc[1]] = '.';

        Loop:
        while(true) {
            // 클러스터 내의 위치들을 순회하면서 fallAmount만큼 떨어뜨려보고
            // 떨어뜨린 후에 위치가 바닥이거나 미네랄을 만났다면
            // 그때의 fallAmount만큼 클러스터를 떨어뜨린다
            for(int[] loc : cluster) {
                int x = loc[0] + fallAmount, y = loc[1];

                if(x == R || map[x][y] == 'x') {
                    fallAmount--;
                    break Loop;
                }
            }

            fallAmount++;
        }

        for(int[] loc : cluster)
            map[loc[0] + fallAmount][loc[1]] = 'x';
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    static void throwStick(int height, boolean isLeft) {
        if(isLeft) throwStickToRight(height);
        else throwStickToLeft(height);
    }

    static void throwStickToRight(int height) {
        for(int col = 0; col < C; col++) {
            if(map[height][col] == 'x') {
                map[height][col] = '.';
                break;
            }
        }
    }

    static void throwStickToLeft(int height) {
        for(int col = C - 1; col >= 0; col--) {
            if(map[height][col] == 'x') {
                map[height][col] = '.';
                break;
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
