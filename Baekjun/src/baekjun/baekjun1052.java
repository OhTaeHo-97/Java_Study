package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1052 {
	public int getBottleNum(int n, int k) {
		int bottleNum = 0;
		while(true) {
			int temp = n + bottleNum;
			int count = 0;
			while(temp > 0) {
				if(temp % 2 == 1) {
					count++;
				}
				temp /= 2;
			}
			if(count <= k) {
				break;
			}
			bottleNum++;
		}
		return bottleNum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		br.close();
		baekjun1052 b = new baekjun1052();
		bw.write(b.getBottleNum(n, k) + "\n");
		bw.flush();
		bw.close();
	}
}
