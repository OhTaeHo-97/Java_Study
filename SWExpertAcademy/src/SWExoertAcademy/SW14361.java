package SWExoertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class SW14361 {
	public String[] isAnotherMultiple(String[] nums) {
		String[] result = new String[nums.length];
		Arrays.fill(result, "impossible");
		for(int i = 0; i < nums.length; i++) {
			if(nums[i].length() == 1) {
				continue;
			}
			int[] digits = new int[nums[i].length()];
			for(int j = 0; j < nums[i].length(); j++) {
				digits[j] = nums[i].charAt(j) - '0';
			}
			int mul = 2;
			int testcase = Integer.parseInt(nums[i]);
			String multiply_str;
			boolean isMultiple = true;
			while(Integer.toString(testcase * mul).length() == nums[i].length()) {
				isMultiple = true;
				multiply_str = Integer.toString(testcase * mul);
				boolean[] isUsed = new boolean[nums[i].length()];
				int[] multiple_list = new int[nums[i].length()];
				for(int j = 0; j < multiple_list.length; j++) {
					multiple_list[j] = multiply_str.charAt(j) - '0';
					for(int l = 0; l < digits.length; l++) {
						if(multiple_list[j] == digits[l] && !isUsed[l]) {
							isUsed[l] = true;
							break;
						}
					}
				}
				for(int j = 0; j < isUsed.length; j++) {
					if(!isUsed[j]) {
						isMultiple = false;
						break;
					}
				}
				if(isMultiple) {
					result[i] = "possible";
					break;
				}
				mul++;
			}
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
		SW14361 s = new SW14361();
		String[] result = s.isAnotherMultiple(nums);
		for(int i = 0; i < result.length; i++) {
			bw.write("#" + (i + 1) + " " + result[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
