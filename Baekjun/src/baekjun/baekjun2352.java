package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun2352 {
	static int n;
    static int[] ports;

    static void input() {
        Reader scanner = new Reader();
        n = scanner.nextInt();
        ports = new int[n];

        for(int idx = 0; idx < n; idx++)
            ports[idx] = scanner.nextInt();
    }

    static void solution() {
        ArrayList<Integer> LIS = new ArrayList<>();
        LIS.add(0);

        for(int idx = 0; idx < ports.length; idx++) {
            if(LIS.get(LIS.size() - 1) < ports[idx]) {
                LIS.add(ports[idx]);
            } else {
                int index = binarySearch(1, LIS.size() - 1, ports[idx], LIS);
                LIS.set(index, ports[idx]);
            }
        }

        System.out.println(LIS.size() - 1 == 0 ? 1 : LIS.size() - 1);
    }

    static int binarySearch(int left, int right, int target, ArrayList<Integer> LIS) {
        while(left < right) {
            int mid = (left + right) / 2;

            if(LIS.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
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
