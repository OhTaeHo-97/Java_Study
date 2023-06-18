package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun13459 {
    static int N, M;
    static char[][] board;
    static int[] hole;
    static Turn init;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        board = new char[N][M];

        int[] red = null, blue = null;

        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < M; col++) {
                board[row][col] = info.charAt(col);

                // 빨간 공이 있는 위치이거나 파란 공이 있는 위치라면 해당 위치는 빈 칸으로 변경
                if(board[row][col] == 'B') {
                    blue = new int[] {row, col};
                    board[row][col] = '.';
                }
                else if(board[row][col] == 'R') {
                    red = new int[] {row, col};
                    board[row][col] = '.';
                }
                else if(board[row][col] == 'O')
                    hole = new int[] {row, col};
            }
        }

        init = new Turn(red, blue, 0);
    }

    static void solution() {
        System.out.println(bfs(init));
    }

    static int bfs(Turn init) {
        Queue<Turn> queue = new LinkedList<>();
        // visited[빨간공 행 좌표][빨간공 열 좌표][파란공 행 좌표][파란공 열 좌표]
        //  -> 빨간공과 파란공이 위 좌표에 위치할 때를 방문했었는지에 대한 방문 체크 배열
        boolean[][][][] visited = new boolean[N][M][N][M];

        queue.offer(init);
        visited[init.red[0]][init.red[1]][init.blue[0]][init.blue[1]] = true;

        while(!queue.isEmpty()) {
            Turn cur = queue.poll();

            // 빨간공은 구멍에, 파란공은 구멍에 위치하지 않는다면
            // 구멍을 통해 빨간 구슬만 뺴낼 수 있다는 뜻이므로 1을 출력한다
            if(board[cur.red[0]][cur.red[1]] == 'O' && board[cur.blue[0]][cur.blue[1]] != 'O')
                return 1;
                // 만약 파란색 공이 구멍에 위치한다면 이는 실패한 경우이므로 다음 경우를 탐색한다
            else if(board[cur.blue[0]][cur.blue[1]] == 'O') continue;

            int[] red = cur.red, blue = cur.blue;
            // 네 방향으로 기울여보며 가능한 경우를 다음 탐색에 추가한다
            for(int dir = 0; dir < 4; dir++) {
                int[] tempRed = null, tempBlue = null;

                if(dir == 0) { // 왼쪽으로 기울인 경우
                    // 왼쪽으로 기울였을 때 빨간색 공과 파란색 공이 위치하는 위치들을 찾는다
                    tempRed = moveLeft(red);
                    tempBlue = moveLeft(blue);

                    // 만약 두 공이 모두 구멍에 위치하게 된다면 파란색 공이 빠지는 경우이므로 실패이기 떄문에 다음 경우를 탐색한다
                    if(board[tempRed[0]][tempRed[1]] == 'O' && board[tempBlue[0]][tempBlue[1]] == 'O')
                        continue;
                    // 만약 이동시킨 후 두 공이 같은 위치에 있다면 이전 위치를 고려해 하나의 공을 이동시킨다
                    if(tempRed[0] == tempBlue[0] && tempRed[1] == tempBlue[1]) {
                        if(red[1] > blue[1]) tempRed[1]++;
                        else tempBlue[1]++;
                    }
                } else if(dir == 1) { // 오른쪽으로 기울인 경우
                    // 오른쪽으로 기울였을 때 빨간색 공과 파란색 공이 위치하는 위치들을 찾는다
                    tempRed = moveRight(red);
                    tempBlue = moveRight(blue);

                    // 만약 두 공이 모두 구멍에 위치하게 된다면 파란색 공이 빠지는 경우이므로 실패이기 떄문에 다음 경우를 탐색한다
                    if(board[tempRed[0]][tempRed[1]] == 'O' && board[tempBlue[0]][tempBlue[1]] == 'O')
                        continue;
                    // 만약 이동시킨 후 두 공이 같은 위치에 있다면 이전 위치를 고려해 하나의 공을 이동시킨다
                    if(tempRed[0] == tempBlue[0] && tempRed[1] == tempBlue[1]) {
                        if(red[1] > blue[1]) tempBlue[1]--;
                        else tempRed[1]--;
                    }
                } else if(dir == 2) { // 위로 기울인 경우
                    // 위쪽으로 기울였을 때 빨간색 공과 파란색 공이 위치하는 위치들을 찾는다
                    tempRed = moveUp(red);
                    tempBlue = moveUp(blue);

                    // 만약 두 공이 모두 구멍에 위치하게 된다면 파란색 공이 빠지는 경우이므로 실패이기 떄문에 다음 경우를 탐색한다
                    if(board[tempRed[0]][tempRed[1]] == 'O' && board[tempBlue[0]][tempBlue[1]] == 'O')
                        continue;
                    // 만약 이동시킨 후 두 공이 같은 위치에 있다면 이전 위치를 고려해 하나의 공을 이동시킨다
                    if(tempRed[0] == tempBlue[0] && tempRed[1] == tempBlue[1]) {
                        if(red[0] > blue[0]) tempRed[0]++;
                        else tempBlue[0]++;
                    }
                } else if(dir == 3) { // 아래로 기울인 경우
                    // 아래쪽으로 기울였을 때 빨간색 공과 파란색 공이 위치하는 위치들을 찾는다
                    tempRed = moveDown(red);
                    tempBlue = moveDown(blue);

                    // 만약 두 공이 모두 구멍에 위치하게 된다면 파란색 공이 빠지는 경우이므로 실패이기 떄문에 다음 경우를 탐색한다
                    if(board[tempRed[0]][tempRed[1]] == 'O' && board[tempBlue[0]][tempBlue[1]] == 'O')
                        continue;
                    // 만약 이동시킨 후 두 공이 같은 위치에 있다면 이전 위치를 고려해 하나의 공을 이동시킨다
                    if(tempRed[0] == tempBlue[0] && tempRed[1] == tempBlue[1]) {
                        if(red[0] > blue[0]) tempBlue[0]--;
                        else tempRed[0]--;
                    }
                }

                // 이미 두 공이 해당 위치에 존재하는 경우를 방문했다면 또 탐색할 필요는 없으니 다음 경우를 탐색한다
                if(visited[tempRed[0]][tempRed[1]][tempBlue[0]][tempBlue[1]]) continue;
                // 만약 다음 턴이 10번을 초과한다면 더이상 탐색할 필요가 없으니 다음 경우를 탐색한다
                if(cur.moveNum + 1 > 10) continue;

                // 방문체크를 진행하고 다음 탐색에 사용할 Turn 객체를 추가한다
                visited[tempRed[0]][tempRed[1]][tempBlue[0]][tempBlue[1]] = true;
                queue.offer(new Turn(tempRed, tempBlue, cur.moveNum + 1));
            }
        }

        // 위 while문을 진행하는 동안 1이 반환되지 않았다면 빨간 구슬을 빼낼 수 없다는 뜻이므로 0을 출력한다

        return 0;
    }

    static int[] moveLeft(int[] loc) {
        for(int col = loc[1]; col >= 0; col--) {
            if(board[loc[0]][col] == 'O') return new int[] {loc[0], col};
            if(board[loc[0]][col] == '#') return new int[] {loc[0], col + 1};
        }

        return new int[] {loc[0], 0};
    }

    static int[] moveRight(int[] loc) {
        for(int col = loc[1]; col < M; col++) {
            if(board[loc[0]][col] == 'O') return new int[] {loc[0], col};
            else if(board[loc[0]][col] == '#') return new int[] {loc[0], col - 1};
        }

        return new int[] {loc[0], M - 1};
    }

    static int[] moveUp(int[] loc) {
        for(int row = loc[0]; row >= 0; row--) {
            if(board[row][loc[1]] == 'O') return new int[] {row, loc[1]};
            if(board[row][loc[1]] == '#') return new int[] {row + 1, loc[1]};
        }

        return new int[] {0, loc[1]};
    }

    static int[] moveDown(int[] loc) {
        for(int row = loc[0]; row < N; row++) {
            if(board[row][loc[1]] == 'O') return new int[] {row, loc[1]};
            if(board[row][loc[1]] == '#') return new int[] {row - 1, loc[1]};
        }

        return new int[] {N - 1, loc[1]};
    }

    static class Turn {
        int[] red, blue;
        int moveNum;

        public Turn(int[] red, int[] blue, int moveNum) {
            this.red = red;
            this.blue = blue;
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
