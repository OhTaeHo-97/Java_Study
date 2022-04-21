package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1325 {
	int[] count;
	
	public void dfs(int start, boolean[] visited, ArrayList<Integer>[] rels) {
		visited[start] = true;
		for(int n : rels[start]) {
			if(!visited[n]) {
				count[n]++;
				dfs(n, visited, rels);
			}
		}
	}
	
	public ArrayList<Integer> getComputerNum(int com_num, String[] relations) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Integer>[] rels = new ArrayList[com_num + 1];
		for(int i = 1 ; i < rels.length; i++) {
			rels[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < relations.length; i++) {
			StringTokenizer st = new StringTokenizer(relations[i]);
			int trust = Integer.parseInt(st.nextToken());
			int trusted = Integer.parseInt(st.nextToken());
			rels[trust].add(trusted);
		}
		count = new int[com_num + 1];
		int max = Integer.MIN_VALUE;
		for(int i = 1; i < rels.length; i++) {
			boolean[] visited = new boolean[com_num + 1];
			dfs(i, visited, rels);
		}
		for(int i = 1; i < rels.length; i++) {
			max = Math.max(max, count[i]);
		}
		for(int i = 1; i < rels.length; i++) {
			if(count[i] == max) {
				result.add(i);
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		int com_num = Integer.parseInt(st.nextToken());
		int rel_num = Integer.parseInt(st.nextToken());
		String[] relations = new String[rel_num];
		for(int i = 0; i < rel_num; i++) {
			relations[i] = br.readLine();
		}
		br.close();
		baekjun1325 b = new baekjun1325();
		ArrayList<Integer> maxVisited = b.getComputerNum(com_num, relations);
		for(int i = 0; i < maxVisited.size(); i++) {
			bw.write(maxVisited.get(i) + " ");
		}
		bw.flush();
		bw.close();
	}
}
