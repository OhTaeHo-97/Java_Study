package programmers;

import java.util.*;

public class Level3_ReturnToForce {
	static HashMap<Integer, ArrayList<Edge>> map;
	public static int[] solution(int n, int[][] roads, int[] sources, int destination) {
		makeMap(n, roads);
		int[] time = bfs(n, destination);
		int[] answer = new int[sources.length];
		for(int idx = 0; idx < sources.length; idx++)
			answer[idx] = time[sources[idx]] == Integer.MAX_VALUE ? -1 : time[sources[idx]];
		return answer;
	}
	
	static void makeMap(int n, int[][] roads) {
		map = new HashMap<>();
		for(int unit = 1; unit <= n; unit++) map.put(unit, new ArrayList<Edge>());
		for(int[] road : roads) {
			int unit1 = road[0], unit2 = road[1];
			map.get(unit1).add(new Edge(unit2, 1));
			map.get(unit2).add(new Edge(unit1, 1));
		}
	}
	
	static int[] bfs(int n, int start) {
		int[] time = new int[n + 1];
		Arrays.fill(time, Integer.MAX_VALUE);
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		time[start] = 0;
		queue.offer(new Edge(start, 0));
		while(!queue.isEmpty()) {
			Edge cur = queue.poll();
			for(Edge next : map.get(cur.unit)) {
				if(time[next.unit] > time[cur.unit] + 1) {
					time[next.unit] = time[cur.unit] + 1;
					queue.offer(new Edge(next.unit, time[next.unit]));
				}
			}
		}
		return time;
	}
	
	static class Edge implements Comparable<Edge> {
		int unit, weight;
		public Edge(int unit, int weight) {
			this.unit = unit;
			this.weight = weight;
		}
		public int compareTo(Edge e) {
			return weight - e.weight;
		}
	}

	public static void main(String[] args) {
		int n = 3, destination = 1;
		int[][] roads = {{1, 2}, {2, 3}};
		int[] sources = {2, 3};
		int[] answer = solution(n, roads, sources, destination);
		for(int a : answer) System.out.println(a);
	}
}
