package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun8980 {
	static int N, C, M;
    static Stuff[] stuffs;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        C = scanner.nextInt();
        M = scanner.nextInt();
        stuffs = new Stuff[M];

        for(int idx = 0; idx < M; idx++) {
            int sentTown = scanner.nextInt();
            int receivedTown = scanner.nextInt();
            int boxNum = scanner.nextInt();

            stuffs[idx] = new Stuff(sentTown, receivedTown, boxNum);
        }
    }

    static void solution() {
        Arrays.sort(stuffs);

        // 각 마을당 보낼 수 있는 최대 용량을 설정
        int[] weight = new int[N + 1];
        Arrays.fill(weight, C);

        int answer = 0;
        for(int idx = 0; idx < M; idx++) {
            Stuff stuff = stuffs[idx];
            int maxBoxNum = Integer.MAX_VALUE;

            // 보내는 마을과 받는 마을 사이의 경로 마을 중에서 보낼 수 있는 최대 한도를 구한다.
            for(int town = stuff.sentTown; town < stuff.receivedTown; town++) {
                maxBoxNum = Math.min(maxBoxNum, weight[town]);
            }

            // 위에서 구한 보낼 수 있는 최대 한도가 현재 보내려는 택배의 개수보다 크다면,
            // 보내는 마을과 받는 마을 사이의 경로 마을 모두 용량에서 현재 보내려는 택배의 개수를 뺀다.
            if(maxBoxNum >= stuff.boxNum) {
                for(int town = stuff.sentTown; town < stuff.receivedTown; town++) {
                    weight[town] -= stuff.boxNum;
                }
                answer += stuff.boxNum;
            } else {
                // 보낼 수 있는 최대 한도보다 현재 보내려는 택배의 개수가 클 경우,
                // 현재 보내려는 택배의 개수가 아닌 위에서 구한 최대 한도를 기준으로 한다.
                for(int town = stuff.sentTown; town < stuff.receivedTown; town++) {
                    weight[town] -= maxBoxNum;
                }
                answer += maxBoxNum;
            }
        }

        System.out.println(answer);
    }

    static class Stuff implements Comparable<Stuff> {
        int sentTown, receivedTown, boxNum;

        public Stuff(int sentTown, int receivedTown, int boxNum) {
            this.sentTown = sentTown;
            this.receivedTown = receivedTown;
            this.boxNum = boxNum;
        }

        @Override
        public int compareTo(Stuff s) {
            return this.receivedTown - s.receivedTown;
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
