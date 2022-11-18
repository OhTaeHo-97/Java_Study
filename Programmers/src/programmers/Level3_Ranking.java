package programmers;

import java.util.*;

public class Level3_Ranking {
	// 1 <= 선수의 수 n <= 100
	// 1 <= 경기 결과 results의 길이 <= 4500
	// results의 원소 [A, B] -> A 선수가 B 선수를 이겼다는 의미
	// 정확하게 순위를 매길 수 있는 선수의 수를 return
	
	static HashMap<Integer, ArrayList<Integer>> map;
	public static int solution(int n, int[][] results) {
		makeMap(n, results);
		int[][] distance = new int[n + 1][n + 1];
		for(int player = 1; player <= n; player++) dijkstra(player, distance[player]);
		int answer = 0;
		for(int player = 1; player <= n; player++) {
			boolean flag = true;
			for(int other = 1; other <= n; other++) {
				if(other == player) continue;
				if(distance[player][other] == Integer.MAX_VALUE && distance[other][player] == Integer.MAX_VALUE) {
					flag = false;
					break;
				}
			}
			if(flag) answer++;
		}
		return answer;
	}
	
	static void makeMap(int n, int[][] results) {
		map = new HashMap<>();
		for(int player = 1; player <= n; player++) map.put(player, new ArrayList<>());
		for(int[] result : results) {
			int win = result[0], lose = result[1];
			map.get(lose).add(win);
		}
	}
	
	static void dijkstra(int player, int[] distance) {
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[player] = 0;
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		queue.offer(new Edge(player, 0));
		while(!queue.isEmpty()) {
			Edge cur = queue.poll();
			if(distance[cur.player] < cur.weight) continue;
			for(int next : map.get(cur.player)) {
				if(distance[next] > distance[cur.player] + 1) {
					distance[next] = distance[cur.player] + 1;
					queue.offer(new Edge(next, distance[next]));
				}
			}
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int player, weight;
		public Edge(int player, int weight) {
			this.player = player;
			this.weight = weight;
		}
		public int compareTo(Edge e) {
			return weight - e.weight;
		}
	}
	
	public static void main(String[] args) {
		int n = 5;
		int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
		System.out.println(solution(n, results));
	}
}
