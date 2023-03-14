package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun2457 {
	static int N;
    static Flower[] flowers;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        flowers = new Flower[N];

        for(int idx = 0; idx < N; idx++) {
            int startMonth = scanner.nextInt(), startDay = scanner.nextInt();
            int endMonth = scanner.nextInt(), endDay = scanner.nextInt();

            int start = startMonth * 100 + startDay;
            int end = endMonth * 100 + endDay;

            flowers[idx] = new Flower(start, end);
        }
    }

    static void solution() {
        Arrays.sort(flowers);

        int start = 301, end = 1201;
        int count = 0, max = 0, index = 0;

        while(start < end) {
            boolean hasNext = false; // 새 꽃 찾은지 여부

            for(int idx = index; idx < N; idx++) {
                // 종료일이 시작일이 이후면 의미 없음. 종료일에는 시작해야 이어지기 때문
                if(flowers[idx].start > start) break;

                if(max < flowers[idx].end) {
                    hasNext = true;
                    max = flowers[idx].end;
                    index = idx + 1;
                }
            }

            if(hasNext) {
                start = max;
                count++;
            } else break;
        }

        if(max < end) System.out.println(0);
        else System.out.println(count);
    }

    static class Flower implements Comparable<Flower> {
        int start, end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if(start != o.start) return start - o.start;
            return o.end - end;
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
