package baekjun;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class baekjun1246 {
	public Integer[] EggPrice(int n, Integer[] price) {
		Arrays.sort(price, Collections.reverseOrder());
		Integer[] result = new Integer[2];
		// CASE 1. 달걀 수가 1이거나 사람 수가 1인 경우
		if(n == 1 || price.length == 1) {
			result[0] = price[0];
			result[1] = price[0];
		// CASE 2. 사람 수보다 달걀 수가 적을 경우
		} else if(n < price.length) {
			int max = 0;
			int index = -1;
			for(int i = n - 1; i >= 0; i--) {
				int total_price = price[i] * (i + 1);
				if(max < total_price) {
					max = total_price;
					index = i;
				}
			}
			result[0] = price[index];
			result[1] = price[index] * (index + 1);
		// CASE 3. 사람 수보다 달걀 수가 크거나 같은 경우
		} else {
			int max = 0;
			int index = -1;
			for(int i = price.length - 1; i >= 0; i--) {
				int total_price = price[i] * (i + 1);
				if(max < total_price) {
					max = total_price;
					index = i;
				}
			}
			result[0] = price[index];
			result[1] = price[index] * (index + 1);
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s= br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Integer[] price = new Integer[m];
		for(int i = 0; i < price.length; i++) {
			price[i] = Integer.parseInt(br.readLine());
		}
		baekjun1246 b = new baekjun1246();
		Integer[] result = b.EggPrice(n, price);
		for(int i = 0; i < 2; i++) {
			System.out.print(result[i] + " ");
		}
	}
}
