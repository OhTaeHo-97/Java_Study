package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun5557 {
	static int[] nums;
	static long[][] dp;
	static int result, count = 0;
	public long findCaseNum(int index, int total) {
		if(total < 0 || total > 20) {
			return 0;
		}
		if(index == nums.length - 2) {
			return (total == nums[nums.length - 1]) ? 1 : 0;
		}
		if(dp[total][index] != -1) {
			return dp[total][index];
		}
		dp[total][index] = 0;
		return dp[total][index] += findCaseNum(index + 1, total + nums[index + 1]) + findCaseNum(index + 1, total - nums[index + 1]);
	}
	
	public long getCaseNum() {
		dp = new long[21][100];
		for(int i = 0; i < 21; i++) {
			Arrays.fill(dp[i], -1);
		}
		return findCaseNum(0, nums[0]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		br.close();
		nums = new int[num];
		for(int i = 0; i < num; i++) {
			nums[i] = Integer.parseInt(input[i]);
		}
		baekjun5557 b = new baekjun5557();
		bw.write(b.getCaseNum() + "\n");
		bw.flush();
		bw.close();
	}
}
