package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class baekjun1916 {
//	HashMap<Integer, ArrayList<Edge>> map;
//	public HashMap<Integer, Integer> dijkstra(int start) {
//		HashMap<Integer, Integer> distances = new HashMap<Integer, Integer>();
//		PriorityQueue<Edge> pQueue = new PriorityQueue<Edge>();
//		for(int key : map.keySet()) {
//			distances.put(key, Integer.MAX_VALUE);
//		}
//		distances.put(start, 0);
//		pQueue.add(new Edge(start, distances.get(start)));
//		while(!pQueue.isEmpty()) {
//			Edge cur_node = pQueue.poll();
//			int cur_vertex = cur_node.vertex;
//			int cur_distance = cur_node.distance;
//			if(distances.get(cur_vertex) < cur_distance) {
//				continue;
//			}
//			ArrayList<Edge> nodeList = map.get(cur_vertex);
//			for(int i = 0; i < nodeList.size(); i++) {
//				Edge adjacentNode = nodeList.get(i);
//				int adjacent = adjacentNode.vertex;
//				int weight = adjacentNode.distance;
//				int distance = weight + cur_distance;
//				
//				if(distance < distances.get(adjacent)) {
//					distances.put(adjacent, distance);
//					pQueue.add(new Edge(adjacent, distance));
//				}
//			}
//		}
//		return distances;
//	}
//	
//	public void makeMap(int city_num, int[] starts, int[] ends, int[] weights) {
//		map = new HashMap<>();
//		for(int i = 1; i <= city_num; i++) {
//			map.put(i, new ArrayList<Edge>());
//		}
//		for(int i = 0; i < starts.length; i++) {
//			ArrayList<Edge> list = map.get(starts[i]);
//			list.add(new Edge(ends[i], weights[i]));
//			map.put(starts[i], list);
//		}
//	}
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		int city_num = Integer.parseInt(br.readLine());
//		int bus_num = Integer.parseInt(br.readLine());
//		int[] starts = new int[bus_num];
//		int[] ends = new int[bus_num];
//		int[] weights = new int[bus_num];
//		String[] input;
//		for(int i = 0; i < bus_num; i++) {
//			input = br.readLine().split(" ");
//			starts[i] = Integer.parseInt(input[0]);
//			ends[i] = Integer.parseInt(input[1]);
//			weights[i] = Integer.parseInt(input[2]);
//		}
//		input = br.readLine().split(" ");
//		int start = Integer.parseInt(input[0]);
//		int end = Integer.parseInt(input[1]);
//		br.close();
//		baekjun1916 b = new baekjun1916();
//		b.makeMap(city_num, starts, ends, weights);
//		HashMap<Integer, Integer> distances = b.dijkstra(start);
//		bw.write(distances.get(end) + "\n");
//		bw.flush();
//		bw.close();
//	}
	
	static ArrayList<ArrayList<Edge>> list; // 인접리스트
	static int[] dist; // 시작점에서 각 정점으로 가는 최단거리
	static boolean[] check; // 방문 확인
	static int city_num, bus_num;
	public int dijkstra(int start, int end) {
		PriorityQueue<Edge> pQueue = new PriorityQueue<Edge>();
		boolean[] check = new boolean[city_num + 1];
		pQueue.offer(new Edge(start, 0));
		dist[start] = 0;
		while(!pQueue.isEmpty()) {
			Edge cur_edge = pQueue.poll();
			int cur_vertex = cur_edge.vertex;
			if(!check[cur_vertex]) {
				check[cur_vertex] = true;
				for(Edge e : list.get(cur_vertex)) {
					if(!check[e.vertex] && dist[e.vertex] > dist[cur_vertex] + e.distance) {
						dist[e.vertex] = dist[cur_vertex] + e.distance;
						pQueue.add(new Edge(e.vertex, dist[e.vertex]));
					}
				}
			}
		}
		return dist[end];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		city_num = Integer.parseInt(br.readLine());
		bus_num = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		dist = new int[city_num + 1];
		check = new boolean[city_num + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		for(int i = 0; i <= city_num; i++) {
			list.add(new ArrayList<>());
		}
		String[] input;
		for(int i = 0; i < bus_num; i++) {
			input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			int weight = Integer.parseInt(input[2]);
			list.get(start).add(new Edge(end, weight));
		}
		input = br.readLine().split(" ");
		int start = Integer.parseInt(input[0]);
		int end = Integer.parseInt(input[1]);
		br.close();
		baekjun1916 b = new baekjun1916();
		bw.write(b.dijkstra(start, end) + "\n");
		bw.flush();
		bw.close();
	}
	public static class Edge implements Comparable<Edge> {
		int vertex;
		int distance;
		public Edge(int vertex, int distance) {
			this.vertex = vertex;
			this.distance = distance;
		}
		@Override
		public int compareTo(Edge e) {
			// TODO Auto-generated method stub
			return this.distance - e.distance;
		}
		
	}
}
