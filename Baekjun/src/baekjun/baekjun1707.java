package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class baekjun1707 {
	static StringBuilder sb = new StringBuilder();
	static int V, E;
	static int[] colors;
	static boolean isTree = true;
	static HashMap<Integer, ArrayList<Integer>> map;
	static void input() {
		Reader scanner = new Reader();
		int test_num = scanner.nextInt();
		for(int i = 0; i < test_num; i++) {
			V = scanner.nextInt();
			E = scanner.nextInt();
			colors = new int[V + 1];
			map = new HashMap<>();
            isTree = true;
			for(int j = 1; j <= V; j++) map.put(j, new ArrayList<Integer>());
			for(int j = 0; j < E; j++) {
				int u = scanner.nextInt();
				int v = scanner.nextInt();
				map.get(u).add(v);
				map.get(v).add(u);
			}
			for(int j = 1; j <= V; j++) {
				if(!isTree) break;
				if(colors[j] == 0) dfs(j, 1);
			}
			sb.append(isTree ? "YES" : "NO").append('\n');
		}
	}
	
	static void dfs(int node, int color) {
		colors[node] = color;
		for(int n : map.get(node)) {
			if(colors[n] == color) {
				isTree = false;
				return;
			}
			if(colors[n] == 0) dfs(n, -color);
		}
	}
	
	public static void main(String[] args) {
		input();
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
					// TODO Auto-generated catch block
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
