package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1735 {
	public int gcd(int numerator, int denominator) {
		int min, max;
		int num;
		if(numerator > denominator) {
			min = denominator;
			max = numerator;
		} else {
			min = numerator;
			max = denominator;
		}
		while(min != 0) {
			num = max % min;
			max = min;
			min = num;
		}
		return max;
	}
	
	public int[] getSimpleFraction(String input1, String input2) {
		int[] result = new int[2];
		StringTokenizer st = new StringTokenizer(input1);
		int numerator1 = Integer.parseInt(st.nextToken());
		int denominator1 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(input2);
		int numerator2 = Integer.parseInt(st.nextToken());
		int denominator2 = Integer.parseInt(st.nextToken());
		int numerator = numerator1 * denominator2 + numerator2 * denominator1;
		int denominator = denominator1 * denominator2;
		int gcd = gcd(numerator, denominator);
		result[0] = numerator / gcd;
		result[1] = denominator / gcd;
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input1 = br.readLine();
		String input2 = br.readLine();
		br.close();
		baekjun1735 b = new baekjun1735();
		int[] result = b.getSimpleFraction(input1, input2);
		for(int i = 0; i < 2; i++) {
			bw.write(result[i] + " ");
		}
		bw.flush();
		bw.close();
	}
}
