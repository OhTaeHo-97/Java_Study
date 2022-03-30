package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun9613 {
	public int gcd(int a, int b) {
		if(b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}
	
	public long[] getGCDSum(String[] testcase) {
		long[] result = new long[testcase.length];
		for(int i = 0; i < testcase.length; i++) {
			StringTokenizer st = new StringTokenizer(testcase[i]);
			int num = Integer.parseInt(st.nextToken());
			int[] nums = new int[num];
			for(int j = 0; j < num; j++) {
				nums[j] = Integer.parseInt(st.nextToken());
			}
			long sum = 0;
			int gcd = 0;
			for(int j = 0; j < nums.length - 1; j++) {
				for(int k = j + 1; k < nums.length; k++) {
					if(nums[j] < nums[k]) {
						gcd = gcd(nums[k], nums[j]);
					} else {
						gcd = gcd(nums[j], nums[k]);
					}
					sum += gcd;
				}
			}
			result[i] = sum;
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_num = Integer.parseInt(br.readLine());
		String[] testcase = new String[test_num];
		for(int i = 0; i < test_num; i++) {
			testcase[i] = br.readLine();
		}
		br.close();
		baekjun9613 b = new baekjun9613();
		long[] result = b.getGCDSum(testcase);
		for(int i = 0; i < result.length; i++) {
			bw.write(result[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
