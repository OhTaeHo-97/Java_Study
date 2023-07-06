package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun12837 {
    static Reader scanner;
    static int N, Q, height, nodeCnt, startIdx;
    static long[] segmentTree;

    static void input() {
        scanner = new Reader();

        N = scanner.nextInt();
        Q = scanner.nextInt();

        height = getTreeHeight(N, 2);
        nodeCnt = Math.toIntExact(getNodeCnt(height));
        startIdx = (int) Math.pow(2, height - 1);
        segmentTree = new long[nodeCnt];
    }

    static int getTreeHeight(int size, int base) {
        return (int)Math.ceil(Math.log(size) / Math.log(base)) + 1;
    }

    static long getNodeCnt(int height) {
        return Math.round(Math.pow(2, height));
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();
        for(int idx = 0; idx < Q; idx++) {
            int type = scanner.nextInt(), num1 = scanner.nextInt(), num2 = scanner.nextInt();
            if(type == 1)
                updateSegmentTree(num1 - 1 + startIdx, num2, segmentTree);
            else if(type == 2)
                sb.append(getSum(num1 - 1 + startIdx, num2 - 1 + startIdx, segmentTree)).append('\n');
        }

        System.out.print(sb);
    }

    static void updateSegmentTree(int targetIdx, int target, long[] segmentTree) {
        segmentTree[targetIdx] += target;
        targetIdx /= 2;

        while(targetIdx > 0) {
            segmentTree[targetIdx] = segmentTree[targetIdx * 2] + segmentTree[targetIdx * 2 + 1];
            targetIdx /= 2;
        }
    }

    static long getSum(int startIdx, int endIdx, long[] segmentTree) {
        long sum = 0;

        while(startIdx <= endIdx) {
            if(startIdx % 2 == 1) sum += segmentTree[startIdx];
            if(endIdx % 2 == 0) sum += segmentTree[endIdx];

            startIdx = (startIdx + 1) / 2;
            endIdx = (endIdx - 1) / 2;
        }

        return sum;
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
