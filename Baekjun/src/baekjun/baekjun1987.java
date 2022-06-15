package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class baekjun1987 {
	static char[][] map;
	static boolean[][] visited;
	ArrayList<Character> visited_char;
	int count;
	public void dfs(int x, int y) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		for(int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if(cx >= 0 && cx < map.length && cy >= 0 && cy < map[0].length) {
				if(!visited[cx][cy] && !visited_char.contains(map[cx][cy])) {
					visited[cx][cy] = true;
					visited_char.add(map[cx][cy]);
					dfs(cx, cy);
					visited[cx][cy] = false;
					count = count > visited_char.size() ? count : visited_char.size();
					visited_char.remove(visited_char.size() - 1);
				}
			}
		}
	}
	
	public int getMaxSpace() {
		visited_char = new ArrayList<>();
		visited[0][0] = true;
		visited_char.add(map[0][0]);
		count = 1;
		dfs(0, 0);
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int row = Integer.parseInt(input[0]);
		int col = Integer.parseInt(input[1]);
		map = new char[row][col];
		visited = new boolean[row][col];
		for(int i = 0; i < row; i++) {
			String alphabets = br.readLine();
			for(int j = 0; j < col; j++) {
				map[i][j] = alphabets.charAt(j);
			}
		}
		br.close();
		baekjun1987 b = new baekjun1987();
		bw.write(b.getMaxSpace() + "\n");
		bw.flush();
		bw.close();
	}
}
