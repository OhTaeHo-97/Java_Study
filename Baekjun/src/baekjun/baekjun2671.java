package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun2671 {
	public String isSubmarine(String input) {
		String pattern = "^(100+1+|01)+$";
		return input.matches(pattern) ? "SUBMARINE" : "NOISE";
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		br.close();
		baekjun2671 b = new baekjun2671();
		bw.write(b.isSubmarine(input) + "\n");
		bw.flush();
		bw.close();
	}
}
