package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1080 {
	static int[][] matrix;
	static int[][] result_matrix;
	
	public int getMinConvert() {
		int count = 0;
		for(int i = 0; i < matrix.length - 2; i++) {
			for(int j = 0; j < matrix[i].length - 2; j++) {
				if(matrix[i][j] != result_matrix[i][j]) {
					for(int k = i; k < i + 3; k++) {
						for(int l = j; l < j + 3; l++) {
							if(matrix[k][l] == 1) {
								matrix[k][l] = 0;
							} else {
								matrix[k][l] = 1;
							}
						}
					}
					count++;
				}
			}
		}
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j] != result_matrix[i][j]) {
					return -1;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		matrix = new int[row][col];
		result_matrix = new int[row][col];
		for(int i = 0; i < row; i++) {
			input = br.readLine();
			for(int j = 0; j < col; j++) {
				matrix[i][j] = input.charAt(j) - '0';
			}
		}
		for(int i = 0; i < row; i++) {
			input = br.readLine();
			for(int j = 0; j < col; j++) {
				result_matrix[i][j] = input.charAt(j) - '0';
			}
		}
		br.close();
		baekjun1080 b = new baekjun1080();
		bw.write(b.getMinConvert() + "\n");
		bw.flush();
		bw.close();
	}
}
