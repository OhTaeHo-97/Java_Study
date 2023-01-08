package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class baekjun1941 {
	static final int SIZE = 5, LAST = 7, SOM = 4;
    static int answer;
    static char[][] map;
    static int[] eachX, eachY, dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static void input() {
        Reader scanner = new Reader();
        map = new char[SIZE][SIZE];
        for(int row = 0; row < SIZE; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < SIZE; col++) map[row][col] = info.charAt(col);
        }
    }

    static void solution() {
        answer = 0;
        eachX = new int[SIZE * SIZE];
        eachY = new int[SIZE * SIZE];
        for(int idx = 0; idx < SIZE * SIZE; idx++) {
            eachX[idx] = idx / SIZE;
            eachY[idx] = idx % SIZE;
        }
        chooseStudents(0, 0, new int[LAST]);
        System.out.println(answer);
    }

    static void chooseStudents(int num, int idx, int[] students) {
        if(num == LAST) {
            bfs(students);
            return;
        }
        if(idx == SIZE * SIZE) return;
        students[num] = idx;
        chooseStudents(num + 1, idx + 1, students);
        chooseStudents(num, idx + 1, students);
    }

    static void bfs(int[] students) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[LAST];
        visited[0] = true;
        queue.offer(students[0]);
        int equalCnt = 1, som = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            if(map[eachX[cur]][eachY[cur]] == 'S') som++;
            for(int dir = 0; dir < 4; dir++) {
                for(int next = 1; next < LAST; next++) {
                    int cx = eachX[cur] + dx[dir], cy = eachY[cur] + dy[dir];
                    if(!visited[next]) {
                        if(cx == eachX[students[next]] && cy == eachY[students[next]]) {
                            visited[next] = true;
                            queue.offer(students[next]);
                            equalCnt++;
                        }
                    }
                }
            }
        }
        if(equalCnt == LAST && som >= SOM)
            answer++;
    }

    public static void main(String[] args) {
        input();
        solution();
    }

    static class Reader {
        BufferedReader br;
        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
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
