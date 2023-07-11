package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun18436 {
    static int N, M;
    static int[] nums;
    static int[][] queries;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        nums = new int[N];

        for(int idx = 0; idx < N; idx++)
            nums[idx] = scanner.nextInt();

        M = scanner.nextInt();
        queries = new int[M][3];
        for(int idx = 0; idx < M; idx++) {
            int type = scanner.nextInt(), num1 = scanner.nextInt(), num2 = scanner.nextInt();

            if(type == 1) {
                queries[idx][0] = type;
                queries[idx][1] = num1 - 1;
                queries[idx][2] = num2;
            } else {
                queries[idx][0] = type;
                queries[idx][1] = num1 - 1;
                queries[idx][2] = num2 - 1;
            }
        }
    }

    static void solution() {
        int height = getTreeHeight(N, 2);
        int nodeCnt = Math.toIntExact(getNodeCnt(height));
        int startIdx = (int)Math.pow(2, height - 1);
        int[] oddSegmentTree = makeOddSegmentTree(nodeCnt, startIdx), evenSegmentTree = makeEvenSegmentTree(nodeCnt, startIdx);

        StringBuilder sb = new StringBuilder();
        for(int idx = 0; idx < M; idx++) {
            int[] query = queries[idx];

            if(query[0] == 1)
                changeNum(startIdx + query[1], query[2], oddSegmentTree, evenSegmentTree);
            else if(query[0] == 2)
                sb.append(getEvenNum(startIdx + query[1], startIdx + query[2], evenSegmentTree)).append('\n');
            else if(query[0] == 3)
                sb.append(getOddNum(startIdx + query[1], startIdx + query[2], oddSegmentTree)).append('\n');
        }

        System.out.print(sb);
    }

    static int getOddNum(int startIdx, int endIdx, int[] oddSegmentTree) {
        int sum = 0;

        while(startIdx <= endIdx) {
            if(startIdx % 2 == 1) sum += oddSegmentTree[startIdx];
            if(endIdx % 2 == 0) sum += oddSegmentTree[endIdx];

            startIdx = (startIdx + 1) / 2;
            endIdx = (endIdx - 1) / 2;
        }

        return sum;
    }

    static int getEvenNum(int startIdx, int endIdx, int[] evenSegmentTree) {
        int sum = 0;

        while(startIdx <= endIdx) {
            if(startIdx % 2 == 1) sum += evenSegmentTree[startIdx];
            if(endIdx % 2 == 0) sum += evenSegmentTree[endIdx];

            startIdx = (startIdx + 1) / 2;
            endIdx = (endIdx - 1) / 2;
        }

        return sum;
    }

    static void changeNum(int targetIdx, int targetNum, int[] oddSegmentTree, int[] evenSegmentTree) {
        oddSegmentTree[targetIdx] = targetNum % 2 == 1 ? 1 : 0;
        evenSegmentTree[targetIdx] = targetNum % 2 == 0 ? 1 : 0;
        targetIdx /= 2;

        while(targetIdx > 0) {
            oddSegmentTree[targetIdx] = oddSegmentTree[targetIdx * 2] + oddSegmentTree[targetIdx * 2 + 1];
            evenSegmentTree[targetIdx] = evenSegmentTree[targetIdx * 2] + evenSegmentTree[targetIdx * 2 + 1];

            targetIdx /= 2;
        }
    }

    static int getTreeHeight(int size, int base) {
        return (int)Math.ceil(Math.log(size) / Math.log(base)) + 1;
    }

    static long getNodeCnt(int height) {
        return Math.round(Math.pow(2, height));
    }

    static int[] makeOddSegmentTree(int nodeCnt, int startIdx) {
        int[] oddSegmentTree = new int[nodeCnt];
        for(int idx = 0; idx < N; idx++)
            oddSegmentTree[startIdx + idx] = nums[idx] % 2 == 1 ? 1 : 0;

        for(int idx = startIdx - 1; idx > 0; idx--)
            oddSegmentTree[idx] = oddSegmentTree[idx * 2] + oddSegmentTree[idx * 2 + 1];

        return oddSegmentTree;
    }

    static int[] makeEvenSegmentTree(int nodeCnt, int startIdx) {
        int[] evenSegmentTree = new int[nodeCnt];
        for(int idx = 0; idx < N; idx++)
            evenSegmentTree[startIdx + idx] = nums[idx] % 2 == 0 ? 1 : 0;

        for(int idx = startIdx - 1; idx > 0; idx--)
            evenSegmentTree[idx] = evenSegmentTree[idx * 2] + evenSegmentTree[idx * 2 + 1];

        return evenSegmentTree;
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
