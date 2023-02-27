package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun4256 {
	static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();
    static int n;
    static int[] preOrder, inOrder;

    static void input() {
        n = scanner.nextInt();
        preOrder = new int[n];
        inOrder = new int[n];

        for(int node = 0; node < n; node++)
            preOrder[node] = scanner.nextInt();

        for(int node = 0; node < n; node++)
            inOrder[node] = scanner.nextInt();
    }

    static void solution() {
        getPostOrder(0, 0, n - 1);
        sb.append('\n');
    }

    static void getPostOrder(int rootIdx, int start, int end) {
        if(rootIdx >= n) return;

        int root = preOrder[rootIdx];

        for(int idx = start; idx <= end; idx++) {
            if(root == inOrder[idx]) {
                getPostOrder(rootIdx + 1, start, idx);
                getPostOrder(rootIdx + 1 + idx - start, idx + 1, end);
                sb.append(root + " ");
            }
        }
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();

        while(T-- > 0) {
            input();
            solution();
        }

        System.out.println(sb);
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
