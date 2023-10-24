package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun16161 {
    static int seriesCnt;
    static int[] series;

    static void input() {
        Reader scanner = new Reader();

        seriesCnt = scanner.nextInt();
        series = new int[seriesCnt];

        for(int idx = 0; idx < seriesCnt; idx++) {
            series[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        System.out.println(twoPointer(0, 0));
    }

    static int twoPointer(int leftPtr, int rightPtr) {
        int answer = 1;

        while(leftPtr <= rightPtr && rightPtr < seriesCnt - 1) {
            if(series[rightPtr] < series[rightPtr + 1]) {
                rightPtr++;
                continue;
            }
            if(series[rightPtr] == series[rightPtr + 1]) {
                int halfLen = 0;
                for(halfLen = 0; halfLen <= rightPtr - leftPtr; halfLen++) {
                    if((rightPtr + 1) + halfLen >= seriesCnt) {
                        break;
                    }
                    if(series[rightPtr - halfLen] != series[(rightPtr + 1) + halfLen]) {
                        break;
                    }
                }

                answer = Math.max(answer, halfLen * 2);
                leftPtr = rightPtr + 1;
                rightPtr++;
                continue;
            }
            if(series[rightPtr] > series[rightPtr + 1]) {
                int halfLen = 0;
                for(halfLen = 0; halfLen < rightPtr - leftPtr; halfLen++) {
                    if((rightPtr + 1) + halfLen >= seriesCnt) {
                        break;
                    }
                    if(series[(rightPtr - 1) - halfLen] != series[(rightPtr + 1) + halfLen]) {
                        break;
                    }
                }

                answer = Math.max(answer, halfLen * 2 + 1);
                leftPtr = rightPtr + 1;
                rightPtr++;
            }
        }

        return answer;
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
