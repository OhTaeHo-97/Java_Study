package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1285 {
    static int N;
    static char[][] map;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        map = new char[N][N];

        for(int row = 0; row < N; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < N; col++)
                map[row][col] = info.charAt(col);
        }
    }

    static void solution() {
        int answer = Integer.MAX_VALUE;
        // 각 자리의 비트는 행을 나타낸다
        // 만약 그 자리의 비트가 1이라면 해당 행은 뒤집는다
        for(int caseBit = 1; caseBit < (1 << N); caseBit++) {
            int totalBackNum = 0; // 뒷면에 해당하는 동전들의 개수
            // 뒤집기로 한 행들을 뒤집는다
            for(int col = 0; col < N; col++) {
                int back = 0; // 현재 열의 해당 행을 뒤집고/뒤집지 않고 난 이후의 뒷면의 개수
                // 열을 기준으로 뒤집기로 한 행들을 뒤집는다
                for(int row = 0; row < N; row++) {
                    char cur = map[row][col];
                    // 만약 현재 행이 뒤집어야 하는 행이라면
                    if((caseBit & (1 << row)) != 0)
                        cur = reverse(cur); // 해당 행의 동전을 뒤집어준다

                    if(cur == 'T') back++; // 만약 동전이 뒷면이라면 back을 1 증가시킨다
                }
                // 전체 동전 뒷면 개수에 현재 열의 뒷면 개수를 누적해주는데, 뒷면의 개수와 앞면의 개수 중 더 적은 수를 누적해준다
                // 왜냐하면 해당 열을 뒤집는다고 생각하면 현재 앞면 개수가 뒷면의 개수가 될 수 있으므로 더 작은 것으로 누적해주면 된다
                totalBackNum += Math.min(back, N - back);
            }
            // 답을 갱신한다
            answer = Math.min(answer, totalBackNum);
        }

        System.out.println(answer);
    }

    static char reverse(char coin) {
        if(coin == 'T') return 'H';
        return 'T';
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
                e.printStackTrace();
            }

            return str;
        }
    }
}
