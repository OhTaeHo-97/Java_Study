package programmers;

import java.util.*;

public class Level3_MostFarNode {
	static HashMap<Integer, ArrayList<Integer>> map;
	public static int solution(int n, int[][] edge) {
		makeMap(n, edge);
		int[] distance = new int[n + 1];
		dijkstra(distance);
		int max = Integer.MIN_VALUE, answer = 0;
		for(int index = 1; index <= n; index++) {
			if(distance[index] > max) {
				max = distance[index];
				answer = 1;
			} else if(distance[index] == max) answer++;
		}
		return answer;
	}
	
	static void makeMap(int n, int[][] edge) {
		map = new HashMap<>();
		for(int node = 1; node <= n; node++) map.put(node, new ArrayList<>());
		for(int[] e : edge) {
			map.get(e[0]).add(e[1]);
			map.get(e[1]).add(e[0]);
		}
	}
	
	static void dijkstra(int[] distance) {
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[1] = 0;
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		queue.offer(new Edge(1, 0));
		while(!queue.isEmpty()) {
			Edge cur = queue.poll();
			if(distance[cur.node] < cur.weight) continue;
			for(int node : map.get(cur.node)) {
				if(distance[node] > distance[cur.node] + 1) {
					distance[node] = distance[cur.node] + 1;
					queue.offer(new Edge(node, distance[node]));
				}
			}
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int node, weight;
		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
		public int compareTo(Edge e) {
			return weight - e.weight;
		}
	}
	
	public static void main(String[] args) {
		int n = 6;
		int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		System.out.println(solution(n, edge));
	}
}
