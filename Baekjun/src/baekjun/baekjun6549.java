package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun6549 {
    static StringBuilder answer = new StringBuilder();
    static Reader scanner = new Reader();

    static int rectangleCount;
    static int treeStartIndex;
    static int[] heights;
    static int[] segmentTree;

    static boolean input() {
        rectangleCount = scanner.nextInt();
        if (rectangleCount == 0) {
            return false;
        }
        heights = new int[rectangleCount];

        for (int idx = 0; idx < rectangleCount; idx++) {
            heights[idx] = scanner.nextInt();
        }

        return true;
    }

    static void solution() {
        int height = getHeight(rectangleCount);
        int nodeCount = getNodeCount(height);
        treeStartIndex = nodeCount / 2;
        makeSegmentTree(nodeCount, treeStartIndex);
        answer.append(getMaxArea(0, rectangleCount - 1)).append('\n');
    }

    static long getMaxArea(int startIndex, int endIndex) {
        int minHeight = findMinHeightIndex(startIndex, endIndex);

        long area = (endIndex - startIndex + 1) * (long) heights[minHeight];

        if (startIndex <= minHeight - 1) {
            long leftMaxArea = getMaxArea(startIndex, minHeight - 1);
            area = Math.max(area, leftMaxArea);
        }
        if (endIndex >= minHeight + 1) {
            long rightMaxArea = getMaxArea(minHeight + 1, endIndex);
            area = Math.max(area, rightMaxArea);
        }

        return area;
    }

    static int findMinHeightIndex(int start, int end) {
        int startIndex = treeStartIndex + start;
        int endIndex = treeStartIndex + end;
        int index = segmentTree[startIndex];

        while (startIndex <= endIndex) {
            if (startIndex % 2 == 1) {
                if (heights[index] > heights[segmentTree[startIndex]]) {
                    index = segmentTree[startIndex];
                }
            }
            if (endIndex % 2 == 0) {
                if (heights[index] > heights[segmentTree[endIndex]]) {
                    index = segmentTree[endIndex];
                }
            }

            startIndex = (startIndex + 1) / 2;
            endIndex = (endIndex - 1) / 2;
        }

        return index;
    }

    static int getHeight(int size) {
        return (int) Math.ceil(Math.log(size) / Math.log(2)) + 1;
    }

    static int getNodeCount(int height) {
        return Math.toIntExact(Math.round(Math.pow(2, height)));
    }

    static void makeSegmentTree(int nodeCount, int startIndex) {
        segmentTree = new int[nodeCount];
        Arrays.fill(segmentTree, -1);
        for (int idx = 0; idx < rectangleCount; idx++) {
            segmentTree[startIndex + idx] = idx;
        }

        for (int idx = startIndex - 1; idx > 0; idx--) {
            if (segmentTree[idx * 2] == -1) {
                segmentTree[idx] = -1;
                continue;
            }
            if (segmentTree[idx * 2 + 1] == -1) {
                segmentTree[idx] = segmentTree[idx * 2];
                continue;
            }
            if (heights[segmentTree[idx * 2]] <= heights[segmentTree[idx * 2 + 1]]) {
                segmentTree[idx] = segmentTree[idx * 2];
                continue;
            }
            segmentTree[idx] = segmentTree[idx * 2 + 1];
        }
    }

    public static void main(String[] args) {
        while (true) {
            if (!input()) {
                break;
            }
            solution();
        }
        System.out.print(answer);
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
