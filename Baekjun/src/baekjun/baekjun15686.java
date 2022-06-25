package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class baekjun15686 {
	static int[][] map;
	static int store_num;
	static ArrayList<Point> human;
	static ArrayList<Point> store;
	static int min;
	boolean[] live;
	public void dfs(int start, int count) {
		if(count == store_num) {
			int total = 0;
			for(int i = 0; i < human.size(); i++) {
				int length = Integer.MAX_VALUE;
				for(int j = 0; j < store.size(); j++) {
					if(live[j]) {
						length = Math.min(length, Math.abs(human.get(i).x - store.get(j).x) + Math.abs(human.get(i).y - store.get(j).y));
					}
				}
				total += length;
			}
			min = Math.min(min, total);
			return;
		}
		for(int i = start; i < store.size(); i++) {
			live[i] = true;
			dfs(i + 1, count + 1);
			live[i] = false;
		}
	}
	
	public void getMinLength() {
		min = Integer.MAX_VALUE;
		live = new boolean[store.size()];
		dfs(0, 0);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int size = Integer.parseInt(input[0]);
		store_num = Integer.parseInt(input[1]);
		map = new int[size][size];
		human = new ArrayList<>();
		store = new ArrayList<>();
		for(int i = 0; i < size; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				if(map[i][j] == 1) {
					human.add(new Point(i, j));
				} else if(map[i][j] == 2) {
					store.add(new Point(i, j));
				}
			}
		}
		br.close();
		baekjun15686 b = new baekjun15686();
		b.getMinLength();
		bw.write(min + "\n");
		bw.flush();
		bw.close();
	}
	
	public static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
