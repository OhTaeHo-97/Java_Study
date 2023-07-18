package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon1615 {
    static int N, M;
    static Map<Integer, List<Integer>> map;
    static int[] tree;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new HashMap<>();
        tree = new int[N + 1];

        for(int edge = 0; edge < M; edge++) {
            int i = scanner.nextInt(), j = scanner.nextInt();
            map.computeIfAbsent(i, list -> new ArrayList<>()).add(j);
        }
    }

    static void solution() {
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);

        int idx = 0;
        long result = 0;
        for(int key : keys) {
            for(int num : map.get(key)) {
                result += (idx++) - sum(num);
                update(num, 1);
            }
        }

        System.out.println(result);
    }

    static long sum(int idx) {
        long result = 0;
        while (idx > 0) {
            result += tree[idx];
            idx -= idx & -idx;
        }

        return result;
    }

    static void update(int idx, int val) {
        while(idx <= N) {
            tree[idx] += val;
            idx += idx & -idx;
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
