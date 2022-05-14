package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun9461 {
	Long[] dp;
	// Bottom-Up 방식
//	public long[] getWaveHalfSeq(int[] nums) {
//		long[] result = new long[nums.length];
//		for(int i = 0; i < nums.length; i++) {
//			if(1 <= nums[i] && nums[i] <= 3) {
//				result[i] = 1;
//				continue;
//			} else if(4 <= nums[i] && nums[i] <= 5) {
//				result[i] = 2;
//				continue;
//			}
//			long[] dp = new long[nums[i] + 1];
//			dp[1] = 1L;
//			dp[2] = 1L;
//			dp[3] = 1L;
//			dp[4] = 2L;
//			dp[5] = 2L;
//			for(int j = 6; j <= nums[i]; j++) {
//				dp[j] = dp[j - 5] + dp[j - 1];
//			}
//			result[i] = dp[nums[i]];
//		}
//		return result;
//	}
	
	public long findWaveHalfSeq(int n) {
		if(dp[n] == null) {
			dp[n] = findWaveHalfSeq(n - 1) + findWaveHalfSeq(n - 5);
		}
		return dp[n];
	}
	
	public long[] getWaveHalfSeq(int[] nums) {
		dp = new Long[101];
		dp[0] = 0L;
		dp[1] = 1L;
		dp[2] = 1L;
		dp[3] = 1L;
		dp[4] = 2L;
		dp[5] = 2L;
		long[] result = new long[nums.length];
		for(int i = 0; i < nums.length; i++) {
			result[i] = findWaveHalfSeq(nums[i]);
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_num = Integer.parseInt(br.readLine());
		int[] nums = new int[test_num];
		for(int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun9461 b = new baekjun9461();
		long[] result = b.getWaveHalfSeq(nums);
		for(int i = 0; i < result.length; i++) {
			bw.write(result[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
