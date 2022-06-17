package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun14888 {
	static int[] nums;
	static int[] operators;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int n;
	public void getMinMax(int num, int index) {
		if(index == n) {
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}
		for(int i = 0; i < 4; i++) {
			if(operators[i] > 0) {
				operators[i]--;
				switch(i) {
				case 0:
					getMinMax(num + nums[index], index + 1);
					break;
				case 1:
					getMinMax(num - nums[index], index + 1);
					break;
				case 2:
					getMinMax(num * nums[index], index + 1);
					break;
				case 3:
					getMinMax(num / nums[index], index + 1);
					break;
				}
				operators[i]++;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		String[] input = br.readLine().split(" ");
		for(int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(input[i]);
		}
		operators = new int[4];
		input = br.readLine().split(" ");
		for(int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(input[i]);
		}
		baekjun14888 b = new baekjun14888();
		b.getMinMax(nums[0], 1);
		bw.write(max + "\n" + min + "\n");
		bw.flush();
		bw.close();
	}
}
