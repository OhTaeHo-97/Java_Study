package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class baekjun13023 {
	boolean isRelation = false;
	public void findRelation(int start, ArrayList<ArrayList<Integer>> relations, boolean[] visited, int count) {
		if(count == 5) {
			isRelation = true;
			return;
		}
		visited[start] = true;
		for(int i : relations.get(start)) {
			if(!visited[i]) {
				findRelation(i, relations, visited, count + 1);
			}
			if(isRelation) {
				return;
			}
		}
		visited[start] = false; // 일직선이 아닐 경우, 방문한 지점은 모두 false 처리
	}
	
	public int getRelationNum(int people_num, ArrayList<ArrayList<Integer>> relations) {
		boolean[] visited = new boolean[people_num];
		for(int i = 0; i < people_num; i++) {
			Arrays.fill(visited, false);
			findRelation(i, relations, visited, 1);
			if(isRelation) {
				return 1;
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
		ArrayList<ArrayList<Integer>> relations = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < people_num; i++) {
			relations.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < relation_num; i++) {
			input = br.readLine().split(" ");
			relations.get(Integer.parseInt(input[0])).add(Integer.parseInt(input[1]));
			relations.get(Integer.parseInt(input[1])).add(Integer.parseInt(input[0]));
		}
		br.close();
		baekjun13023 b = new baekjun13023();
		bw.write(b.getRelationNum(people_num, relations) + "\n");
		bw.flush();
		bw.close();
	}
}
