package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class baekjun1504 {
	static HashMap<Integer, ArrayList<Edge>> map;
	public HashMap<Integer, Integer> dijkstra(int start) {
		HashMap<Integer, Integer> distances = new HashMap<>();
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		for(int key : map.keySet()) {
			distances.put(key, Integer.MAX_VALUE);
		}
		distances.put(start, 0);
		queue.add(new Edge(start, distances.get(start)));
		while(!queue.isEmpty()) {
			Edge cur_node = queue.poll();
			int cur_vertex = cur_node.vertex;
			int cur_weight = cur_node.weight;
			if(distances.get(cur_vertex) < cur_weight) {
				continue;
			}
			ArrayList<Edge> nodeList = map.get(cur_vertex);
			for(int i = 0; i < nodeList.size(); i++) {
				Edge adjacentNode = nodeList.get(i);
				int adjacent = adjacentNode.vertex;
				int weight = adjacentNode.weight;
				int distance = weight + cur_weight;
				if(distance < distances.get(adjacent)) {
					distances.put(adjacent, distance);
					queue.add(new Edge(adjacent, distance));
				}
			}
		}
		return distances;
	}
	
	public int getMinWeight(int u, int v) {
		HashMap<Integer, Integer> start = dijkstra(1);
		HashMap<Integer, Integer> stopover1 = dijkstra(u);
		HashMap<Integer, Integer> stopover2 = dijkstra(v);
		int st1 = Integer.MAX_VALUE;
		int st2 = Integer.MAX_VALUE;
		if(start.get(u) != Integer.MAX_VALUE) {
			if(stopover1.get(v) != Integer.MAX_VALUE) {
				if(stopover2.get(map.size()) != Integer.MAX_VALUE) {
					st1 = start.get(u) + stopover1.get(v) + stopover2.get(map.size());
				}
			}
		}
		if(start.get(v) != Integer.MAX_VALUE) {
			if(stopover2.get(u) != Integer.MAX_VALUE) {
				if(stopover1.get(map.size()) != Integer.MAX_VALUE) {
					st2 = start.get(v) + stopover2.get(u) + stopover1.get(map.size());
				}
			}
		}
		if(st1 == Integer.MAX_VALUE && st2 == Integer.MAX_VALUE) {
			return -1;
		}
		if(st1 > st2) {
			return st2;
		} else {
			return st1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int vertex_num = Integer.parseInt(input[0]);
		int edge_num = Integer.parseInt(input[1]);
		map = new HashMap<>();
		for(int i = 1; i <= vertex_num; i++) {
			map.put(i, new ArrayList<Edge>());
		}
		for(int i = 0; i < edge_num; i++) {
			input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			int weight = Integer.parseInt(input[2]);
			ArrayList<Edge> edges = map.get(start);
			edges.add(new Edge(end, weight));
			map.put(start, edges);
			edges = map.get(end);
			edges.add(new Edge(start, weight));
			map.put(end, edges);
		}
		input = br.readLine().split(" ");
		int u = Integer.parseInt(input[0]);
		int v = Integer.parseInt(input[1]);
		br.close();
		baekjun1504 b = new baekjun1504();
		bw.write(b.getMinWeight(u, v) + "\n");
		bw.flush();
		bw.close();
	}
	
	public static class Edge implements Comparable<Edge> {
		int vertex, weight;
		public Edge(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge e) {
			// TODO Auto-generated method stub
			return this.weight - e.weight;
		}
	}
}
