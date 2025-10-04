package baekjun;

import java.io.*;
import java.util.*;

public class baekjun19237 {
    /*
     * 상어가 사는 공간에 더 이상 물고기는 오지 않고 다른 상어들만이 남아 있음
     * 상어에는 1 ~ M 자연수 번호가 붙어 있고, 모든 번호는 서로 다름
     * 상어들은 영역을 사수하기 위해 다른 상어들을 쫓아내려고 하는데, 1의 번호를 가진 어른 상어는 가장 강력해서 나머지 모두를 쫓아낼 수 있음
     *
     * N x N 크기 격자 중 M개의 칸에 상어가 한 마리씩 들어 있음
     *  - 처음에는 모든 상어가 자신의 위치에 자신의 냄새를 뿌림
     *  - 그후 1초마다 모든 상어가 동시에 상하좌우로 인접한 칸 중 하나로 이동
     *      - 자신의 냄새를 그 칸에 뿌림
     *  - 냄새는 상어가 k번 이동하고 나면 사라짐
     *  - 각 상어가 이동 방향을 결정할 때는, 먼저 인접한 칸 중 아무 냄새가 없는 칸의 방향으로 잡음
     *  - 그런 칸이 없으면 자신의 냄새가 있는 칸의 방향으로 잡음
     *  - 가능한 칸이 여러 개일 수 있는데, 그 경우에는 특정한 우선순위를 따름
     *      - 우선순위는 상어마다 다를 수 있고, 같은 상어라도 현재 상어가 보고 있는 방향에 따라 다를 수 있음
     *      - 상어가 맨 처음에 보고 있는 방향은 입력으로 주어지고, 그 후에는 방금 이동한 방향이 보고 있는 방향이 된다
     *  - 모든 상어가 이동한 후 한 칸에 여러 마리의 상어가 남아있으면, 가장 작은 번호를 가진 상어를 제외 모두 격자 밖으로 쫓겨남
     *
     * 1번 상어만 격자에 남게 되기까지 몇 초가 걸리는가?
     */

    private static int[][] directions = new int[][] {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static int size;
    private static int sharkCount;
    private static int time;

    private static Shark[] sharks;
    private static boolean[] isDead;
    private static Queue<Scent> scents;
    private static int[][] sharkNumber;
    private static Scent[][] scentMap;

    private static void input() {
        Reader scanner = new Reader();

        size = scanner.nextInt();
        sharkCount = scanner.nextInt();
        time = scanner.nextInt();
        sharks = new Shark[sharkCount + 1];
        isDead = new boolean[sharkCount + 1];
        sharkNumber = new int[size][size];
        scentMap = new Scent[size][size];
        scents = new PriorityQueue<>();

        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                sharkNumber[row][col] = scanner.nextInt();
                if(sharkNumber[row][col] != 0) {
                    sharks[sharkNumber[row][col]] = new Shark(sharkNumber[row][col], row, col);
                }
            }
        }

        for(int number = 1; number <= sharkCount; number++) {
            sharks[number].direction = scanner.nextInt();
        }

        for(int number = 1; number <= sharkCount; number++) {
            for(int direction = 1; direction <= 4; direction++) {
                for(int idx = 0; idx < 4; idx++) {
                    sharks[number].priorityDirections[direction][idx] = scanner.nextInt();
                }
            }
        }
    }

    private static void solution() {
        // 자신의 위치에 자신의 냄새 뿌리기
        for(int number = 1; number <= sharkCount; number++) {
            Scent scent = new Scent(number, sharks[number].x, sharks[number].y, time);
            scents.add(scent);
            scentMap[sharks[number].x][sharks[number].y] = scent;
        }

        System.out.println(simulation());
    }

    private static int simulation() {
        int answer = 0;
        while(!isEnd()) {
            answer++;
            // 냄새 뿌릴 위치 찾기
            // 해당 위치로 이동
            // 겹쳤을 때 작은 번호만 남기기
            for(int number = sharkCount; number > 0; number--) {
                int[] nextPosition = findNextPosition(sharks[number]);
                if(sharkNumber[nextPosition[0]][nextPosition[1]] != 0) {
                    sharkNumber[sharks[number].x][sharks[number].y] = 0;
                    isDead[sharkNumber[nextPosition[0]][nextPosition[1]]] = true;
                    sharkNumber[nextPosition[0]][nextPosition[1]] = number;
                    sharks[number].x = nextPosition[0];
                    sharks[number].y= nextPosition[1];
                }
            }

            // 냄새 뿌리기 및 냄새 시간 감소, 사라진 상어의 냄새는 제거
            changeScentInfo();
        }

        return answer;
    }

    private static boolean isEnd() {
        for(int number = 2; number <= sharkCount; number++) {
            if(!isDead[number]) return false;
        }
        return true;
    }

    private static void changeScentInfo() {
        Queue<Scent> scents = new PriorityQueue<>();
        for(Scent scent : scents) {
            scent.time--;
            if(scent.time > 0 && !isDead[scent.id]) {
                scents.add(scent);
            } else {
                scentMap[scent.x][scent.y] = null;
            }
        }

        for(int number = 1; number <= sharkCount; number++) {
            if(!isDead[number]) {
                Scent scent = new Scent(number, sharks[number].x, sharks[number].y, time);
                scents.add(scent);
                scentMap[scent.x][scent.y] = scent;
            }
        }
    }

    private static int[] findNextPosition(Shark shark) {
        int[] priority = shark.priorityDirections[shark.direction];
        int emptyDir = hasEmptyPosition(shark);

        if(emptyDir != 0) {
            return new int[] {shark.x + directions[emptyDir][0], shark.y + directions[emptyDir][1]};
        } else {
            for(int dir : priority) {
                int nx = shark.x + directions[dir][0];
                int ny = shark.y + directions[dir][1];

                if(isInMap(nx, ny) && scentMap[nx][ny] != null && scentMap[nx][ny].id == shark.id) {
                    return new int[] {nx, ny};
                }
            }
        }

        return new int[] {shark.x, shark.y};
    }

    private static int hasEmptyPosition(Shark shark) {
        int[] priority = shark.priorityDirections[shark.direction];
        for(int dir : priority) {
            int nx = shark.x + directions[dir][0];
            int ny = shark.y + directions[dir][1];
            if(isInMap(nx, ny) && scentMap[nx][ny] == null) {
                return dir;
            }
        }
        return 0;
    }

    private static boolean isInMap(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    static class Shark {
        int id;
        int x;
        int y;
        // 1 : 위, 2 : 아래, 3 : 왼쪽, 4 : 오른쪽
        int direction;
        int[][] priorityDirections;

        public Shark(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            priorityDirections = new int[5][4];
        }
    }

    static class Scent implements Comparable<Scent> {
        int id;
        int x;
        int y;
        int time;

        public Scent(int id, int x, int y, int time) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Scent scent) {
            if(time != scent.time) return Integer.compare(time, scent.time);
            return Integer.compare(scent.id, id);
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
