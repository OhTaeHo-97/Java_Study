package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun3745 {
	static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] stocks;

    static void solution() {
        ArrayList<Integer> subsequence = new ArrayList<>();
        subsequence.add(0);

        for(int idx = 0; idx < N; idx++) {
            if(subsequence.get(subsequence.size() - 1) < stocks[idx])
                subsequence.add(stocks[idx]);
            else {
                int index = binarySearch(0, subsequence.size() - 1, stocks[idx], subsequence);
                subsequence.set(index, stocks[idx]);
            }
        }

        System.out.println(subsequence.size() - 1 == 0 ? 1 : subsequence.size() - 1);
        sb.append(subsequence.size() - 1 == 0 ? 1 : subsequence.size() - 1).append('\n');
    }

    static int binarySearch(int left, int right, int objective, ArrayList<Integer> subsequence) {
        int mid = 0;
        while(left < right) {
            mid = (left + right) / 2;
            if(subsequence.get(mid) < objective) left = mid + 1;
            else right = mid;
        }

        return right;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = null;

        while((inputStr = br.readLine()) != null) {
            N = Integer.parseInt(inputStr.trim());
            stocks = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int idx = 0; idx < N; idx++)
                stocks[idx] = Integer.parseInt(st.nextToken());

            solution();
        }

        System.out.print(sb);
    }
}
