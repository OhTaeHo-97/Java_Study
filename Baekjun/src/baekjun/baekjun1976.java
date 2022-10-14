package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class baekjun1976 {
	// DFS 풀이
	static int N, M;
	static HashMap<Integer, ArrayList<Integer>> map;
	static boolean[][] canVisit;
	static int[] schedule;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		map = new HashMap<>();
		canVisit = new boolean[N + 1][N + 1];
		for(int city = 1; city <= N; city++) map.put(city, new ArrayList<Integer>());
		for(int city = 1; city <= N; city++) {
			for(int connect = 1; connect <= N; connect++) {
				int connectivity = scanner.nextInt();
				if(connectivity == 1) {
					map.get(city).add(connect);
					map.get(connect).add(city);
				}
			}
		}
		schedule = new int[M];
		for(int city = 0; city < M; city++) schedule[city] = scanner.nextInt();
	}
	
	static void solution() {
		for(int city = 1; city <= N; city++) dfs(city, city);
		String answer = isPossibleSchedule();
		System.out.println(answer);
	}
	
	static void dfs(int start, int city) {
		canVisit[start][city] = true;
		for(int near : map.get(city)) {
			if(!canVisit[start][near]) dfs(start, near);
		}
	}
	
	static String isPossibleSchedule() {
		for(int index = 0; index < M - 1; index++) {
			if(!canVisit[schedule[index]][schedule[index + 1]]) return "NO";
		}
		return "YES";
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
