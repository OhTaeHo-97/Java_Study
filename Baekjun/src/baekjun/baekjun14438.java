package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun14438 {
    static int N, M;
    static long[] nums;
    static long[][] orders;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        nums = new long[N];
        for(int idx = 0; idx < N; idx++) nums[idx] = scanner.nextLong();

        M = scanner.nextInt();
        orders = new long[M][3];
        for(int idx = 0; idx < M; idx++) {
            int type = scanner.nextInt();
            int num1 = scanner.nextInt();
            long num2 = scanner.nextLong();

            orders[idx][0] = type;
            if(type == 1) {
                orders[idx][1] = num1 - 1;
                orders[idx][2] = num2;
            } else if(type == 2) {
                orders[idx][1] = num1 - 1;
                orders[idx][2] = num2 - 1;
            }
        }
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();
        int height = getTreeHeight(N, 2);
        int nodeCnt = Math.toIntExact(getNodeCnt(height));
        int startIdx = (int)Math.pow(2, height - 1);
        long[] segmentTree = makeSegmentTree(nodeCnt, startIdx);

        for(int idx = 0; idx < M; idx++) {
            long[] order = orders[idx];

            if(order[0] == 1)
                changeNum((int)order[1] + startIdx, order[2], segmentTree);
            else if(order[0] == 2)
                sb.append(getMinNum((int)order[1] + startIdx, (int)order[2] + startIdx, segmentTree)).append('\n');
        }

        System.out.print(sb);
    }

    static long getMinNum(int startIdx, int endIdx, long[] segmentTree) {
        long min = Long.MAX_VALUE;

        while(startIdx <= endIdx) {
            if(startIdx % 2 == 1) min = Math.min(min, segmentTree[startIdx]);
            if(endIdx % 2 == 0) min = Math.min(min, segmentTree[endIdx]);

            startIdx = (startIdx + 1) / 2;
            endIdx = (endIdx - 1) / 2;
        }

        return min;
    }

    static void changeNum(int targetIdx, long changedNum, long[] segmentTree) {
        segmentTree[targetIdx] = changedNum;
        targetIdx /= 2;

        while(targetIdx > 0) {
            segmentTree[targetIdx] = Math.min(segmentTree[targetIdx * 2], segmentTree[targetIdx * 2 + 1]);
            targetIdx /= 2;
        }
    }

    static long[] makeSegmentTree(int nodeCnt, int startIdx) {
        long[] segmentTree = new long[nodeCnt];
        Arrays.fill(segmentTree, Long.MAX_VALUE);

        for(int idx = 0; idx < N; idx++)
            segmentTree[startIdx + idx] = nums[idx];

        for(int idx = startIdx - 1; idx > 0; idx--)
            segmentTree[idx] = Math.min(segmentTree[idx * 2], segmentTree[idx * 2 + 1]);

        return segmentTree;
    }

    static int getTreeHeight(int size, int base) {
        return (int)Math.ceil(Math.log(size) / Math.log(base)) + 1;
    }

    static long getNodeCnt(int height) {
        return Math.round(Math.pow(2, height));
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
