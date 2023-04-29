package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2042 {
	static int N, M, K;
    static long[] nums;
    static long[][] commands;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
        nums = new long[N];
        commands = new long[M + K][3];

        for(int idx = 0; idx < N; idx++) nums[idx] = scanner.nextLong();
        for(int idx = 0; idx < M + K; idx++) {
            long type = scanner.nextLong(), num1 = scanner.nextLong(), num2 = scanner.nextLong();
            commands[idx][0] = type;
            commands[idx][1] = num1;
            commands[idx][2] = num2;
        }
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();
        int level = getLevel(N);
        int size = (int)Math.pow(2, level), startIdx = (int)(Math.pow(2, level - 1));
        long[] tree = makeTree(size, startIdx);

        for(long[] command : commands) {
            if(command[0] == 1) {
                int targetIdx = (int)(startIdx + command[1] - 1);
                long gap = command[2] - tree[targetIdx];
                while(targetIdx > 0) {
                    tree[targetIdx] += gap;
                    targetIdx /= 2;
                }
            } else {
                int start = (int)command[1] + startIdx - 1, end = (int)command[2] + startIdx - 1;
                long sum = 0;

                while(start <= end) {
                    if(start % 2 == 1) sum += tree[start];
                    if(end % 2 == 0) sum += tree[end];

                    start = (start + 1) / 2;
                    end = (end - 1) / 2;
                }

                sb.append(sum).append('\n');
            }
        }

        System.out.print(sb);
    }

    static long[] makeTree(int size, int startIdx) {
        long[] tree = new long[size];

        // 입력값들 리프노드에 넣기
        for(int idx = 0; idx < N; idx++)
            tree[startIdx + idx] = nums[idx];

        // 자식들의 합 채우기
        for(int idx = startIdx - 1; idx >= 1; idx--)
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];

        return tree;
    }

    static int getLevel(int nodeNum) {
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
