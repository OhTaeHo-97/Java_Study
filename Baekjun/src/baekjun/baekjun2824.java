package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class baekjun2824 {
	public BigInteger gcd(BigInteger max, BigInteger min) {
		if(min.compareTo(BigInteger.ZERO) == 0) {
			return max;
		}
		return gcd(min, max.mod(min));
	}
	
	public String getGCD(String[] first_num, String[] second_num) {
		BigInteger first = BigInteger.ONE;
		for(int i = 0; i < first_num.length; i++) {
			first = first.multiply(new BigInteger(first_num[i]));
		}
		BigInteger second = BigInteger.ONE;
		for(int i = 0; i < second_num.length; i++) {
			second = second.multiply(new BigInteger(second_num[i]));
		}
		BigInteger max = first.compareTo(second) > 0 ? first: second;
		BigInteger min = first.compareTo(second) > 0 ? second: first;
		BigInteger gcd_num = gcd(max, min);
		String gcd_str = gcd_num.toString();
		if(gcd_str.length() <= 9) {
			return gcd_str;
		} else {
			return gcd_str.substring(gcd_str.length() - 9, gcd_str.length());
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		String[] first_num = new String[num];
		String[] input = br.readLine().split(" ");
		for(int i = 0; i < num; i++) {
			first_num[i] = input[i];
		}
		num = Integer.parseInt(br.readLine());
		String[] second_num = new String[num];
		input = br.readLine().split(" ");
		for(int i = 0; i < num; i++) {
			second_num[i] = input[i];
		}
		br.close();
		baekjun2824 b = new baekjun2824();
		bw.write(b.getGCD(first_num, second_num) + "\n");
		bw.flush();
		bw.close();
	}
}
