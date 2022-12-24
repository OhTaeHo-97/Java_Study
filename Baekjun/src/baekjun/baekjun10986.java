package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun10986 {
	static int N, M;
    static long answer;
    static long[] prefixSum, count;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        M = scanner.nextInt();
        prefixSum = new long[N + 1];
        count = new long[M];
        answer = 0;
        for(int idx = 1; idx <= N; idx++) {
            prefixSum[idx] = (prefixSum[idx - 1] + scanner.nextInt()) % M;
            if(prefixSum[idx] == 0) answer++;
            count[(int)prefixSum[idx]]++;
        }
    }

    static void solution() {
        for(int idx = 0; idx < M; idx++)
            answer += count[idx] * (count[idx] - 1) / 2;
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
