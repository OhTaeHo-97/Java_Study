package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class baekjun16928 {
	static int[] equipments;
	static int[] count;
	boolean[] visited;
	public void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1);
		visited[1] = true;
		while(!queue.isEmpty()) {
			int cur_position = queue.poll();
			if(cur_position == 100) {
				break;
			}
			for(int i = 1; i < 7; i++) {
				int position = cur_position + i;
				if(position > 0 && position <= 100 && !visited[position]) {
					visited[position] = true;
					if(equipments[position] != 0) {
						if(!visited[equipments[position]]) {
							queue.offer(equipments[position]);
							visited[equipments[position]] = true;
							count[equipments[position]] = count[cur_position] + 1;
						}
					} else {
						queue.offer(position);
						count[position] = count[cur_position] + 1;
					}
				}
			}
		}
	}
	
	public void getMinCount() {
		visited = new boolean[101];
		count = new int[101];
		bfs();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int ladder_num = Integer.parseInt(input[0]);
		int snake_num = Integer.parseInt(input[1]);
		equipments = new int[101];
		for(int i = 0; i < ladder_num + snake_num; i++) {
			input = br.readLine().split(" ");
			equipments[Integer.parseInt(input[0])] = Integer.parseInt(input[1]);
		}
		br.close();
		baekjun16928 b = new baekjun16928();
		b.getMinCount();
		bw.write(count[100] + "\n");
		bw.flush();
		bw.close();
	}
}