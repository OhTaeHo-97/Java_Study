package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1113 {
    static int N, M, maxHeight, answer;
    static int[][] map;
    static boolean isVisitEdge;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new int[N][M];

        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < M; col++) {
                map[row][col] = info.charAt(col) - '0';
                maxHeight = Math.max(maxHeight, map[row][col]); // 최대 높이 구하기
            }
        }
    }

    static void solution() {
        // 높이 제한을 2부터 최대 높이까지 두면서 각 높이 제한에서 구역을 구해나가며 물의 양을 구한다
        for(int height = 2; height <= maxHeight; height++) {
            boolean[][] visited = new boolean[N][M]; // 방문 체크 배열
            // 가장자리를 제외한 곳을 순회하면서 BFS를 통해 구역을 정하고 그 구역의 수를 누적해나가며 물의 양을 구한다
            for(int row = 1; row < N - 1; row++) {
                for(int col = 1; col < M - 1; col++) {
                    isVisitEdge = false; // 가장자리에 다다랐는지 확인하는 변수
                    // 아직 방문하지 않았고 높이가 높이 제한보다 낮은 경우에 bfs를 통해 구역에 속하는 개수를 구하여 누적한다
                    if(map[row][col] < height && !visited[row][col]) {
                        visited[row][col] = true;
                        answer += bfs(row, col, height, visited);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static int bfs(int x, int y, int height, boolean[][] visited) {
        int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x, y});

        int size = 1; // 구역의 개수
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 상하좌우를 확인하여 구역을 확인한다
            for(int dir = 0; dir < 4; dir++) {
                int cx = cur[0] + dx[dir], cy = cur[1] + dy[dir];

                // 수영장 내라면(가장자리에 도달하지 않았다면)
                // 아직 방문하지 않았고 높이 제한보다 낮은 구역에 대해 방문 체크를 진행하고 size를 누적해나가며 구역의 개수를 구한다
                if(isInMap(cx, cy)) {
                    if(!visited[cx][cy] && map[cx][cy] < height) {
                        visited[cx][cy] = true;
                        queue.offer(new int[] {cx, cy});
                        size++;
                    }
                } else {
                    // 가장자리에 도달했다면
                    // 이를 표현하기 위해 isVisitEdge 값을 true로 변경한다
                    isVisitEdge = true;
                }
            }
        }

        // 만약 isVisitEdge가 true라면 최소 한 구역 이상 가장자리에 도달했다는 뜻이고
        // 이는 물이 결국 가장자리 쪽으로 나간다는 뜻이 되므로 물을 부을 수 없다는 뜻이 된다
        // 그러므로 이럴 때에는 0을 반환하고 isVisitEdge가 false일 때만 누적한 구역의 개수를 반환한다
        if(isVisitEdge) size = 0;
        return size;
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
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
