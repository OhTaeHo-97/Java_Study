package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun2230_TwoPointer {
	public int getMinDif(int dif, int[] series) {
		Arrays.sort(series);
		int left = 0;
		int right = 0;
		int min = Integer.MAX_VALUE;
		while(right < series.length) {
			int difference = series[right] - series[left];
			if(difference < dif) {
				right++;
				continue;
			}
			if(difference == dif) {
				return dif;
			}
			left++;
			min = Math.min(min, difference);
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int num = Integer.parseInt(input[0]);
		int dif = Integer.parseInt(input[1]);
		int[] series = new int[num];
		for(int i = 0; i < num; i++) {
			series[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun2230_TwoPointer b = new baekjun2230_TwoPointer();
		bw.write(b.getMinDif(dif, series) + "\n");
		bw.flush();
		bw.close();
	}
}
