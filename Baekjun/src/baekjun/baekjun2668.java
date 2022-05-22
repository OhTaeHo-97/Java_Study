package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class baekjun2668 {
	static Integer[] nums;
	boolean[] visited;
	static ArrayList<Integer> visit_num;
	public void findEqualSet(int start, int target) {
		if(!visited[nums[start]]) {
			visited[nums[start]] = true;
			findEqualSet(nums[start], target);
			visited[nums[start]] = false;
		}
		if(nums[start] == target) {
			visit_num.add(target);
		}
	}
	
	public void getEqualSet() {
		visit_num = new ArrayList<Integer>();
		visited = new boolean[nums.length];
		for(int i = 1; i < nums.length; i++) {
			visited[i] = true;
			findEqualSet(i, i);
			visited[i] = false;
		}
		Collections.sort(visit_num);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		nums = new Integer[num + 1];
		for(int i = 1; i <= num; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun2668 b = new baekjun2668();
		b.getEqualSet();
		bw.write(visit_num.size() + "\n");
		for(int i = 0; i < visit_num.size(); i++) {
			bw.write(visit_num.get(i) + "\n");
		}
		bw.flush();
		bw.close();
	}
}
