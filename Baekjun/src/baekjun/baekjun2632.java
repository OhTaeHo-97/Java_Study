package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2632 {
	static int m, n, target;
    static int[] A, B;

    static void input() {
        Reader scanner = new Reader();

        target = scanner.nextInt();
        m = scanner.nextInt();
        n = scanner.nextInt();
        A = new int[m];
        B = new int[n];

        for(int idx = 0; idx < m; idx++)
            A[idx] = scanner.nextInt();

        for(int idx = 0; idx < n; idx++)
            B[idx] = scanner.nextInt();
    }

    static void solution() {
        int[] caseA = makeCaseNum(target, A);
        int[] caseB = makeCaseNum(target, B);

        long answer = (long)(caseA[target] + caseB[target]);
        for(int idx = 1; idx < target; idx++)
            answer += (caseA[idx] * caseB[target - idx]);

        System.out.println(answer);
    }

    static int[] makeCaseNum(int target, int[] sizes) {
        int[] caseNum = new int[target + 1];
        int[] expandedSizes = new int[sizes.length * 2];
        System.arraycopy(sizes, 0, expandedSizes, 0, sizes.length);
        System.arraycopy(sizes, 0, expandedSizes, sizes.length, sizes.length);

        for(int start = 0; start < sizes.length; start++) {
            int sum = 0;
            for(int idx = 0; idx < (start == 0 ? sizes.length : sizes.length - 1); idx++) {
                sum += expandedSizes[start + idx];
                if(sum <= target) caseNum[sum]++;
            }
        }

        return caseNum;
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
