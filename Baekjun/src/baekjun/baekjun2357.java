package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2357 {
	static int N, M;
    static int[] nums;
    static int[][] pairs;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        nums = new int[N];
        pairs = new int[M][2];

        for(int idx = 0; idx < N; idx++) nums[idx] = scanner.nextInt();

        for(int idx = 0; idx < M; idx++) {
            int start = scanner.nextInt(), end = scanner.nextInt();
            pairs[idx][0] = start;
            pairs[idx][1] = end;
        }
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();
        int treeHeight = getTreeHeight(N, 2);
        long treeNodeCnt = getTreeNodeCnt(treeHeight);
        int startIdx = (int)Math.pow(2, treeHeight - 1);
        int[] minSegmentTree = makeMinTree(treeNodeCnt, startIdx);
        int[] maxSegmentTree = makeMaxTree(treeNodeCnt, startIdx);

        for(int[] pair : pairs) {
            int start = pair[0] + startIdx - 1, end = pair[1] + startIdx - 1;
            int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

            while(start <= end) {
                if(start % 2 == 1) {
                    min = Math.min(min, minSegmentTree[start]);
                    max = Math.max(max, maxSegmentTree[start]);
                }
                if(end % 2 == 0) {
                    min = Math.min(min, minSegmentTree[end]);
                    max = Math.max(max, maxSegmentTree[end]);
                }

                start = (start + 1) / 2;
                end = (end - 1) / 2;
            }

            sb.append(min).append(' ').append(max).append('\n');
        }

        System.out.println(sb);
    }

    static int[] makeMaxTree(long treeNodeCnt, int startIdx) {
        int[] segmentTree = new int[Math.toIntExact(treeNodeCnt)];
        for(int idx = 0; idx < N; idx++)
            segmentTree[startIdx + idx] = nums[idx];

        for(int idx = startIdx - 1; idx >= 1; idx--)
            segmentTree[idx] = Math.max(segmentTree[idx * 2], segmentTree[idx * 2 + 1]);

        return segmentTree;
    }

    static int[] makeMinTree(long treeNodeCnt, int startIdx) {
        int[] segmentTree = new int[Math.toIntExact(treeNodeCnt)];
        for(int idx = 0; idx < N; idx++)
            segmentTree[startIdx + idx] = nums[idx];

        for(int idx = startIdx - 1; idx >= 1; idx--)
            segmentTree[idx] = Math.min(segmentTree[idx * 2], segmentTree[idx * 2 + 1]);

        return segmentTree;
    }

    static long getTreeNodeCnt(int treeHeight) {
        return Math.round(Math.pow(2, treeHeight));
    }

    static int getTreeHeight(int nodeNum, int base) {
        return (int)Math.ceil(Math.log(nodeNum) / Math.log(base)) + 1;
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
