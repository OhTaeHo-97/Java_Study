package programmers;

import java.util.*;

public class Level3_SharedTaxiFare {
	static final int MAX = 20000001;
	static HashMap<Integer, ArrayList<Edge>> map;
	
	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = Integer.MAX_VALUE;
		map = new HashMap<Integer, ArrayList<Edge>>();
		for(int vertex = 1; vertex <= n; vertex++) map.put(vertex, new ArrayList<Edge>());
		for(int[] fare : fares) {
			map.get(fare[0]).add(new Edge(fare[1], fare[2]));
			map.get(fare[1]).add(new Edge(fare[0], fare[2]));
		}
		
		int[] startA = new int[n + 1];
		int[] startB = new int[n + 1];
		int[] start = new int[n + 1];
		
		Arrays.fill(startA, MAX);
		Arrays.fill(startB, MAX);
		Arrays.fill(start, MAX);
		
		dijkstra(a, startA);
		dijkstra(b, startB);
		dijkstra(s, start);
		
		for(int vertex = 1; vertex <= n; vertex++)
			answer = Math.min(answer, startA[vertex] + startB[vertex] + start[vertex]);
		return answer;
	}
	
	public void dijkstra(int start, int[] weights) {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		queue.offer(new Edge(start, 0));
		weights[start] = 0;
		
		while(!queue.isEmpty()) {
			Edge cur_edge = queue.poll();
			int cur_vertex = cur_edge.vertex;
			int cur_weight = cur_edge.weight;
			if(cur_weight > weights[cur_vertex]) continue;
			ArrayList<Edge> edges = map.get(cur_vertex);
			for(Edge edge : edges) {
				int weight = weights[cur_vertex] + edge.weight;
				if(weight < weights[edge.vertex]) {
					weights[edge.vertex] = weight;
					queue.offer(new Edge(edge.vertex, weights[edge.vertex]));
				}
			}
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int vertex, weight;
		public Edge(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		
		public int compareTo(Edge e) {
			return this.weight - e.weight;
		}
	}
    
    public static void main(String[] args) {
    	Level3_SharedTaxiFare l = new Level3_SharedTaxiFare();
    	int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
    	System.out.println(l.solution(6, 4, 5, 2, fares));
	}
}
