package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class baekjun2780 {
	HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>(){{
		put(0, new ArrayList<Integer>(Arrays.asList(7)));
		put(1, new ArrayList<Integer>(Arrays.asList(2,4)));
		put(2, new ArrayList<Integer>(Arrays.asList(1,3,5)));
		put(3, new ArrayList<Integer>(Arrays.asList(2,6)));
		put(4, new ArrayList<Integer>(Arrays.asList(1,5,7)));
		put(5, new ArrayList<Integer>(Arrays.asList(2,4,6,8)));
		put(6, new ArrayList<Integer>(Arrays.asList(3,5,9)));
		put(7, new ArrayList<Integer>(Arrays.asList(4,8,0)));
		put(8, new ArrayList<Integer>(Arrays.asList(5,7,9)));
		put(9, new ArrayList<Integer>(Arrays.asList(6,8)));
	}};
	int[][] dp = new int[1000][10];
	
	public int[] getPWNum(int[] pw_size) {
		int[] result = new int[pw_size.length];
		for(int i = 0; i < dp[0].length; i++) {
			dp[0][i] = 1;
		}
		for(int i = 1; i < dp.length; i++) {
			for(int j = 0; j < dp[i].length; j++) {
				for(int num : map.get(j)) {
					dp[i][j] += dp[i - 1][num];
					dp[i][j] %= 1234567;
				}
			}
		}
		for(int i = 0; i < pw_size.length; i++) {
			for(int j = 0; j < dp[0].length; j++) {
				result[i] += dp[pw_size[i] - 1][j];
			}
			result[i] %= 1234567;
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		int[] pw_size = new int[num];
		for(int i = 0; i < num; i++) {
			pw_size[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun2780 b = new baekjun2780();
		int[] result = b.getPWNum(pw_size);
		for(int i = 0; i < result.length; i++) {
			bw.write(result[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
