package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1012 {
	static int count = 0;
	int[][] map;
	int[][] check;
	static int row;
	static int col;
	
	public void dfs(int x, int y) {
		check[x][y] = 1;
		int[] four_directions_x = {0, 0, -1, 1};
		int[] four_directions_y = {-1, 1, 0, 0};
		for(int i = 0; i < 4; i++) {
			int check_x = x + four_directions_x[i];
			int check_y = y + four_directions_y[i];
			if(check_x >= 0 && check_y >= 0 && check_x < row && check_y < col) {
				if(map[check_x][check_y] == 1 && check[check_x][check_y] == 0) {
					dfs(check_x, check_y);
				}
			}
		}
	}
	
	public void makeMap(ArrayList<String> point) {
		map = new int[row][col];
		check = new int[row][col];
		for(int i = 0; i < point.size(); i++) {
			StringTokenizer st = new StringTokenizer(point.get(i));
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[x][y] = 1;
		}
	}
	
	public void getMinNum(ArrayList<String> point) {
		makeMap(point);
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(map[i][j] == 1 && check[i][j] == 0) {
					count++;
					dfs(i, j);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int case_num = Integer.parseInt(br.readLine());
		int[] result = new int[case_num];
		for(int i = 0; i < case_num; i++) {
			String case_str = br.readLine();
			StringTokenizer st = new StringTokenizer(case_str);
			col = Integer.parseInt(st.nextToken());
			row = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			ArrayList<String> point = new ArrayList<String>();
			for(int j = 0; j < num; j++) {
				point.add(br.readLine());
			}
			baekjun1012 b = new baekjun1012();
			b.getMinNum(point);
			result[i] = count;
			count = 0;
		}
		br.close();
		for(int i = 0; i < result.length; i++) {
			bw.write(result[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
