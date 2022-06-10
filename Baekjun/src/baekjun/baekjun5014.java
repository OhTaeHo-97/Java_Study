package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class baekjun5014 {
	static int[] list;
	public String getMinPressNum(int current, int target, int up, int down) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(current);
		list[current] = 1;
		while(!queue.isEmpty()) {
			int cur_point = queue.poll();
			if(cur_point == target) {
				return Integer.toString(list[target] - 1);
			}
			if(cur_point + up < list.length && list[cur_point + up] == 0) {
				queue.offer(cur_point + up);
				list[cur_point + up] = list[cur_point] + 1;
			}
			if(cur_point - down > 0 && list[cur_point - down] == 0) {
				queue.offer(cur_point - down);
				list[cur_point - down] = list[cur_point] + 1;
			}
		}
		return "use the stairs";
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int height = Integer.parseInt(input[0]);
		list = new int[height + 1];
		int current = Integer.parseInt(input[1]);
		int target = Integer.parseInt(input[2]);
		int up = Integer.parseInt(input[3]);
		int down = Integer.parseInt(input[4]);
		br.close();
		baekjun5014 b = new baekjun5014();
		bw.write(b.getMinPressNum(current, target, up, down) + "\n");
		bw.flush();
		bw.close();
	}
}
