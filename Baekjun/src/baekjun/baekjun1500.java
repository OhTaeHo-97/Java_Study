package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1500 {
	public long getMaxMultiply(int s, int k) {
		int[] nums = new int[k];
		int div = s / k;
		int remain = s % k;
		for(int i = 0; i < nums.length; i++) {
			nums[i] = div;
		}
		for(int i = 0; i < remain; i++) {
			nums[i]++;
		}
		long mul = 1;
		for(int i = 0; i < nums.length; i++) {
			mul *= nums[i];
		}
		return mul;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		br.close();
		StringTokenizer st = new StringTokenizer(input);
		int s = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		baekjun1500 b = new baekjun1500();
		bw.write(b.getMaxMultiply(s, k) + "\n");
		bw.flush();
		bw.close();
	}
}
