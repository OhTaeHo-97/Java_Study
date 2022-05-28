package SWExoertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SW12051 {
	public String[] isPossible(long[] n, int[] pd, int[] pg) {
		String[] result = new String[n.length];
		for(int i = 0; i < n.length; i++) {
			if(pd[i] != 100 && pg[i] == 100) {
				result[i] = "Broken";
			} else if(pd[i] != 0 && pg[i] == 0) {
				result[i] = "Broken";
			} else {
				boolean isDecimal = true;
				for(long j = 1; j <= n[i]; j++) {
					double num = (j * pd[i]) / (double)100;
					if(num % 1 == 0) {
						isDecimal = false;
						break;
					}
				}
				if(!isDecimal) {
					result[i] = "Possible";
				} else {
					result[i] = "Broken";
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_num = Integer.parseInt(br.readLine());
		long[] n = new long[test_num];
		int[] pd = new int[test_num];
		int[] pg = new int[test_num];
		for(int i = 0; i < test_num; i++) {
			String[] input = br.readLine().split(" ");
			n[i] = Long.parseLong(input[0]);
			pd[i] = Integer.parseInt(input[1]);
			pg[i] = Integer.parseInt(input[2]);
		}
		br.close();
		SW12051 t = new SW12051();
		String[] result = t.isPossible(n, pd, pg);
		for(int i = 0; i < result.length; i++) {
			bw.write("#" + (i + 1) + " " + result[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
