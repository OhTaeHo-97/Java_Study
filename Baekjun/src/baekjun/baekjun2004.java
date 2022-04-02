package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun2004 {
	public long getTwoNum(int num) {
		int count = 0;
		while(num >= 2) {
			count += (num / 2);
			num /= 2;
		}
		return count;
	}
	
	public long getFiveNum(int num) {
		int count = 0;
		while(num >= 5) {
			count += (num / 5);
			num /= 5;
		}
		return count;
	}
	
	public long getZeroNum(int big, int small) {
		if(small > big / 2) {
			small = big - small;
		}
		long two = getTwoNum(big) - getTwoNum(small) - getTwoNum(big - small);
		long five = getFiveNum(big) - getFiveNum(small) - getFiveNum(big - small);
		long count = Math.min(two, five);
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		br.close();
		StringTokenizer st = new StringTokenizer(input);
		int big = Integer.parseInt(st.nextToken());
		int small = Integer.parseInt(st.nextToken());
		baekjun2004 b = new baekjun2004();
		bw.write(b.getZeroNum(big, small) + "\n");
		bw.flush();
		bw.close();
	}
}
