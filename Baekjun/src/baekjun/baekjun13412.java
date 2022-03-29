package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class baekjun13412 {
	public int gcd(int a, int b) {
		if(b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}
	public int[] getPairNum(int[] testcase) {
		int[] result = new int[testcase.length];
		for(int i = 0; i < testcase.length; i++) {
			HashMap<Integer, Integer> pair = new HashMap<Integer, Integer>();
			for(int j= 1; j <= Math.sqrt(testcase[i]); j++) {
				if(testcase[i] % j == 0) {
					if(pair.containsKey((int)testcase[i] / j)) {
						continue;
					}
					int gcd = gcd(testcase[i] / j, j);
					if(gcd == 1) {
						pair.put(j, testcase[i] / j);
					}
				}
			}
			result[i] = pair.size();
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		int[] testcase = new int[num];
		for(int i = 0; i < testcase.length; i++) {
			testcase[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun13412 b = new baekjun13412();
		int[] result = b.getPairNum(testcase);
		for(int i = 0; i < result.length; i++) {
			bw.write(result[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
