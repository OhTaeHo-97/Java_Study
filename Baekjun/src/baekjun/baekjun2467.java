package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// 투포인터 알고리즘
public class baekjun2467 {
	public int[] getNearZero(int[] solution) {
		int[] result = new int[2];
		long min = Long.MAX_VALUE;
		Arrays.sort(solution);
		int left = 0;
		int right = solution.length - 1;
		int al = 0;
		int ar = solution.length - 1;
		while(left <= right) {
			long sum = (long)solution[right] + solution[left];
			if(left != right && Math.abs(sum) < min) {
				al = left;
				ar = right;
				min = Math.abs(sum);
			}
			if(sum >= 0) {
				right--;
			} else {
				left++;
			}
		}
		result[0] = solution[al];
		result[1] = solution[ar];
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		int[] solution = new int[num];
		String[] input = br.readLine().split(" ");
		br.close();
		for(int i = 0; i < num; i++) {
			solution[i] = Integer.parseInt(input[i]);
		}
		baekjun2467 b = new baekjun2467();
		int[] result = b.getNearZero(solution);
		for(int i = 0; i < result.length; i++) {
			bw.write(result[i] + " ");
		}
		bw.flush();
		bw.close();
	}
}
