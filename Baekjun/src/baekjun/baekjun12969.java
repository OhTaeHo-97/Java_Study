package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun12969 {
    static final int MAX = 30;
    static int N, K;
    static char[] str;
    static boolean[][][][] dp;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        K = scanner.nextInt();
        str = new char[N];
        dp = new boolean[MAX + 1][MAX + 1][MAX + 1][((MAX * (MAX - 1)) / 2) + 1];
        // dp[idx][a][b][pairNum] = idx번째까지 A의 개수가 a, B의 개수가 b일 때
        //                          조건을 만족하는 쌍의 개수가 pairNum인 문자열이 존재하는지 여부
    }

    static void solution() {
        if(findStr(0, 0, 0, 0)) System.out.println(new String(str));
        else System.out.println(-1);
    }

    static boolean findStr(int idx, int a, int b, int pairNum) {
        if(idx == N) { // N개의 알파벳을 모두 채웠다면
            // 만약 조건에 맞는 쌍의 개수가 K개라면 해당 알파벳들은 문제의 답이 되기 때문에 true를 반환하고
            // 그렇지 않다면 문제의 답이 될 수 없으므로 false를 반환한다
            if(pairNum == K) return true;
            return false;
        }

        // 이미 idx번째까지 A의 개수가 a, B의 개수가 b일 때 조건을 만족하는 쌍의 개수가 pairNum인 문자열을 방문했다면
        // 즉, 현재 문자열의 상황을 이미 방문했다면
        // 이후에 N개까지 진행하더라도 문자열을 만들 수 없다는 뜻이므로 false를 반환한다
        if(dp[idx][a][b][pairNum]) return false;
        // 현재 문자열의 상황에 방문 체크를 진행한다
        dp[idx][a][b][pairNum] = true;

        // 현재 idx번째 문자를 A로 채우고 재귀를 통해 다음 문자들을 채워나가며 정답이 되는지 확인한다
        str[idx] = 'A';
        // 만약 재귀의 반환값이 true라면 정답을 만들었다는 뜻이므로 true를 반환한다
        if(findStr(idx + 1, a + 1, b, pairNum)) return true;

        // 현재 idx번째 문자를 B로 채우고 재귀를 통해 다음 문자들을 채워나가며 정답이 되는지 확인한다
        // 현재 idx번째 문자가 B가 됐으니 idx번째 이전 문자들에서 A에 해당하는 문자들은
        // 조건에 맞는 쌍을 현재 idx번째 문자와 만들 수 있으니 조건에 맞는 쌍의 개수를 a의 개수만큼 늘려준다
        str[idx] = 'B';
        // 만약 재귀의 반환값이 true라면 정답을 만들었다는 뜻이므로 true를 반환한다
        if(findStr(idx + 1, a, b + 1, pairNum + a)) return true;

        // 현재 idx번째 문자를 C로 채우고 재귀를 통해 다음 문자들을 채워나가며 정답이 되는지 확인한다
        // 현재 idx번째 문자가 C가 됐으니 idx번째 이전 문자들에서 A, B에 해당하는 문자들은
        // 조건에 맞는 쌍을 현재 idx번째 문자와 만들 수 있으니 조건에 맞는 쌍의 개수를 a, b의 개수만큼 늘려준다
        str[idx] = 'C';
        // 만약 재귀의 반환값이 true라면 정답을 만들었다는 뜻이므로 true를 반환한다
        if(findStr(idx + 1, a, b, pairNum + a + b)) return true;

        return false;
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
