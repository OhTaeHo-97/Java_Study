package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun6213 {
    static int cowNum;
    static int groupNum;
    static int[] cowHeights;
    static int[][] groups;

    static void input() {
        Reader scanner = new Reader();

        cowNum = scanner.nextInt();
        groupNum = scanner.nextInt();
        cowHeights = new int[cowNum];
        groups = new int[groupNum][2];

        for(int cowIdx = 0; cowIdx < cowNum; cowIdx++) {
            cowHeights[cowIdx] = scanner.nextInt();
        }

        for(int groupIdx = 0; groupIdx < groupNum; groupIdx++) {
            groups[groupIdx][0] = scanner.nextInt() - 1;
            groups[groupIdx][1] = scanner.nextInt() - 1;
        }
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();
        int treeHeight = getTreeHeight(cowNum);
        int nodeNum = getNodeNum(treeHeight);
        int startIdx = Math.toIntExact((long)Math.pow(2, treeHeight - 1));
        int[] maxSegmentTree = getSegmentTree(nodeNum, startIdx, true);
        int[] minSegmentTree = getSegmentTree(nodeNum, startIdx, false);

        for(int groupIdx = 0; groupIdx < groupNum; groupIdx++) {
            int maxHeight = findHeight(groups[groupIdx][0] + startIdx, groups[groupIdx][1] + startIdx, maxSegmentTree, true);
            int minHeight = findHeight(groups[groupIdx][0] + startIdx, groups[groupIdx][1] + startIdx, minSegmentTree, false);

            sb.append(maxHeight - minHeight).append('\n');
        }

        System.out.print(sb);
    }

    static int findHeight(int startIdx, int endIdx, int[] segmentTree, boolean isMax) {
        int height = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        while(startIdx <= endIdx) {
            if(startIdx % 2 == 1) {
                height = compare(height, segmentTree[startIdx], isMax);
            }
            if(endIdx % 2 == 0) {
                height = compare(height, segmentTree[endIdx], isMax);
            }

            startIdx = (startIdx + 1) / 2;
            endIdx = (endIdx - 1) / 2;
        }

        return height;
    }

    static int[] getSegmentTree(int nodeNum, int startIdx, boolean isMax) {
        int[] segmentTree = new int[nodeNum];
        for(int idx = 0; idx < cowNum; idx++) {
            segmentTree[idx + startIdx] = cowHeights[idx];
        }

        for(int idx = startIdx - 1; idx > 0; idx--) {
            segmentTree[idx] = compare(segmentTree[idx * 2], segmentTree[idx * 2 + 1], isMax);
        }

        return segmentTree;
    }

    static int compare(int num1, int num2, boolean isMax) {
        if(isMax) return Math.max(num1, num2);
        else return Math.min(num1, num2);
    }

    static int getNodeNum(int treeHeight) {
        return Math.toIntExact((long)Math.pow(2, treeHeight));
    }

    static int getTreeHeight(int nodeNum) {
        return (int)Math.ceil(Math.log(nodeNum) / Math.log(2)) + 1;
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
