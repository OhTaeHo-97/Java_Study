package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class baekjun13023 {
	static int[][] relations;
	boolean[][] visited;
	Queue<Integer> candidate;
	HashSet<Integer> relation;
	boolean isRelation;
	public void findRelation(int row, int col) {
		visited[row][col] = true;
		candidate.add(relations[row][col]);
		relation.add(row);
		if(relation.size() == 5) {
			isRelation = true;
		}
		for(int i = 0; i < relations.length; i++) {
			if(!visited[relations[row][col]][i] && relations[relations[row][col]][i] == 1) {
				findRelation(relations[row][col], i);
			}
		}
	}
	
	public int getRelationNum() {
		for(int l = 0; l < relations.length; l++) {
			Queue<Integer> q = new LinkedList<Integer>();
			for(int i = 0; i < relations[l].length; i++) {
				if(relations[l][i] == 1) {
					q.add(i);
				}
			}
			for(int i = 0; i < q.size(); i++) {
				candidate = new LinkedList<Integer>();
				relation = new HashSet<Integer>();
				visited = new boolean[relations.length][relations[0].length];
				isRelation = false;
				findRelation(l, i);
				if(isRelation) {
					return 1;
				}
				System.out.println(relation);
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int people_num = Integer.parseInt(input[0]);
		int relation_num = Integer.parseInt(input[1]);
		relations = new int[people_num][people_num];
		for(int i = 0; i < relation_num; i++) {
			input = br.readLine().split(" ");
			relations[Integer.parseInt(input[0])][Integer.parseInt(input[1])] = 1;
		}
		br.close();
		baekjun13023 b = new baekjun13023();
		bw.write(b.getRelationNum() + "\n");
		bw.flush();
		bw.close();
	}
}
