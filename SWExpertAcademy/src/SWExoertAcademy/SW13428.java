package SWExoertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class SW13428 {
	public int[][] getMaxMinValue(String[] nums) {
		int[][] result = new int[nums.length][2];
		for(int i = 0; i < nums.length; i++) {
			char[] num = nums[i].toCharArray();
			int min = Integer.parseInt(nums[i]);
			int max = min;
			for(int j = 0; j < num.length - 1; j++) {
				for(int l = j + 1; l < num.length; l++) {
					char temp = num[j];
					num[j] = num[l];
					num[l] = temp;
					if(num[0] != '0' && Integer.parseInt(String.valueOf(num)) < min) {
						min = Integer.parseInt(String.valueOf(num));
					}
					if(num[0] != '0' && Integer.parseInt(String.valueOf(num)) > max) {
						max = Integer.parseInt(String.valueOf(num));
					}
					temp = num[j];
					num[j] = num[l];
					num[l] = temp;
				}
			}
			result[i][0] = min;
			result[i][1] = max;
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		String[] nums = new String[num];
		for(int i = 0; i < num; i++) {
			nums[i] = br.readLine();
		}
		br.close();
		SW13428 s = new SW13428();
		int[][] result = s.getMaxMinValue(nums);
		for(int i = 0; i < result.length; i++) {
			bw.write("#" + (i + 1) + " " + result[i][0] + " " + result[i][1] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
