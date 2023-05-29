package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun11505 {
	static final int DIVISOR = 1_000_000_007;
    static int N, M, K;
    static int[] nums;
    static int[][] commands;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
        nums = new int[N];
        commands = new int[M + K][3];

        for(int idx = 0; idx < N; idx++) nums[idx] = scanner.nextInt();
        for(int idx = 0; idx < M + K; idx++) {
            int commandType = scanner.nextInt(), start = scanner.nextInt(), end = scanner.nextInt();

            commands[idx][0] = commandType;
            commands[idx][1] = start;
            commands[idx][2] = end;
        }
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();
        int treeHeight = getTreeHeight(N, 2);
        int nodeCnt = Math.toIntExact(getTreeNodeCnt(treeHeight));
        int startIdx = (int)Math.pow(2, treeHeight - 1);
        int[] segmentTree = makeSegmentTree(nodeCnt, startIdx);

        for(int idx = 0; idx < M + K; idx++) {
            int[] command = commands[idx];
            if(command[0] == 1)
                changeNum(startIdx + (command[1] - 1), command[2], segmentTree);
            else
                sb.append(getMultiplyNums(startIdx + (command[1] - 1), startIdx + (command[2] - 1), segmentTree)).append('\n');
        }

        System.out.print(sb);
    }

    static void changeNum(int target, int newNum, int[] segmentTree) {
        segmentTree[target] = newNum;
        target = target / 2;

        while(target != 0) {
            segmentTree[target] = (int)((long)segmentTree[target * 2] * segmentTree[target * 2 + 1] % DIVISOR);
            target /= 2;
        }
    }

    static int getMultiplyNums(int start, int end, int[] segmentTree) {
        int multiply = 1;

        while(start <= end) {
            if(start % 2 == 1) multiply = (int)(((long)multiply * segmentTree[start]) % DIVISOR);
            if(end % 2 == 0) multiply = (int)(((long)multiply * segmentTree[end]) % DIVISOR);

            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }

        return multiply;
    }

    static int[] makeSegmentTree(int nodeCnt, int startIdx) {
        int[] segmentTree = new int[nodeCnt];
        Arrays.fill(segmentTree, 1);

        for(int idx = 0; idx < N; idx++)
            segmentTree[startIdx + idx] = nums[idx];

        for(int idx = startIdx - 1; idx > 0; idx--)
            segmentTree[idx] = (int)((long)segmentTree[idx * 2] * segmentTree[idx * 2 + 1] % DIVISOR);

        return segmentTree;
    }

    static int getTreeHeight(int nodeNum, int base) {
        return (int)Math.ceil(Math.log(nodeNum) / Math.log(base)) + 1;
    }

    static long getTreeNodeCnt(int height) {
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
    }
}
