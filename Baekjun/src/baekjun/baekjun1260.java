package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class baekjun1260 {
	public ArrayList<Integer> DFS(int start, HashMap<Integer, ArrayList<Integer>> graph) {
		ArrayList<Integer> visited = new ArrayList<Integer>();
		ArrayList<Integer> needVisit = new ArrayList<Integer>();
		needVisit.add(start);
		while(needVisit.size() > 0) {
			int node = needVisit.remove(0);
			if(!visited.contains(node)) {
				visited.add(node);
				needVisit.addAll(0, graph.get(node));
			}
		}
		
		return visited;
	}
	
	public ArrayList<Integer> BFS(int start, HashMap<Integer, ArrayList<Integer>> graph) {
		ArrayList<Integer> visited = new ArrayList<Integer>();
		ArrayList<Integer> needVisit = new ArrayList<Integer>();
		needVisit.add(start);
		int count = 0;
		while(needVisit.size() > 0) {
			count++;
			int node = needVisit.remove(0);
			
			if(!visited.contains(node)) {
				visited.add(node);
				needVisit.addAll(graph.get(node));
			}
		}
		
		return visited;
	}
	
	public HashMap<Integer, ArrayList<Integer>> makeGraph(int vertex, ArrayList<String> edges) {
		HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> each_edge = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < vertex; i++) {
			each_edge.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < edges.size(); i++) {
			StringTokenizer st = new StringTokenizer(edges.get(i));
			int vertex1 = Integer.parseInt(st.nextToken());
			int vertex2 = Integer.parseInt(st.nextToken());
			each_edge.get(vertex1 - 1).add(vertex2);
			each_edge.get(vertex2 - 1).add(vertex1);
		}
		for(int i = 0; i < each_edge.size(); i++) {
			Collections.sort(each_edge.get(i));
			graph.put(i + 1, each_edge.get(i));
		}
		return graph;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		int vertex = Integer.parseInt(st.nextToken());
		int edge = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		ArrayList<String> edges = new ArrayList<String>();
		for(int i = 0; i < edge; i++) {
			edges.add(br.readLine());
		}
		br.close();
		baekjun1260 b = new baekjun1260();
		HashMap<Integer, ArrayList<Integer>> graph = b.makeGraph(vertex, edges);
		ArrayList<Integer> dfs = b.DFS(start, graph);
		for(int i = 0; i < dfs.size(); i++) {
			bw.write(dfs.get(i) + " ");
		}
		bw.write("\n");
		ArrayList<Integer> bfs = b.BFS(start, graph);
		for(int i = 0; i < bfs.size(); i++) {
			bw.write(bfs.get(i) + " ");
		}
		bw.flush();
		bw.close();
	}
}
