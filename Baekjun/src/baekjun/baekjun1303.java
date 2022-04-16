package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1303 {
	static char[][] battlefield;
	boolean[][] visited;
	int[] soldierNum;
	int count;
	
	public void dfs(int x, int y, char team) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		visited[x][y] = true;
		soldierNum[count]++;
		for(int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if(cx >= 0 && cx < battlefield.length && cy >= 0 && cy < battlefield[0].length) {	
				if(!visited[cx][cy] && battlefield[cx][cy] == team) {
					dfs(cx, cy, team);
				}
			}
		}
	}
	
	public int[] getSumOfPower() {
		if(battlefield.length == 1 && battlefield[0].length == 1) {
			if(battlefield[0][0] == 'W') {
				int[] sumOfPower = {1, 0};
				return sumOfPower;
			} else {
				int[] sumOfPower = {0, 1};
				return sumOfPower;
			}
		}
		int[] sumOfPower = new int[2];
		visited = new boolean[battlefield.length][battlefield[0].length];
		soldierNum = new int[battlefield.length * battlefield[0].length];
		for(int i = 0; i < battlefield.length; i++) {
			for(int j = 0; j < battlefield[i].length; j++) {
				if(!visited[i][j] && battlefield[i][j] == 'W') {
					count++;
					dfs(i, j, 'W');
				}
			}
		}
		for(int i = 0; i < soldierNum.length; i++) {
			if(soldierNum[i] != 0) {
				sumOfPower[0] += Math.pow(soldierNum[i], 2);
			}
		}
		count = 0;
		soldierNum = new int[battlefield.length * battlefield[0].length];
		for(int i = 0; i < battlefield.length; i++) {
			for(int j = 0; j < battlefield[i].length; j++) {
				if(!visited[i][j] && battlefield[i][j] == 'B') {
					count++;
					dfs(i, j, 'B');
				}
			}
		}
		for(int i = 0; i < soldierNum.length; i++) {
			if(soldierNum[i] != 0) {
				sumOfPower[1] += Math.pow(soldierNum[i], 2);
			}
		}
		return sumOfPower;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		battlefield = new char[col][row];
		for(int i = 0; i < col; i++) {
			input = br.readLine();
			for(int j = 0; j < row; j++) {
				battlefield[i][j] = input.charAt(j);
			}
		}
		br.close();
		baekjun1303 b = new baekjun1303();
		int[] result = b.getSumOfPower();
		bw.write(result[0] + " " + result[1] + "\n");
		bw.flush();
		bw.close();
	}
}
