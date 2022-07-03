package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class baekjun2493 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		Stack<Tower> towers = new Stack<Tower>();
		String[] input = br.readLine().split(" ");
		br.close();
		for(int i = 0; i < num; i++) {
			int height = Integer.parseInt(input[i]);
			if(towers.isEmpty()) {
				bw.write("0 ");
				towers.push(new Tower(i + 1, height));
			} else {
				while(true) {
					if(towers.isEmpty()) {
						bw.write("0 ");
						towers.push(new Tower(i + 1, height));
						break;
					}
					Tower tower = towers.peek();
					if(tower.height > height) {
						bw.write(tower.tower_num + " ");
						towers.push(new Tower(i + 1, height));
						break;
					} else {
						towers.pop();
					}
				}
			}
		}
		bw.flush();
		bw.close();
	}
	
	public static class Tower {
		int tower_num, height;
		public Tower(int tower_num, int height) {
			this.tower_num = tower_num;
			this.height = height;
		}
	}
}
