package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun2933 {
	static int R, C, N;
    static char[][] map;
    static int[] stickHeights;

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
        stickHeights = new int[N];

        for(int idx = 0; idx < N; idx++)
            stickHeights[idx] = scanner.nextInt();
    }

    static void solution() {
        for(int idx = 0; idx < N; idx++) {
            int height = stickHeights[idx];
            turn(height, idx % 2 == 0 ? 0 : 1);
        }

        StringBuilder sb = print(map);
        System.out.println(sb);
    }

    static StringBuilder print(char[][] map) {
        StringBuilder sb = new StringBuilder();
        for(int row = 0; row < R; row++) {
            for(int col = 0; col < C; col++)
                sb.append(map[row][col]);
            sb.append('\n');
        }

        return sb;
    }

    // 한 턴을 나타냄
    static void turn(int height, int direction) {
        // 막대기를 던진 후에 사라지는 미네랄을 찾아 제거
        // direction이 0이라면 왼쪽에서 오른쪽으로, direction이 1이라면 오른쪽에서 왼쪽으로 막대기가 날아옴
        removeMineral(height, direction, map);

        // 각 위치를 방문하였는지 방문 체크를 위한 배열
        boolean[][] visited = new boolean[R][C];

        for(int row = 0; row < R; row++) {
            for(int col = 0; col < C; col++) {
                // 각 위치를 돌면서 만약 미네랄이 있고 아직 방문하지 않은 곳이라면
                if(map[row][col] == 'x' && !visited[row][col]) {
                    // 클러스터를 찾고 만약 해당 클러스터가 떠있는 상태라면
                    // 해당 클러스터를 아래로 떨어뜨리고 다음 막대기를 던짐
                    // 문제에서 두 개 또는 그 이상의 클러스터가 동시에 떨어지는 경우가 없다고 하였으므로
                    // 떠있는 클러스터를 찾았다면 떨어뜨리고 바로 다음 막대기를 던지도록 넘어가도 됨
                    if(findCluster(row, col, visited)) return;
                }
            }
        }
    }

    static void fallCluster(ArrayList<int[]> cluster) {
        int fallNum = 1; // 얼마나 떨어져야 하는지를 나타내는 변수

        // 우선 동굴에서 클러스터에 있는 미네랄들의 위치를 빈칸으로 변경
        for(int[] loc : cluster)
            map[loc[0]][loc[1]] = '.';

        // 떨어져야 하는 수를 1씩 증가시키면서 땅이나 다른 미네랄에 닿는지 확인
        // 땅에 닿거나 다른 미네랄에 닿으면 해당 루프는 종료
        Loop:
        while(true) {
            for(int[] loc : cluster) {
                int x = loc[0] + fallNum, y = loc[1];

                if(x == R || map[x][y] == 'x') {
                    fallNum--;
                    break Loop;
                }
            }

            fallNum++;
        }

        // 떨어져야 하는 수만큼 클러스터의 모든 미네랄들을 떨어뜨림
        for(int[] loc : cluster)
            map[loc[0] + fallNum][loc[1]] = 'x';
    }

    static boolean findCluster(int x, int y, boolean[][] visited) {
        int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

        Queue<int[]> queue = new LinkedList<>();
        // 클러스터에 들어가는 미네랄들을 넣기 위한 ArrayList
        ArrayList<int[]> minerals = new ArrayList<>();
        // 해당 클러스터의 가장 낮은 높이에 있는 미네랄의 높이를 나타내는 변수
        int lowestLoc = Integer.MIN_VALUE;

        queue.offer(new int[] {x, y});
        visited[x][y] = true; // 방문 체크를 하고 BFS 실행

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 클러스터에 들어가는 미네랄을 ArrayList에 추가하고
            // lowestLoc 값을 갱신
            minerals.add(new int[] {cur[0], cur[1]});
            lowestLoc = Math.max(lowestLoc, cur[0]);

            for(int dir = 0; dir < 4; dir++) {
                // 인접한 위치를 구함
                int cx = cur[0] + dx[dir], cy = cur[1] + dy[dir];
                // 인접한 위치가 동굴 안에 존재하고 아직 방문하지 않았으며 미네랄이 있는 위치라면
                // Queue에 해당 위치를 넣어 다음 탐색에 이용하고 방문 체크를 진행
                if(isInMap(cx, cy)) {
                    if(map[cx][cy] == 'x' && !visited[cx][cy]) {
                        visited[cx][cy] = true;
                        queue.offer(new int[] {cx, cy});
                    }
                }
            }
        }

        // 만약 lowestLoc의 값이 R - 1이 아니라면
        // 바닥에 닿지 않은 상태이므로 떠있는 상태
        // 이 때 클러스터를 아래로 떨어뜨림
        if(lowestLoc != R - 1) {
            fallCluster(minerals);
            return true;
        }

        // 해당 클러스터는 떠있지 않음을 나타내기 위해 false 반환
        return false;
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < R && y >= 0 && y < C) return true;
        return false;
    }

    static void removeMineral(int height, int direction, char[][] map) {
        if(direction == 0) { // direction이 0이라면 왼쪽에서 오른쪽으로 막대기를 던짐
            for(int col = 0; col < C; col++) {
                if(map[R - height][col] == 'x') {
                    map[R - height][col] = '.';
                    break;
                }
            }
        } else if(direction == 1) { // direction이 0이라면 오른쪽에서 왼쪽으로 막대기를 던짐
            for(int col = C - 1; col >= 0; col--) {
                if(map[R - height][col] == 'x') {
                    map[R - height][col] = '.';
                    break;
                }
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
