package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class baekjun1068 {
	static ArrayList<Integer>[] parents;
	static int num;
	public void remove(int node) {
		if(parents[node].size() > 0) {
			int s = parents[node].size();
			while(s > 0) {
				int child = parents[node].get(--s);
				remove(child);
			}
		}
		for(int i = 0; i < num; i++) {
			if(parents[i].contains(node)) {
				for(int j = 0; j < parents[i].size(); j++) {
					if(parents[i].get(j) == node) {
						parents[i].remove(j);
					}
				}
			}
		}
	}
	
	public int findNumOfLeaf(int node) {
		Queue<Integer> q = new LinkedList<>();
		q.add(node);
		int count = 0;
		while(!q.isEmpty()) {
			int pos = q.poll();
			if(parents[pos].size() == 0) {
				count++;
				continue;
			}
			for(int next:parents[pos]) {
				q.add(next);
			}
		}
		return count;
	}
	
	public int getLeafNodeNum(int root, int del) {
		remove(del);
		if(del == root) {
			return 0;
		} else {
			return findNumOfLeaf(root);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		num = Integer.parseInt(br.readLine());
		parents = new ArrayList[num];
		for(int i = 0; i < num; i++) {
			parents[i] = new ArrayList<Integer>();
		}
		int root = -1;
		String[] input = br.readLine().split(" ");
		for(int i = 0; i < parents.length; i++) {
			if(Integer.parseInt(input[i]) == -1) {
				root = i;
				continue;
			}
			parents[Integer.parseInt(input[i])].add(i);
		}
		int del = Integer.parseInt(br.readLine());
		br.close();
		baekjun1068 b = new baekjun1068();
		bw.write(b.getLeafNodeNum(root, del) + "\n");
		bw.flush();
		bw.close();
	}
}
