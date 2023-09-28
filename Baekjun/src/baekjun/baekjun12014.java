package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun12014 {
    static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();

    static int caseNum;
    static int N;
    static int K;
    static int[] stockPrices;

    static void input() {
        N = scanner.nextInt();
        K = scanner.nextInt();
        stockPrices = new int[N];

        for(int idx = 0; idx < N; idx++) {
            stockPrices[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        sb.append("Case #").append(caseNum++).append('\n');
        if(N < K) {
            sb.append(0).append('\n');
            return;
        }

        List<Integer> lis = new ArrayList<>();
        lis.add(0);

        for(int idx = 0; idx < N; idx++) {
            if(lis.get(lis.size() - 1) < stockPrices[idx]) {
                lis.add(stockPrices[idx]);
            } else {
                int lisIdx = getLisIdx(stockPrices[idx], lis);
                lis.set(lisIdx, stockPrices[idx]);
            }
        }

        int lisSize = lis.size() - 1 == 0 ? 1 : lis.size() - 1;
        if(K <= lisSize) {
            sb.append(1).append('\n');
        } else {
            sb.append(0).append('\n');
        }
    }

    static int getLisIdx(int target, List<Integer> lis) {
        int left = 1;
        int right = lis.size() - 1;

        while(left < right) {
            int mid = (left + right) / 2;

            if(lis.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    public static void main(String[] args) {
        caseNum = 1;
        int T = scanner.nextInt();
        while(T-- > 0) {
            input();
            solution();
        }

        System.out.print(sb);
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
