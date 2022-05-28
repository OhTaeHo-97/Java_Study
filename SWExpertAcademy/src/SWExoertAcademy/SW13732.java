package SWExoertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SW13732 {
	static int size;
	static int[][] map;
	boolean[][] visited;
	int[] dx = {-1, 0, 1, 0};
	int[] dy = {0, -1, 0, 1};
	int count;
	public void dfs(int x, int y) {
		visited[x][y] = true;
		for(int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if(cx >= 0 && cx < size && cy >= 0 && cy < size) {
				if(!visited[cx][cy] && map[cx][cy] == 1) {
					dfs(cx, cy);
				}
			}
		}
	}
	
	public String isSquare() {
		visited = new boolean[size][size];
		count = 0;
		int x = 0;
		int y = 0;
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					x= i;
					y= j;
					count++;
					dfs(i, j);
				}
			}
		}
		if(count == 1) {
			int square_size = 0;
			for(int i = y; i < map.length; i++) {
				if(map[x][i] == 1) {
					square_size++;
				} else {
					break;
				}
			}
			boolean isSqr = true;
			int temp = 0;
			for(int i = x; i < map.length; i++) {
				temp = 0;
				for(int j = y; j < map[i].length; j++) {
					if(map[i][j] == 1) {
						temp++;
					} else {
						break;
					}
				}
				if(temp != square_size && temp != 0) {
					isSqr = false;
					break;
				}
			}
			if(!isSqr) return "no";
			for(int i = y; i < map[0].length; i++) {
				temp = 0;
				for(int j = x; j < map.length; j++) {
					if(map[j][i] == 1) {
						temp++;
					} else {
						break;
					}
				}
				if(temp != square_size && temp != 0) {
					isSqr = false;
					break;
				}
			}
			if(isSqr) return "yes";
			else return "no";
		}
		return "no";
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		SW13732 s = new SW13732();
		int test_num = Integer.parseInt(br.readLine());
		String[] result = new String[test_num + 1];
		for(int i = 1; i <= test_num; i++) {
			size = Integer.parseInt(br.readLine());
			map = new int[size][size];
			for(int j = 0; j < size; j++) {
				String input = br.readLine();
				for(int l = 0; l < size; l++) {
					if(input.charAt(l) == '.') {
						map[j][l] = 0;
					} else if(input.charAt(l) == '#') {
						map[j][l] = 1;
					}
				}
			}
			result[i] = s.isSquare();
		}
		br.close();
		for(int i = 1; i < result.length; i++) {
			bw.write("#" + i + " " + result[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
