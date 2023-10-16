package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2092 {
    static final int DIVISOR = 1_000_000;

    static int max;
    static int numberCnt;
    static int minPickedCnt;
    static int maxPickedCnt;
    static int[] numbers;
    static int[] eachNumberCnt;

    static void input() {
        Reader scanner = new Reader();

        max = scanner.nextInt();
        numberCnt = scanner.nextInt();
        minPickedCnt = scanner.nextInt();
        maxPickedCnt = scanner.nextInt();
        numbers = new int[numberCnt];
        eachNumberCnt = new int[max + 1];

        for(int idx = 0; idx < numberCnt; idx++) {
            numbers[idx] = scanner.nextInt();
            eachNumberCnt[numbers[idx]]++; // 각 숫자의 개수를 센다
        }
    }

    static void solution() {
        // dp[num][k] = num 이하의 값을 가진 수 중에서 k개 선택하여 집합을 생성하는 경우의 수
        int[][] dp = new int[max + 1][numberCnt + 1];
        dp[0][0] = 1;

        for(int num = 1; num <= max; num++) {
            // 우선 숫자 하나로만 만드는 경우를 추가
            //  - 예를 들어 1이 5개 있다면, 1을 통해 1, 11, 111, 1111, 11111 이렇게 만들 수 있다
            //  - 이러한 경우들을 추가하는 과정이다
            for(int numCnt = 0; numCnt <= eachNumberCnt[num]; numCnt++) {
                dp[num][numCnt]++;
            }

            for(int numCnt = 0; numCnt <= numberCnt; numCnt++) {
                // 이전까지 구해놓은 숫자들의 조합 개수를 더해준다
                //  - 전체 경우의 수를 구할 때는 이전 경우의 수도 같이 더해줘야 전체 경우의 수를 구할 수 있기 때문에 이전까지 구한 조합 개수를 더해준다
                dp[num][numCnt] += dp[num - 1][numCnt];
                // 이전 조합들에 현재 숫자를 추가하며 조합 개수를 채워나간다
                for(int curNumCnt = 1; curNumCnt <= eachNumberCnt[num]; curNumCnt++) {
                    // 구성하려는 조합의 전체 원소 수보다 현재 채워넣으려는 수가 적다면, 즉 현재 수를 채워넣을 수 있을만큼
                    if(numCnt - curNumCnt > 0) {
                        // 이전 조합에 현재 수를 채워넣는 것이니, 이전 조합의 수를 더해준다
                        dp[num][numCnt] += dp[num - 1][numCnt - curNumCnt];
                        dp[num][numCnt] %= DIVISOR;
                    }
                }
            }
        }

        int total = 0;
        for(int idx = minPickedCnt; idx <= maxPickedCnt; idx++) {
            total += dp[max][idx];
            total %= DIVISOR;
        }

        System.out.println(total);
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
            while (st == null || !st.hasMoreElements()) {
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
