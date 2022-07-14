package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class baekjun2056 {
	public int getMinTime(HashMap<Integer, ArrayList<Integer>> map, int[] time, int[] related_num) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int[] each_time = new int[time.length];
		for(int i = 1; i <= map.size(); i++) {
			each_time[i] = time[i];
			if(related_num[i] == 0) {
				queue.offer(i);
			}
		}
		while(!queue.isEmpty()) {
			int cur_task = queue.poll();
			for(int i : map.get(cur_task)) {
				related_num[i]--;
				each_time[i] = Math.max(each_time[i], each_time[cur_task] + time[i]);
				if(related_num[i] == 0) {
					queue.offer(i);
				}
			}
		}
		int max = 0;
		for(int i = 0; i < each_time.length; i++) {
			max = Math.max(max, each_time[i]);
		}
		return max;
 	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		int[] time = new int[num + 1];
		int[] related_num = new int[num + 1];
		for(int i = 1; i <= num; i++) {
			map.put(i, new ArrayList<Integer>());
		}
		for(int i = 1; i <= num; i++) {
			String[] input = br.readLine().split(" ");
			time[i] = Integer.parseInt(input[0]);
			int n = Integer.parseInt(input[1]);
			for(int j = 0; j < n; j++) {
				map.get(Integer.parseInt(input[j + 2])).add(i);
				related_num[i]++;
			}
		}
		br.close();
		baekjun2056 b = new baekjun2056();
		bw.write(b.getMinTime(map, time, related_num) + "\n");
		bw.flush();
		bw.close();
	}
}
