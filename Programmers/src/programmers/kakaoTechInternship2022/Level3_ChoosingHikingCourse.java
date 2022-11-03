package programmers.kakaoTechInternship2022;

import java.util.*;

public class Level3_ChoosingHikingCourse {
	static int size;
	static HashSet<Integer> gate, summit;
	static ArrayList<Edge>[] map;
	public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		init(gates, summits);
		makeMap(n, paths);
		int[] intensities = dijkstra(gates);
		Arrays.sort(summits);
		int[] answer = new int[] {-1, Integer.MAX_VALUE};
		for(int s : summits) {
			if(intensities[s] < answer[1]) {
				answer[1] = intensities[s];
				answer[0] = s;
			}
		}
		return answer;
    }
	
	static void init(int[] gates, int[] summits) {
		gate = new HashSet<>();
		summit = new HashSet<>();
		for(int len = 0; len < gates.length; len++) gate.add(gates[len]);
		for(int len = 0; len < summits.length; len++) summit.add(summits[len]);
	}
	
	static void makeMap(int n, int[][] paths) {
		size = n;
		map = new ArrayList[n + 1];
		for(int point = 1; point <= n; point++) map[point] = new ArrayList<>();
		for(int path = 0; path < paths.length; path++) {
			int start = paths[path][0], end = paths[path][1], distance = paths[path][2];
			if(gate.contains(start) || summit.contains(end))
				map[start].add(new Edge(end, distance));
			else if(gate.contains(end) || summit.contains(start))
				map[end].add(new Edge(start, distance));
			else {
				map[start].add(new Edge(end, distance));
				map[end].add(new Edge(start, distance));
			}
		}
	}
	
	static int[] dijkstra(int[] gates) {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		int[] intensities = new int[size + 1];
		Arrays.fill(intensities, Integer.MAX_VALUE);
		for(int g : gates) {
			queue.offer(new Edge(g, 0));
			intensities[g] = 0;
		}
		while(!queue.isEmpty()) {
			Edge cur = queue.poll();
			if(intensities[cur.point] < cur.intensity) continue;
			for(Edge e : map[cur.point]) {
				if(intensities[e.point] > Math.max(intensities[cur.point], e.intensity)) {
					intensities[e.point] = Math.max(intensities[cur.point], e.intensity);
					queue.offer(e);
				}
			}
		}
		return intensities;
	}
	
	static class Edge implements Comparable<Edge> {
		int point, intensity;
		public Edge(int point, int intensity) {
			this.point = point;
			this.intensity = intensity;
		}
		public int compareTo(Edge e) {
			return intensity - e.intensity;
		}
	}
	
	public static void main(String[] args) {
//		int n = 6;
//		int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
//		int[] gates = {1, 3}, summits = {5};
//		int n = 7;
//		int[][] paths = {{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}};
//		int[] gates = {1}, summits = {2, 3, 4};
//		int n = 7;
//		int[][] paths = {{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}};
//		int[] gates = {3, 7}, summits = {1, 5};
		int n = 5;
		int[][] paths = {{1, 3, 10}, {1, 4, 20}, {2, 3, 4}, {2, 4, 6}, {3, 5, 20}, {4, 5, 6}};
		int[] gates = {1, 2}, summits = {5};
		System.out.println(solution(n, paths, gates, summits));
	}
}
