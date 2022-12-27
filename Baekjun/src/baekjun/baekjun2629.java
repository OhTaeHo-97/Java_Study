package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2629 {
	static final int MAX = 15000;
    static StringBuilder sb = new StringBuilder();
    static int weightNum, checkNum;
    static int[] weights, checkedWeights;
    static boolean[][] visited;
    static void input() {
        Reader scanner = new Reader();
        weightNum = scanner.nextInt();
        weights = new int[weightNum];
        for(int idx = 0; idx < weightNum; idx++) weights[idx] = scanner.nextInt();
        checkNum = scanner.nextInt();
        checkedWeights = new int[checkNum];
        for(int idx = 0; idx < checkNum; idx++) checkedWeights[idx] = scanner.nextInt();
    }

    static void solution() {
        visited = new boolean[weightNum + 1][MAX + 1];
        dfs(0, 0);
        for(int idx = 0; idx < checkNum; idx++) {
            if(checkedWeights[idx] > MAX) sb.append('N').append(' ');
            else if(!visited[weightNum][checkedWeights[idx]]) sb.append('N').append(' ');
            else sb.append('Y').append(' ');
        }
        System.out.println(sb);
    }

    static void dfs(int index, int sum) {
        if(visited[index][sum]) return;
        visited[index][sum] = true;
        if(index == weightNum) return;
        dfs(index + 1, sum + weights[index]);
        dfs(index + 1, sum);
        dfs(index + 1, Math.abs(sum - weights[index]));
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
