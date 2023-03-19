package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun6086 {
	static final int SIZE = 52;

    static int N;
    static int[][] flow, capacity;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        flow = new int[SIZE][SIZE];
        capacity = new int[SIZE][SIZE];

        for(int pipe = 0; pipe < N; pipe++) {
            int start = charToInt(scanner.next().charAt(0)), end = charToInt(scanner.next().charAt(0));
            int amount = scanner.nextInt();

            capacity[start][end] += amount;
            capacity[end][start] += amount;
        }
    }

    static void solution() {
        findMaxFlow(0, 25);
    }

    static void findMaxFlow(int source, int sink) {
        int totalFlow = 0;
        int[] parents = new int[SIZE];
        Queue<Integer> queue;

        while(true) {
            Arrays.fill(parents, -1);
            queue = new LinkedList<>();

            parents[source] = source;
            queue.offer(source);

            while(!queue.isEmpty() && parents[sink] == -1) {
                int cur = queue.poll();
                for(int next = 0; next < SIZE; next++) {
                    // 잔여 용량이 남아있는 간선을 따라 탐색
                    if(capacity[cur][next] - flow[cur][next] > 0 && parents[next] == -1) {
                        queue.offer(next);
                        parents[next] = cur;
                    }
                }
            }

            // 더 이상 증가 경로가 없으면 종료
            if(parents[sink] == -1) break;

            // 증가 경로를 통해 유량을 얼마나 보낼지 결정
            int amount = Integer.MAX_VALUE;
            for(int idx = sink; idx != source; idx = parents[idx]) {
                amount = Math.min(capacity[parents[idx]][idx] - flow[parents[idx]][idx], amount);
            }

            // 증가 경로를 통해 유량을 보낸다
            for(int idx = sink; idx != source; idx = parents[idx]) {
                flow[parents[idx]][idx] += amount;
                flow[idx][parents[idx]] -= amount;
            }

            totalFlow += amount;
        }

        System.out.println(totalFlow);
    }

    static int charToInt(char alphabet) {
        if('A' <= alphabet && alphabet <= 'Z') return alphabet - 'A';
        else if('a' <= alphabet && alphabet <= 'z') return alphabet - 'A' - 6;
        return -1;
    }

    static class Pipe {
        char end;
        int amount;

        public Pipe(char end, int amount) {
            this.end = end;
            this.amount = amount;
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
