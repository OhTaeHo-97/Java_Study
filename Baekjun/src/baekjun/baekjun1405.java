package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun1405 {
	static double result = 0;
	static int n;
	static double[] direction;
	
	public void getPercentage(int x, int y, boolean[][] visited, int count, double percentage) {
		if(count == n) {
			result += percentage;
			return;
		}
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		for(int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if(cx > 0 && cx < visited.length && cy > 0 && cy < visited.length) {
				if(!visited[cx][cy]) {
					visited[cx][cy] = true;
					getPercentage(cx, cy, visited, count + 1, percentage * direction[i]);
					visited[cx][cy] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		br.close();
		n = Integer.parseInt(input[0]);
		direction = new double[4];
		for(int i = 0; i < 4; i++) {
			direction[i] = Double.parseDouble(input[i + 1]) * 0.01;
		}
		boolean[][] visited = new boolean[30][30];
		visited[15][15] = true;
		baekjun1405 b = new baekjun1405();
		b.getPercentage(15, 15, visited, 0, 1);
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
}
