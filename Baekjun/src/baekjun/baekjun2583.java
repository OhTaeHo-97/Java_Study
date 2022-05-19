package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun2583 {
	static int row, col;
	static int[][] square;
	static int count = 0;
	static int[] result;
	boolean[][] visited;
	public void dfs(int x, int y) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		visited[x][y] = true;
		result[count]++;
		for(int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if(cx >= 0 && cx < row && cy >= 0 && cy < col) {
				if(!visited[cx][cy] && square[cx][cy] == 0) {
					dfs(cx, cy);
				}
			}
		}
	}
	
	public int[] getNumOfArea() {
		result = new int[row * col + 1];
		visited = new boolean[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(!visited[i][j] && square[i][j] == 0) {
					count++;
					dfs(i, j);
				}
			}
		}
		int[] area = new int[count];
		for(int i = 1; i <= count; i++) {
			area[i - 1] = result[i];
		}
		Arrays.sort(area);
		return area;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		row = Integer.parseInt(input[0]);
		col = Integer.parseInt(input[1]);
		int k = Integer.parseInt(input[2]);
		square = new int[row][col];
		for(int i = 0; i < k; i++) {
			input = br.readLine().split(" ");
			int y1 = Integer.parseInt(input[0]);
			int x1 = Integer.parseInt(input[1]);
			int y2 = Integer.parseInt(input[2]) - 1;
			int x2 = Integer.parseInt(input[3]) - 1;
			for(int j = x1; j <= x2; j++) {
				for(int l = y1; l <= y2; l++) {
					square[j][l] = 1;
				}
			}
		}
		br.close();
		baekjun2583 b = new baekjun2583();
		int[] area = b.getNumOfArea();
		bw.write(count + "\n");
		for(int i = 0; i < count; i++) {
			bw.write(area[i] + " ");
		}
		bw.flush();
		bw.close();
	}
}
