package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class baekjun1240 {
	static ArrayList<Edge>[] map;
	static int result;
	
	public void getPairDistance(int start, int end, int prev_node, int distance) {
		if(start == end) {
			result = distance;
		}
		for(Edge e : map[end]) {
			if(e.node != prev_node) {
				getPairDistance(start, e.node, end, distance + e.distance);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int node_num = Integer.parseInt(input[0]);
		int pair_num = Integer.parseInt(input[1]);
		map = new ArrayList[node_num + 1];
		for(int i = 1; i <= node_num; i++) {
			map[i] = new ArrayList<Edge>();
		}
		for(int i = 0; i < node_num - 1; i++) {
			input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			int distance = Integer.parseInt(input[2]);
			map[start].add(new Edge(end, distance));
			map[end].add(new Edge(start, distance));
		}
		baekjun1240 b = new baekjun1240();
		for(int i = 0; i < pair_num; i++) {
			input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			b.getPairDistance(start, end, -1, 0);
			bw.write(result + "\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static class Edge {
		int node;
		int distance;
		public Edge(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}
	}
}
