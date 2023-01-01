package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjun17299 {
	static final int MAX = 1000000;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] num, counting;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        num = new int[N];
        counting = new int[MAX + 1];
        for(int idx = 0; idx < N; idx++) {
            num[idx] = scanner.nextInt();
            counting[num[idx]]++;
        }
    }

    static void solution() {
        Stack<Integer> stack = new Stack<>();
        for(int idx = 0; idx < N; idx++) {
            while(!stack.isEmpty() && counting[num[stack.peek()]] < counting[num[idx]]) {
                num[stack.pop()] = num[idx];
            }
            stack.push(idx);
        }
        while(!stack.isEmpty()) {
            num[stack.pop()] = -1;
        }
        for(int idx = 0; idx < N; idx++) sb.append(num[idx] + " ");
        System.out.println(sb);
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
