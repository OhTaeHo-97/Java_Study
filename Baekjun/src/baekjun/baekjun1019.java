package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1019 {
	static int N, start, end, digit;
    static int[] countNum;
    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        countNum = new int[10];
    }

    static void solution() {
        while(start <= end) {
            while (start % 10 != 0 && start <= end) {
                countNum(start, digit);
                start++;
            }
            while (end % 10 != 9 && start <= end) {
                countNum(end, digit);
                end--;
            }

            if (start > end) break;

            start /= 10;
            end /= 10;

            for (int idx = 0; idx < 10; idx++)
                countNum[idx] += (end - start + 1) * digit;

            digit *= 10;
        }

        for(long num : countNum)
            System.out.println(num + " ");
    }

    static void countNum(int num, int digit) {
        while (num > 0) {
            countNum[num % 10] += digit;
            num /= 10;
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
