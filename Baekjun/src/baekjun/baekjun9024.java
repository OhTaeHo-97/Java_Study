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
		int left = 0;
		int right = nums.length - 1;
		int near = Integer.MAX_VALUE;
		int count = 0;
		while(true) {
			int sum = nums[left] + nums[right];
			if(Math.abs(sum - dif) == near) {
				count++;
			} else if(Math.abs(sum - dif) < near) {
				count = 1;
				near = Math.abs(sum - dif);
			}
			if(sum == dif) {
				left++;
				right--;
			} else if(sum < dif) {
				left++;
			} else {
				right--;
			}
			if(left >= right) {
				break;
			}
		}
		return count;
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
