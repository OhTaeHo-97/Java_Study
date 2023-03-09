package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1561 {
	static int N, M;
    static int[] times;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        times = new int[M];

        for(int idx = 0; idx < M; idx++)
            times[idx] = scanner.nextInt();
    }

    static void solution() {
        if(N <= M) {
            System.out.println(N);
            return;
        }

        long maxTime = (N / M) * 30L;
        long targetTime = binarySearch(0, maxTime, N);

        System.out.println(getFinalRide(targetTime));
    }

    static int getFinalRide(long targetTime) {
        int count = getPrevPeopleNum(targetTime - 1);

        for(int idx = 0; idx < M; idx++) {
            if(targetTime % times[idx] == 0) count++;
            if(count == N) return (idx + 1);
        }

        return M;
    }

    static int getPrevPeopleNum(long time) {
        int count = M;

        for(int idx = 0; idx < M; idx++)
            count += time / times[idx];

        return count;
    }

    static long binarySearch(long left, long right, int target) {
        while(left <= right) {
            long mid = (left + right) / 2;
            long sum = M;

            for(int idx = 0; idx < M; idx++)
                sum += mid / times[idx];

            if(sum < target) left = mid + 1;
            else right = mid - 1;
        }

        return left;
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
