package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun2381 {
    static int pointCount;
    static List<Integer> plus;
    static List<Integer> minus;

    static void input() {
        Reader scanner = new Reader();

        pointCount = scanner.nextInt();
        plus = new ArrayList<>();
        minus = new ArrayList<>();

        for (int point = 0; point < pointCount; point++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            plus.add(x + y);
            minus.add(x - y);
        }
    }

    static void solution() {
        Collections.sort(plus);
        Collections.sort(minus);
        System.out.println(Math.max(plus.get(pointCount - 1) - plus.get(0), minus.get(pointCount - 1) - minus.get(0)));
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
