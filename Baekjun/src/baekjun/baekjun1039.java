package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1039 {
	static int N, K;
    static int answer;

    static void input() {
        Reader scanner = new Reader();

        String integer = scanner.next();
        N = Integer.parseInt(integer);
        K = scanner.nextInt();
    }

    static void solution() {
        answer = Integer.MIN_VALUE;
        bfs();
        System.out.println(answer);
    }

    static void bfs() {
        Queue<Number> queue = new LinkedList<>();
        boolean[][] visited = new boolean[1000001][K + 1];

        queue.offer(new Number(N, 0));
        visited[N][0] = true;

        while(!queue.isEmpty()) {
            Number cur = queue.poll();

            if(cur.count == K) {
                answer = Math.max(answer, cur.num);
                continue;
            }

            int len = String.valueOf(cur.num).length();

            for(int i = 0; i < len - 1; i++) {
                for(int j = i + 1; j < len; j++) {
                    int next = swap(cur.num, i, j);

                    if(next != -1 && !visited[next][cur.count + 1]) {
                        queue.offer(new Number(next, cur.count + 1));
                        visited[next][cur.count + 1] = true;
                    }
                }
            }
        }
    }

    static int swap(int num, int i, int j) {
        char[] numArr = String.valueOf(num).toCharArray();

        if(i == 0 && numArr[j] == '0') return -1;

        char temp = numArr[i];
        numArr[i] = numArr[j];
        numArr[j] = temp;

        return Integer.parseInt(new String(numArr));
    }

    static class Number {
        int num, count;

        public Number(int num, int count) {
            this.num = num;
            this.count = count;
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
