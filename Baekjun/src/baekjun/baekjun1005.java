package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1005 {
	static StringBuilder sb = new StringBuilder();
	static int N, K, W;
	static int[] time, linkNum;
	static HashMap<Integer, ArrayList<Integer>> map;
	static void input() {
		Reader scanner = new Reader();
		int T = scanner.nextInt();
		for(int test = 0; test < T; test++) {
			N = scanner.nextInt();
			K = scanner.nextInt();
			time = new int[N + 1];
			linkNum = new int[N + 1];
			map = new HashMap<Integer, ArrayList<Integer>>();
			for(int structure = 1; structure <= N; structure++) map.put(structure, new ArrayList<Integer>());
			for(int structure = 1; structure <= N; structure++) time[structure] = scanner.nextInt();
			for(int link = 0; link < K; link++) {
				int first = scanner.nextInt();
				int second = scanner.nextInt();
				map.get(first).add(second);
				linkNum[second]++;
			}
			W = scanner.nextInt();
			solution();
		}
	}
	
	static void solution() {
		Queue<Integer> structures = new LinkedList<Integer>();
		int[] answer = new int[N + 1];
		for(int structure = 1; structure <= N; structure++) {
			if(linkNum[structure] == 0) {
				answer[structure] = time[structure];
				structures.offer(structure);
			}
		}
		while(!structures.isEmpty()) {
			int curStructure = structures.poll();
			for(int structure : map.get(curStructure)) {
				answer[structure] = Math.max(answer[structure], answer[curStructure] + time[structure]);
				linkNum[structure]--;
				if(linkNum[structure] == 0) structures.offer(structure);
			}
		}
		sb.append(answer[W]).append('\n');
	}
	
	public static void main(String[] args) {
		input();
		System.out.println(sb.toString());
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
