package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2263 {
	static int n, index;
    static int[] post, in, pre;
    static void input() {
        Reader scanner = new Reader();
        n = scanner.nextInt();
        post = new int[n];
        in = new int[n];
        pre = new int[n];

        for(int idx = 0; idx < n; idx++)
            in[idx] = scanner.nextInt();

        for(int idx = 0; idx < n; idx++)
            post[idx] = scanner.nextInt();
    }

    static void solution() {
        index = 0;
        preOrder(0, n - 1, 0, n - 1);

        StringBuilder sb = new StringBuilder();
        for(int node : pre) sb.append(node).append(' ');

        System.out.println(sb);
    }

    static void preOrder(int inStart, int inEnd, int postStart, int postEnd) {
        if(inStart <= inEnd && postStart <= postEnd) {
            pre[index++] = post[postEnd];

            int root = inStart;
            for(int idx = inStart; idx <= inEnd; idx++) {
                if(in[idx] == post[postEnd]) {
                    root = idx;
                    break;
                }
            }

            preOrder(inStart, root - 1, postStart, postStart + root - inStart - 1);
            preOrder(root + 1, inEnd, postStart + root - inStart, postEnd - 1);
        }
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
