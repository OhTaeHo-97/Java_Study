package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun2343 {
	public int getMinSize(int bluray_num, int[] class_min) {
		int left = 0;
		int right = 0;
		for(int i = 0; i < class_min.length; i++) {
			right += class_min[i];
			left = Math.max(left, class_min[i]);
		}
		while(left <= right) {
			int count = 0;
			int sum = 0;
			int middle = (left + right) / 2;
			for(int i = 0; i < class_min.length; i++) {
				sum += class_min[i];
				if(sum > middle) {
					sum = class_min[i];
					count++;
				}
			}
			if(sum != 0) {
				count++;
			}
			if(count > bluray_num) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}
		return left;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		int class_num = Integer.parseInt(st.nextToken());
		int bluray_num = Integer.parseInt(st.nextToken());
		int[] class_min = new int[class_num];
		input = br.readLine();
		br.close();
		st = new StringTokenizer(input);
		for(int i = 0; i < class_min.length; i++) {
			class_min[i] = Integer.parseInt(st.nextToken());
		}
		baekjun2343 b = new baekjun2343();
		bw.write(b.getMinSize(bluray_num, class_min) + "\n");
		bw.flush();
		bw.close();
	}
}
