package showMeTheCode1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ConnectingNumbers_bj24955 {
	static final String DIVIDER = "1000000007";
    static int N, Q;
    static String[] A;
    static HashMap<Integer, ArrayList<Integer>> map;
    static int[][] plays;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        Q = scanner.nextInt();
        A = new String[N + 1];
        map = new HashMap<>();
        plays = new int[Q][2];
        for(int house = 1; house <= N; house++) {
            map.put(house, new ArrayList<>());
            A[house] = scanner.next();
        }
        for(int edge = 0; edge < N - 1; edge++) {
            int h1 = scanner.nextInt(), h2 = scanner.nextInt();
            map.get(h1).add(h2);
            map.get(h2).add(h1);
        }
        for(int idx = 0; idx < Q; idx++) {
            plays[idx][0] = scanner.nextInt();
            plays[idx][1] = scanner.nextInt();
        }
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();
        for(int idx = 0; idx < Q; idx++) {
            String answer = bfs(plays[idx][0], plays[idx][1]);
            BigInteger ans = new BigInteger(answer);
            BigInteger remainder = ans.remainder(new BigInteger(DIVIDER));
            sb.append(remainder).append('\n');
        }
        System.out.print(sb);
    }

    static String bfs(int start, int end) {
        Queue<Number> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        queue.offer(new Number(start, A[start]));
        String answer = "0";
        while(!queue.isEmpty()) {
            Number cur = queue.poll();
            if(cur.house == end) {
                if(answer.compareTo(cur.number) < 0) {
                    answer = cur.number;
                }
            }
            for(int neighbor : map.get(cur.house)) {
                if(!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(new Number(neighbor, cur.number + A[neighbor]));
                }
            }
        }
        return answer;
    }

    static class Number {
        int house;
        String number;
        public Number(int house, String number) {
            this.house = house;
            this.number = number;
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
