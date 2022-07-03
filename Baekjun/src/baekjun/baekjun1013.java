package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun1013 {
	public String isPattern(String test) {
		String expression = "^(100+1+|01)+$";
		return test.matches(expression) ? "YES" : "NO";
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		baekjun1013 b = new baekjun1013();
		int test_num = Integer.parseInt(br.readLine());
		for(int i = 0; i < test_num; i++) {
			String test = br.readLine();
			bw.write(b.isPattern(test) + "\n");
		}
		br.close();
		bw.flush();
		bw.close();
	}
}
