package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class baekjun13398 {
	public int getMaxSum(int[] nums) {
		int[] dp_left = new int[nums.length];
		int[] dp_right = new int[nums.length];
		dp_left[0] = nums[0];
		int max = nums[0];
		for(int i = 1; i < nums.length; i++) {
			dp_left[i] = Math.max(dp_left[i - 1] + nums[i], nums[i]);
			max = Math.max(max, dp_left[i]);
		}
		dp_right[nums.length - 1] = nums[nums.length - 1];
		for(int i = nums.length - 2; i >= 0; i--) {
			dp_right[i] = Math.max(dp_right[i + 1] + nums[i], nums[i]);
		}
		for(int i = 1; i < nums.length - 1; i++) {
			int temp = dp_left[i - 1] + dp_right[i + 1];
			max = Math.max(max, temp);
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		int[] nums = new int[num];
//		ArrayList<Integer> negative = new ArrayList<Integer>();
		String[] input = br.readLine().split(" ");
		br.close();
		for(int i = 0; i < num; i++) {
			nums[i] = Integer.parseInt(input[i]);
//			if(nums[i] < 0) {
//				negative.add(i);
//			}
		}
		baekjun13398 b = new baekjun13398();
		bw.write(b.getMaxSum(nums) + "\n");
		bw.flush();
		bw.close();
	}
}
