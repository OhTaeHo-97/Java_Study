package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun2660 {
	static int n, score;
	static boolean[] visited;
	static HashMap<Integer, ArrayList<Integer>> friends;
	
	static void input() {
		Reader scanner = new Reader();
		friends = new HashMap<>();
		n = scanner.nextInt();
		int n1, n2;
		n1 = scanner.nextInt();
		n2 = scanner.nextInt();
		while(n1 != -1 && n2 != -1) {
			if(friends.containsKey(n1)) {
				friends.get(n1).add(n2);
			} else {
				friends.put(n1, new ArrayList<Integer>(Arrays.asList(n2)));
			}
			if(friends.containsKey(n2)) {
				friends.get(n2).add(n1);
			} else {
				friends.put(n2, new ArrayList<Integer>(Arrays.asList(n1)));
			}
			n1 = scanner.nextInt();
			n2 = scanner.nextInt();
		}
	}
	
	static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[start] = true;
		for(int friend : friends.get(start)) {
			queue.offer(friend);
		}
		int size = queue.size();
		while(!queue.isEmpty()) {
			int cur_position = queue.poll();
			if(!visited[cur_position]) {
				visited[cur_position] = true;
				for(int friend : friends.get(cur_position)) {
					if(!visited[friend])
						queue.offer(friend);
				}
				score++;
			}
		}
	}
	
	static void solution() {
		for(int i = 1; i <= n; i++) {
			visited = new boolean[n + 1];
			score = 0;
			bfs(i);
			System.out.println(i + ": " + score);
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
	
	static class Edge {
		int node, weight;
		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}
}
