package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1850 {
	public long gcd(long a, long b) {
		if(b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}
	
	public long getGCD(String input) {
		StringTokenizer st = new StringTokenizer(input);
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		long gcd = 0;
		if(a > b) {
			gcd = gcd(a, b);
		} else {
			gcd = gcd(b, a);
		}
		return gcd;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		br.close();
		baekjun1850 b = new baekjun1850();
		long result = b.getGCD(input);
		for(int i = 0; i < result; i++) {
			bw.write("1");
		}
		bw.flush();
		bw.close();
	}
}
