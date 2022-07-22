package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun1451 {
	public long getMaxMultiply(int[][] nums, int row, int col) {
		long result = 0L;
		long[][] sum = new long[row + 1][col + 1];
		for(int i = 1; i <= row; i++) {
			for(int j = 1; j <= col; j++) {
				sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + (long)nums[i][j];
			}
		}
		
		for(int i = 1; i <= col - 2; i++) {
			for(int j = i + 1; j <= col - 1; j++) {
				long rectangle1 = sum[row][i] - sum[row][0] - sum[0][i] + sum[0][0];
				long rectangle2 = sum[row][j] - sum[row][i] - sum[0][j] + sum[0][i];
				long rectangle3 = sum[row][col] - sum[row][j] - sum[0][col] + sum[0][j];
				if(result < rectangle1 * rectangle2 * rectangle3) {
					result = rectangle1 * rectangle2 * rectangle3;
				}
			}
		}
		
		for(int i = 1; i <= row - 2; i++) {
			for(int j = i + 1; j <= row - 1; j++) {
				long rectangle1 = sum[i][col] - sum[i][0] - sum[0][col] + sum[0][0];
				long rectangle2 = sum[j][col] - sum[j][0] - sum[i][col] + sum[i][0];
				long rectangle3 = sum[row][col] - sum[row][0] - sum[j][col] + sum[j][0];
				if(result < rectangle1 * rectangle2 * rectangle3) {
					result = rectangle1 * rectangle2 * rectangle3;
				}
			}
		}
		
		for(int i = 1; i <= row - 1; i++) {
			for(int j = 1; j <= col - 1; j++) {
				long rectangle1 = sum[row][j] - sum[row][0] - sum[0][j] + sum[0][0];
				long rectangle2 = sum[i][col] - sum[i][j] - sum[0][col] + sum[0][j];
				long rectangle3 = sum[row][col] - sum[row][j] - sum[i][col] + sum[i][j];
				if(result < rectangle1 * rectangle2 * rectangle3) {
					result = rectangle1 * rectangle2 * rectangle3;
				}
				
				rectangle1 = sum[i][j] - sum[i][0] - sum[0][j] + sum[0][0];
				rectangle2 = sum[row][j] - sum[row][0] - sum[i][j] + sum[i][0];
				rectangle3 = sum[row][col] - sum[row][j] - sum[0][col] + sum[0][j];
				if(result < rectangle1 * rectangle2 * rectangle3) {
					result = rectangle1 * rectangle2 * rectangle3;
				}
				
				rectangle1 = sum[i][col] - sum[i][0] - sum[0][col] + sum[0][0];
				rectangle2 = sum[row][j] - sum[row][0] - sum[i][j] + sum[i][0];
				rectangle3 = sum[row][col] - sum[row][j] - sum[i][col] + sum[i][j];
				if(result < rectangle1 * rectangle2 * rectangle3) {
					result = rectangle1 * rectangle2 * rectangle3;
				}
				
				rectangle1 = sum[i][j] - sum[i][0] - sum[0][j] + sum[0][0];
				rectangle2 = sum[i][col] - sum[i][j] - sum[0][col] + sum[0][j];
				rectangle3 = sum[row][col] - sum[row][0] - sum[i][col] + sum[i][0];
				if(result < rectangle1 * rectangle2 * rectangle3) {
					result = rectangle1 * rectangle2 * rectangle3;
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int row = Integer.parseInt(input[0]);
		int col = Integer.parseInt(input[1]);
		int[][] nums = new int[row + 1][col + 1];
		for(int i = 1; i <= row; i++) {
			String str = br.readLine();
			for(int j = 1; j <= col; j++) {
				nums[i][j] = str.charAt(j - 1) - '0';
			}
		}
		br.close();
		baekjun1451 b = new baekjun1451();
		bw.write(b.getMaxMultiply(nums, row, col) + "\n");
		bw.flush();
		bw.close();
	}
}
