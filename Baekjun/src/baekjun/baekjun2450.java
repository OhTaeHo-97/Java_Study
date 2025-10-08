package baekjun;

import java.io.*;
import java.util.*;

public class baekjun2450 {
    private static final int MAX_SHAPE_NUMBER = 3;
    private static final int[][] SHAPE_COMBINATIONS = new int[][] {{1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}};

    private static int answer;
    private static int shapeCount;
    private static int[] shapes;
    private static int[] eachShapeCount;
    private static int[][] wrongShapeCount;

    private static void input() {
        Reader scanner = new Reader();

        shapeCount = scanner.nextInt();
        shapes = new int[shapeCount];
        eachShapeCount = new int[MAX_SHAPE_NUMBER + 1];
        answer = Integer.MAX_VALUE;

        for(int idx = 0; idx < shapeCount; idx++) {
            shapes[idx] = scanner.nextInt();
        }
    }

    private static void solution() {
        countEachShape();
        for(int[] sequence : SHAPE_COMBINATIONS) {
            wrongShapeCount = new int[MAX_SHAPE_NUMBER + 1][MAX_SHAPE_NUMBER + 1];
            findMinimumSwipeCount(sequence);
        }
        System.out.println(answer);
    }

    private static void findMinimumSwipeCount(int[] sequence) {
        int startIdx = 0;
        for(int shape : sequence) {
            int currentShapeCount = eachShapeCount[shape];
            for(int idx = startIdx; idx < startIdx + currentShapeCount; idx++) {
                if(shapes[idx] != shape) {
                    wrongShapeCount[shape][shapes[idx]]++;
                }
            }
            startIdx += currentShapeCount;
        }

        answer = Math.min(answer, wrongShapeCount[sequence[0]][sequence[1]] + wrongShapeCount[sequence[0]][sequence[2]] + Math.max(wrongShapeCount[sequence[1]][sequence[2]], wrongShapeCount[sequence[2]][sequence[1]]));
    }

    private static void countEachShape() {
        for(int shape : shapes) {
            eachShapeCount[shape]++;
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
