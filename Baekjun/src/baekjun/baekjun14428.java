package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun14428 {
    static int N, M;
    static int[] series;
    static int[][] queries;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        series = new int[N + 1];
        series[0] = Integer.MAX_VALUE;
        for(int idx = 1; idx <= N; idx++)
            series[idx] = scanner.nextInt();

        M = scanner.nextInt();
        queries = new int[M][3];
        for(int idx = 0; idx < M; idx++) {
            int type = scanner.nextInt(), num1 = scanner.nextInt(), num2 = scanner.nextInt();

            queries[idx][0] = type;
            queries[idx][1] = num1;
            queries[idx][2] = num2;
        }
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();
        int height = getTreeHeight(N, 2);
        int nodeCnt = Math.toIntExact(getNodeCnt(height));
        int startIdx = (int)Math.pow(2, height - 1);
        int[] segmentTree = makeSegmentTree(nodeCnt, startIdx);

        for(int idx = 0; idx < M; idx++) {
            int[] query = queries[idx];

            if(query[0] == 1) changeNum(query[1], startIdx, query[2], segmentTree);
            else if(query[0] == 2) sb.append(getMinIdx(startIdx + query[1] - 1, startIdx + query[2] - 1, segmentTree)).append('\n');
        }

        System.out.print(sb);
    }

    static int getMinIdx(int startIdx, int endIdx, int[] segmentTree) {
        int minIdx = Integer.MAX_VALUE, min = Integer.MAX_VALUE;

        while(startIdx <= endIdx) {
            if(startIdx % 2 == 1) {
                if(min > series[segmentTree[startIdx]]) {
                    min = series[segmentTree[startIdx]];
                    minIdx = segmentTree[startIdx];
                } else if(min == series[segmentTree[startIdx]]) {
                    minIdx = Math.min(minIdx, segmentTree[startIdx]);
                }
            }
            if(endIdx % 2 == 0) {
                if(min > series[segmentTree[endIdx]]) {
                    min = series[segmentTree[endIdx]];
                    minIdx = segmentTree[endIdx];
                } else if(min == series[segmentTree[endIdx]]) {
                    minIdx = Math.min(minIdx, segmentTree[endIdx]);
                }
            }

            startIdx = (startIdx + 1) / 2;
            endIdx = (endIdx - 1) / 2;
        }

        return minIdx;
    }

    static void changeNum(int idx, int startIdx, int target, int[] segmentTree) {
        series[idx] = target;
        int targetIdx = idx + startIdx - 1;
        targetIdx /= 2;

        while(targetIdx > 0) {
            if(series[segmentTree[targetIdx * 2]] > series[segmentTree[targetIdx * 2 + 1]])
                segmentTree[targetIdx] = segmentTree[targetIdx * 2 + 1];
            else if(series[segmentTree[targetIdx * 2]] < series[segmentTree[targetIdx * 2 + 1]])
                segmentTree[targetIdx] = segmentTree[targetIdx * 2];
            else
                segmentTree[targetIdx] = Math.min(segmentTree[targetIdx * 2], segmentTree[targetIdx * 2 + 1]);

            targetIdx /= 2;
        }
    }

    static int[] makeSegmentTree(int nodeCnt, int startIdx) {
        int[] segmentTree = new int[nodeCnt];
        for(int idx = 1; idx <= N; idx++)
            segmentTree[idx + startIdx - 1] = idx;

        for(int idx = startIdx - 1; idx > 0; idx--) {
            if(series[segmentTree[idx * 2]] > series[segmentTree[idx * 2 + 1]])
                segmentTree[idx] = segmentTree[idx * 2 + 1];
            else if(series[segmentTree[idx * 2]] < series[segmentTree[idx * 2 + 1]])
                segmentTree[idx] = segmentTree[idx * 2];
            else
                segmentTree[idx] = Math.min(segmentTree[idx * 2], segmentTree[idx * 2 + 1]);
        }

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
    }
}
