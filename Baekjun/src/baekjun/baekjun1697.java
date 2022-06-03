package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class baekjun1697 {
	static int[] list;
	
	public static int bfs(int su, int younger) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(su);
		int index = su;
		int l = 0;
		list[index] = 1;
		while(!queue.isEmpty()) {
			l = queue.poll();
			if(l == younger) {
				return list[l] - 1;
			}
			if(l - 1 >= 0 && list[l - 1] == 0) {
				list[l - 1] = list[l] + 1;
				queue.offer(l - 1);
			}
			if(l + 1 <= 100000 && list[l + 1] == 0) {
				list[l + 1] = list[l] + 1;
				queue.offer(l + 1);
			}
			if(2 * l <= 100000 && list[2 * l] == 0) {
				list[2 * l] = list[l] + 1;
				queue.offer(2 * l);
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		br.close();
		int su = Integer.parseInt(input[0]);
		int younger = Integer.parseInt(input[1]);
		list = new int[100001];
		bw.write(bfs(su, younger) + "\n");
		bw.flush();
		bw.close();
	}
}
