package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun10868 {
	static int N, M;
    static int[] nums;
    static int[][] pairs;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        nums = new int[N];
        pairs = new int[M][2];

        for(int idx = 0; idx < N; idx++)
            nums[idx] = scanner.nextInt();

        for(int idx = 0; idx < M; idx++) {
            int start = scanner.nextInt(), end = scanner.nextInt();

            pairs[idx][0] = start;
            pairs[idx][1] = end;
        }
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();

        int height = getTreeHeight(N, 2);
        int nodeCnt = Math.toIntExact(getTreeNodeCnt(height));
        int startIdx = (int)Math.pow(2, height - 1);
        int[] segmentTree = makeSegmentTree(nodeCnt, startIdx);

        for(int idx = 0; idx < M; idx++)
            sb.append(getMinNum(pairs[idx][0] + startIdx - 1, pairs[idx][1] + startIdx - 1, segmentTree)).append('\n');

        System.out.print(sb);
    }

    static int getMinNum(int start, int end, int[] segmentTree) {
        int min = Integer.MAX_VALUE;

        while(start <= end) {
            if(start % 2 == 1) min = Math.min(min, segmentTree[start]);
            if(end % 2 == 0) min = Math.min(min, segmentTree[end]);

            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }

        return min;
    }

    static int[] makeSegmentTree(int nodeCnt, int startIdx) {
        int[] segmentTree = new int[nodeCnt];
        Arrays.fill(segmentTree, Integer.MAX_VALUE);

        for(int idx = 0; idx < N; idx++)
            segmentTree[startIdx + idx] = nums[idx];

        for(int idx = startIdx - 1; idx > 0; idx--)
            segmentTree[idx] = Math.min(segmentTree[idx * 2], segmentTree[idx * 2 + 1]);

        return segmentTree;
    }

    static int getTreeHeight(int nodeNum, int base) {
        return (int)(Math.ceil(Math.log(nodeNum) / Math.log(base))) + 1;
    }

    static long getTreeNodeCnt(int treeHeight) {
        return Math.round(Math.pow(2, treeHeight));
    }

    public static void main(String[] args) {
        input();
        solution();
    }

    static class Reader{
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
