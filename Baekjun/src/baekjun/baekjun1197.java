package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class baekjun1197 {
//	static HashMap<Integer, Integer> parent;
//	static HashMap<Integer, Integer> rank;
//	static ArrayList<Edge> edges;
//	static ArrayList<Integer> vertices;
//	static int V, E, totalWeight;
//	static void input() {
//		Reader scanner  = new Reader();
//		V = scanner.nextInt();
//		E = scanner.nextInt();
//		edges = new ArrayList<>();
//		vertices = new ArrayList<>();
//		for(int i = 0; i < E; i++) {
//			edges.add(new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
//		}
//		for(int i = 1; i <= V; i++) {
//			vertices.add(i);
//		}
//		parent = new HashMap<>();
//		rank = new HashMap<>();
//		totalWeight = 0;
//	}
//	
//	static void kruskal() {
//		ArrayList<Edge> mst = new ArrayList<>();
//		for(int i = 0; i < V; i++) {
//			makeSet(vertices.get(i));
//		}
//		Collections.sort(edges);
//		for(int i = 0; i < E; i++) {
//			Edge curNode = edges.get(i);
//			if(find(curNode.s_vertex) != find(curNode.e_vertex)) {
//				union(curNode.s_vertex, curNode.e_vertex);
//				mst.add(curNode);
//			}
//		}
//		for(int i = 0; i < mst.size(); i++) {
//			totalWeight += mst.get(i).weight;
//		}
//		System.out.println(totalWeight);
//	}
//	
//	static void makeSet(int node) {
//		parent.put(node, node);
//		rank.put(node, 0);
//	}
//	
//	static int find(int node) {
//		if(parent.get(node) != node) {
//			parent.put(node, find(parent.get(node)));
//		}
//		return parent.get(node);
//	}
//	
//	static void union(int s_vertex, int e_vertex) {
//		int s_parent = find(s_vertex);
//		int e_parent = find(e_vertex);
//		if(rank.get(s_parent) > rank.get(e_parent)) {
//			parent.put(e_parent, s_parent);
//		} else {
//			parent.put(s_parent, e_parent);
//			if(rank.get(s_parent) == rank.get(e_parent)) {
//				rank.put(e_parent, rank.get(e_parent) + 1);
//			}
//		}
//	}
	
	static int V, E, totalWeight;
	static int[] parents, rank;
	static ArrayList<Edge> edges;
	static void input() {
		Reader scanner = new Reader();
		edges = new ArrayList<>();
		V = scanner.nextInt();
		E = scanner.nextInt();
		totalWeight = 0;
		parents = new int[V + 1];
		rank = new int[V + 1];
		for(int i = 1; i <= V; i++) {
			parents[i] = i;
		}
		for(int i = 0; i < E; i++) edges.add(new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
	}
	
	static void kruskal() {
		ArrayList<Edge> mst = new ArrayList<>();
		Collections.sort(edges);
		for(Edge edge : edges) {
			if(mst.size() == V - 1)
				break;
			if(!isSameParents(edge.s_node, edge.e_node)) {
				union(edge.s_node, edge.e_node);
				mst.add(edge);
			}
		}
		for(Edge e : mst) {
			totalWeight += e.weight;
		}
		System.out.println(totalWeight);
	}
	
	static int findParents(int node) {
		if(parents[node] == node) return node;
		return parents[node] = findParents(parents[node]);
	}
	
	static void union(int s_node, int e_node) {
		int s_parent = findParents(s_node);
		int e_parent = findParents(e_node);
		if(s_parent != e_parent) {
			if(s_parent < e_parent) parents[e_parent] = s_parent;
			else parents[s_parent] = e_parent;
		}
	}
	
	static boolean isSameParents(int s_node, int e_node) {
		int s_parent = findParents(s_node);
		int e_parent = findParents(e_node);
		if(s_parent == e_parent) return true;
		return false;
	}
	
	public static void main(String[] args) {
		input();
		kruskal();
	}
	
	static class Reader {
		BufferedReader br;
		StringTokenizer st;
		public Reader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		String next() {
			while(st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int s_node, e_node, weight;
		public Edge(int s_node, int e_node, int weight) {
			this.s_node = s_node;
			this.e_node = e_node;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge e) {
			// TODO Auto-generated method stub
			return this.weight - e.weight;
		}
	}
}
