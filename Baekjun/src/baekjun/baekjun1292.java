package baekjun;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;


public class baekjun1292 {
	public int sum(int a, int b) {
		int[] sums = new int[b];
		sums[0] = 1;
		int num = 2;
		int count = 0;
		for(int i = 1; i < b; i++) {
			sums[i] = sums[i - 1] + num;
			count++;
			if(count == num) {
				num++;
				count = 0;
			}
		}
		
		if(a <= 1)
			return sums[b - 1];
		
		return sums[b - 1] - sums[a - 2];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		baekjun1292 baek = new baekjun1292();
		System.out.println(baek.sum(a, b));
	}
}
