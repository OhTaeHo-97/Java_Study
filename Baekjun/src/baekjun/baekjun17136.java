package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun17136 {
	static final int SIZE = 10;
    static int[][] map;
    static int[] paperSize = {0, 5, 5, 5, 5, 5};
    static int answer;

    static void input() {
        Reader scanner = new Reader();
        map = new int[SIZE][SIZE];
        answer = Integer.MAX_VALUE;

        for(int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++)
                map[row][col] = scanner.nextInt();
        }
    }

    static void solution() {
        dfs(0, 0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void dfs(int x, int y, int count) {
        // 맨 끝 점에 도달 -> 정답 갱신
        if(x >= SIZE - 1 && y >= SIZE) {
            answer = Math.min(answer, count);
            return;
        }

        // count가 answer보다 크거나 같은 경우, 그 뒤에 색종이를 더 붙이면 어차피 답이 될 수 없음
        // 그러므로 끝냄
        if(answer <= count) return;

        // 아래 줄로 이동
        if(y > SIZE - 1) {
            dfs(x + 1, 0, count);
            return;
        }
        
        if(map[x][y] == 1) {
            for(int size = 5; size >= 1; size--) {
                if(paperSize[size] > 0 && isAttach(x, y, size)) {
                    attach(x, y, size, 0);
                    paperSize[size]--;
                    dfs(x, y + 1, count + 1);
                    attach(x, y, size, 1);
                    paperSize[size]++;
                }
            }
        } else {
            dfs(x, y + 1, count);
        }
    }

    static void attach(int x, int y, int size, int paper) {
        for(int row = x; row < x + size; row++) {
            for(int col = y; col < y + size; col++)
                map[row][col] = paper;
        }
    }

    static boolean isAttach(int x, int y, int size) {
        for(int row = x; row < x + size; row++) {
            for(int col = y; col < y + size; col++) {
                if(!isInMap(row, col)) return false;

                if(map[row][col] != 1) return false;
            }
        }

        return true;
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < SIZE && y >= 0 && y < SIZE) return true;
        return false;
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

//큰 색종이부터 붙이면 된다는 것의 반례
//(만약 아래와 같은 예에서 5 * 5 색종이부터 붙인다면
//4 * 4를 이용하는 것보다 더 많은 색종이를 사용)
//1 1 1 1 1 1 1 1 0 0
//1 1 1 1 1 1 1 1 0 0
//1 1 1 1 1 1 1 1 0 0
//1 1 1 1 1 1 1 1 0 0
//0 1 1 1 1 1 1 1 0 0
//0 1 1 1 1 1 0 0 0 0
//0 1 1 1 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0