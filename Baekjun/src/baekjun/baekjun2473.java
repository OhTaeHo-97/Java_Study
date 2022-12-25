package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun2473 {
	static StringBuilder sb = new StringBuilder();
    static int N;
    static long answer;
    static int[] solutions, result;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        solutions = new int[N];
        for(int idx = 0; idx < N; idx++) solutions[idx] = scanner.nextInt();
    }

    static void solution() {
        Arrays.sort(solutions);
        result = new int[3];
        answer = Long.MAX_VALUE;
        for(int idx = 0; idx < N; idx++) twoPointer(solutions, idx);
        Arrays.sort(result);
        for(int r : result) sb.append(r).append(' ');
        System.out.println(sb);
    }

    static void twoPointer(int[] solutions, int idx) {
        int left = 0, right = N - 1;
        if(idx == 0) left = 1;
        if(idx == N - 1) right = N - 2;
        while(left < right) {
            long sum = (long)solutions[left] + solutions[right] + solutions[idx];
            if(answer > Math.abs(sum)) {
                answer = Math.abs(sum);
                result[0] = solutions[left];
                result[1] = solutions[idx];
                result[2] = solutions[right];
            }
            if(sum >= 0) {
                right--;
                if(idx == right) right--;
            } else {
                left++;
                if(idx == left) left++;
            }
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
