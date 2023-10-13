package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun5425 {
    static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();

    static long start;
    static long end;

    static void input() {
        start = scanner.nextLong();
        end = scanner.nextLong();
    }

    static void solution() {
        long startSum = start == 0 ? 0 : getPlaceValueSum(start - 1, Long.toString(start - 1).length());
        long endSum = end == 0 ? 0 : getPlaceValueSum(end, Long.toString(end).length());

        sb.append(endSum - startSum).append('\n');
    }

    static long getPlaceValueSum(long num, int length) {
        if(length <= 1) {
            return getSumBetweenOneAndNumber(num);
        } else {
            long divisor = 1;
            long[] sumOfEachPlaceValue = new long[10];

            while(num > 0) {
                long oneToNineCnt = num / (divisor * 10); // 횟수
                long remainder = num % (divisor * 10); // 나머지(현재 자리수의 수 판단 위함)

                // 1~9까지 나오는 횟수 구하기 (현재 자릿수의 1 ~ 9까지의 합이 몇 번 나오는지 확인하여 그만큼 더함)
                for(int idx = 1; idx < 10; idx++) {
                    sumOfEachPlaceValue[idx] += oneToNineCnt * divisor;
                }

                // 1 ~ (현재 자리수의 수)까지 몇 번 나오는지 구함
                for(int idx = 1; idx < remainder / divisor + 1; idx++) {
                    sumOfEachPlaceValue[idx] += divisor;
                }

                sumOfEachPlaceValue[(int)((remainder / divisor + 1) % 10)] += remainder % divisor;

                // 자리수에 맞게 빼줘서 다음에 구할 수를 구함
                num -= 9 * divisor;
                divisor *= 10;
            }

            long result = 0L;
            for(int idx = 1; idx < 10; idx++) {
                result += sumOfEachPlaceValue[idx] * idx;
            }
            return result;
        }
    }

    static long getSumBetweenOneAndNumber(long number) {
        long answer = 0L;
        for(int num = 1; num <= number; num++) {
            answer += num;
        }

        return answer;
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();

        while(T-- > 0) {
            input();
            solution();
        }

        System.out.print(sb);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
