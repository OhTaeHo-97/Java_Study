package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun5676 {
    static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();
    static int N, K;
    static int[] nums;
    static String[][] orders;

    static void input() {
        String info = scanner.nextLine();
        if(info == null || info.isEmpty()) {
            System.out.print(sb);
            System.exit(0);
        }

        StringTokenizer st = new StringTokenizer(info);
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nums = new int[N];
        orders = new String[K][3];

        for(int idx = 0; idx < N; idx++) nums[idx] = scanner.nextInt();

        for(int idx = 0; idx < K; idx++) {
            orders[idx][0] = scanner.next();
            orders[idx][1] = scanner.next();
            orders[idx][2] = scanner.next();
        }
    }

    static void solution() {
        int height = getTreeHeight(N, 2);
        int nodeCnt = Math.toIntExact(getNodeCnt(height));
        int startIdx = (int)Math.pow(2, height - 1);
        int[] segmentTree = makeSegmentTree(nodeCnt, startIdx);

        for(int idx = 0; idx < K; idx++) {
            String[] order = orders[idx];

            if(order[0].equals("C"))
                changeNum(Integer.parseInt(order[1]) - 1 + startIdx, Integer.parseInt(order[2]), segmentTree);
            else if(order[0].equals("P")) {
                int num = getMultiplyNum(Integer.parseInt(order[1]) - 1 + startIdx, Integer.parseInt(order[2]) - 1 + startIdx, segmentTree);
                if(num > 0) sb.append('+');
                else if(num < 0) sb.append('-');
                else sb.append(0);
            }
        }

        sb.append('\n');
    }

    static int getMultiplyNum(int startIdx, int endIdx, int[] segmentTree) {
        int result = 1;

        while(startIdx <= endIdx) {
            if(startIdx % 2 == 1) result *= segmentTree[startIdx];
            if(endIdx % 2 == 0) result *= segmentTree[endIdx];

            startIdx = (startIdx + 1) / 2;
            endIdx = (endIdx - 1) / 2;
        }

        return result;
    }

    static void changeNum(int targetIdx, int num, int[] segmentTree) {
        if(num > 0) segmentTree[targetIdx] = 1;
        else if(num < 0) segmentTree[targetIdx] = -1;
        else segmentTree[targetIdx] = 0;
        targetIdx /= 2;

        while(targetIdx > 0) {
            segmentTree[targetIdx] = segmentTree[targetIdx * 2] * segmentTree[targetIdx * 2 + 1];
            targetIdx /= 2;
        }
    }

    static int[] makeSegmentTree(int nodeCnt, int startIdx) {
        int[] segmentTree = new int[nodeCnt];

        for(int idx = 0; idx < N; idx++) {
            if(nums[idx] > 0) segmentTree[idx + startIdx] = 1;
            else if(nums[idx] < 0) segmentTree[idx + startIdx] = -1;
            else segmentTree[idx + startIdx] = 0;
        }

        for(int idx = startIdx - 1; idx > 0; idx--)
            segmentTree[idx] = segmentTree[idx * 2] * segmentTree[idx * 2 + 1];

        return segmentTree;
    }

    static long getNodeCnt(int height) {
        return Math.round(Math.pow(2, height));
    }

    static int getTreeHeight(int size, int base) {
        return (int)Math.ceil(Math.log(size) / Math.log(base)) + 1;
    }

    public static void main(String[] args) {
        while(true) {
            input();
            solution();
        }
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
