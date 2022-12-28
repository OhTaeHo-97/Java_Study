package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun17822 {
	static int N, M, T, platesNumCount;
    static int[] xi, di, ki;
    static int[][] plates;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        M = scanner.nextInt();
        T = scanner.nextInt();
        plates = new int[N + 1][M];
        for(int plate = 1; plate <= N; plate++) {
            for(int num = 0; num < M; num++) plates[plate][num] = scanner.nextInt();
        }
        platesNumCount = N * M;
        xi = new int[T];
        di = new int[T];
        ki = new int[T];
        for(int count = 0; count < T; count++) {
            xi[count] = scanner.nextInt();
            di[count] = scanner.nextInt();
            ki[count] = scanner.nextInt();
        }
    }

    static void solution() {
        for(int count = 0; count < T; count++) {
            int[] rotatePlates = getRotatePlates(xi[count]);
            boolean[][] visited = new boolean[N + 1][M];
            rotate(rotatePlates, di[count], ki[count]);
            remove();
            print();
            System.out.println();
        }
        int answer = sumNums();
        System.out.println(answer);
    }

    static void print() {
        for(int plate = 1; plate <= N; plate++) {
            for(int idx = 0; idx < M; idx++)
                System.out.print(plates[plate][idx] + " ");
            System.out.println();
        }
    }

    static int[] getRotatePlates(int n) {
        int[] rotatePlates = new int[N / n];
        int index = 0;
        for(int plate = 1; plate <= N; plate++) {
            if(plate % n == 0) {
                rotatePlates[index] = plate;
                index++;
            }
        }
        return rotatePlates;
    }

    static int sumNums() {
        int answer = 0;
        for(int plate = 1; plate <= N; plate++) {
            for(int idx = 0; idx < M; idx++) answer += plates[plate][idx];
        }
        return answer;
    }

    static void remove() {
        boolean isChanged = false;
        for(int plate = 1; plate <= N; plate++) {
            boolean[][] visited = new boolean[N + 1][M];
            if(!findSameInSamePlate(plate, visited) &&
                    !findSameInNeighborPlate(plate, visited)) {
                continue;
            }
            isChanged = true;
            removeSames(visited);
        }
        if(!isChanged) changeNums();
    }

    static void changeNums() {
        int sum = sumNums();
        System.out.println(sum);
        System.out.println(platesNumCount);
        double avg = (double)sum / platesNumCount;
        System.out.println(avg);
        adjust(avg);
    }

    static void adjust(double avg) {
        for(int plate = 1; plate <= N; plate++) {
            for(int idx = 0; idx < M; idx++) {
                if(plates[plate][idx] == 0) continue;
                if(plates[plate][idx] > avg) plates[plate][idx]--;
                else if(plates[plate][idx] < avg) plates[plate][idx]++;
            }
        }
    }

    static void removeSames(boolean[][] visited) {
        for(int plate = 1; plate <= N; plate++) {
            for(int idx = 0; idx < M; idx++) {
                if(visited[plate][idx]) {
                    plates[plate][idx] = 0;
                    platesNumCount--;
                }
            }
        }
    }

    static boolean findSameInSamePlate(int plate, boolean[][] visited) {
        boolean isChanged = false;
        for(int idx = 0; idx < M; idx++) {
            if(plates[plate][idx] == 0) continue;
            int left = idx - 1, right = idx + 1;
            if(left == -1) left = M - 1;
            if(right == M) right = 0;
            if(plates[plate][idx] == plates[plate][left]) {
                visited[plate][idx] = true;
                visited[plate][left] = true;
                isChanged = true;
            }
            if(plates[plate][idx] == plates[plate][right]) {
                visited[plate][idx] = true;
                visited[plate][right] = true;
                isChanged = true;
            }
        }
        return isChanged;
    }

    static boolean findSameInNeighborPlate(int plate, boolean[][] visited) {
        boolean isChanged = false;
        for(int idx = 0; idx < M; idx++) {
            if(plates[plate][idx] == 0) continue;
            int prev = plate - 1, next = plate + 1;
            if(prev != 0) {
                if(plates[plate][idx] == plates[prev][idx]) {
                    visited[plate][idx] = true;
                    visited[prev][idx] = true;
                    isChanged = true;
                }
            }
            if(next != N + 1) {
                if(plates[plate][idx] == plates[next][idx]) {
                    visited[plate][idx] = true;
                    visited[next][idx] = true;
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    static void rotate(int[] rotatePlates, int direction, int amount) {
        for(int plate : rotatePlates)
            rotatePlate(plate, direction, amount);
    }

    static void rotatePlate(int plate, int direction, int amount) {
        int dir = direction == 0 ? 1 : -1;
        int[] newNum = new int[M];
        for(int idx = 0; idx < M; idx++) {
            int newIdx = idx + (dir * amount);
            if(Math.abs(newIdx) >= M) {
                if(newIdx < 0) {
                    newIdx %= M;
                    newIdx = M - newIdx;
                } else {
                    newIdx %= M;
                }
            } else {
                if(newIdx < 0) newIdx = M + newIdx;
            }
            newNum[newIdx] = plates[plate][idx];
        }
        plates[plate] = newNum.clone();
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
