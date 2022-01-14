package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1312 {
	public int nthDecimal(int[] inputs) {
		if(inputs[0] % inputs[1] == 0) {
			return 0;
		}
		int remainder = inputs[0] % inputs[1];
		int result = inputs[0] / inputs[1];
		for(int i = 0; i < inputs[2]; i++) {
			remainder *= 10;
			result = remainder / inputs[1];
			remainder %= inputs[1];
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		int[] inputs = new int[3];
		for(int i = 0; i < inputs.length; i++) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}
		baekjun1312 b = new baekjun1312();
		System.out.println(b.nthDecimal(inputs));
	}
}
