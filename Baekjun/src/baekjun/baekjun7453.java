package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun7453 {
	static int n;
    static int[] A, B, C, D;

    static void input() {
        Reader scanner = new Reader();
        n = scanner.nextInt();
        A = new int[n];
        B = new int[n];
        C = new int[n];
        D = new int[n];

        for(int idx = 0; idx < n; idx++) {
            A[idx] = scanner.nextInt();
            B[idx] = scanner.nextInt();
            C[idx] = scanner.nextInt();
            D[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        int[] AB = new int[n * n], CD = new int[n * n];

        getSequenceSum(A, B, C, D, AB, CD);

        Arrays.sort(AB);
        Arrays.sort(CD);

        for(int idx = 0; idx < AB.length; idx++) {
            System.out.println("AB[" + idx + "] = " + AB[idx]);
            System.out.println("CD[" + idx + "] = " + CD[idx]);
        }

        long answer = 0L;

        for(int idx = 0; idx < AB.length; idx++) {
            int target = AB[idx];
            answer += binarySearch(target * (-1), CD);
        }

        System.out.println(answer);
    }

    static int binarySearch(int target, int[] sum) {
        int right = upperBound(target, sum);
        System.out.println("right = " + right);
        int left = lowerBound(target, sum);
        System.out.println("left = " + left);

        return right - left;
    }

    static int upperBound(int target, int[] sum) {
        int left = 0, right = sum.length;
        while(left < right) {
            int mid = (left + right) / 2;

            if(sum[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    static int lowerBound(int target, int[] sum) {
        int left = 0, right = sum.length;
        while(left < right) {
            int mid = (left + right) / 2;

            if(sum[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    static void getSequenceSum(int[] s1, int[] s2, int[] s3, int[] s4, int[] sum1, int[] sum2) {
        int index = 0;
        for(int idx = 0; idx < n; idx++) {
            for(int idx2 = 0; idx2 < n; idx2++) {
                sum1[index] = s1[idx] + s2[idx2];
                sum2[index] = s3[idx] + s4[idx2];
                index++;
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
    }
}
