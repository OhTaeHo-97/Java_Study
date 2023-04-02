package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun7570 {
	static int N;
    static int[] consecutiveLISNum;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        consecutiveLISNum = new int[N + 1];

        // 연속된 수들로 이루어진 LIS의 길이를 구한다
        for(int idx = 0; idx < N; idx++) {
            int num = scanner.nextInt();
            consecutiveLISNum[num] = consecutiveLISNum[num - 1] + 1;
        }
    }

    static void solution() {
        // 정렬을 하여 가장 긴 연속된 수들로 이루어진 LIS의 길이를 찾는다
        Arrays.sort(consecutiveLISNum);
        // 연속된 수들로 이루어진 LIS에 포함되는 번호들은 제일 앞이나 뒤로 보낼 필요가 없다
        // 그러므로 그러한 번호들의 개수를 전체 어린이 수에서 빼주면 그 값이 움직여야 할 번호들의 수가 된다
        System.out.println(N - consecutiveLISNum[N]);
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
