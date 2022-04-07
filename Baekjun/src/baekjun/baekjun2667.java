package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun2667 {
	static int[][] map;
	static boolean[][] visited;
	static int[] complexNum;
	static int complex = 0;
	public void dfs(int x, int y) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		visited[x][y] = true;
		complexNum[complex]++;
		for(int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if(cx >= 0 && cy >= 0 && cx < map.length && cy < map.length) {
				if(map[cx][cy] == 1 && !visited[cx][cy]) {
					dfs(cx, cy);
				}
			}
		}
	}
	
	public void getComplexNum() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					complex++;
					dfs(i, j);
				}
			}
		}
		Arrays.sort(complexNum);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int size = Integer.parseInt(br.readLine());
		map = new int[size][size];
		visited = new boolean[size][size];
		complexNum = new int[size * size];
		for(int i = 0; i < map.length; i++) {
			String isHouse = br.readLine();
			for(int j = 0; j < isHouse.length(); j++) {
				map[i][j] = Integer.parseInt(isHouse.substring(j, j + 1));
			}
		}
		br.close();
		baekjun2667 b = new baekjun2667();
		b.getComplexNum();
		bw.write(complex + "\n");
		for(int i = 0; i < complexNum.length; i++) {
			if(complexNum[i] != 0) {
				bw.write(complexNum[i] + "\n");
			}
		}
		bw.flush();
		bw.close();
	}
}
