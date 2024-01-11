package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1081 {
    static final int TEN = 10;

    static int startNumber;
    static int endNumber;

    static void input() {
        Reader scanner = new Reader();

        startNumber = scanner.nextInt();
        endNumber = scanner.nextInt();
    }

    static void solution() {
        long[] startNumberCount = countNumbers(startNumber - 1);
        long[] endNumberCount = countNumbers(endNumber);
        long[] numberCount = findNumberCountBetweenStartAndEnd(startNumberCount, endNumberCount);

        long answer = 0L;
        for (int num = 0; num < TEN; num++) {
            answer += (numberCount[num] * num);
        }

        System.out.println(answer);
    }

    static long[] findNumberCountBetweenStartAndEnd(long[] startNumberCount, long[] endNumberCount) {
        long[] result = new long[TEN];
        for (int num = 0; num < TEN; num++) {
            result[num] = endNumberCount[num] - startNumberCount[num];
        }

        return result;
    }

    static long[] countNumbers(int end) {
        int start = 1;
        int digit = 1;
        long[] numberCount = new long[TEN];

        while (start <= end) {
            while (start % TEN != 0 && start <= end) {
                countNumber(start, digit, numberCount);
                start++;
            }

            while (end % TEN != 9 && start <= end) {
                countNumber(end, digit, numberCount);
                end--;
            }

            if (start > end) {
                break;
            }

            start /= TEN;
            end /= TEN;
            for (int num = 0; num < TEN; num++) {
                numberCount[num] += (end - start + 1) * digit;
            }
            digit *= TEN;
        }

        return numberCount;
    }

    static void countNumber(int number, int digit, long[] numberCount) {
        while (number > 0) {
            numberCount[number % TEN] += digit;
            number /= TEN;
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
