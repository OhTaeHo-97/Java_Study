package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun17471 {
	static int N, result = Integer.MAX_VALUE;
	static int[] population;
	static ArrayList<Integer>[] map;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		population = new int[N + 1];
		map = new ArrayList[N + 1];
		for(int area = 1; area <= N; area++) {
			population[area] = scanner.nextInt();
			map[area] = new ArrayList<>();
		}
		for(int area = 1; area <= N; area++) {
			int num = scanner.nextInt();
			for(int count = 1; count <= num; count++) {
				int neighbor = scanner.nextInt();
				map[area].add(neighbor);
			}
		}
	}
	
	static void solution() {
		ArrayList<Integer> list = new ArrayList<>();
		for(int size = 1; size <= N / 2; size++) makeList(1, size, list);
		if(result == Integer.MAX_VALUE) result = -1;
		System.out.println(result);
	}
	
	static void makeList(int start, int size, ArrayList<Integer> list) {
		if(size == 0) {
			calcPopulation(list);
			return;
		}
		for(int area = start; area <= N; area++) {
			list.add(area);
			makeList(area + 1, size - 1, list);
			list.remove(list.size() - 1);
		}
	}
	
	static void calcPopulation(ArrayList<Integer> list) {
		if(!isConnected(list.get(0), list, list.size())) return;
		ArrayList<Integer> list2 = new ArrayList<>();
		for(int area = 1; area <= N; area++) {
			if(list.contains(area)) continue;
			list2.add(area);
		}
		if(!isConnected(list2.get(0), list2, list2.size())) {
			return;
		}
		int p1 = 0;
		for(int index = 0; index < list.size(); index++)
			p1 += population[list.get(index)];
		int p2 = 0;
		for(int index = 0; index < list2.size(); index++)
			p2 += population[list2.get(index)];
		int dif = Math.abs(p1 - p2);
		result = Math.min(result, dif);
	}
	
	static boolean isConnected(int area, ArrayList<Integer> list, int size) {
		boolean[] visited = new boolean[N + 1];
		visited[area] = true;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(area);
		int count = 1;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for(int neighbor : map[cur]) {
				if(!visited[neighbor] && list.contains(neighbor)) {
					visited[neighbor] = true;
					count++;
					queue.offer(neighbor);
				}
			}
		}
		if(count == size) return true;
		return false;
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
