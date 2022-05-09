package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun9024 {
	public int getNearestDif(int dif, int[] nums) {
		Arrays.sort(nums);
		int count = 0;
		int near = Integer.MAX_VALUE;
		for(int i = 0; i < nums.length - 1; i++) {
			int start = 0;
			int end = nums.length;
			while(start < end) {
				int mid = (start + end) / 2;
				int sum = nums[i] + nums[mid];
				System.out.println("near: " + near);
				System.out.println("Start : " + start + ", end : " + end);
				if(Math.abs(sum - dif) < Math.abs(near - dif)) {
					near = sum;
					count = 0;
				}
				if(sum >= dif) {
					if(sum == near) {
						count++;
					}
					end = mid;
				} else {
					start = mid + 1;
				}
			}
		}
		return count;
//		Arrays.sort(nums);
//		int left = 0;
//		int right = 0;
//		int near = Integer.MAX_VALUE;
//		int count = 0;
//		while(left <= right) {
//			int difference = nums[left] + nums[right];
//			if(Math.abs(difference - dif) < Math.abs(near - dif)) {
//				near = difference;
//				count = 0;
//			}
//			if(difference <= dif) {
//				if(difference == near) {
//					count++;
//				}
//				if(right == nums.length - 1) {
//					left++;
//				} else {
//					right++;
//				}
//			} else {
//				left++;
//			}
//		}
//		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_num = Integer.parseInt(br.readLine());
		baekjun9024 b = new baekjun9024();
		for(int i = 0; i < test_num; i++) {
			String[] input = br.readLine().split(" ");
			int num = Integer.parseInt(input[0]);
			int dif = Integer.parseInt(input[1]);
			int[] nums = new int[num];
			input = br.readLine().split(" ");
			for(int j = 0; j < num; j++) {
				nums[j] = Integer.parseInt(input[j]);
			}
			bw.write(b.getNearestDif(dif, nums) + "\n");
		}
		br.close();
		bw.flush();
		bw.close();
	}
}
