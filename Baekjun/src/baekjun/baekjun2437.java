package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun2437 {
	// 추는 오름차순으로 정렬
    // 추[0] ~ 추[n - 1]까지의 합을 m이라고 할 때, 1 ~ m 사이의 무게는 추[0] ~ 추[n - 1]로 표현 가능하다
    //      -> 1이 있어야 가능!
    //      -> 1이 없다면 1이 표현이 불가능한 것도 있지만 1 ~ m 사이의 모든 무게를 표현하려면 1이 필요!
    // 1. 추[n] <= m + 1 -> 추[0] ~ 추[n]을 이용해 1 ~ (m + 추[n])의 무게를 표현 가능하다
    //      -> 추[0] ~ 추[n - 1]을 이용해 m이라는 무게까지는 모두 표현할 수 있기에 다음에 나오는 무게가 m + 1 이하여야 이어지는 수가 될 수 있음
    // 2. 추[n] > m + 1 -> 추[0] ~ 추[n]을 이용해 m + 1의 무게는 표현이 불가능
    
    static int N;
    static int[] weights;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        weights = new int[N];

        for(int idx = 0; idx < N; idx++) weights[idx] = scanner.nextInt();
    }

    static void solution() {
        Arrays.sort(weights);

        if(weights[0] != 1) {
            System.out.println(1);
            return;
        }

        int sum = 0;
        for(int idx = 0; idx <N; idx++) {
            if(weights[idx] <= sum + 1) sum += weights[idx];
            else break;
        }

        System.out.println((sum + 1));
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
