package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1275 {
	static int N, Q;
    static int[] nums;
    static int[][] commands;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        Q = scanner.nextInt();
        nums = new int[N];
        commands = new int[Q][4];

        for(int idx = 0; idx < N; idx++) nums[idx] = scanner.nextInt();

        for(int idx = 0; idx < Q; idx++) {
            int x = scanner.nextInt(), y = scanner.nextInt(), a = scanner.nextInt(), b = scanner.nextInt();
            if(x < y) {
                commands[idx][0] = x;
                commands[idx][1] = y;
            } else {
                commands[idx][0] = y;
                commands[idx][1] = x;
            }

            commands[idx][2] = a;
            commands[idx][3] = b;
        }
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();
        int treeHeight = getTreeHeight(N);
        int treeNodeCnt = Math.toIntExact(getNodeCnt(treeHeight));
        int startIdx = (int)Math.pow(2, treeHeight - 1);
        long[] segmentTree = makeSegmentTree(treeNodeCnt, startIdx);

        for(int[] command : commands) {
            sb.append(getSum(startIdx + command[0] - 1, startIdx + command[1] - 1, segmentTree)).append('\n');
            changeNum(startIdx + command[2] - 1, command[3], segmentTree);
        }

        System.out.print(sb);
    }

    static void changeNum(int targetIdx, int num, long[] segmentTree) {
        long gap = (long)num - segmentTree[targetIdx];

        while(targetIdx > 0) {
            segmentTree[targetIdx] += gap;
            targetIdx /= 2;
        }
    }

    static long getSum(int start, int end, long[] segmentTree) {
        long sum = 0L;

        while(start <= end) {
            if(start % 2 == 1) sum += segmentTree[start];
            if(end % 2 == 0) sum += segmentTree[end];

            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }

        return sum;
    }

    static long[] makeSegmentTree(int treeNodeCnt, int startIdx) {
        long[] segmentTree = new long[treeNodeCnt];

        for(int idx = 0; idx < N; idx++)
            segmentTree[startIdx + idx] = nums[idx];

        for(int idx = startIdx - 1; idx > 0; idx--)
            segmentTree[idx] = segmentTree[idx * 2] + segmentTree[idx * 2 + 1];

        return segmentTree;
    }

    static long getNodeCnt(int treeHeight) {
        return Math.round(Math.pow(2, treeHeight));
    }

    static int getTreeHeight(int numsCnt) {
        return (int)Math.ceil(Math.log(numsCnt) / Math.log(2)) + 1;
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
