package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun5214 {
	static int N, K, M;
    static ArrayList<ArrayList<Integer>> map;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        K = scanner.nextInt();
        M = scanner.nextInt();

        map = new ArrayList<>();
        for(int idx = 0; idx < N + M; idx++)
            map.add(new ArrayList<>());
    }

    static void solution() {

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
