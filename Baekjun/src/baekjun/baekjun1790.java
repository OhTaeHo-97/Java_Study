package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun1790 {
	public int getKthNum(int n, int k) {
		long cur_position = k;
		long len = 1;
		long count_num = 9;
		long digit = 0;
		while(cur_position > count_num * len) {
			cur_position -= count_num * len;
			digit += count_num;
			count_num *= 10;
			len++;
		}
		digit = (digit + 1) + ((cur_position - 1) / len);
		if(digit > n) {
			return -1;
		} else {
			int idx = (int)((cur_position - 1) % len);
			return Character.getNumericValue(String.valueOf(digit).charAt(idx));
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		br.close();
		int n = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]);
		baekjun1790 b = new baekjun1790();
		bw.write(b.getKthNum(n, k) + "\n");
		bw.flush();
		bw.close();
	}
}
