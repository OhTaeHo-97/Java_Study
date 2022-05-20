package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun10026 {
	static int num;
	static char[][] painting;
	boolean[][] visited;
	public void dfs(int x, int y, char color) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		visited[x][y] = true;
		for(int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if(cx >= 0 && cx < num && cy >= 0 && cy < num) {
				if(!visited[cx][cy] && painting[cx][cy] == color) {
					dfs(cx, cy, color);
				}
			}
		}
	}
	
	public void dfs(int x, int y, char color, char color2) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		visited[x][y] = true;
		for(int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if(cx >= 0 && cx < num && cy >= 0 && cy < num) {
				if(!visited[cx][cy] && (painting[cx][cy] == color || painting[cx][cy] == color2)) {
					dfs(cx, cy, color, color2);
				}
			}
		}
	}
	
	public int[] getNumOfArea() {
		int[] counts = new int[2];
		visited = new boolean[num][num];
		char[] color = {'R', 'G', 'B'};
		for(int i = 0; i < color.length; i++) {
			for(int j = 0; j < num; j++) {
				for(int l = 0; l < num; l++) {
					if(!visited[j][l] && painting[j][l] == color[i]) {	
						counts[0]++;
						dfs(j, l, color[i]);
					}
				}
			}
		}
		visited = new boolean[num][num];
		for(int i = 1; i < color.length; i++) {
			if(i == 1) {
				for(int j = 0; j < num; j++) {
					for(int l = 0; l < num; l++) {
						if(!visited[j][l] && (painting[j][l] == color[1] || painting[j][l] == color[0])) {	
							counts[1]++;
							dfs(j, l, color[1], color[0]);
						}
					}
				}
			} else {				
				for(int j = 0; j < num; j++) {
					for(int l = 0; l < num; l++) {
						if(!visited[j][l] && painting[j][l] == color[i]) {	
							counts[1]++;
							dfs(j, l, color[i]);
						}
					}
				}
			}
		}
		return counts;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		num = Integer.parseInt(br.readLine());
		painting = new char[num][num];
		for(int i = 0; i < num; i++) {
			String input = br.readLine();
			for(int j = 0; j < num; j++) {
				painting[i][j] = input.charAt(j);
			}
		}
		br.close();
		baekjun10026 b = new baekjun10026();
		int[] result = b.getNumOfArea();
		for(int i = 0; i < result.length; i++) {
			bw.write(result[i] + " ");
		}
		bw.flush();
		bw.close();
	}
}
