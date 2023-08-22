package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun12850 {
    static final int DIVISOR = 1_000_000_007;
    static final int SIZE = 8;

    static int D;
    static long[][] map;

    static void input() {
        Reader scanner = new Reader();

        // 0 : 정보과학관, 1 : 전산관, 2 : 미래관, 3 : 신양관, 4 : 한경직기념관, 5 : 진리관, 6 : 형남공학관, 7 : 학생회관
        // map[b1][b2] = b1번 건물에서 b2번 건물로 가는 경우의 수
        map = new long[SIZE][SIZE];
        map[0][1] = map[0][2] = 1;
        map[1][0] = map[1][2] = map[1][3] = 1;
        map[2][0] = map[2][1] = map[2][3] = map[2][4] = 1;
        map[3][1] = map[3][2] = map[3][4] = map[3][5] = 1;
        map[4][2] = map[4][3] = map[4][5] = map[4][6] = 1;
        map[5][3] = map[5][4] = map[5][7] = 1;
        map[6][4] = map[6][7] = 1;
        map[7][5] = map[7][6] = 1;

        D = scanner.nextInt();
    }

    static void solution() {
        // 분할 정복을 통해 map을 곱해나가며 D분에 각 위치에서 다른 모든 위치로 이동할 수 있는 모든 경우의 수를 구한다
        // map -> 이동한 시간이 1분일 때의 각 위치에서 다른 모든 위치로 이동할 수 있는 모든 경우의 수
        // map * map -> 이동한 시간이 2분일 때의 각 위치에서 다른 모든 위치로 이동할 수 있는 모든 경우의 수
        // (map * map) * map -> 이동한 시간이 3분일 때의 각 위치에서 다른 모든 위치로 이동할 수 있는 모든 경우의 수
        // (map * map) * (map * map) -> 이동한 시간이 4분일 때의 각 위치에서 다른 모든 위치로 이동할 수 있는 모든 경우의 수
        // 이렇게 이동할 수 있는 모든 경우의 수를 나타내는 행렬을 곱해나가면서 D분일 때의 이동할 수 있는 모든 경우의 수를 구한다
        long[][] result = calculateMoveNum(D, map);
        // 구하고자 하는 정보과학관에서 정보과학관으로 돌아오는 경우의 수는 정보과학관이 0번이기 때문에
        // result[0][0]이 정보과학관에서 정보과학관으로 돌아오는 경우의 수가 된다
        System.out.println(result[0][0]);
    }

    static long[][] calculateMoveNum(int exponent, long[][] moveNum) {
        // 만약 현재 시간이 1이라면 moveNum을 그대로 반환한다
        if(exponent == 1) return moveNum;

        // 재귀를 통해 (exponent / 2)분일 때의 이동할 수 있는 경우의 수를 구한다
        long[][] temp = calculateMoveNum(exponent / 2, moveNum);

        // 현재 우리가 구하고자 하는 것은 exponent분일 때의 이동할 수 있는 경우의 수이므로
        // (exponent / 2)분일 때의 이동할 수 있는 경우의 수인 temp를 이용하여 temp * temp를 통해
        // exponent분일 때의 이동할 수 있는 경우의 수를 구한다
        long[][] result = multiplyMatrix(temp, temp);
        // 만약 exponent가 홀수라면, temp * temp만 가지고는 exponent분일 때의 이동할 수 있는 경우의 수를 구할 수 없으니
        // temp * temp를 한 행렬에 1분일 때의 경우의 수인 moveNum을 곱하여 exponent분일 때의 이동할 수 있는 경우의 수를 구한다
        if(exponent % 2 == 1)
            result = multiplyMatrix(result, moveNum);

        return result;
    }

    static long[][] multiplyMatrix(long[][] mat1, long[][] mat2) {
        long[][] result = new long[SIZE][SIZE];

        for(int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++) {
                for(int idx = 0; idx < SIZE; idx++) {
                    result[row][col] += (mat1[row][idx] * mat2[idx][col]);
                    result[row][col] %= DIVISOR;
                }
            }
        }

        return result;
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
