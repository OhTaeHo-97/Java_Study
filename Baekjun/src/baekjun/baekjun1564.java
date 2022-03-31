package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun1564 {
	public String getFactorial5(int num) {
		long factorial = 1;
		long temp = (long)Math.pow(10, 12);
		for(int i = 1; i <= num; i++) {
			factorial *= i;
			while(factorial % 10 == 0) {
				factorial /= 10;
			}
			factorial %= temp;
		}
		String factorial5 = Long.toString(factorial % 100000);
		while(factorial5.length() < 5) {
			factorial5 = "0" + factorial5;
		}
		return factorial5;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		br.close();
		baekjun1564 b = new baekjun1564();
		bw.write(b.getFactorial5(num) + "\n");
		bw.flush();
		bw.close();
	}
}
