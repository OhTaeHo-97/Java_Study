package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun1654 {
	public long getMaxLength(int num, int[] LAN_nums) {
		Arrays.sort(LAN_nums);
		long start = 0;
		long end = (long)LAN_nums[LAN_nums.length - 1] + 1;
		while(start < end) {
			long count = 0;
			long mid = (start + end) / 2;
			for(int i = 0; i < LAN_nums.length; i++) {
				count += (LAN_nums[i] / mid);
			}
			if(count < num) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return start - 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int k = Integer.parseInt(input[0]);
		int num = Integer.parseInt(input[1]);
		int[] LAN_nums = new int[k];
		for(int i = 0; i < k; i++) {
			LAN_nums[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun1654 b = new baekjun1654();
		bw.write(b.getMaxLength(num, LAN_nums) + "\n");
		bw.flush();
		bw.close();
	}
}
