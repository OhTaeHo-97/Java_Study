package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun2877 {
	public String getMinNum(int num) {
		String[] two_cipher = {"44", "47", "74", "77"};
		if(num == 1) {
			return "4";
		} else if(num == 2) {
			return "7";
		} else  if(num > 2 && num <= 6) {
			return two_cipher[num - 3];
		}
		String result = "";
		int copy = num;
		int cipher = 0;
		while(copy > 0) {
			cipher++;
			copy -= Math.pow(2, cipher);
		}
		copy += Math.pow(2, cipher);
		for(int i = cipher; i > 1; i--) {
			if(i == 2) {
				result += two_cipher[copy - 1];
			} else {
				if(copy <= (Math.pow(2, i) / 2)) {
					result += "4";
				} else {
					result += "7";
					copy -= (Math.pow(2, i) / 2);
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		br.close();
		baekjun2877 b = new baekjun2877();
		bw.write(b.getMinNum(num) + "\n");
		bw.flush();
		bw.close();
	}
}
