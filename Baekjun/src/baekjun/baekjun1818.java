package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun1818 {
	static int N;
    static int[] books;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        books = new int[N];

        for(int idx = 0; idx < N; idx++) books[idx] = scanner.nextInt();
    }

    static void solution() {
        List<Integer> LIS = new ArrayList<>();
        LIS.add(0);

        for(int idx = 0; idx < N; idx++) {
            int book = books[idx];

            if(LIS.get(LIS.size() - 1) < book) {
                LIS.add(book);
            } else {
                int index = binarySearch(1, LIS.size() - 1, book, LIS);
                LIS.set(index, book);
            }
        }

        int size = LIS.size() - 1 == 0 ? 1 : LIS.size() - 1;
        System.out.println(N - size);
    }

    static int binarySearch(int min, int max, int book, List<Integer> LIS) {
        while(min < max) {
            int mid = (min + max) / 2;

            if(LIS.get(mid) < book) min = mid + 1;
            else max = mid;
        }

        return max;
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
