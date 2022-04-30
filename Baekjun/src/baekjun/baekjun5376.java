package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class baekjun5376 {
	public long gcd(long a, long b) {
		if(b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
	
	public String[] getFraction(String[] nums) {
		String[] fraction = new String[nums.length];
		for(int i = 0; i < nums.length; i++) {
			if(!nums[i].contains("(")) {
				int denominator = (int)Math.pow(10, nums[i].length() - 2);
				int numerator = Integer.parseInt(nums[i].substring(2, nums[i].length()));
				long gcd_num = gcd(denominator, numerator);
				fraction[i] = Long.toString(numerator / gcd_num) + "/" + Long.toString(denominator / gcd_num);
			} else {
				int index = nums[i].indexOf("(");
				int len = nums[i].length() - 2;
				long denominator = (long)Math.pow(10, len - 2) - (long)Math.pow(10, index - 2);
				String numerator_str = nums[i].substring(2, nums[i].length() - 1);
				numerator_str = numerator_str.replace("(", "");
				numerator_str = numerator_str.replace(")", "");
				long numerator = Long.parseLong(numerator_str);
				if(!nums[i].substring(2, index).equals("")) {
					numerator -= Long.parseLong(nums[i].substring(2, index));
				}
				long gcd_num = gcd(denominator, numerator);
				fraction[i] = Long.toString(numerator / gcd_num) + "/" + Long.toString(denominator / gcd_num);
			}
		}
		return fraction;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_num = Integer.parseInt(br.readLine());
		String[] nums = new String[test_num];
		for(int i = 0; i < nums.length; i++) {
			nums[i] = br.readLine();
		}
		br.close();
		baekjun5376 b = new baekjun5376();
		String[] fraction = b.getFraction(nums);
		for(String f : fraction) {
			bw.write(f + "\n");
		}
		bw.flush();
		bw.close();
	}
}
