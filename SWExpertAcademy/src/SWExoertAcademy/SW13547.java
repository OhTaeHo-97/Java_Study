package SWExoertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SW13547 {
	public String[] canPass(String[] testcases) {
		String[] result = new String[testcases.length];
		for(int i = 0; i < testcases.length; i++) {
			int extra_match = 15 - testcases[i].length();
			if(extra_match >= 8) {
				result[i] = "YES";
				continue;
			}
			int win = 0;
			for(int j = 0; j < testcases[i].length(); j++) {
				if(testcases[i].charAt(j) == 'o') {
					win++;
				}
			}
			if(extra_match >= (8 - win)) {
				result[i] = "YES";
			} else {
				result[i] = "NO";
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		String[] testcases = new String[num];
		for(int i = 0; i < num; i++) {
			testcases[i] = br.readLine();
		}
		br.close();
		SW13547 s = new SW13547();
		String[] result = s.canPass(testcases);
		for(int i = 0; i < result.length; i++) {
			bw.write("#" + (i + 1) + " " + result[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
