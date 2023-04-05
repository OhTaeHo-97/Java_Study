package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjun9661 {
	static long N;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
    }

    static void solution() {
//        boolean[] dp = new boolean[20];
//
//        for(int idx = 1; idx <= 20; idx++) {
//            int count = 0;
//
//            while(idx - Math.pow(4, count) >= 0) {
//                // 창영이가 지는 상황이 한 번이라도 있는 경우
//                if(!dp[idx - (int)Math.pow(4, count)]) {
//                    dp[idx] = true; // 상근이는 이기는 상황이 존재함
//                    break;
//                }
//
//                count++;
//            }
//        }
        // false true false true true 가 반복적으로 나타남
        // 돌을 가져갈 수 있는 개수는 4의 n제곱 꼴이고, n은 0부터 시작하므로
        // 상근이가 특정 돌 n개를 가져가고 난 다음 상황에 대한 정의는 dp[i - 4^cnt]로 표현할 수 있다
        // 다음 상황에서 dp[i - 4^cnt] = false인, 즉 단 한 번이라도 창영이가 지는 상황이 나온다면, 현재 상황에서 무조건 이기는 상황이 존재
        // 반대로, 다음 상황에서 창영이가 모두 이긴다면, 현재 상황은 패배하는 상황 밖에 없다

        System.out.println((N % 5 == 0 || N % 5 == 2) ? "CY" : "SK");

    }

    static double baseLog(long num, long base) {
        int exponent = (int)Math.floor(Math.log10(num) / Math.log10(base));
        return Math.pow(base, exponent);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
