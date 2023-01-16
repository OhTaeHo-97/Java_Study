package showMeTheCode2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ShowMeTheDungeon {
	static int N, K, answer;
    static int[] attackPowers, residents;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        K = scanner.nextInt();
        attackPowers = new int[N + 1];
        residents = new int[N + 1];
        for(int idx = 1; idx <= N; idx++) attackPowers[idx] = scanner.nextInt();
        for(int idx = 1; idx <= N; idx++) residents[idx] = scanner.nextInt();
    }

    static void solution() {
        answer = 0;
        rec_func(0, 0, 0, new boolean[N + 1]);
        System.out.println(answer);
    }

    static void rec_func(int sum, int prev, int resident, boolean[] visited) {
        if(sum <= K) answer = Math.max(answer, resident);
        if(sum > K) return;

        for(int idx = 1; idx <= N; idx++) {
            if(!visited[idx]) {
                visited[idx] = true;
                int exhaustion = prev + attackPowers[idx];
                rec_func(sum + exhaustion, exhaustion, resident + residents[idx], visited);
                visited[idx] = false;
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
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return str;
        }
    }
}
