package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1039 {
    private static char[] N;
    private static int K;
    private static int max = -1;

    private static void input() {
        Reader scanner = new Reader();

        String number = scanner.next();
        N = number.toCharArray();
        K = scanner.nextInt();
    }

    private static void solution() {
        bfs();
        System.out.println(max);
    }

    private static void bfs() {
        Queue<String> queue = new LinkedList<>();
        boolean[][] visited = new boolean[1_000_000 + 1][K + 1];

        queue.offer(new String(N));
        visited[Integer.parseInt(new String(N))][0] = true;

        int turn = 0;
        while (!queue.isEmpty() && turn < K) {
            int size = queue.size();
            for (int count = 0; count < size; count++) {
                String cur = queue.poll();
                for (int idx1 = 0; idx1 < cur.length(); idx1++) {
                    for (int idx2 = idx1 + 1; idx2 < cur.length(); idx2++) {
                        String swappedNumber = swap(idx1, idx2, cur);
                        if (swappedNumber.charAt(0) == '0') {
                            continue;
                        }
                        int number = Integer.parseInt(swappedNumber);
                        if (!visited[number][turn + 1]) {
                            visited[number][turn + 1] = true;
                            if (turn + 1 == K) {
                                max = Math.max(max, number);
                            } else {
                                queue.offer(String.valueOf(number));
                            }
                        }
                    }
                }
            }
            turn++;
        }
    }

    private static String swap(int idx1, int idx2, String number) {
        char[] numArr = number.toCharArray();
        char temp = numArr[idx1];
        numArr[idx1] = numArr[idx2];
        numArr[idx2] = temp;

        return new String(numArr);
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
