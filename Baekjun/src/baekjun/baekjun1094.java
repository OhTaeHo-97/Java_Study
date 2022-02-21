package baekjun;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class baekjun1094 {
	public int bar(int n) {
		if(n == 64) {
			return 1;
		} else {
			int len = 64, sum = 0, count = 0;
			while(sum != n) {
				len /= 2;
				sum += len;
				len /= 2;
				count++;
				if(sum > n) {
					sum -= len;
					count--;
				} else if(sum == n) {
					break;
				}
			}
			
			return count;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input_string = br.readLine();
		int input = Integer.parseInt(input_string);
		baekjun1094 b = new baekjun1094();
		System.out.println(b.bar(input));
	}
}
