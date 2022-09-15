package programmers;

import java.util.*;

public class Level3_SharedTaxiFare {
	static int fee = 0, a_idx = 0, b_idx = 0;
	static HashMap<Integer, ArrayList<Edge>> map;
    static int[] parents, depth;
    static boolean[] visited;
    static ArrayList<int[]> edges;
    static ArrayList<Edge> dfsOrder;
    public void makeMap(int n) {
        map = new HashMap<Integer, ArrayList<Edge>>();
        for(int point = 1; point <= n; point++)
            map.put(point, new ArrayList<Edge>());
        for(int[] edge : edges) {
            map.get(edge[0]).add(new Edge(edge[1], edge[2]));
            map.get(edge[1]).add(new Edge(edge[0], edge[2]));
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        Arrays.sort(fares, new Comparator<int[]>() {
            public int compare(int[] fare1, int[] fare2) {
                if(fare1[2] != fare2[2]) return fare1[2] - fare2[2];
                else {
                    int min1 = Math.min(fare1[0], fare1[1]);
                    int min2 = Math.min(fare2[0], fare2[1]);
                    return min1 - min2;
                }
            }
        });
        Kruskal(n, fares);
        makeMap(n);
        depth = new int[n + 1];
        Arrays.fill(depth, -1);
        depth[s] = 0;
        dfsOrder = new ArrayList<Edge>();
        visited = new boolean[n + 1];
        dfs(new Edge(s, 0), a, b);
        for(int i = 1; i <= n; i++) System.out.print(depth[i] + " ");
        System.out.println();
        int minDepth = Math.min(depth[a], depth[b]), maxDepth = Math.max(depth[a], depth[b]);
        System.out.println("maxDepth: " + maxDepth);
        int minIdx = Math.min(a_idx, b_idx), maxIdx = Math.max(a_idx, b_idx);
        int idx = -1;
        for(int index = maxIdx - 1; index > minIdx; index--) {
        	if(depth[dfsOrder.get(index + 1).vertex] <= depth[dfsOrder.get(index).vertex]) {
        		idx = index;
        		break;
        	}
        }
        if(idx != -1) {
        	// idx - 1;
        	for(int index = idx - 1; depth[index] > depth[idx - 1]; index++) {
        		
        	}
        }
        System.out.println(fee);
        return answer;
    }
    
    public void dfs(Edge edge, int a, int b) {
        dfsOrder.add(edge);
        visited[edge.vertex] = true;
        fee += edge.weight;
        for(Edge e : map.get(edge.vertex)) {
            if(!visited[e.vertex]) {
            	if(e.vertex == a) a_idx = dfsOrder.size();
            	else if(e.vertex == b) b_idx = dfsOrder.size();
                depth[e.vertex] = depth[edge.vertex] + 1;
                dfs(e, a, b);
            }
        }
    }
    
    public void Kruskal(int n, int[][] fares) {
        parents = new int[n + 1];
        for(int point = 1; point <= n; point++) parents[point] = point;
        edges = new ArrayList<int[]>();
        for(int[] fare : fares) {
            if(!isSameParents(fare[0], fare[1])) {
                union(fare[0], fare[1]);
                edges.add(fare);
            }
        }
    }
    
    public int findParent(int vertex) {
        if(vertex == parents[vertex]) return vertex;
        return parents[vertex] = findParent(parents[vertex]);
    }
    
    public void union(int v1, int v2) {
        int parent1 = findParent(v1), parent2 = findParent(v2);
        if(parent1 != parent2) {
            if(parent1 < parent2) parents[parent2] = parent1;
            else parents[parent1] = parent2;
        }
    }
    
    public boolean isSameParents(int v1, int v2) {
        if(findParent(v1) == findParent(v2)) return true;
        return false;
    }
    
    static class Edge {
        int vertex, weight;
        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    
    public static void main(String[] args) {
    	Level3_SharedTaxiFare l = new Level3_SharedTaxiFare();
    	int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
    	l.solution(6, 4, 5, 2, fares);
	}
}
