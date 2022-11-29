package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun9466 {
	static StringBuilder sb = new StringBuilder();
	static Reader scanner = new Reader();
	static int n, count;
	static int[] selected;
	static boolean[] visited, finished;
	static void input() {
		n = scanner.nextInt();
		selected = new int[n + 1];
		visited = new boolean[n + 1];
		finished = new boolean[n + 1];
		count = 0;
		for(int idx = 1; idx <= n; idx++) selected[idx] = scanner.nextInt();
	}
	
	static void solution() {
		for(int student = 1; student <= n; student++) dfs(student);
		sb.append(n - count).append('\n');
	}
	
	static void dfs(int student) {
		if(visited[student]) return;
		visited[student] = true;
		int next = selected[student];
		if(!visited[next]) dfs(next);
		else {
			if(!finished[next]) {
				count++;
				for(int idx = next; idx != student; idx = selected[idx]) count++;
			}
		}
		finished[student] = true;
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
				} catch(IOException e) {
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
