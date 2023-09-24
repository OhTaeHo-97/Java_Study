package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1242 {
    static int n;
    static int k;
    static int m;

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextInt();
        k = scanner.nextInt();
        m = scanner.nextInt();
    }

    static void solution() {
        int answer = 1;
        int removedNum;

        // 매턴마다 동호의 순서가 언제인지를 구하며 그 순서가 K를 말하는 순서와 같아질 때까지 반복한다
        // K를 말하는 순서 -> k % n (총 n명이서 1번부터 숫자를 K까지 말할 때, K를 말하는 사람의 순서)
        // 동호의 순서 -> m % n
        // 1. 1부터 K를 말하는 순서 사이에 동호가 들어있는 경우
        //  - (n + 동호의 순서) - (K를 말하는 순서)
        // 2. 1부터 K를 말하는 순서 사이에 동호가 없는 경우
        //  - (동호의 순서) - (K를 말하는 순서)
        while((removedNum = k % n) != m % n) {
            if(removedNum < m) {
                m -= removedNum;
            } else {
                m = (m + n) - removedNum;
            }

            n--;
            answer++;
        }

        System.out.println(answer);
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
