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

public class baekjun1753 {
	static HashMap<Integer, ArrayList<Edge>> map;
	public int[] getMinWeight(int start) {
		int[] weights = new int[map.size() + 1];
		Arrays.fill(weights, Integer.MAX_VALUE);
		weights[start] = 0;
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
		queue.offer(new Edge(start, weights[start]));
		while(!queue.isEmpty()) {
			Edge cur_edge = queue.poll();
			int cur_vertex = cur_edge.vertex;
			int cur_weight = cur_edge.weight;
			if(weights[cur_vertex] < cur_weight) {
				continue;
			}
			for(Edge e : map.get(cur_vertex)) {
				if(weights[e.vertex] > weights[cur_vertex] + e.weight) {
					weights[e.vertex] = weights[cur_vertex] + e.weight;
					queue.offer(new Edge(e.vertex, weights[e.vertex]));
				}
			}
		}
		return weights;
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
		int start = Integer.parseInt(br.readLine());
		for(int i = 0; i < edge_num; i++) {
			input = br.readLine().split(" ");
			map.get(Integer.parseInt(input[0])).add(new Edge(Integer.parseInt(input[1]), Integer.parseInt(input[2])));
		}
		br.close();
		baekjun1753 b = new baekjun1753();
		int[] weights = b.getMinWeight(start);
		for(int i = 1; i <= vertex_num; i++) {
			if(weights[i] == Integer.MAX_VALUE) {
				bw.write("INF\n");
			} else {				
				bw.write(weights[i] + "\n");
			}
		}
		bw.flush();
		bw.close();
	}
	
	public static class Edge implements Comparable<Edge> {
		int vertex;
		int weight;
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
