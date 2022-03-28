package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun9734 {
	public int gcd(int a, int b) {
		if(b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}
	
	public String getFraction(String circul_decimals) {
		StringTokenizer st = new StringTokenizer(circul_decimals, ".");
		int num = Integer.parseInt(st.nextToken());
		String next = st.nextToken();
		int first = next.indexOf("(");
		int last = next.indexOf(")") - 1;
		st = new StringTokenizer(next, "()");
		ArrayList<String> nums = new ArrayList<String>();
		while(st.hasMoreTokens()) {
			nums.add(st.nextToken());
		}
		String decimal = num == 0 ? "" : Integer.toString(num);
		int len = decimal.equals("0") ? 0 : decimal.length();
		for(int i = 0; i < nums.size(); i++) {
			decimal += nums.get(i);
		}
		int up = Integer.parseInt(decimal.substring(0, decimal.length())) - (decimal.substring(0, len + first).equals("") ? 0 : Integer.parseInt(decimal.substring(0, len + first)));
		int down = (int)(Math.pow(10, last) - Math.pow(10, first));
		int gcd = gcd(up, down);
		up /= gcd;
		down /= gcd;
		return circul_decimals + " = " + up + " / " + down;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String circul_decimals = "";
		baekjun9734 b = new baekjun9734();
		while((circul_decimals = br.readLine()) != null) {
			bw.write(b.getFraction(circul_decimals) + "\n");
		}
		br.close();
		bw.flush();
		bw.close();
	}
}
