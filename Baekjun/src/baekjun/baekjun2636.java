package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun2636 {
	static int row, col;
	static int[][] map;
	boolean[][] visited;
	static int count;
	int[] dx = {-1, 0, 1, 0};
	int[] dy = {0, -1, 0, 1};
	public void dfs(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if(cx >= 0 && cx < row && cy >= 0 && cy < col) {
				if(!visited[cx][cy]) {
					visited[cx][cy] = true;
					if(map[cx][cy] == 1) {
						map[cx][cy] = 2;
						count++;
					} else {
						dfs(cx, cy);
					}
				}
			}
		}
	}
	
	public boolean findEdge() {
		// map[i][j] = 2 -> 공기와 맞닿은 부분! -> 녹은 것을 표시
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(map[i][j] == 2) {
					map[i][j] = 0;
				}
			}
		}
		// 판 위에 치즈 있는지 확인
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(map[i][j] == 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int getHourAndCheese() {
		visited = new boolean[row][col];
		int result;
		for(result = 0; findEdge(); result++) {
			for(boolean[] v : visited) {
				Arrays.fill(v, false);
			}
			visited[0][0] = true;
			count = 0;
			dfs(0, 0);
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		row = Integer.parseInt(input[0]);
		col = Integer.parseInt(input[1]);
		map = new int[row][col];
		for(int i = 0; i < row; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		br.close();
		baekjun2636 b = new baekjun2636();
		bw.write(b.getHourAndCheese() + "\n" + count + "\n");
		bw.flush();
		bw.close();
	}
}
