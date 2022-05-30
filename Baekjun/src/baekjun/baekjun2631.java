package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun2631 {
	static int[] nums;
	int[] dp;
	public int getMinNum() {
		int max = 0;
		dp = new int[nums.length];
		Arrays.fill(dp, 1);
		for(int i = 1; i < nums.length; i++) {
			for(int j = 0; j < i; j++) {
				if(nums[j] < nums[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			max = max < dp[i] ? dp[i] : max;
		}
		return nums.length - max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int people_num = Integer.parseInt(br.readLine());
		nums = new int[people_num];
		for(int i = 0; i < people_num; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun2631 b= new baekjun2631();
		bw.write(b.getMinNum() + "\n");
		bw.flush();
		bw.close();
	}
}
