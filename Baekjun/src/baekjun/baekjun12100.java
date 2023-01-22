package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjun12100 {
	static int N, answer;
    static int[][] map;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        map = new int[N][N];
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) map[row][col] = scanner.nextInt();
        }
    }

    static void solution() {
        answer = 0;
        rec_func(0, map);
        System.out.println(answer);
    }

    static void rec_func(int count, int[][] map) {
        if(count == 5) {
            answer = Math.max(answer, findMax(map));
            return;
        }

        rec_func(count + 1, moveUp(map));
        rec_func(count + 1, moveDown(map));
        rec_func(count + 1, moveLeft(map));
        rec_func(count + 1, moveRight(map));
    }

    static int findMax(int[][] map) {
        int max = Integer.MIN_VALUE;
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                max = Math.max(max, map[row][col]);
            }
        }
        return max;
    }

    static int[][] moveRight(int[][] map) {
        int[][] copy = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        for(int row = 0; row < N; row++) copy[row] = map[row].clone();

        Stack<Integer> stack = new Stack<>();

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N - 1; col++) {
                if(!visited[row][col]) {
                    if(copy[row][col] == copy[row][col + 1]) {
                        copy[row][col + 1] *= 2;
                        copy[row][col] = 0;
                        visited[row][col + 1] = true;
                        stack.push(copy[row][col + 1]);
                    } else {
                        stack.push(copy[row][col]);
                    }
                    visited[row][col] = true;
                }
            }

            for(int col = N - 1; col >= 0; col--) {
                if(!stack.isEmpty()) copy[row][col] = stack.pop();
                else copy[row][col] = 0;
            }
        }

        return copy;
    }

    static int[][] moveLeft(int[][] map) {
        int[][] copy = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        for(int row = 0; row < N; row++) copy[row] = map[row].clone();

        Stack<Integer> stack = new Stack<>();

        for(int row = 0; row < N; row++) {
            for(int col = N - 1; col > 0; col--) {
                if(!visited[row][col]) {
                    if(copy[row][col] == copy[row][col - 1]) {
                        copy[row][col - 1] *= 2;
                        copy[row][col] = 0;
                        visited[row][col - 1] = true;
                        stack.push(copy[row][col - 1]);
                    } else {
                        stack.push(copy[row][col]);
                    }
                    visited[row][col] = true;
                }
            }

            for(int col = 0; col < N; col++) {
                if(!stack.isEmpty()) copy[row][col] = stack.pop();
                else copy[row][col] = 0;
            }
        }

        return copy;
    }

    static int[][] moveDown(int[][] map) {
        int[][] copy = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        for(int row = 0; row < N; row++) copy[row] = map[row].clone();

        Stack<Integer> stack = new Stack<>();

        for(int col = 0; col < N; col++) {
            for(int row = 0; row < N - 1; row++) {
                if(!visited[row][col]) {
                    if(copy[row][col] == copy[row + 1][col]) {
                        copy[row + 1][col] *= 2;
                        copy[row][col] = 0;
                        visited[row + 1][col] = true;
                        stack.push(copy[row + 1][col]);
                    } else {
                        stack.push(copy[row][col]);
                    }
                    visited[row][col] = true;
                }
            }

            for(int row = N - 1; row >= 0; row--) {
                if(!stack.isEmpty()) copy[row][col] = stack.pop();
                else copy[row][col] = 0;
            }
        }

        return copy;
    }

    static int[][] moveUp(int[][] map) {
        int[][] copy = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        for(int row = 0; row < N; row++) copy[row] = map[row].clone();

        Stack<Integer> stack = new Stack<>();

        for(int col = 0; col < N; col++) {
            for(int row = N - 1; row > 0; row--) {
                if(!visited[row][col]) {
                    if(copy[row][col] == copy[row - 1][col]) {
                        copy[row - 1][col] *= 2;
                        copy[row][col] = 0;
                        visited[row - 1][col] = true;
                        stack.push(copy[row - 1][col]);
                    } else {
                        stack.push(copy[row][col]);
                    }
                    visited[row][col] = true;
                }
            }

            for(int row = 0; row < N; row++) {
                if(!stack.isEmpty()) copy[row][col] = stack.pop();
                else copy[row][col] = 0;
            }
        }

        print(copy);

        return copy;
    }

    static void print(int[][] map) {
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                System.out.print(map[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
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
