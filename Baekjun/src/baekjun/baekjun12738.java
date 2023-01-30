package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun12738 {
	static int N;
    static int[] sequence;

    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        sequence = new int[N];

        for(int idx = 0; idx < N; idx++)
            sequence[idx] = scanner.nextInt();
    }

//    static void solution() {
//        ArrayList<Integer> subsequence = new ArrayList<>();
//        subsequence.add(0);
//        for(int idx = 0; idx < N; idx++) {
//            if(sequence[idx] > subsequence.get(subsequence.size() - 1)) {
//                subsequence.add(sequence[idx]);
//            } else {
//                int index = binarySearch(1, subsequence.size() - 1, sequence[idx], subsequence);
//                subsequence.set(index, sequence[idx]);
//            }
//        }
//        System.out.println(subsequence.size() - 1 == 0 ? 1 : subsequence.size() - 1);
//    }
//
//    static int binarySearch(int left, int right, int objective, ArrayList<Integer> subsequence) {
//        while(left < right) {
//            int mid = (left + right) / 2;
//            if(subsequence.get(mid) < objective) {
//                left = mid + 1;
//            } else {
//                right = mid;
//            }
//        }
//        return right;
//    }

    static void solution() {
        int[] subsequence = new int[N];

        int index = 0;
        for(int idx = 0; idx < N; idx++) {
            int n = Arrays.binarySearch(subsequence, 0, index, sequence[idx]);

            if(n < 0) n = Math.abs(n + 1);

            subsequence[n] = sequence[idx];
            if(n == index) index++;
        }

        System.out.println(index);
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
