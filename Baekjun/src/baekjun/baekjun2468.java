package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class baekjun2468 {
	static int num;
	static int[][] heights;
	boolean[][] visited;
	
	public void bfs(int x, int y, int h) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{x, y});
		visited[x][y] = true;
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int px = pos[0];
			int py = pos[1];
			for(int i = 0; i < 4; i++) {
				int cx = px + dx[i];
				int cy = py + dy[i];
				
				if(cx < 0 || cy < 0 || cx > num - 1 || cy > num - 1) {
					continue;
				}
				if(visited[cx][cy]) {
					continue;
				}
				if(heights[cx][cy] > h) {
					visited[cx][cy] = true;
					q.add(new int[]{cx, cy});
				}
			}
		}
	}
	
	public void dfs(int x, int y, int h) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		visited[x][y] = true;
		for(int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if(cx >= 0 && cx < num && cy >= 0 && cy < num) {
				if(!visited[cx][cy] && heights[cx][cy] > h) {
					dfs(cx, cy, h);
				}
			}
		}
	}
	
	public int getMaxNumOfSafety(int max) {
		int result = 0;
		for(int i = 0; i <= max; i++) {
			visited = new boolean[num][num];
			int count = 0;
			for(int j = 0; j < heights.length; j++) {
				for(int l = 0; l < heights[j].length; l++) {
					if(!visited[j][l] && heights[j][l] > i) {
						count++;
						bfs(j, l, i);
					}
				}
			}
			result = Math.max(result, count);
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		num = Integer.parseInt(br.readLine());
		heights = new int[num][num];
		int max = 0;
		for(int i = 0; i < num; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < num; j++) {
				heights[i][j] = Integer.parseInt(input[j]);
				max = Math.max(max, heights[i][j]);
			}
		}
		br.close();
		baekjun2468 b = new baekjun2468();
		bw.write(b.getMaxNumOfSafety(max) + "\n");
		bw.flush();
		bw.close();
	}
}
