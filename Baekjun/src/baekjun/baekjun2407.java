package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class baekjun2407 {
	public BigInteger getCombination(int n, int m) {
		BigInteger num1 = BigInteger.ONE;
		BigInteger num2 = BigInteger.ONE;
		for(int i = 0; i < m; i++) {
			num1 = num1.multiply(new BigInteger(String.valueOf(n - i)));
			num2 = num2.multiply(new BigInteger(String.valueOf(i + 1)));
		}
		BigInteger result = num1.divide(num2);
		return result;
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();
        br.close();
        StringTokenizer st = new StringTokenizer(input);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        baekjun2407 b = new baekjun2407();
        bw.write(b.getCombination(n, m) + "\n");
        bw.flush();
        bw.close();
    }
}