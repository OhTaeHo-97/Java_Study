package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun2672 {
    static int rectangleCount;
    static int max;
    static int[] rectangleCounts;
    static List<Vertical> verticals;

    static void input() {
        Reader scanner = new Reader();

        rectangleCount = scanner.nextInt();
        max = 0;
        verticals = new ArrayList<>();

        for (int count = 0; count < rectangleCount; count++) {
            double startX = scanner.nextDouble();
            double startY = scanner.nextDouble();
            double width = scanner.nextDouble();
            double height = scanner.nextDouble();

            verticals.add(
                    new Vertical((int) (startX * 10), (int) (startY * 10), (int) (startY * 10) + (int) (height * 10),
                            1));
            verticals.add(new Vertical((int) (startX * 10) + (int) (width * 10), (int) (startY * 10),
                    (int) (startY * 10) + (int) (height * 10), -1));
            max = Math.max(max, (int) (startX * 10) + (int) (width * 10));
            max = Math.max(max, (int) (startY * 10) + (int) (height * 10));
        }
    }

    static void solution() {
        rectangleCounts = new int[max + 1];
        Collections.sort(verticals);
        long answer = calculateArea();
        print(answer);
    }

    static void print(long answer) {
        if (answer % 100 == 0) {
            System.out.println(answer / 100);
        } else {
            double a = 0;
            a = (double) answer / 100;
            System.out.println(String.format("%.2f", a));
        }
    }

    static long calculateArea() {
        long answer = 0L;
        int x = 0;

        for (Vertical vertical : verticals) {
            int count = findRectangleCount();
            answer += count * (vertical.x - x);
            updateRectangleCounts(vertical);
            x = vertical.x;
        }

        return answer;
    }

    static int findRectangleCount() {
        int count = 0;
        for (int idx = 0; idx <= max; idx++) {
            if (rectangleCounts[idx] > 0) {
                count++;
            }
        }
        return count;
    }

    static void updateRectangleCounts(Vertical vertical) {
        for (int idx = vertical.startY + 1; idx <= vertical.endY; idx++) {
            if (vertical.isStart == 1) {
                rectangleCounts[idx]++;
            } else {
                rectangleCounts[idx]--;
            }
        }
    }

    static class Vertical implements Comparable<Vertical> {
        int x;
        int startY;
        int endY;
        int isStart;

        public Vertical(int x, int startY, int endY, int isStart) {
            this.x = x;
            this.startY = startY;
            this.endY = endY;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Vertical o) {
            if (x != o.x) {
                return x - o.x;
            }
            return isStart - o.isStart;
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

        Double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
